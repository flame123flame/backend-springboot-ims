package th.co.baiwa.buckwaframework.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.preferences.constant.MessageConstants.MESSAGE_LANG;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.GeoAmphurRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.GeoDistrictRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.GeoProvinceRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.GeoSectorRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.MessageRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.ParameterGroupRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.ParameterInfoRepository;
import th.co.baiwa.buckwaframework.preferences.vo.GeoAmphurVo;
import th.co.baiwa.buckwaframework.preferences.vo.GeoDistrictVo;
import th.co.baiwa.buckwaframework.preferences.vo.GeoProvinceVo;
import th.co.baiwa.buckwaframework.preferences.vo.GeoSectorVo;
import th.co.baiwa.buckwaframework.preferences.vo.MessageVo;
import th.co.baiwa.buckwaframework.preferences.vo.ParameterGroupVo;
import th.co.baiwa.buckwaframework.preferences.vo.ParameterInfoVo;
import th.co.baiwa.buckwaframework.support.domain.GeoAmphur;
import th.co.baiwa.buckwaframework.support.domain.GeoDistrict;
import th.co.baiwa.buckwaframework.support.domain.GeoProvince;
import th.co.baiwa.buckwaframework.support.domain.GeoSector;
import th.co.baiwa.buckwaframework.support.domain.Message;
import th.co.baiwa.buckwaframework.support.domain.ParamGroup;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.persistence.repository.ExciseCtrlDutyRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseDepartmentRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseDutyGroupRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseIncMastRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseSubdeptRepository;
import th.go.excise.ims.preferences.vo.ExciseCtrlDuty;
import th.go.excise.ims.preferences.vo.ExciseCtrlDutyVo;
import th.go.excise.ims.preferences.vo.ExciseDepartment;
import th.go.excise.ims.preferences.vo.ExciseDepartmentVo;
import th.go.excise.ims.preferences.vo.ExciseDutyGroup;
import th.go.excise.ims.preferences.vo.ExciseDutyGroupVo;
import th.go.excise.ims.preferences.vo.ExciseIncMast;
import th.go.excise.ims.preferences.vo.ExciseIncMastVo;
import th.go.excise.ims.preferences.vo.ExciseSubdept;
import th.go.excise.ims.preferences.vo.ExciseSubdeptVo;

@Component
public class ApplicationCache {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationCache.class);
	
	// Parameter and Message
	private ParameterGroupRepository parameterGroupRepository;
	private ParameterInfoRepository parameterInfoRepository;
	private MessageRepository messageRepository;
	// Geography
	private GeoSectorRepository geoSectorRepository;
	private GeoProvinceRepository geoProvinceRepository;
	private GeoAmphurRepository geoAmphurRepository;
	private GeoDistrictRepository geoDistrictRepository;
	// Excise Master Data
	private ExciseDepartmentRepository exciseDepartmentRepository;
	private ExciseSubdeptRepository exciseSubdeptRepository;
	private ExciseDutyGroupRepository exciseDutyGroupRepository;
	private ExciseCtrlDutyRepository exciseCtrlDutyRepository;
	private ExciseIncMastRepository exciseIncMastRepository;
	
	// Parameter and Message
	private static final ConcurrentHashMap<String, ParamGroupWrapper> PARAM_GROUP_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, Message> MESSAGE_MAP = new ConcurrentHashMap<>();
	// Geography
	private static final List<GeoSector> GEO_SECTOR_LIST = new ArrayList<>();
	private static final List<GeoProvince> GEO_PROVINCE_LIST = new ArrayList<>();
	private static final List<GeoAmphur> GEO_AMPHUR_LIST = new ArrayList<>();
	private static final List<GeoDistrict> GEO_DISTRICT_LIST = new ArrayList<>();
	private static final ConcurrentHashMap<String, List<GeoProvince>> GEO_PROVINCE_MAPPER = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<GeoAmphur>> GEO_AMPHUR_MAPPER = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<GeoDistrict>> GEO_DISTRICT_MAPPER = new ConcurrentHashMap<>();
	// Excise Master Data
	private static final ConcurrentHashMap<String, ExciseDepartment> EXCISE_DEPARTMENT_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, ExciseDepartment> EXCISE_SECTOR_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<ExciseDepartment>> EXCISE_AREA_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<ExciseDepartment>> EXCISE_BRANCH_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<ExciseDepartment>> EXCISE_CENTRAL_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<ExciseSubdept>> EXCISE_SUBDEPT_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<ExciseDutyGroup>> EXCISE_DUTY_GROUP_BY_TYPE_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, ExciseDutyGroup> EXCISE_DUTY_GROUP_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, List<ExciseCtrlDuty>> EXCISE_CTRL_DUTY_MAP = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<String, ExciseIncMast> EXCISE_INC_MAST_MAP = new ConcurrentHashMap<>();
	
	
	@Autowired
	public ApplicationCache(
			ParameterGroupRepository parameterGroupRepository,
			ParameterInfoRepository parameterInfoRepository,
			MessageRepository messageRepository,
			GeoSectorRepository geoSectorRepository,
			GeoProvinceRepository geoProvinceRepository,
			GeoAmphurRepository geoAmphurRepository,
			GeoDistrictRepository geoDistrictRepository,
			ExciseDepartmentRepository exciseDepartmentRepository,
			ExciseSubdeptRepository exciseSubdeptRepository,
			ExciseDutyGroupRepository exciseDutyGroupRepository,
			ExciseCtrlDutyRepository exciseCtrlDutyRepository,
			ExciseIncMastRepository exciseIncMastRepository) {
		this.parameterGroupRepository = parameterGroupRepository;
		this.parameterInfoRepository = parameterInfoRepository;
		this.messageRepository = messageRepository;
		this.geoSectorRepository = geoSectorRepository;
		this.geoProvinceRepository = geoProvinceRepository;
		this.geoAmphurRepository = geoAmphurRepository;
		this.geoDistrictRepository = geoDistrictRepository;
		this.exciseDepartmentRepository = exciseDepartmentRepository;
		this.exciseSubdeptRepository = exciseSubdeptRepository;
		this.exciseDutyGroupRepository = exciseDutyGroupRepository;
		this.exciseCtrlDutyRepository = exciseCtrlDutyRepository;
		this.exciseIncMastRepository = exciseIncMastRepository;
	}
	
	/** Reload */
	@PostConstruct
	public synchronized void reloadCache() {
		logger.info("ApplicationCache Reloading...");
		loadParameter();
		loadMessage();
		loadGeography();
		loadExciseDepartment();
		loadExciseSubdept();
		loadExciseDutyGroup();
		loadExciseCtrlDuty();
		loadExciseIncMast();
		logger.info("ApplicationCache Reloaded");
	}

	/********************* Method for Get Cache - Start *********************/
	/** Parameter Group & Parameter Info */
	public static List<ParamGroup> getParamGroupList() {
		List<ParamGroup> resultList = new ArrayList<>();
		for (Entry<String, ParamGroupWrapper> entry : PARAM_GROUP_MAP.entrySet()) {
			resultList.add(entry.getValue().getParamGroup());
		}
		return Collections.unmodifiableList(resultList);
	}
	
	public static ParamGroup getParamGroupByCode(String paramGroupCode) {
		return PARAM_GROUP_MAP.get(paramGroupCode).getParamGroup();
	}
	
	public static ParamInfo getParamInfoByCode(String paramGroupCode, String paramInfoCode) {
		return PARAM_GROUP_MAP.get(paramGroupCode).getParamInfoCodeMap().get(paramInfoCode);
	}

	public static List<ParamInfo> getParamInfoListByGroupCode(String paramGroupCode) {
		ParamGroupWrapper paramGroupWrapper = PARAM_GROUP_MAP.get(paramGroupCode);

		List<ParamInfo> resultList = new ArrayList<>();
		if (paramGroupWrapper != null) {
			for (Entry<String, ParamInfo> entry : paramGroupWrapper.getParamInfoCodeMap().entrySet()) {
				resultList.add(entry.getValue());
			}
		}

		return Collections.unmodifiableList(resultList);
	}

	final class ParamGroupWrapper {

		private ParamGroup paramGroup;
		private Map<String, ParamInfo> paramInfoCodeMap = new LinkedHashMap<>();

		public ParamGroupWrapper(ParamGroup paramGroup, List<? extends ParamInfo> paramInfoList) {
			this.paramGroup = paramGroup;
			for (ParamInfo paramInfo : paramInfoList) {
				paramInfoCodeMap.put(paramInfo.getParamCode(), paramInfo);
			}
		}

		public ParamGroup getParamGroup() {
			return paramGroup;
		}

		public Map<String, ParamInfo> getParamInfoCodeMap() {
			return paramInfoCodeMap;
		}

	}

	/** Message */
	public static Message getMessage(String messageCode) {
		return MESSAGE_MAP.get(messageCode);
	}

	public static String getMessage(String messageCode, String lang) {
		Message message = MESSAGE_MAP.get(messageCode);
		String msgDesc = null;
		if (MESSAGE_LANG.EN.equals(lang)) {
			msgDesc = message.getMessageEn();
		} else if (MESSAGE_LANG.TH.equals(lang)) {
			msgDesc = message.getMessageTh();
		}
		return msgDesc;
	}
	
	/** Geography */
	public static List<GeoSector> getGeoSectorList() {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_SECTOR_LIST, new ArrayList<>()));
	}

	public static List<GeoProvince> getGeoProvinceList() {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_PROVINCE_LIST, new ArrayList<>()));
	}
	
	public static List<GeoProvince> getGeoProvinceList(String sectorCode) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_PROVINCE_MAPPER.get(sectorCode), new ArrayList<>()));
	}

	public static List<GeoAmphur> getGeoAmphurList() {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_AMPHUR_LIST, new ArrayList<>()));
	}
	
	public static List<GeoAmphur> getGeoAmphurList(String proviceCode) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_AMPHUR_MAPPER.get(proviceCode), new ArrayList<>()));
	}

	public static List<GeoDistrict> getGeoDistrictList() {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_DISTRICT_LIST, new ArrayList<>()));
	}
	
	public static List<GeoDistrict> getGeoDistrictList(String amphurCode) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(GEO_DISTRICT_MAPPER.get(amphurCode), new ArrayList<>()));
	}
	
	/** Excise Master Data */
	public static ExciseDepartment getExciseDepartment(String officeCode) {
		return EXCISE_DEPARTMENT_MAP.get(officeCode);
	}

	public static List<ExciseDepartment> getExciseSectorList() {
		List<ExciseDepartment> resultList = EXCISE_SECTOR_MAP.values().stream().collect(Collectors.toList());
		resultList.sort((p1, p2) -> p1.getOfficeCode().compareTo(p2.getOfficeCode()));
		return Collections.unmodifiableList(resultList);
	}
	
	public static List<ExciseDepartment> getExciseCentralList(String officeCode) {
		 return Collections.unmodifiableList(ObjectUtils.defaultIfNull(EXCISE_CENTRAL_MAP.get(officeCode), new ArrayList<>()));
	}

	public static List<ExciseDepartment> getExciseAreaList(String officeCode) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(EXCISE_AREA_MAP.get(officeCode), new ArrayList<>()));
	}

	public static List<ExciseDepartment> getExciseBranchList(String officeCode) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(EXCISE_BRANCH_MAP.get(officeCode), new ArrayList<>()));
	}
	
	public static List<ExciseSubdept> getExciseSubdeptList(String officeCode) {
		return Collections.unmodifiableList(ObjectUtils.defaultIfNull(EXCISE_SUBDEPT_MAP.get(officeCode), new ArrayList<>()));
	}
	
	public static List<ExciseDutyGroup> getExciseDutyGroupListByType(String dutyGroupType) {
		List<ExciseDutyGroup> resultList = EXCISE_DUTY_GROUP_BY_TYPE_MAP.get(dutyGroupType);
		resultList.sort((p1, p2) -> p1.getDutyGroupCode().compareTo(p2.getDutyGroupCode()));
		return Collections.unmodifiableList(resultList);
	}
	
	public static ExciseDutyGroup getExciseDutyGroup(String dutyGroupCode) {
		return EXCISE_DUTY_GROUP_MAP.get(dutyGroupCode);
	}

	public static boolean isCtrlDutyGroupByOfficeCode(String officeCode) {
		return EXCISE_CTRL_DUTY_MAP.containsKey(officeCode);
	}
	
	public static ExciseIncMast getExciseIncMastByIncCode(String incCode) {
		return EXCISE_INC_MAST_MAP.get(incCode);
	}
	
	public static List<ExciseIncMast> getExciseIncMastList() {
		return new ArrayList<ExciseIncMast>(EXCISE_INC_MAST_MAP.values());
	}
	/********************* Method for Get Cache - End *********************/

	
	private void loadParameter() {
		logger.info("load Paramter loading...");

		PARAM_GROUP_MAP.clear();

		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup> paramGroupList = parameterGroupRepository.findAll();
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo> paramInfoList = parameterInfoRepository.findAll();
		List<ParamInfo> paramInfoVoList = null;
		ParameterGroupVo paramGroupVo = null;
		ParameterInfoVo paramInfoVo = null;
		
		for (th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup paramGroup : paramGroupList) {
			paramInfoVoList = new ArrayList<>();
			for (th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo paramInfo : paramInfoList) {
				if (paramGroup.getParamGroupCode().equals(paramInfo.getParamGroupCode())) {
					paramInfoVo = new ParameterInfoVo();
					paramInfoVo.setParamInfoId(paramInfo.getParamInfoId());
					paramInfoVo.setParamGroupCode(paramInfo.getParamGroupCode());
					paramInfoVo.setParamCode(paramInfo.getParamCode());
					paramInfoVo.setValue1(paramInfo.getValue1());
					paramInfoVo.setValue2(paramInfo.getValue2());
					paramInfoVo.setValue3(paramInfo.getValue3());
					paramInfoVo.setValue4(paramInfo.getValue4());
					paramInfoVo.setValue5(paramInfo.getValue5());
					paramInfoVo.setValue6(paramInfo.getValue6());
					paramInfoVo.setSortingOrder(paramInfo.getSortingOrder());
					paramInfoVo.setIsDefault(paramInfo.getIsDefault());
					paramInfoVoList.add(paramInfoVo);
				}
			}
			paramInfoVoList.sort((p1, p2) -> p1.getSortingOrder() - p2.getSortingOrder());
			PARAM_GROUP_MAP.put(paramGroup.getParamGroupCode(), new ParamGroupWrapper(paramGroupVo, paramInfoVoList));
		}

		logger.info("load Paramter loaded [{}]", PARAM_GROUP_MAP.size());
	}

	private void loadMessage() {
		logger.info("load Message loading...");
		
		MESSAGE_MAP.clear();
		
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.Message> messageList = messageRepository.findAll();
		
		MessageVo messageVo = null;
		for (th.co.baiwa.buckwaframework.preferences.persistence.entity.Message message : messageList) {
			messageVo = new MessageVo();
			messageVo.setMessageId(message.getMessageId());
			messageVo.setMessageCode(message.getMessageCode());
			messageVo.setMessageEn(message.getMessageEn());
			messageVo.setMessageTh(message.getMessageTh());
			messageVo.setMessageType (message.getMessageType());
			MESSAGE_MAP.put(messageVo.getMessageCode(), messageVo);
		}
		
		logger.info("load Message loaded [{}]", MESSAGE_MAP.size());
	}
	
	private void loadGeography() {
		logger.info("load Geography loading...");
		GEO_SECTOR_LIST.clear();
		GEO_PROVINCE_MAPPER.clear();
		GEO_AMPHUR_MAPPER.clear();
		GEO_DISTRICT_MAPPER.clear();
		
		/** Geography */
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoSector> geoSectorList = geoSectorRepository.findAll();
		GeoSectorVo sectorVo = null;
		for (th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoSector sector : geoSectorList) {
			sectorVo = new GeoSectorVo();
			sectorVo.setSectorCode(sector.getSectorCode());
			sectorVo.setSectorName(sector.getSectorName());
			GEO_SECTOR_LIST.add(sectorVo);
		}
		GEO_SECTOR_LIST.sort((p1, p2) -> p1.getSectorCode().compareTo(p2.getSectorCode()));
		
		/** Province */
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoProvince> geoProvinceList = geoProvinceRepository.findAll();
		List<GeoProvince> provinceList = null;
		GeoProvinceVo provinceVo = null;
		try {
			for (th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoProvince province : geoProvinceList) {
				provinceList = GEO_PROVINCE_MAPPER.get(province.getSectorCode());
				if (provinceList == null) {
					provinceList = new ArrayList<GeoProvince>();
				}
				provinceVo = new GeoProvinceVo();
				provinceVo.setSectorCode(province.getSectorCode());
				provinceVo.setProvinceCode(province.getProvinceCode());
				provinceVo.setProvinceName(province.getProvinceName());
				provinceList.add(provinceVo);
				GEO_PROVINCE_LIST.add(provinceVo);
				GEO_PROVINCE_MAPPER.put(provinceVo.getSectorCode(), provinceList);
			}
			GEO_PROVINCE_LIST.sort((p1, p2) -> p1.getProvinceCode().compareTo(p2.getProvinceCode()));
			GEO_PROVINCE_MAPPER.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getProvinceCode().compareTo(p2.getProvinceCode())));
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		
		/** Amphur */
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoAmphur> geoAmphurList = geoAmphurRepository.findAll();
		List<GeoAmphur> amphurList = null;
		GeoAmphurVo amphurVo = null;
		for (th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoAmphur amphur : geoAmphurList) {
			amphurList = GEO_AMPHUR_MAPPER.get(amphur.getAmphurCode().substring(0, 2));
			if (amphurList == null) {
				amphurList = new ArrayList<GeoAmphur>();
			}
			amphurVo = new GeoAmphurVo();
			amphurVo.setAmphurCode(amphur.getAmphurCode());
			amphurVo.setAmphurName(amphur.getAmphurName());
			amphurList.add(amphurVo);
			GEO_AMPHUR_LIST.add(amphurVo);
			GEO_AMPHUR_MAPPER.put(amphurVo.getAmphurCode().substring(0, 2), amphurList);
		}
		GEO_AMPHUR_LIST.sort((p1, p2) -> p1.getAmphurCode().compareTo(p2.getAmphurCode()));
		GEO_AMPHUR_MAPPER.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getAmphurCode().compareTo(p2.getAmphurCode())));
		
		/** District */
		List<th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoDistrict> geoDistricList = geoDistrictRepository.findAll();
		List<GeoDistrict> districtList = null;
		GeoDistrictVo districtVo = null;
		for (th.co.baiwa.buckwaframework.preferences.persistence.entity.GeoDistrict district : geoDistricList) {
			districtList = GEO_DISTRICT_MAPPER.get(district.getDistrictCode().substring(0, 4));
			if (districtList == null) {
				districtList = new ArrayList<GeoDistrict>();
			}
			districtVo = new GeoDistrictVo();
			districtVo.setDistrictCode(district.getDistrictCode());
			districtVo.setDistrictName(district.getDistrictName());
			districtList.add(districtVo);
			GEO_DISTRICT_LIST.add(districtVo);
			GEO_DISTRICT_MAPPER.put(districtVo.getDistrictCode().substring(0, 4), districtList);
		}
		GEO_DISTRICT_LIST.sort((p1, p2) -> p1.getDistrictCode().compareTo(p2.getDistrictCode()));
		GEO_DISTRICT_MAPPER.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getDistrictCode().compareTo(p2.getDistrictCode())));
		
		logger.info("load Geography loaded Sector[{}], Province[{}], Amphur[{}], District[{}]", GEO_SECTOR_LIST.size(), GEO_PROVINCE_LIST.size(), GEO_AMPHUR_LIST.size(), GEO_DISTRICT_LIST.size());
	}
	
	private void loadExciseDepartment() {
		logger.info("load ExciseDepartment loading...");
		
		EXCISE_DEPARTMENT_MAP.clear();
		EXCISE_SECTOR_MAP.clear();
		EXCISE_AREA_MAP.clear();
		EXCISE_BRANCH_MAP.clear();
		
		List<th.go.excise.ims.preferences.persistence.entity.ExciseDepartment> exciseDepartmentList = exciseDepartmentRepository.findAll();
		
		ExciseDepartmentVo deptVo = null;
		List<ExciseDepartment> areaList = null;
		List<ExciseDepartment> branchList = null;
		List<ExciseDepartment> cenList = null;
		for (th.go.excise.ims.preferences.persistence.entity.ExciseDepartment exciseDepartment : exciseDepartmentList) {
			deptVo = new ExciseDepartmentVo();
			toExciseDepartmentVo(deptVo, exciseDepartment);
			EXCISE_DEPARTMENT_MAP.put(deptVo.getOfficeCode(), deptVo);
			
			if ( ExciseUtils.isSector(exciseDepartment.getOffCode())) {
				deptVo = new ExciseDepartmentVo();
				toExciseDepartmentVo(deptVo, exciseDepartment);
				EXCISE_SECTOR_MAP.put(deptVo.getOfficeCode(), deptVo);
			} else if (ExciseUtils.isArea(exciseDepartment.getOffCode())) {
				areaList = EXCISE_AREA_MAP.get(exciseDepartment.getOffCode().substring(0, 2) + "0000");
				deptVo = new ExciseDepartmentVo();
				toExciseDepartmentVo(deptVo, exciseDepartment);
				if (areaList == null) {
					areaList = new ArrayList<>();
				}
				areaList.add(deptVo);
				EXCISE_AREA_MAP.put(deptVo.getOfficeCode().substring(0, 2) + "0000", areaList);
			} else if (ExciseUtils.isBranch(exciseDepartment.getOffCode())) {
				branchList = EXCISE_BRANCH_MAP.get(exciseDepartment.getOffCode().substring(0, 4) + "00");
				deptVo = new ExciseDepartmentVo();
				toExciseDepartmentVo(deptVo, exciseDepartment);
				if (branchList == null) {
					branchList = new ArrayList<>();
				}
				branchList.add(deptVo);
				EXCISE_BRANCH_MAP.put(deptVo.getOfficeCode().substring(0, 4) + "00", branchList);
			} else {
				cenList = EXCISE_CENTRAL_MAP.get(exciseDepartment.getOffCode().substring(0, 4) + "00");
				deptVo = new ExciseDepartmentVo();
				toExciseDepartmentVo(deptVo, exciseDepartment);
				if (cenList == null) {
					cenList = new ArrayList<>();
				}
				cenList.add(deptVo);
				EXCISE_CENTRAL_MAP.put(deptVo.getOfficeCode().substring(0, 4) + "00", cenList);
			}
		}
		
		// Sorting
		EXCISE_AREA_MAP.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getOfficeCode().compareTo(p2.getOfficeCode())));
		EXCISE_BRANCH_MAP.entrySet().forEach(e -> e.getValue().sort((p1, p2) -> p1.getOfficeCode().compareTo(p2.getOfficeCode())));
		
		logger.info("load ExciseDepartment Sector[{}], Area[{}], Branch[{}]", EXCISE_SECTOR_MAP.size(), EXCISE_AREA_MAP.size(), EXCISE_BRANCH_MAP.size());
	}
	
	private void toExciseDepartmentVo(ExciseDepartmentVo deptVo, th.go.excise.ims.preferences.persistence.entity.ExciseDepartment exciseDepartment) {
		deptVo.setOfficeCode(exciseDepartment.getOffCode());
		deptVo.setDeptName(exciseDepartment.getOffName());
		deptVo.setDeptShortName(exciseDepartment.getOffShortName());
	}
	
	private void loadExciseSubdept() {
		logger.info("load ExciseSubdept loading...");
		
		EXCISE_SUBDEPT_MAP.clear();
		
		List<th.go.excise.ims.preferences.persistence.entity.ExciseSubdept> exciseSubdeptList = exciseSubdeptRepository.findAll();
		
		ExciseSubdeptVo subdeptVo = null;
		List<ExciseSubdept> subdeptList = null;
		for (th.go.excise.ims.preferences.persistence.entity.ExciseSubdept exciseSubdept : exciseSubdeptList) {
			subdeptList = EXCISE_SUBDEPT_MAP.get(exciseSubdept.getOffCode());
			if (subdeptList == null) {
				subdeptList = new ArrayList<>();
			}
			subdeptVo = new ExciseSubdeptVo();
			subdeptVo.setOfficeCode(exciseSubdept.getOffCode());
			subdeptVo.setSubdeptCode(exciseSubdept.getSubdeptCode());
			subdeptVo.setSubdeptName(exciseSubdept.getSubdeptName());
			subdeptVo.setSubdeptShortName(exciseSubdept.getSubdeptShortName());
			subdeptList.add(subdeptVo);
			EXCISE_SUBDEPT_MAP.put(exciseSubdept.getOffCode(), subdeptList);
		}
	}
	
	private void loadExciseDutyGroup() {
		logger.info("load ExciseDutyGroup loading...");
		
		EXCISE_DUTY_GROUP_BY_TYPE_MAP.clear();
		EXCISE_DUTY_GROUP_MAP.clear();
		
		List<th.go.excise.ims.preferences.persistence.entity.ExciseDutyGroup> exciseDutyGroupList = exciseDutyGroupRepository.findAllByDutyGroupStatus(FLAG.N_FLAG);
		
		ExciseDutyGroupVo dutyGroupVo = null;
		List<ExciseDutyGroup> dutyGroupList = null;
		for (th.go.excise.ims.preferences.persistence.entity.ExciseDutyGroup exciseDutyGroup : exciseDutyGroupList) {
			dutyGroupList = EXCISE_DUTY_GROUP_BY_TYPE_MAP.get(exciseDutyGroup.getDutyGroupType());
			if (dutyGroupList == null) {
				dutyGroupList = new ArrayList<>();
			}
			dutyGroupVo = new ExciseDutyGroupVo();
			dutyGroupVo.setDutyGroupCode(exciseDutyGroup.getDutyGroupCode());
			dutyGroupVo.setDutyGroupName(exciseDutyGroup.getDutyGroupName());
			dutyGroupVo.setDutyGroupStatus(exciseDutyGroup.getDutyGroupStatus());
			dutyGroupVo.setDutyGroupType(exciseDutyGroup.getDutyGroupType());
			dutyGroupList.add(dutyGroupVo);
			EXCISE_DUTY_GROUP_BY_TYPE_MAP.put(exciseDutyGroup.getDutyGroupType(), dutyGroupList);
			EXCISE_DUTY_GROUP_MAP.put(exciseDutyGroup.getDutyGroupCode(), dutyGroupVo);
		}
	}
	
	private void loadExciseCtrlDuty() {
		logger.info("load ExciseCtrlDuty loading...");
		
		EXCISE_CTRL_DUTY_MAP.clear();
		
		List<th.go.excise.ims.preferences.persistence.entity.ExciseCtrlDuty> exciseCtrlDutyList = exciseCtrlDutyRepository.findAll();
		
		ExciseCtrlDutyVo ctrlDutyVo = null;
		List<ExciseCtrlDuty> ctrlDutyList = null;
		for (th.go.excise.ims.preferences.persistence.entity.ExciseCtrlDuty exciseCtrlDuty : exciseCtrlDutyList) {
			if (StringUtils.isNotEmpty(exciseCtrlDuty.getResOffcode())) {
				ctrlDutyList = EXCISE_CTRL_DUTY_MAP.get(exciseCtrlDuty.getResOffcode());
				if (ctrlDutyList == null) {
					ctrlDutyList = new ArrayList<>();
				}
				ctrlDutyVo = new ExciseCtrlDutyVo();
				ctrlDutyVo.setResOffcode(exciseCtrlDuty.getResOffcode());
				ctrlDutyVo.setDutyGroupCode(exciseCtrlDuty.getDutyGroupCode());
				ctrlDutyVo.setDutyGroupName(exciseCtrlDuty.getDutyGroupName());
				ctrlDutyList.add(ctrlDutyVo);
				EXCISE_CTRL_DUTY_MAP.put(exciseCtrlDuty.getResOffcode(), ctrlDutyList);
			}
		}
	}
	
	private void loadExciseIncMast() {
		logger.info("load loadExciseIncMast loading...");
		
		EXCISE_INC_MAST_MAP.clear();
		
		List<th.go.excise.ims.preferences.persistence.entity.ExciseIncMast> exciseIncMastList = exciseIncMastRepository.findAll();
		
		ExciseIncMastVo incMastVo = null;
		for (th.go.excise.ims.preferences.persistence.entity.ExciseIncMast exciseIncMast : exciseIncMastList) {
			incMastVo = new ExciseIncMastVo();
			incMastVo.setIncCode(exciseIncMast.getIncCode());
			incMastVo.setIncType(exciseIncMast.getIncType());
			incMastVo.setIncName(exciseIncMast.getIncName());
			incMastVo.setIncNamePrint(exciseIncMast.getIncNamePrint());
			incMastVo.setIncFlag(exciseIncMast.getIncFlag());
			incMastVo.setAccsendCode(exciseIncMast.getAccsendCode());
			incMastVo.setAccCode(exciseIncMast.getAccCode());
			incMastVo.setCdFlag(exciseIncMast.getCdFlag());
			incMastVo.setFormCode(exciseIncMast.getFormCode());
			incMastVo.setGroupId(exciseIncMast.getGroupId());
			incMastVo.setInccodCd(exciseIncMast.getInccodCd());
			incMastVo.setInccodExp(exciseIncMast.getInccodExp());
			incMastVo.setInccodFlag(exciseIncMast.getInccodFlag());
			incMastVo.setInccodOth(exciseIncMast.getInccodOth());
			incMastVo.setInccodPrnstamp(exciseIncMast.getInccodPrnstamp());
			incMastVo.setIncgrpCode(exciseIncMast.getIncgrpCode());
			incMastVo.setIncgrpFlag(exciseIncMast.getIncgrpFlag());
			incMastVo.setLoctypFlag(exciseIncMast.getLoctypFlag());
			incMastVo.setLocFlag(exciseIncMast.getLocFlag());
			incMastVo.setMoneyType(exciseIncMast.getMoneyType());
			incMastVo.setPrnstampFlag(exciseIncMast.getPrnstampFlag());
			incMastVo.setRecFlag(exciseIncMast.getRecFlag());
			EXCISE_INC_MAST_MAP.put(incMastVo.getIncCode(), incMastVo);
		}
	}

}
