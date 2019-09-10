package th.co.baiwa.buckwaframework.preferences.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.GeoAmphur;
import th.co.baiwa.buckwaframework.support.domain.GeoDistrict;
import th.co.baiwa.buckwaframework.support.domain.GeoProvince;
import th.co.baiwa.buckwaframework.support.domain.GeoSector;

@RestController
@RequestMapping("/api/preferences/geography")
public class GeographyController {
	
	private static final Logger logger = LoggerFactory.getLogger(GeographyController.class);
	
	@PostMapping("/sector-list")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Sector List"
	)
	public ResponseData<List<GeoSector>> getGeoSectorList() {
		logger.info("getGeoSectorList");
		
		ResponseData<List<GeoSector>> response = new ResponseData<>();
		List<GeoSector> geoSectorList = ApplicationCache.getGeoSectorList();
		if (!CollectionUtils.isEmpty(geoSectorList)) {
			response.setData(geoSectorList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Geography List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/provice-list")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Province List"
	)
	public ResponseData<List<GeoProvince>> getProvinceList() {
		logger.info("getProvinceList");
		
		ResponseData<List<GeoProvince>> response = new ResponseData<>();
		List<GeoProvince> geoProvinceList = ApplicationCache.getGeoProvinceList();
		if (!CollectionUtils.isEmpty(geoProvinceList)) {
			response.setData(geoProvinceList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Province List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/provice-list/{sectorCode}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Province List by Sector Code"
	)
	public ResponseData<List<GeoProvince>> getProvinceListBySectorCode(@PathVariable("sectorCode") String sectorCode) {
		logger.info("getProvinceListBySectorCode sectorCode={}", sectorCode);
		
		ResponseData<List<GeoProvince>> response = new ResponseData<>();
		List<GeoProvince> geoProvinceList = ApplicationCache.getGeoProvinceList(sectorCode);
		if (!CollectionUtils.isEmpty(geoProvinceList)) {
			response.setData(geoProvinceList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Province List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/amphur-list")
	@ApiOperation(
			tags = MODULE_NAME.PREFERENCES,
			value = "Get Amphur List"
			)
	public ResponseData<List<GeoAmphur>> getAmphurList() {
		logger.info("getAmphurList");
		
		ResponseData<List<GeoAmphur>> response = new ResponseData<>();
		List<GeoAmphur> geoAmphurList = ApplicationCache.getGeoAmphurList();
		if (!CollectionUtils.isEmpty(geoAmphurList)) {
			response.setData(geoAmphurList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Amphur List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/amphur-list/{provinceCode}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Amphur List by Province Code"
	)
	public ResponseData<List<GeoAmphur>> getAmphurListByProvinceCode(@PathVariable("provinceCode") String provinceCode) {
		logger.info("getAmphurListByProvinceCode provinceCode={}", provinceCode);
		
		ResponseData<List<GeoAmphur>> response = new ResponseData<>();
		List<GeoAmphur> geoAmphurList = ApplicationCache.getGeoAmphurList(provinceCode);
		if (!CollectionUtils.isEmpty(geoAmphurList)) {
			response.setData(geoAmphurList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Amphur List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/district-list")
	@ApiOperation(
			tags = MODULE_NAME.PREFERENCES,
			value = "Get District List"
			)
	public ResponseData<List<GeoDistrict>> getDistrictList() {
		logger.info("getDistrictList");
		
		ResponseData<List<GeoDistrict>> response = new ResponseData<>();
		List<GeoDistrict> geoDistrictList = ApplicationCache.getGeoDistrictList();
		if (!CollectionUtils.isEmpty(geoDistrictList)) {
			response.setData(geoDistrictList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("District List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

	@PostMapping("/district-list/{amphurCode}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get District List by Amphur Code"
	)
	public ResponseData<List<GeoDistrict>> getDistrictListByAmphurCode(@PathVariable("amphurCode") String amphurCode) {
		logger.info("getDistrictListByAmphurCode amphurCode={}", amphurCode);
		
		ResponseData<List<GeoDistrict>> response = new ResponseData<>();
		List<GeoDistrict> geoDistrictList = ApplicationCache.getGeoDistrictList(amphurCode);
		if (!CollectionUtils.isEmpty(geoDistrictList)) {
			response.setData(geoDistrictList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("District List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
}
