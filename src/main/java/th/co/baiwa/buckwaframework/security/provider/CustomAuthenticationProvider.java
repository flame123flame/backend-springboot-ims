package th.co.baiwa.buckwaframework.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.ROLE;
import th.co.baiwa.buckwaframework.security.domain.UserDetails;
import th.co.baiwa.buckwaframework.security.persistence.entity.WsRole;
import th.co.baiwa.buckwaframework.security.persistence.entity.WsUser;
import th.co.baiwa.buckwaframework.security.persistence.repository.WsUserRepository;
import th.co.baiwa.buckwaframework.security.persistence.repository.WsUserRoleRepository;
import th.go.excise.dexsrvint.schema.authenandgetuserrole.AuthenAndGetUserRoleRequest;
import th.go.excise.dexsrvint.schema.authenandgetuserrole.AuthenAndGetUserRoleResponse;
import th.go.excise.dexsrvint.schema.ldapuserbase.MessageBase;
import th.go.excise.dexsrvint.schema.ldapuserbase.RoleBase;
import th.go.excise.dexsrvint.schema.ldapuserbase.RolesBase;
import th.go.excise.dexsrvint.wsdl.ldapgateway.ldpagauthenandgetuserrole.LDPAGAuthenAndGetUserRolePortType;
import th.go.excise.ims.preferences.service.ExcisePersonService;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private WsUserRepository wsUserRepository;
	@Autowired
	private WsUserRoleRepository wsUserRoleRepository;
	@Autowired
	private LDPAGAuthenAndGetUserRolePortType loginLdapProxy;
	@Autowired
	private ExcisePersonService excisePersonService;

	@Value("${spring.profiles.active}")
	private String profileActive;

	@Override
	protected void additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
	}

	@Override
	protected org.springframework.security.core.userdetails.UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		logger.info("CustomAuthenticationProvider : {}", username);
		String password = authentication.getCredentials().toString();
		UserDetails userDetails = null;

		// Login with LoginLdapUser
		AuthenAndGetUserRoleResponse response = login(username, password);
		if ("000".equals(response.getMessage().getCode())) {
			// Assign Default ROLE_USER
			List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
			grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE.USER));
			// Assign ROLE_ADMIN
			if (username.contains("admin")) {
				grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE.ADMIN));
			}
			// Assign ROLE from WS
			for (RoleBase wsRole : response.getRoles().getRole()) {
				grantedAuthorityList.add(new SimpleGrantedAuthority(wsRole.getRoleName()));
			}

			// Create UserDetails
			userDetails = new UserDetails(username, password, grantedAuthorityList);

			// Assign Detail for UserDetailes
			userDetails.setUserThaiName(response.getUserThaiName());
			userDetails.setUserThaiSurname(response.getUserThaiSurname());
			userDetails.setTitle(response.getTitle());
			userDetails.setOfficeCode(response.getOfficeId());
		} else if (PROFILE.UAT.equals(profileActive)) {
			AuthenAndGetUserRoleRequest ldap = new AuthenAndGetUserRoleRequest();
			ldap.setUserId(username);
			ldap.setPassword(password);
			ldap.setApplicationId("TAX AUDIT");
			response = loginLdapProxy.ldpagAuthenAndGetUserRoleOperation(ldap);
			if ("000".equals(response.getMessage().getCode())) {
				List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
				grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE.USER));
				if (username.contains("admin")) {
					grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE.ADMIN));
				}
				for (RoleBase wsRole : response.getRoles().getRole()) {
					if (StringUtils.isNotBlank(wsRole.getRoleName())) {
						grantedAuthorityList.add(new SimpleGrantedAuthority(wsRole.getRoleName().split("=")[1]));
					}
				}
				userDetails = new UserDetails(username, password, grantedAuthorityList);
				userDetails.setUserThaiName(response.getUserThaiName());
				userDetails.setUserThaiSurname(response.getUserThaiSurname());
				userDetails.setTitle(response.getTitle());
				userDetails.setOfficeCode(response.getOfficeId());
				
				excisePersonService.checkAccountByLdapLogin(userDetails);
			} else {
				throw new BadCredentialsException(response.getMessage().getDescription());
			}
		}
		
		excisePersonService.addAdditionalInfo(userDetails);
		
		return userDetails;
	}

	public AuthenAndGetUserRoleResponse login(String username, String password) {
		logger.info("login username={}, password={}", username, password);

		WsUser wsUser = wsUserRepository.findByUsernameAndPassword(username, password);

		AuthenAndGetUserRoleResponse response = new AuthenAndGetUserRoleResponse();
		if (wsUser != null) {
			MessageBase messageBase = new MessageBase();
			messageBase.setCode("000");
			messageBase.setDescription("Authentication Success");
			response.setMessage(messageBase);
			response.setTitle(wsUser.getTitle());
			response.setUserThaiName(wsUser.getUserThaiName());
			response.setUserThaiSurname(wsUser.getUserThaiSurname());
			response.setUserEngName(wsUser.getUserEngName());
			response.setUserEngSurname(wsUser.getUserEngSurname());
			response.setUserId(wsUser.getUserId());
			response.setEmail(wsUser.getEmail());
			response.setCnName(wsUser.getCnName());
			response.setOfficeId(wsUser.getOfficeCode());
			response.setAccessAttr(wsUser.getAccessAttr());

			RolesBase roles = prepareRoles(username);
			response.setRoles(roles);
		} else {
			MessageBase messageBase = new MessageBase();
			messageBase.setCode("ERR001");
			messageBase.setDescription("Authentication Failed.");
			response.setMessage(messageBase);
		}

		return response;
	}

	private RolesBase prepareRoles(String username) {
		RolesBase roles = new RolesBase();
		RoleBase role = null;
		List<WsRole> wsRoleList = wsUserRoleRepository.findByUsername(username);
		for (WsRole wsRole : wsRoleList) {
			role = new RoleBase();
			role.setRoleName(wsRole.getRoleCode());
			roles.getRole().add(role);
		}
		return roles;
	}

	

}
