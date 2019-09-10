package th.go.excise.ims.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.vo.Int030102FormVo;

@Service
public class Int0306Service {
	
	@Autowired
	private Int030102Service int030102Service;
	
	public void delete(Int030102FormVo form) {		
			int030102Service.editStatus(form);
			int030102Service.save(form);
	}
	
	
}
