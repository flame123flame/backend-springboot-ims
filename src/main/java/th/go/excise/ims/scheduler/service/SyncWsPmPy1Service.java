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
import th.go.excise.ims.ws.client.pm.py1.model.Data;
import th.go.excise.ims.ws.client.pm.py1.model.RequestData;
import th.go.excise.ims.ws.client.pm.py1.model.ResponseData;
import th.go.excise.ims.ws.client.pm.py1.model.TopicDetail;
import th.go.excise.ims.ws.client.pm.py1.service.PmPy1Service;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1D;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1H;
import th.go.excise.ims.ws.persistence.repository.WsPmPy1DRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmPy1HRepository;

@Service
public class SyncWsPmPy1Service {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncWsPmPy1Service.class);
	
	@Autowired
	private PmPy1Service pmPy1Service;
	
	@Autowired
	private WsPmPy1HRepository wsPmPy1HRepository;
	
	@Autowired
	private WsPmPy1DRepository wsPmPy1DRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData(RequestData requestData) throws IOException {
		logger.info("syncData PmPy1 Start");
		long start = System.currentTimeMillis();
		
		ResponseData responseData = pmPy1Service.execute(requestData);
		List<Data> dataList = responseData.getData();
		
		WsPmPy1H pmPy1H = null;
		List<WsPmPy1H> pmPy1HList = new ArrayList<>();
		WsPmPy1D pmPy1D = null;
		List<WsPmPy1D> pmPy1DList = new ArrayList<>();
		
		for (Data data : dataList) {
			pmPy1H = new WsPmPy1H();
			pmPy1H.setOffCode(data.getOffCode());
			pmPy1H.setOffName(data.getOffName());
			pmPy1H.setFormYear(data.getFormYear());
			pmPy1H.setFormCode(data.getFormCode());
			pmPy1H.setFormName(data.getFormName());
			pmPy1H.setFormRound(data.getFormRound());
			pmPy1H.setFormStatus(data.getFormStatus());
			pmPy1H.setFormStatusDesc(data.getFormStatusDesc());
			pmPy1H.setSummary(data.getSummary());
			pmPy1H.setProcessBy(data.getProcessBy());
			pmPy1H.setProcessPosition(data.getProcessPosition());
			pmPy1H.setProcessDate(ConvertDateUtils.parseStringToLocalDate(data.getProcessDate(), ConvertDateUtils.DD_MM_YYYY,ConvertDateUtils.LOCAL_TH));
			wsPmPy1HRepository.save(pmPy1H);
//			pmPy1HList.add(pmPy1H);
			for (TopicDetail topicDetail : data.getTopicDetail()) {
				pmPy1D = new WsPmPy1D();
				pmPy1D.setOffCode(data.getOffCode());
				pmPy1D.setFormCode(data.getFormCode());
				pmPy1D.setTopicCode(topicDetail.getTopicCode());
				pmPy1D.setTopicName(topicDetail.getTopicName());
				pmPy1D.setTopicDesc(topicDetail.getTopicDesc());
				pmPy1D.setTopicAnswer(topicDetail.getTopicAnswer());
				wsPmPy1DRepository.save(pmPy1D);
//				pmPy1DList.add(pmPy1D);
			}
		}
		
//		wsPmPy1HRepository.batchMerge(pmPy1HList);
//		logger.info("Batch Merge WsPmAssessH Success");
//				
//		wsPmPy1DRepository.batchMerge(pmPy1DList);
//		logger.info("Batch Merge WsPmAssessD Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData PmPy1 Success, using {} seconds", (float) (end - start) / 1000F);
	}
	
}
