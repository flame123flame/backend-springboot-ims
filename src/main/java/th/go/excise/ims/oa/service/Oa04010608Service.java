package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.go.excise.ims.oa.persistence.entity.OaAlcoholLabel;
import th.go.excise.ims.oa.persistence.repository.OaAlcoholLabelRepository;
import th.go.excise.ims.oa.vo.Oa04010608FormVo;

@Service
public class Oa04010608Service {

	@Autowired
	private OaAlcoholLabelRepository oaAlcoholLabelRep;

	public List<OaAlcoholLabel> findAll(String oaAlcoholIdStr) {
		BigDecimal oaAlcoholId = new BigDecimal(oaAlcoholIdStr);
		List<OaAlcoholLabel> responses = oaAlcoholLabelRep.findByOaAlcoholIdAndIsDeleted(oaAlcoholId,
				FLAG.N_FLAG);
		return responses;
	}

	@Transactional
	public Oa04010608FormVo updateLabel(Oa04010608FormVo form) {
		// update
		int seq = 0;
		for (OaAlcoholLabel save : form.getSave()) {
			if (save.getOaAlcoholLabelId() != null) {
				Optional<OaAlcoholLabel> find = oaAlcoholLabelRep.findById(save.getOaAlcoholLabelId());
				if (find.isPresent()) {
					find.get().setAuditDate(save.getAuditDate());
					find.get().setBalance(save.getBalance());
					find.get().setEvidence(save.getEvidence());
					find.get().setLabel(save.getLabel());
					find.get().setPay(save.getPay());
					find.get().setReceive(save.getReceive());
					find.get().setSeq(new BigDecimal(seq++));
					save = oaAlcoholLabelRep.save(find.get());
				}
			} else {
				save.setSeq(new BigDecimal(seq++));
				save = oaAlcoholLabelRep.save(save);
			}
		}
		// delete
		for (BigDecimal delete : form.getDelete()) {
			oaAlcoholLabelRep.deleteById(delete);
		}
		form.setDelete(new ArrayList<BigDecimal>());
		return form;
	}

}
