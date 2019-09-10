package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.go.excise.ims.oa.persistence.entity.OaAlcoholPacking;
import th.go.excise.ims.oa.persistence.repository.OaAlcoholPackingRepository;
import th.go.excise.ims.oa.vo.Oa04010607FormVo;

@Service
public class Oa04010607Service {

	@Autowired
	private OaAlcoholPackingRepository oaAlcoholPackingRep;

	public List<OaAlcoholPacking> findAll(String oaAlcoholIdStr) {
		BigDecimal oaAlcoholId = new BigDecimal(oaAlcoholIdStr);
		List<OaAlcoholPacking> responses = oaAlcoholPackingRep.findByOaAlcoholIdAndIsDeleted(oaAlcoholId,
				FLAG.N_FLAG);
		return responses;
	}

	@Transactional
	public Oa04010607FormVo updatePacking(Oa04010607FormVo form) {
		// update
		int seq = 0;
		for (OaAlcoholPacking save : form.getSave()) {
			if (save.getOaAlcoholPackingId() != null) {
				Optional<OaAlcoholPacking> find = oaAlcoholPackingRep.findById(save.getOaAlcoholPackingId());
				if (find.isPresent()) {
					find.get().setAuditDate(save.getAuditDate());
					find.get().setDegree28(save.getDegree28());
					find.get().setDegree30(save.getDegree30());
					find.get().setDegree35(save.getDegree35());
					find.get().setDegree40(save.getDegree40());
					find.get().setName(save.getName());
					find.get().setRemark(save.getRemark());
					find.get().setSeq(new BigDecimal(seq++));
					save = oaAlcoholPackingRep.save(find.get());
				}
			} else {
				save.setSeq(new BigDecimal(seq++));
				save = oaAlcoholPackingRep.save(save);
			}
		}
		// delete
		for (BigDecimal delete : form.getDelete()) {
			oaAlcoholPackingRep.deleteById(delete);
		}
		form.setDelete(new ArrayList<BigDecimal>());
		return form;
	}

}
