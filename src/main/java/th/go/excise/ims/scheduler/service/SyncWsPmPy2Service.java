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
import th.go.excise.ims.ws.client.pm.py2.model.Data;
import th.go.excise.ims.ws.client.pm.py2.model.DivDetail;
import th.go.excise.ims.ws.client.pm.py2.model.JobDetail;
import th.go.excise.ims.ws.client.pm.py2.model.Py2Detail;
import th.go.excise.ims.ws.client.pm.py2.model.RequestData;
import th.go.excise.ims.ws.client.pm.py2.model.ResponseData;
import th.go.excise.ims.ws.client.pm.py2.service.PmPy2Service;
import th.go.excise.ims.ws.persistence.entity.WsPmPy2D;
import th.go.excise.ims.ws.persistence.entity.WsPmPy2H;
import th.go.excise.ims.ws.persistence.repository.WsPmPy2DRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmPy2HRepository;

@Service
public class SyncWsPmPy2Service {

	private static final Logger logger = LoggerFactory.getLogger(SyncWsPmPy2Service.class);

	@Autowired
	private PmPy2Service pmPy2Service;

	@Autowired
	private WsPmPy2HRepository wsPmPy2HRepository;

	@Autowired
	private WsPmPy2DRepository wsPmPy2DRepository;

	@Transactional(rollbackOn = { Exception.class })
	public void syncData(RequestData requestData) throws IOException {
		logger.info("syncData PmPy2 Start");
		long start = System.currentTimeMillis();

		ResponseData responseData = pmPy2Service.execute(requestData);
		List<Data> dataList = responseData.getData();

		WsPmPy2H pmPy2H = null;
		List<WsPmPy2H> pmPy2HList = new ArrayList<>();
		WsPmPy2D pmPy2D = null;
		List<WsPmPy2D> pmPy2DList = new ArrayList<>();

		for (Data data : dataList) {
			pmPy2H = new WsPmPy2H();
			pmPy2H.setOffCode(data.getOffCode());
			pmPy2H.setOffName(data.getOffName());
			pmPy2H.setFormYear(data.getFormYear());
			pmPy2H.setFormCode(data.getFormCode());
			pmPy2H.setFormName(data.getFormName());
			pmPy2H.setFormRound(data.getFormRound());
			pmPy2H.setFormStatus(data.getFormStatus());
			pmPy2H.setFormStatusDesc(data.getFormStatusDesc());
			pmPy2HList.add(pmPy2H);
			for (DivDetail divDetail : data.getDivDetail()) {
				for (JobDetail jobDetail : divDetail.getJobDetail()) {
					for (Py2Detail py2Detail : jobDetail.getPy2Detail()) {
						pmPy2D = new WsPmPy2D();
						pmPy2D.setOffCode(data.getOffCode());
						pmPy2D.setFormCode(data.getFormCode());
						pmPy2D.setDivSeq(divDetail.getDivSeq());
						pmPy2D.setDivName(divDetail.getDivName());
						
						pmPy2D.setJobName(jobDetail.getJobName());
						pmPy2D.setProcessBy(jobDetail.getProcessBy());
						pmPy2D.setProcessPosition(jobDetail.getProcessPosition());
						pmPy2D.setProcessDate(ConvertDateUtils.parseStringToLocalDate(jobDetail.getProcessDate(), ConvertDateUtils.DD_MM_YYYY));
						
						pmPy2D.setPy2TopicSeq(py2Detail.getPy2TopicSeq());
						pmPy2D.setPy2TopicName(py2Detail.getPy2TopicName());
						pmPy2D.setPy2Topic1Main(py2Detail.getPy2Topic1Main());
						pmPy2D.setPy2Topic2Ctl(py2Detail.getPy2Topic2Ctl());
						pmPy2D.setPy2Topic3Assess(py2Detail.getPy2Topic3Assess());
						pmPy2D.setPy2Topic4Risk(py2Detail.getPy2Topic4Risk());
						pmPy2D.setPy2Topic5Improve(py2Detail.getPy2Topic5Improve());
						pmPy2D.setPy2Topic6Owner(py2Detail.getPy2Topic6Owner());
						pmPy2D.setPy2Topic7Remark(py2Detail.getPy2Topic7Remark());						
						pmPy2DList.add(pmPy2D);
					}
				}
			}
		}
		wsPmPy2HRepository.batchMerge(pmPy2HList);
		wsPmPy2DRepository.batchMerge(pmPy2DList);

		long end = System.currentTimeMillis();
		logger.info("syncData PmPy2 Success, using {} seconds", (float) (end - start) / 1000F);
	}
}
