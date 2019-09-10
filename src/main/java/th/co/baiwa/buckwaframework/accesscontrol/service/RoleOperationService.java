package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.RoleOperation;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.RoleOperationRepository;
import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleOperationFormVo;
import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleOperationVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;

@Service
public class RoleOperationService {

	private static final Logger logger = LoggerFactory.getLogger(RoleOperationService.class);

	private final RoleOperationRepository roleOperationRepository;

	@Autowired
	public RoleOperationService(RoleOperationRepository roleOperationRepository) {
		this.roleOperationRepository = roleOperationRepository;
	}

	public DataTableAjax<RoleOperationVo> list(RoleOperationFormVo request) {

		List<RoleOperationVo> data = roleOperationRepository.findByCriteria(request);

		Integer total = roleOperationRepository.countByCriteria(request);

		DataTableAjax<RoleOperationVo> dataTableAjax = new DataTableAjax<RoleOperationVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

//	public List<RoleOperationVo> listData(RoleOperationFormVo request) {
//		List<RoleOperationVo> roleOperationVoList = new ArrayList<RoleOperationVo>();
//		roleOperationVoList = roleOperationRepository.findById(request);
//		return roleOperationVoList;
//	}

	public DataTableAjax<RoleOperationVo> listData(RoleOperationFormVo request) {
		List<RoleOperationVo> data = roleOperationRepository.findById(request);

		DataTableAjax<RoleOperationVo> dataTableAjax = new DataTableAjax<RoleOperationVo>();
		dataTableAjax.setData(data);
		return dataTableAjax;
	}

	public RoleOperation findById(Long id) {
		return roleOperationRepository.findById(id).get();
	}

	public List<RoleOperation> findAll(Long id) {
		return roleOperationRepository.findAll();
	}

	public RoleOperation save(RoleOperation roleOperation) {
		return roleOperationRepository.save(roleOperation);
	}

	public void delete(RoleOperation roleOperation) {
		roleOperationRepository.delete(roleOperation);
	}
}
