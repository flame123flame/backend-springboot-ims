package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.DataEntry;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.MaterialData;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.ProductData;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.RequestData;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.ResponseData2;
import th.go.excise.ims.ws.client.pcc.oasfri0100.service.OasFri0100Service;
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100D;
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100H;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100DRepository;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100HRepository;

@Service
public class SyncWsOasfri0100Service {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncWsOasfri0100Service.class);
	
	@Autowired
	private OasFri0100Service oasFri0100Service;
	
	@Autowired
	private WsOasfri0100HRepository wsOasfri0100HRepository;
	@Autowired
	private WsOasfri0100DRepository wsOasfri0100DRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData(String newRegId, LocalDate localDate) throws PccRestfulException {
		logger.info("syncData OASFRI0100 Start, newRegId={}, date={}", newRegId, localDate);
		long start = System.currentTimeMillis();
		
		RequestData requestData = null;
		ResponseData2 responseData = null;
		ThaiBuddhistDate thaiDate = null;
		String taxYear = null;
		String taxYearTh = null;
		String taxMonth = null;
		WsOasfri0100H oasfri0100H = null;
		List<WsOasfri0100H> oasfri0100HList = new ArrayList<>();
		WsOasfri0100D oasfri0100D = null;
		List<WsOasfri0100D> oasfri0100DList = new ArrayList<>();
		
		taxYear = String.valueOf(localDate.get(ChronoField.YEAR));
		thaiDate = ThaiBuddhistDate.from(localDate);
		taxYearTh = String.valueOf(thaiDate.get(ChronoField.YEAR));
		taxMonth = String.valueOf(thaiDate.get(ChronoField.MONTH_OF_YEAR));
		
		requestData = new RequestData();
		requestData.setRegId(newRegId);
		requestData.setTaxYear(taxYearTh);
		requestData.setTaxMonth(taxMonth);
		responseData = oasFri0100Service.execute(requestData);
		
		// Header - Form
		oasfri0100H = new WsOasfri0100H();
		oasfri0100H.setNewRegId(newRegId);
		oasfri0100H.setTaxYear(taxYear);
		oasfri0100H.setTaxMonth(taxMonth);
		oasfri0100H.setFormdocRec0142No(responseData.getFormDoc().getRec0142No());
		oasfri0100H.setFormdocRec0142Date(StringUtils.isNotEmpty(responseData.getFormDoc().getRec0142Date()) ? LocalDate.parse(responseData.getFormDoc().getRec0142Date().substring(0, 10), DateTimeFormatter.ISO_DATE) : null);
		oasfri0100H.setFormdocRec0142By(responseData.getFormDoc().getRec0142By());
		oasfri0100H.setRcvdocSignBy(responseData.getReceiveDoc().getSignBy());
		oasfri0100H.setRcvdocSignDate(StringUtils.isNotEmpty(responseData.getReceiveDoc().getSignDate()) ? LocalDate.parse(responseData.getReceiveDoc().getSignDate().substring(0, 10), DateTimeFormatter.ISO_DATE) : null);
		oasfri0100H.setCreatedBy(SYSTEM_USER.BATCH);
		oasfri0100H.setCreatedDate(LocalDateTime.now());
		oasfri0100HList.add(oasfri0100H);
		// Material
		for (MaterialData materialData : responseData.getMaterialData()) {
			if (materialData.getMaterialEntry() != null && materialData.getMaterialEntry().size() > 0) {
				// MaterialEntry
				for (DataEntry materialEntry : materialData.getMaterialEntry()) {
					oasfri0100D = new WsOasfri0100D();
					oasfri0100D.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL);
					oasfri0100D.setFormdocRec0142No(responseData.getFormDoc().getRec0142No());
					oasfri0100D.setDataSeq(Integer.parseInt(materialData.getMaterialSeq()));
					oasfri0100D.setDataId(materialData.getMaterialId());
					oasfri0100D.setDataName(materialData.getMaterialName());
					oasfri0100D.setBalBfQty(NumberUtils.toBigDecimal(materialData.getBalanceBfQty()));
					oasfri0100D.setSeqNo(Integer.parseInt(materialEntry.getSeqNo()));
					oasfri0100D.setAccountName(materialEntry.getAccountName());
					oasfri0100D.setInQty(NumberUtils.toBigDecimal(materialEntry.getInQty()));
					oasfri0100D.setCreatedBy(SYSTEM_USER.BATCH);
					oasfri0100D.setCreatedDate(LocalDateTime.now());
					oasfri0100DList.add(oasfri0100D);
				}
			} else {
				oasfri0100D = new WsOasfri0100D();
				oasfri0100D.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL);
				oasfri0100D.setFormdocRec0142No(responseData.getFormDoc().getRec0142No());
				oasfri0100D.setDataSeq(Integer.parseInt(materialData.getMaterialSeq()));
				oasfri0100D.setDataId(materialData.getMaterialId());
				oasfri0100D.setDataName(materialData.getMaterialName());
				oasfri0100D.setBalBfQty(NumberUtils.toBigDecimal(materialData.getBalanceBfQty()));
				oasfri0100D.setCreatedBy(SYSTEM_USER.BATCH);
				oasfri0100D.setCreatedDate(LocalDateTime.now());
				oasfri0100DList.add(oasfri0100D);
			}
		}
		// Product
		for (ProductData productData : responseData.getProductData()) {
			if (productData.getProductEntry() != null && productData.getProductEntry().size() > 0) {
				// MaterialEntry
				for (DataEntry productEntry : productData.getProductEntry()) {
					oasfri0100D = new WsOasfri0100D();
					oasfri0100D.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_PRODUCT);
					oasfri0100D.setFormdocRec0142No(responseData.getFormDoc().getRec0142No());
					oasfri0100D.setDataSeq(Integer.parseInt(productData.getProductSeq()));
					oasfri0100D.setDataId(productData.getProductId());
					oasfri0100D.setDataName(productData.getProductName());
					oasfri0100D.setBalBfQty(NumberUtils.toBigDecimal(productData.getBalanceBfQty()));
					oasfri0100D.setSeqNo(Integer.parseInt(productEntry.getSeqNo()));
					oasfri0100D.setAccountName(productEntry.getAccountName());
					oasfri0100D.setInQty(NumberUtils.toBigDecimal(productEntry.getInQty()));
					oasfri0100D.setCreatedBy(SYSTEM_USER.BATCH);
					oasfri0100D.setCreatedDate(LocalDateTime.now());
					oasfri0100DList.add(oasfri0100D);
				}
			} else {
				oasfri0100D = new WsOasfri0100D();
				oasfri0100D.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_PRODUCT);
				oasfri0100D.setFormdocRec0142No(responseData.getFormDoc().getRec0142No());
				oasfri0100D.setDataSeq(Integer.parseInt(productData.getProductSeq()));
				oasfri0100D.setDataId(productData.getProductId());
				oasfri0100D.setDataName(productData.getProductName());
				oasfri0100D.setBalBfQty(NumberUtils.toBigDecimal(productData.getBalanceBfQty()));
				oasfri0100D.setCreatedBy(SYSTEM_USER.BATCH);
				oasfri0100D.setCreatedDate(LocalDateTime.now());
				oasfri0100DList.add(oasfri0100D);
			}
		}
		
		wsOasfri0100HRepository.forceDeleteByDocNo(responseData.getFormDoc().getRec0142No());
		wsOasfri0100HRepository.batchInsert(oasfri0100HList);
		logger.info("Batch Merge WS_OASFRI0100_H Success");
		
		wsOasfri0100DRepository.forceDeleteByDocNo(responseData.getFormDoc().getRec0142No());
		wsOasfri0100DRepository.batchInsert(oasfri0100DList);
		logger.info("Batch Merge WS_OASFRI0100_D Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData OASFRI0100 Success, using {} seconds", (float) (end - start) / 1000F);
	}
	
}
