package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.wsdl.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaAssetBalance;
import th.go.excise.ims.ia.service.Int120201Service;
import th.go.excise.ims.ia.vo.Int120201FormVo;

@Controller
@RequestMapping("api/ia/int12/02/01")
public class Int120201Controller {
	private Logger logger = LoggerFactory.getLogger(Int120201Controller.class);

	@Autowired
	private Int120201Service int120201Service;
	
//	@PostMapping("/dataTableAssetBalance")
//	@ResponseBody
//	public ResponseDataTable<AssetBalance> dataTableAssetBalance(DataTableRequest dataTableRequest, AssetBalance assetBalance) {
//		logger.info("dataTableAssetBalance");
//		ResponseDataTable<AssetBalance> responseDataTable = new ResponseDataTable<AssetBalance>();
//		List<AssetBalance> assetList = assetBalanceService.findAssetBalanceByCriteria(assetBalance);
//		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
//		responseDataTable.setData(assetList);
//		responseDataTable.setRecordsTotal(assetList.size());
//		responseDataTable.setRecordsFiltered(assetList.size());
//		return responseDataTable;
//	}

	@PostMapping("/addAssetMaintenance")
	@ResponseBody
	public ResponseData<String> addAssetMaintenance(@RequestBody Int120201FormVo form) {
		ResponseData<String> response = new ResponseData<String>();

		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();

		IaAssetBalance assetBalance = form.getAssetBalance();
		assetBalance.setExciseDepartment(officeCode.substring(0, 2));
		assetBalance.setExciseDistrict(officeCode.substring(2, 4));
		assetBalance.setExciseRegion(officeCode.substring(4));
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate diffDate = form.getAssetBalance().getDateOfManufacture().toInstant().atZone(defaultZoneId)
				.toLocalDate();
		diffDate = diffDate.plusDays(form.getDay()).plusMonths(form.getMonth()).plusYears(form.getYear());
		String dateOfManufactureStr = ConvertDateUtils.formatDateToString(form.getAssetBalance().getDateOfManufacture(),
				ConvertDateUtils.DD_MM_YYYY);
		LocalDate dateOfManufactureDlc = ConvertDateUtils.parseStringToLocalDate(dateOfManufactureStr,
				ConvertDateUtils.DD_MM_YYYY);

		long daysBetween = ChronoUnit.DAYS.between(dateOfManufactureDlc, diffDate);
		assetBalance.setAccumulatedDepreciation(new BigDecimal(daysBetween));

		try {
			assetBalance = int120201Service.saveAssetBalance(assetBalance);
			response.setData("sucsess");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int120201Controller addAssetMaintenance : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
