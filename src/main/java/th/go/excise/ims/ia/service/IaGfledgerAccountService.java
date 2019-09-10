package th.go.excise.ims.ia.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaGfledgerAccount;
import th.go.excise.ims.ia.persistence.entity.IaGfuploadH;
import th.go.excise.ims.ia.persistence.repository.IaGfledgerAccountRepository;
import th.go.excise.ims.ia.persistence.repository.IaGfuploadHRepository;
import th.go.excise.ims.ia.vo.IaGfledgerAccountVo;
import th.go.excise.ims.ia.vo.Int15ResponseUploadVo;
import th.go.excise.ims.ia.vo.Int15SaveVo;
import th.go.excise.ims.ta.service.TaxAuditService;

@Service
public class IaGfledgerAccountService {

	private static final Logger logger = LoggerFactory.getLogger(TaxAuditService.class);

	@Autowired
	private IaGfuploadHRepository iaGfuploadHRepository;
	@Autowired
	private IaGfledgerAccountRepository iaGfledgerAccountRepository;

	private final String KEY_FILTER[] = { "เลขที่บัญชี G/L", "รหัสหน่วยงาน", "ประเภท", "*" };

	public ResponseData<Int15ResponseUploadVo> addDataByExcel(MultipartFile file) throws Exception {
		logger.info("addDataByExcel filename={}", file.getOriginalFilename());

		ResponseData<Int15ResponseUploadVo> responseData = new ResponseData<Int15ResponseUploadVo>();
		List<IaGfledgerAccountVo> iaGfledgerAccountList = new ArrayList<>();
		IaGfledgerAccountVo iaGfledgerAccount = new IaGfledgerAccountVo();
		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(file.getBytes()));
		Sheet sheet = workbook.getSheetAt(0);
		String glAccNo = "";
		String depCode = "";
		for (Row r : sheet) {
			String val;
			for (Cell c : r) {
				val = ExcelUtils.getCellValueAsString(c);
				if (StringUtils.isNoneBlank(val)) {
					if (StringUtils.trim(val).equals(KEY_FILTER[0]) && c.getColumnIndex() == 0) {
						glAccNo = ExcelUtils.getCellValueAsString(r.getCell(5));
					} else if (StringUtils.trim(val).equals(KEY_FILTER[1]) && c.getColumnIndex() == 0) {
						depCode = ExcelUtils.getCellValueAsString(r.getCell(5));
					} else if (ExcelUtils.getCellValueAsString(r.getCell(1)) == null && ExcelUtils.getCellValueAsString(r.getCell(2)) != null
							&& !StringUtils.trim(ExcelUtils.getCellValueAsString(r.getCell(2))).equals(KEY_FILTER[2])) {
						switch (c.getColumnIndex()) {
						case 2:
							iaGfledgerAccount.setType(val);
							break;
						case 3:
							iaGfledgerAccount.setPeriod(NumberUtils.toBigDecimal(val));
							break;
						case 4:
							iaGfledgerAccount.setDocDate(
									ConvertDateUtils.changPaettleStringDate(val, ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN, ConvertDateUtils.LOCAL_TH));
							break;
						case 6:
							iaGfledgerAccount.setPostingDate(
									ConvertDateUtils.changPaettleStringDate(val, ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN, ConvertDateUtils.LOCAL_TH));
							break;
						case 8:
							iaGfledgerAccount.setDocNo(val);
							break;
						case 9:
							iaGfledgerAccount.setRefCode(val);
							break;
						case 10:
							iaGfledgerAccount.setCurrAmt(NumberUtils.toBigDecimal(val));
							break;
						case 11:
							iaGfledgerAccount.setPkCode(val);
							break;
						case 12:
							iaGfledgerAccount.setRorKor(val);
							break;
						case 13:
							iaGfledgerAccount.setDeterminaton(val);
							break;
						case 14:
							iaGfledgerAccount.setMsg(val);
							break;
						case 15:
							iaGfledgerAccount.setKeyRef3(val);
							break;
						case 16:
							iaGfledgerAccount.setKeyRef1(val);
							break;
						case 17:
							iaGfledgerAccount.setKeyRef2(val);
							break;
						case 18:
							iaGfledgerAccount.setHlodingTaxes(NumberUtils.toBigDecimal(val));
							break;
						case 19:
							iaGfledgerAccount.setDepositAcc(val);
							break;
						case 20:
							iaGfledgerAccount.setAccType(val);
							break;
						case 21:
							iaGfledgerAccount.setCostCenter(val);
							break;
						case 22:
							iaGfledgerAccount.setDeptDisb(val);
							break;
						case 23:
							iaGfledgerAccount.setClrngDoc(val);
							break;

						default:
							break;
						}
					}
				}
			}
			if (StringUtils.isNoneBlank(iaGfledgerAccount.getDocNo())) {
				iaGfledgerAccount.setGlAccNo(glAccNo);
				iaGfledgerAccount.setDepCode(depCode);
				iaGfledgerAccountList.add(iaGfledgerAccount);
				iaGfledgerAccount = new IaGfledgerAccountVo();
			}
		}

		try {
			Int15ResponseUploadVo response = new Int15ResponseUploadVo();
			response.setFileName(file.getOriginalFilename());
			response.setFormData3(iaGfledgerAccountList);
			responseData.setData(response);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	public void saveData(Int15SaveVo form) {
		if (StringUtils.isNotBlank(form.getYear())) {
			form.setYear(ConvertDateUtils.changPaettleStringDate("01/01/" + form.getYear(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH, ConvertDateUtils.LOCAL_EN));
		}
		List<IaGfledgerAccount> iaGfledgerAccountList = new ArrayList<>();
		IaGfuploadH ia = new IaGfuploadH();
		ia.setPeriodMonth(form.getPeriod());
		ia.setPeriodYear(form.getYear());
		ia.setStartDate(ConvertDateUtils.parseStringToDate(form.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		ia.setEndDate(ConvertDateUtils.parseStringToDate(form.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		ia.setUploadType(form.getTypeData());
		ia.setDeptDisb(form.getDisburseMoney());
		ia.setFileName(form.getFileName());
		iaGfuploadHRepository.save(ia);
		if (form.getFormData3() != null && form.getFormData3().size() > 0) {

			IaGfledgerAccount iaGfledgerAccount = new IaGfledgerAccount();
			for (IaGfledgerAccountVo vo : form.getFormData3()) {
				iaGfledgerAccount = new IaGfledgerAccount();
				iaGfledgerAccount.setGfuploadHId(ia.getGfuploadHId());
				iaGfledgerAccount.setIaGfledgerAccountId(vo.getIaGfledgerAccountId());
				iaGfledgerAccount.setGlAccNo(vo.getGlAccNo());
				iaGfledgerAccount.setDepCode(vo.getDepCode());
				iaGfledgerAccount.setType(vo.getType());
				iaGfledgerAccount.setPeriod(vo.getPeriod());
				iaGfledgerAccount.setDocDate(ConvertDateUtils.parseStringToDate(vo.getDocDate(), ConvertDateUtils.DD_MM_YY, ConvertDateUtils.LOCAL_TH));
				iaGfledgerAccount.setPostingDate(ConvertDateUtils.parseStringToDate(vo.getPostingDate(), ConvertDateUtils.DD_MM_YY, ConvertDateUtils.LOCAL_TH));
				iaGfledgerAccount.setDocNo(vo.getDocNo());
				iaGfledgerAccount.setRefCode(vo.getRefCode());
				iaGfledgerAccount.setCurrAmt(vo.getCurrAmt());
				iaGfledgerAccount.setPkCode(vo.getPkCode());
				iaGfledgerAccount.setRorKor(vo.getRorKor());
				iaGfledgerAccount.setDeterminaton(vo.getDeterminaton());
				iaGfledgerAccount.setMsg(vo.getMsg());
				iaGfledgerAccount.setKeyRef3(vo.getKeyRef3());
				iaGfledgerAccount.setKeyRef1(vo.getKeyRef1());
				iaGfledgerAccount.setKeyRef2(vo.getKeyRef2());
				iaGfledgerAccount.setHlodingTaxes(vo.getHlodingTaxes());
				iaGfledgerAccount.setDepositAcc(vo.getDepositAcc());
				iaGfledgerAccount.setAccType(vo.getAccType());
				iaGfledgerAccount.setCostCenter(vo.getCostCenter());
				iaGfledgerAccount.setDeptDisb(vo.getDeptDisb());
				iaGfledgerAccount.setClrngDoc(vo.getClrngDoc());
				iaGfledgerAccount.setPeriodYear(form.getYear());
				iaGfledgerAccountList.add(iaGfledgerAccount);
			}
			iaGfledgerAccountRepository.insertBatch(iaGfledgerAccountList);
			// iaGfledgerAccountRepository.saveAll(iaGfledgerAccountList);
		}
	}
}
