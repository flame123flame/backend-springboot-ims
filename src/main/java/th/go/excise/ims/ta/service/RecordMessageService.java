package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.ta.vo.FormDocTypeVo;

@Service
public class RecordMessageService {
	
	public List<FormDocTypeVo> getTypeDoc() {
		List<FormDocTypeVo> type = new ArrayList<>();
		List<ParamInfo> param = ApplicationCache.getParamInfoListByGroupCode("TA_FORM_TS");
		for (int i = 0; i < param.size(); i++) {
			type.add(new FormDocTypeVo());
			ParamInfo pm = param.get(i);
			type.get(i).setCode(pm.getParamCode());
			type.get(i).setGroupCode(pm.getParamGroupCode());
			type.get(i).setValue(pm.getValue1());
			type.get(i).setDesc(pm.getValue2());
		}
		return type;
	}
}
