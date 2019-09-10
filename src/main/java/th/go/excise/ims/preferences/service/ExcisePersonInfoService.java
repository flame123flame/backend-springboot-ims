package th.go.excise.ims.preferences.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonInfoRepository;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonInfoRepositoryCustom;
import th.go.excise.ims.preferences.vo.ExcisePersonInfoVo;

@Service
public class ExcisePersonInfoService {

	private Logger logger = LoggerFactory.getLogger(ExcisePersonInfoService.class);

	@Autowired
	private ExcisePersonInfoRepository excisePersoninfoRepository;
	
	@Autowired
	private ExcisePersonInfoRepositoryCustom excisePersonInfoRepositoryCustom;
	
	@Transactional
	public List<ExcisePersonInfoVo> findByWorkOffcode() {
		logger.info("findByWorkOffcode");
		String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		List<ExcisePersonInfoVo> resList = excisePersonInfoRepositoryCustom.findByWorkOffcode(offCode);
		return resList;
	}
}
