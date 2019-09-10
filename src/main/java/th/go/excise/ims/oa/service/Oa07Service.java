package th.go.excise.ims.oa.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.repository.jdbc.Oa07JdbcRepository;
import th.go.excise.ims.oa.vo.Oa07FormVo;
import th.go.excise.ims.oa.vo.Oa07Reg4000Vo;
import th.go.excise.ims.oa.vo.Oa07Vo;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000MRepository;

@Service
public class Oa07Service {

	private static final Logger logger = LoggerFactory.getLogger(Oa07Service.class);

	
	@Autowired
	private Oa07JdbcRepository oa07JdbcRepository;

	@Autowired
	private TaWsInc8000MRepository inc8000mRepository;

	public List<Oa07Vo> reg4000(Oa07FormVo formVo) {

		List<Oa07Reg4000Vo> reg4000List = oa07JdbcRepository.reg4000(formVo);

		// ==> Add object
		List<Oa07Vo> voList = new ArrayList<>();
		Oa07Vo vo = null;
		for (Oa07Reg4000Vo reg4000 : reg4000List) {
			vo = new Oa07Vo();
			copyPropertiesReg4000(vo,reg4000);
			
			//==> query tax pay
//			List<TaWsInc8000M> reg8000MList = oa07JdbcRepository.reg8000M(formVo);
//			for (TaWsInc8000M reg8000 : reg8000MList) {
//				//vo.getTaxPayList().add(reg8000.getTaxAmount())
//			}
			
			voList.add(vo);
		}
		return voList;
	}

	private void copyPropertiesReg4000(Oa07Vo vo1, Oa07Reg4000Vo vo2) {
		try {
			BeanUtils.copyProperties(vo1, vo2);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}

}
