package th.go.excise.ims.scheduler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ws.client.pm.assess.model.Data;
import th.go.excise.ims.ws.client.pm.assess.model.RequestData;
import th.go.excise.ims.ws.client.pm.assess.model.ResponseData;
import th.go.excise.ims.ws.client.pm.assess.model.TopicDetail;
import th.go.excise.ims.ws.client.pm.assess.service.PmAccessService;
import th.go.excise.ims.ws.persistence.entity.WsPmAssessD;
import th.go.excise.ims.ws.persistence.entity.WsPmAssessH;
import th.go.excise.ims.ws.persistence.repository.WsPmAssessDRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmAssessHRepository;



@Service
public class SyncWsPmAssessService {
private static final Logger logger = LoggerFactory.getLogger(SyncWsPmAssessService.class);
	
	@Autowired
	private PmAccessService pmAccessService;
	
	@Autowired
	private WsPmAssessHRepository wsPmAssessHRepository;
	
	@Autowired
	private WsPmAssessDRepository wsPmAssessDRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData(RequestData requestData) throws IOException {
		logger.info("syncData Access Start");
		long start = System.currentTimeMillis();
		
		ResponseData responseData = pmAccessService.execute(requestData);
		List<Data> dataList = responseData.getData();
		
		WsPmAssessH pmAssessH = null;
		List<WsPmAssessH> pmAssessHList = new ArrayList<>();
		WsPmAssessD pmAssessD = null;
		List<WsPmAssessD> pmAssessDList = new ArrayList<>();
		
		for (Data data : dataList) {
			pmAssessH = new WsPmAssessH();
			pmAssessH.setOffCode(data.getOffCode());
			pmAssessH.setOffName(data.getOffName());
			pmAssessH.setFormYear(data.getFormYear());
			pmAssessH.setFormCode(data.getFormCode());
			pmAssessH.setFormName(data.getFormName());
			pmAssessH.setFormRound(data.getFormRound());
			pmAssessH.setFormStatus(data.getFormStatus());
			pmAssessH.setFormStatusDesc(data.getFormStatusDesc());
			pmAssessH.setSummary(data.getSummary());
			pmAssessH.setProcessBy(data.getProcessBy());
			pmAssessH.setProcessPosition(data.getProcessPosition());
			pmAssessH.setProcessDate(ConvertDateUtils.parseStringToLocalDate(data.getProcessDate(), ConvertDateUtils.DD_MM_YYYY,ConvertDateUtils.LOCAL_TH));
			wsPmAssessHRepository.save(pmAssessH);
			pmAssessHList.add(pmAssessH);
			for (TopicDetail topicDetail : data.getTopicDetail()) {
				pmAssessD = new WsPmAssessD();
				pmAssessD.setOffCode(data.getOffCode());
				pmAssessD.setFormCode(data.getFormCode());
				pmAssessD.setTopicLevel(topicDetail.getTopicLevel());
				pmAssessD.setTopicCode(topicDetail.getTopicCode());
				pmAssessD.setTopicName(topicDetail.getTopicName());
				pmAssessD.setTopicAnswer(topicDetail.getTopicAnswer());
				wsPmAssessDRepository.save(pmAssessD);
				pmAssessDList.add(pmAssessD);
			}
		}
//		wsPmAssessHRepository.batchMerge(pmAssessHList);
		logger.info("Batch Merge WsPmAssessH Success");
		
//		wsPmAssessDRepository.batchMerge(pmAssessDList);
		logger.info("Batch Merge WsPmAssessD Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData PmPy1 Success, using {} seconds", (float) (end - start) / 1000F);
	}
}
