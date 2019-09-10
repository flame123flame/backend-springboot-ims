package th.go.excise.ims.scheduler.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.client.pcc.anafri0001.model.Form;
import th.go.excise.ims.ws.client.pcc.anafri0001.model.Goods;
import th.go.excise.ims.ws.client.pcc.anafri0001.model.RequestData;
import th.go.excise.ims.ws.client.pcc.anafri0001.service.AnaFri0001Service;
import th.go.excise.ims.ws.persistence.entity.WsAnafri0001D;
import th.go.excise.ims.ws.persistence.entity.WsAnafri0001H;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001HRepository;

@Service
public class SyncWsAnafri0001Service {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncWsAnafri0001Service.class);
	
	private final int WS_DATA_SIZE = 500;
	
	@Autowired
	private AnaFri0001Service anaFri0001Service;
	
	@Autowired
	private WsAnafri0001HRepository wsAnafri0001HRepository;
	
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData(RequestData requestData) {
		logger.info("syncData Anafri0001 Start");
		long start = System.currentTimeMillis();
		
		requestData.setDataPerPage(String.valueOf(WS_DATA_SIZE));
		int indexPage = 0;
		
		List<Form> formList = null;
		WsAnafri0001H anafri0001H = null;
		List<WsAnafri0001H> anafri0001HList = new ArrayList<>();
		WsAnafri0001D anafri0001D = null;
		List<WsAnafri0001D> anafri0001DList = new ArrayList<>();
		do {
			indexPage++;
			requestData.setPageNo(String.valueOf(indexPage));
			try {
				formList = anaFri0001Service.execute(requestData).getFormList();
			} catch (Exception e) {
				logger.warn("newRegId={}, startDate={}, endDate={}, error={}",
					requestData.getRegistrationId(), requestData.getStartDate(), requestData.getEndDate(), e.getMessage());
				continue;
			}
			if (formList != null && formList.size() > 0) {
				logger.info("Restful Post to Anafri0001 Response size: {}", formList.size());
				for (Form form : formList) {
					anafri0001H = new WsAnafri0001H();
					anafri0001H.setNewRegId(requestData.getRegistrationId());
					anafri0001H.setFormCode(requestData.getFormCode());
					anafri0001H.setRegInNo(form.getRegInNo());
					anafri0001H.setRegInDate(ConvertDateUtils.parseStringToLocalDate(form.getRegInDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_TH));
					anafri0001H.setPayType12(form.getPayType12());
					anafri0001H.setReceiptNo(form.getReceiptNo());
					anafri0001H.setReceiptDate(ConvertDateUtils.parseStringToLocalDate(form.getReceiptDate(), ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_TH));
					anafri0001H.setTaxAmt(NumberUtils.toBigDecimal(form.getTaxAmount()));
					anafri0001H.setReduceAmt(NumberUtils.toBigDecimal(form.getReduceAmount()));
					anafri0001H.setDifAmt(NumberUtils.toBigDecimal(form.getDifAmount()));
					anafri0001H.setPenAmt(NumberUtils.toBigDecimal(form.getPenAmount()));
					anafri0001H.setAddAmt(NumberUtils.toBigDecimal(form.getAddAmount()));
					anafri0001H.setCreditAmt(NumberUtils.toBigDecimal(form.getCreditAmount()));
					anafri0001H.setNetTaxAmt(NumberUtils.toBigDecimal(form.getNetTaxAmount()));
					anafri0001H.setCreatedBy(SYSTEM_USER.BATCH);
					anafri0001H.setCreatedDate(LocalDateTime.now());
					if (form.getGoodsList() != null && form.getGoodsList().size() > 0) {
						int i = 1;
						for (Goods goods : form.getGoodsList()) {
							anafri0001D = new WsAnafri0001D();
							anafri0001D.setNewRegId(requestData.getRegistrationId());
							anafri0001D.setFormCode(requestData.getFormCode());
							anafri0001D.setRegInNo(form.getRegInNo());
							anafri0001D.setGoodsSeq(String.valueOf(i));
							anafri0001D.setProductCode(goods.getProductCode());
							anafri0001D.setProductName(goods.getProductName());
							anafri0001D.setBrandMainCode(goods.getBrandMainCode());
							anafri0001D.setBrandMainName(goods.getBrandMainName());
							anafri0001D.setBrandSecondCode(goods.getBrandSecondCode());
							anafri0001D.setBrandSecondName(goods.getBrandSecondName());
							anafri0001D.setModelCode(goods.getModelCode());
							anafri0001D.setModelName(goods.getModelName());
							anafri0001D.setSizeCode(goods.getSizeCode());
							anafri0001D.setSizeName(goods.getSizeName());
							anafri0001D.setUnitCode(goods.getUnitCode());
							anafri0001D.setUnitName(goods.getUnitName());
							anafri0001D.setProductQty(NumberUtils.toBigDecimal(goods.getProductQuantity()));
							anafri0001D.setProductPrice(NumberUtils.toBigDecimal(goods.getProductPrice()));
							anafri0001D.setValueRate(NumberUtils.toBigDecimal(goods.getValueRate()));
							anafri0001D.setQtyRate(NumberUtils.toBigDecimal(goods.getQuantityRate()));
							anafri0001D.setTaxValueAmt(NumberUtils.toBigDecimal(goods.getTaxValueAmount()));
							anafri0001D.setTaxQuantityAmt(NumberUtils.toBigDecimal(goods.getTaxQuantityAmount()));
							anafri0001D.setTaxAmt(NumberUtils.toBigDecimal(goods.getTaxAmount()));
							anafri0001D.setLocAmt(NumberUtils.toBigDecimal(goods.getLocAmount()));
							anafri0001D.setCreatedBy(SYSTEM_USER.BATCH);
							anafri0001D.setCreatedDate(LocalDateTime.now());
							anafri0001DList.add(anafri0001D);
						}
						i++;
					}
					anafri0001HList.add(anafri0001H);
				}
			} else {
				logger.warn("WS AnaFri0001 is empty ResponseData");
			}
		} while (formList != null && formList.size() == WS_DATA_SIZE);
		
		wsAnafri0001HRepository.forceDeleteByFormCode(requestData.getRegistrationId(), requestData.getFormCode(), requestData.getStartDate(), requestData.getEndDate());
		wsAnafri0001HRepository.batchInsert(anafri0001HList);
		logger.info("Batch Insert WS_ANAFRI0001_H Success");
		
		wsAnafri0001DRepository.forceDeleteByFormCode(requestData.getRegistrationId(), requestData.getFormCode(), requestData.getStartDate(), requestData.getEndDate());
		wsAnafri0001DRepository.batchInsert(anafri0001DList);
		logger.info("Batch Insert WS_ANAFRI0001_D Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData Anafri0001 Success, using {} seconds", (float) (end - start) / 1000F);
	}
	
}
