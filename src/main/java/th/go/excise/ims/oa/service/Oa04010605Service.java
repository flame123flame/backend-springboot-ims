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
import th.go.excise.ims.oa.persistence.repository.OaAlcoholFermentRepository;
import th.go.excise.ims.oa.vo.Oa04010605FormVo;

@Service
public class Oa04010605Service {

	@Autowired
	private OaAlcoholFermentRepository oaAlcoholFermentRep;

	public List<OaAlcoholFerment> findAll(String oaAlcoholIdStr) {
		BigDecimal oaAlcoholId = new BigDecimal(oaAlcoholIdStr);
		List<OaAlcoholFerment> responses = oaAlcoholFermentRep.findByOaAlcoholIdAndIsDeleted(oaAlcoholId,
				FLAG.N_FLAG);
		return responses;
	}

	@Transactional
	public Oa04010605FormVo updateFerment(Oa04010605FormVo form) {
		// update
		int seq = 0;
		for (OaAlcoholFerment save : form.getSave()) {
			if (save.getOaAlcoholFermentId() != null) {
				Optional<OaAlcoholFerment> find = oaAlcoholFermentRep.findById(save.getOaAlcoholFermentId());
				if (find.isPresent()) {
					find.get().setAuditDate(save.getAuditDate());
					find.get().setNetQuantity(save.getNetQuantity());
					find.get().setRemark(save.getRemark());
					find.get().setQuantityTank(save.getQuantityTank());
					find.get().setSeq(new BigDecimal(seq++));
					find.get().setTankNumber(save.getTankNumber());
					save = oaAlcoholFermentRep.save(find.get());
				}
			} else {
				save.setSeq(new BigDecimal(seq++));
				save = oaAlcoholFermentRep.save(save);
			}
		}
		// delete
		for (BigDecimal delete : form.getDelete()) {
			oaAlcoholFermentRep.deleteById(delete);
		}
		form.setDelete(new ArrayList<BigDecimal>());
		return form;
	}

}
