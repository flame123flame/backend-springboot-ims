package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.go.excise.ims.oa.persistence.entity.OaAlcoholMaterial;
import th.go.excise.ims.oa.persistence.repository.OaAlcoholMaterialRepository;
import th.go.excise.ims.oa.vo.Oa04010604FormVo;

@Service
public class Oa04010604Service {

	@Autowired
	private OaAlcoholMaterialRepository oaAlcoholMaterialRep;

	public List<OaAlcoholMaterial> findAll(String oaAlcoholIdStr) {
		BigDecimal oaAlcoholId = new BigDecimal(oaAlcoholIdStr);
		List<OaAlcoholMaterial> responses = oaAlcoholMaterialRep.findByOaAlcoholIdAndIsDeleted(oaAlcoholId,
				FLAG.N_FLAG);
		return responses;
	}

	@Transactional
	public Oa04010604FormVo updateMaterial(Oa04010604FormVo form) {
		// update
		int seq = 0;
		for (OaAlcoholMaterial save : form.getSave()) {
			if (save.getOaAlcoholMaterialId() != null) {
				Optional<OaAlcoholMaterial> find = oaAlcoholMaterialRep.findById(save.getOaAlcoholMaterialId());
				if (find.isPresent()) {
					find.get().setAuditDate(save.getAuditDate());
					find.get().setBalance(save.getBalance());
					find.get().setEvidence(save.getEvidence());
					find.get().setMaterial(save.getMaterial());
					find.get().setPay(save.getPay());
					find.get().setReceive(save.getReceive());
					find.get().setSeq(new BigDecimal(seq++));
					save = oaAlcoholMaterialRep.save(find.get());
				}
			} else {
				save.setSeq(new BigDecimal(seq++));
				save = oaAlcoholMaterialRep.save(save);
			}
		}
		// delete
		for (BigDecimal delete : form.getDelete()) {
			oaAlcoholMaterialRep.deleteById(delete);
		}
		form.setDelete(new ArrayList<BigDecimal>());
		return form;
	}

}
