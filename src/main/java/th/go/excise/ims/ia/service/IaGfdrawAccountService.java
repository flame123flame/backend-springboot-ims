package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

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
import th.go.excise.ims.ia.persistence.entity.IaGfdrawAccount;
import th.go.excise.ims.ia.persistence.entity.IaGfuploadH;
import th.go.excise.ims.ia.persistence.repository.IaGfdrawAccountRepository;
import th.go.excise.ims.ia.vo.IaGfdrawAccountExcelVo;
import th.go.excise.ims.ia.vo.Int15ResponseUploadVo;
import th.go.excise.ims.ia.vo.Int15SaveVo;

@Service
public class IaGfdrawAccountService {

	@Autowired
	private IaGfdrawAccountRepository iaGfdrawAccountRepository;

	private final String KEY_FILTER[] = { "รหัสหน่วยเบิกจ่าย", "วันที่บันทึก", "วันที่รายงาน", "กรณีจ่ายตรงผู้ขาย", "กรณีจ่ายผ่านส่วนราชการ", "ผลรวม" };

	private Logger logger = LoggerFactory.getLogger(IaGfdrawAccountService.class);

	// public void addDataByExcel(File file) {
	// try {
	// List<List<String>> ex = ExcelUtils.readExcel(file);
	// for (List<String> list : ex) {
	// for (int i = 0; i < list.size(); i++) {
	// System.out.print(i + " : " + list.get(i) + "||");
	// }
	// System.out.println();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public ResponseData<Int15ResponseUploadVo> addDataByExcel(MultipartFile file) {
		logger.info("addDataByExcel");
		ResponseData<Int15ResponseUploadVo> responseData = new ResponseData<Int15ResponseUploadVo>();
		try {
			String departmentCode = "";
			String periodFrom = "";
			String periodTo = "";
			String repDate = "";
			String repType = "";
			List<IaGfdrawAccountExcelVo> iaGfDrawAccountList = new ArrayList<>();
			IaGfdrawAccountExcelVo iaGfDrawAccount = new IaGfdrawAccountExcelVo();
			List<List<String>> allLine = ExcelUtils.readExcel(file);
			for (List<String> line : allLine) {
				if (line != null && line.size() == 2 && line.get(0) != null && KEY_FILTER[0].equals(line.get(0).trim())) {
					departmentCode = line.get(1);
				} else if (line != null && line.size() == 2 && line.get(0) != null && KEY_FILTER[1].equals(line.get(0).trim())) {
					String[] periodData = line.get(1).trim().split(" ถึง ");
					periodFrom = periodData[0];
					periodTo = periodData[1];
				} else if (line != null && line.size() == 2 && line.get(0) != null && KEY_FILTER[2].equals(line.get(0).trim())) {
					repDate = line.get(1).trim();
				} else if (line != null && line.size() == 1 && line.get(0) != null && KEY_FILTER[3].equals(line.get(0).trim())) {
					repType = "1";
				} else if (line != null && line.size() == 1 && line.get(0) != null && KEY_FILTER[4].equals(line.get(0).trim())) {
					repType = "2";
				} else if (line != null && line.size() == 2 && line.get(0) != null && KEY_FILTER[5].equals(line.get(0).trim())) {
					departmentCode = "";
					periodFrom = "";
					periodTo = "";
					repDate = "";
					repType = "";
				} else if (line != null && line.size() == 13) {
					try {
						iaGfDrawAccount = new IaGfdrawAccountExcelVo();
						iaGfDrawAccount.setDepartmentCode(departmentCode);
						iaGfDrawAccount.setPeriodFrom(ConvertDateUtils.changPaettleStringDate(periodFrom, ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN, ConvertDateUtils.LOCAL_TH));
						iaGfDrawAccount.setPeriodTo(ConvertDateUtils.changPaettleStringDate(periodTo, ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN, ConvertDateUtils.LOCAL_TH));
						iaGfDrawAccount.setRepDate(ConvertDateUtils.changPaettleStringDate(repDate, ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN, ConvertDateUtils.LOCAL_TH));
						iaGfDrawAccount.setRepType(repType);
						iaGfDrawAccount.setRecordDate(ConvertDateUtils.changPaettleStringDate(line.get(0), ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN, ConvertDateUtils.LOCAL_TH));
						iaGfDrawAccount.setRecodeApproveDate(ConvertDateUtils.changPaettleStringDate(line.get(1), ConvertDateUtils.DD_MM_YYYY_DOT, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN, ConvertDateUtils.LOCAL_TH));
						iaGfDrawAccount.setType(line.get(2));
						iaGfDrawAccount.setDocNo(line.get(3));
						iaGfDrawAccount.setSellerName(line.get(4));
						iaGfDrawAccount.setSellerBookBank(line.get(5));
						iaGfDrawAccount.setReferenceCode(line.get(6));
						iaGfDrawAccount.setBudgetCode(line.get(7));
						iaGfDrawAccount.setDisbAmt(NumberUtils.toBigDecimal(line.get(8)));
						iaGfDrawAccount.setTaxAmt(NumberUtils.toBigDecimal(line.get(9)));
						iaGfDrawAccount.setMulctAmt(NumberUtils.toBigDecimal(line.get(10)));
						iaGfDrawAccount.setFeeAmt(NumberUtils.toBigDecimal(line.get(11)));
						iaGfDrawAccount.setNetAmt(NumberUtils.toBigDecimal(line.get(12)));
						iaGfDrawAccountList.add(iaGfDrawAccount);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}

				}
			}
			Int15ResponseUploadVo response = new Int15ResponseUploadVo();
			response.setFileName(file.getOriginalFilename());
			response.setFormData1(iaGfDrawAccountList);
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
		IaGfuploadH ia = new IaGfuploadH();
		ia.setPeriodMonth(form.getPeriod());
		ia.setPeriodYear(form.getYear());
		ia.setStartDate(ConvertDateUtils.parseStringToDate(form.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		ia.setEndDate(ConvertDateUtils.parseStringToDate(form.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		ia.setUploadType(form.getTypeData());
		ia.setDeptDisb(form.getDisburseMoney());
		ia.setFileName(form.getFileName());
		if (form.getFormData1() != null && form.getFormData1().size() > 0) {
			List<IaGfdrawAccount> IaGfdrawAccountList = new ArrayList<>();
			IaGfdrawAccount iaGfDrawAccount = null;
			for (IaGfdrawAccountExcelVo vo : form.getFormData1()) {
				iaGfDrawAccount = new IaGfdrawAccount();
				iaGfDrawAccount.setGfuploadHId(ia.getGfuploadHId());
				iaGfDrawAccount.setDeptDisb(vo.getDepartmentCode());
				iaGfDrawAccount.setPeriodFrom(ConvertDateUtils.parseStringToDate(vo.getPeriodFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				iaGfDrawAccount.setPeriodTo(ConvertDateUtils.parseStringToDate(vo.getPeriodTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				iaGfDrawAccount.setRepDate(ConvertDateUtils.parseStringToDate(vo.getRepDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				iaGfDrawAccount.setRepType(vo.getRepType());
				iaGfDrawAccount.setRecordDate(ConvertDateUtils.parseStringToDate(vo.getRecordDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				iaGfDrawAccount.setRecodeApproveDate(ConvertDateUtils.parseStringToDate(vo.getRecodeApproveDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				iaGfDrawAccount.setType(vo.getType());
				iaGfDrawAccount.setDocNo(vo.getDocNo());
				iaGfDrawAccount.setSellerName(vo.getSellerName());
				iaGfDrawAccount.setSellerBookBank(vo.getSellerBookBank());
				iaGfDrawAccount.setReferenceCode(vo.getReferenceCode());
				iaGfDrawAccount.setBudgetCode(vo.getBudgetCode());
				iaGfDrawAccount.setDisbAmt(vo.getDisbAmt());
				iaGfDrawAccount.setTaxAmt(vo.getTaxAmt());
				iaGfDrawAccount.setMulctAmt(vo.getMulctAmt());
				iaGfDrawAccount.setFeeAmt(vo.getFeeAmt());
				iaGfDrawAccount.setNetAmt(vo.getNetAmt());
				IaGfdrawAccountList.add(iaGfDrawAccount);
			}
			iaGfdrawAccountRepository.saveAll(IaGfdrawAccountList);
		}
	}
}
