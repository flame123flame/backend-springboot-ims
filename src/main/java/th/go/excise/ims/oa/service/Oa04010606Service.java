package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.go.excise.ims.oa.persistence.entity.OaAlcoholDistil;
import th.go.excise.ims.oa.persistence.entity.OaAlcoholFerment;
import th.go.excise.ims.oa.persistence.repository.OaAlcoholDistilRepository;
import th.go.excise.ims.oa.vo.Oa04010606FormVo;

@Service
public class Oa04010606Service {

	@Autowired
	private OaAlcoholDistilRepository oaAlcoholDistilRep;

	public List<OaAlcoholDistil> findAll(String oaAlcoholIdStr) {
		BigDecimal oaAlcoholId = new BigDecimal(oaAlcoholIdStr);
		List<OaAlcoholDistil> responses = oaAlcoholDistilRep.findByOaAlcoholIdAndIsDeleted(oaAlcoholId,
				FLAG.N_FLAG);
		return responses;
	}

	@Transactional
	public Oa04010606FormVo updateDistil(Oa04010606FormVo form) {
		// update
		int seq = 0;
		for (OaAlcoholDistil save : form.getSave()) {
			if (save.getOaAlcoholDistilId() != null) {
				Optional<OaAlcoholDistil> find = oaAlcoholDistilRep.findById(save.getOaAlcoholDistilId());
				if (find.isPresent()) {
					find.get().setAuditDate(save.getAuditDate());
					find.get().setDistilDate(save.getDistilDate());
					find.get().setDistilDegree(save.getDistilDegree());
					find.get().setDistilVolume(save.getDistilVolume());
					find.get().setRemark(save.getRemark());
					find.get().setSeq(new BigDecimal(seq++));
					save = oaAlcoholDistilRep.save(find.get());
				}
			} else {
				save.setSeq(new BigDecimal(seq++));
				save = oaAlcoholDistilRep.save(save);
			}
		}
		// delete
		for (BigDecimal delete : form.getDelete()) {
			oaAlcoholDistilRep.deleteById(delete);
		}
		form.setDelete(new ArrayList<BigDecimal>());
		return form;
	}

}
