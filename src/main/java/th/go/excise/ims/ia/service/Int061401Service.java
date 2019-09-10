package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.persistence.entity.IaAuditTxinsurH;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaAuditTxinsurHJdbcRepository;
import th.go.excise.ims.ia.vo.Int061401FilterVo;
import th.go.excise.ims.ia.vo.Ws_Reg4000Vo;

@Service
public class Int061401Service {

	@Autowired
	private IaAuditTxinsurHJdbcRepository iaAuditTxinsurHJdbcRepository;

	public List<IaAuditTxinsurH> getgetAudittxInsurNoList() {
		return iaAuditTxinsurHJdbcRepository.getAudittxInsurNoList();
	}

	public DataTableAjax<Ws_Reg4000Vo> filter(Int061401FilterVo formVo) {
		List<Ws_Reg4000Vo> data = new ArrayList<Ws_Reg4000Vo>();
		DataTableAjax<Ws_Reg4000Vo> dataTableAjax = new DataTableAjax<Ws_Reg4000Vo>();

		if (formVo.getFlagSearch()) {
			data = iaAuditTxinsurHJdbcRepository.getDataFilter(formVo);
			dataTableAjax.setRecordsTotal(iaAuditTxinsurHJdbcRepository.countDatafilter(formVo));
			dataTableAjax.setRecordsFiltered(iaAuditTxinsurHJdbcRepository.countDatafilter(formVo));
		}
		// dataTableAjax.setDraw(formVo.getDraw() + 1);
		dataTableAjax.setData(data);

		return dataTableAjax;
	}
}
