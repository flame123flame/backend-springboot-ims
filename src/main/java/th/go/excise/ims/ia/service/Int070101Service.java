package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaCheckStamp;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int070101JdbcRepository;
import th.go.excise.ims.ia.vo.Int070101FormVo;

@Service
public class Int070101Service {
	
	@Autowired
	Int070101JdbcRepository int070101JdbcRepository;
	
	public List<IaCheckStamp> list(Int070101FormVo form) {
		List<IaCheckStamp> iaCheckStampList = new ArrayList<IaCheckStamp>();
		iaCheckStampList = int070101JdbcRepository.list(form);
		return iaCheckStampList;
	}
}
