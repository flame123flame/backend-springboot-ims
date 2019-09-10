package th.co.baiwa.buckwaframework.common.config;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import th.go.excise.dexsrvint.wsdl.ldapgateway.ldpagauthenandgetuserrole.LDPAGAuthenAndGetUserRolePortType;




@Configuration
public class WebServiceConfig {

	public class ClientConfig {
			
		@Value("${ws.excise.endpoint.ldapuser.dev}")
		private String ldapDev;
		
		@Bean(name = "loginLdapProxy")	
		public LDPAGAuthenAndGetUserRolePortType loginLdapProxy() {
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			jaxWsProxyFactoryBean.setServiceClass(LDPAGAuthenAndGetUserRolePortType.class);
			jaxWsProxyFactoryBean.setAddress(ldapDev);
			
			LDPAGAuthenAndGetUserRolePortType loginLdapProxy = (LDPAGAuthenAndGetUserRolePortType) jaxWsProxyFactoryBean.create();
			
			Client client = ClientProxy.getClient(loginLdapProxy);
			HTTPConduit http = (HTTPConduit) client.getConduit();
			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
			httpClientPolicy.setConnectionTimeout(36000);
			httpClientPolicy.setReceiveTimeout(36000);
			httpClientPolicy.setAllowChunking(false);
			http.setClient(httpClientPolicy);
			
			client.getInInterceptors().add(new LoggingInInterceptor());
			client.getOutInterceptors().add(new LoggingOutInterceptor());
			
			return loginLdapProxy;
		}
		
	}
}
