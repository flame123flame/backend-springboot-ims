package th.go.excise.ims.scheduler.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ws.client.pm.qt.model.Data;
import th.go.excise.ims.ws.client.pm.qt.model.RequestData;
import th.go.excise.ims.ws.client.pm.qt.model.ResponseData;
import th.go.excise.ims.ws.client.pm.qt.model.TopicDetail;
import th.go.excise.ims.ws.client.pm.qt.service.PmQtService;
import th.go.excise.ims.ws.persistence.entity.WsPmQtD;
import th.go.excise.ims.ws.persistence.entity.WsPmQtH;
import th.go.excise.ims.ws.persistence.repository.WsPmQtDRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmQtHRepository;

@Service
public class SyncWsPmQtService {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncWsPmQtService.class);
	
	@Autowired
	private PmQtService pmQtService ;
	
	@Autowired
	private WsPmQtHRepository wsPmQtHRepository;
	
	@Autowired
	private WsPmQtDRepository wsPmQtDRepository;
	
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData(RequestData requestData) throws IOException {
		logger.info("syncData PmQt Start");
		long start = System.currentTimeMillis();
		
		ResponseData responseData = pmQtService.execute(requestData);
		List<Data> dataList = responseData.getData();
		
		WsPmQtH pmQtH = null;
		List<WsPmQtH> pmQtHList = new ArrayList<>();
		WsPmQtD pmQtD = null;
		List<WsPmQtD> pmQtDList = new ArrayList<>();
		
		for (Data data : dataList) {
			pmQtH = new WsPmQtH();
			pmQtH.setOffCode(data.getOffCode());
			pmQtH.setOffName(data.getOffName());
			pmQtH.setFormYear(data.getFormYear());
			pmQtH.setFormCode(data.getFormCode());
			pmQtH.setFormName(data.getFormName());
			pmQtH.setFormRound(data.getFormRound());
			pmQtH.setFormStatus(data.getFormStatus());
			pmQtH.setFormStatusDesc(data.getFormStatusDesc());
			pmQtH.setSummary(data.getSummary());
			pmQtH.setProcessBy(data.getProcessBy());
			pmQtH.setProcessPosition(data.getProcessPosition());
			LocalDate ProcessDate = ConvertDateUtils.parseStringToLocalDate(data.getProcessDate(), ConvertDateUtils.DD_MM_YYYY);
			pmQtH.setProcessDate(ProcessDate);
//			wsPmQtHRepository.save(pmQtH);
			pmQtHList.add(pmQtH);
			for (TopicDetail topicDetail : data.getTopicDetail()) {
				pmQtD = new WsPmQtD();
				pmQtD.setOffCode(data.getOffCode());
				pmQtD.setFormCode(data.getFormCode());
				pmQtD.setTopicLevel(topicDetail.getTopicLevel());
				pmQtD.setTopicCode(topicDetail.getTopicCode());
				pmQtD.setTopicName(topicDetail.getTopicName());
				pmQtD.setTopicAnswer(topicDetail.getTopicAnswer());
				pmQtD.setTopicResult(topicDetail.getTopicResult());
//				wsPmQtDRepository.save(pmQtD);
				pmQtDList.add(pmQtD);
			}
		}
		wsPmQtHRepository.batchMerge(pmQtHList);
		logger.info("Batch Merge WsPmQtH Success");
		wsPmQtDRepository.batchMerge(pmQtDList);
		logger.info("Batch Merge WsPmQtD Success");
		long end = System.currentTimeMillis();
		logger.info("syncData PmQt Success, using {} seconds", (float) (end - start) / 1000F);
	}
	
}


