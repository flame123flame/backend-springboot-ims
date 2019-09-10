package th.go.excise.ims.ia.service;

import org.springframework.stereotype.Service;

@Service
public class JobLicenseListService {

//	private static final Logger logger = LoggerFactory.getLogger(JobSystemUnworking.class);
//
//	@Autowired
//	private IaCheckStatisticPaintRepository iaCheckStatisticPaintRepository;
//	
//	@Autowired
//	private IaCheckStatisticPaintJdbcRepository iaCheckStatisticPaintJdbcRepository;
//
//	@Autowired
//	private LicFri6010Service licFri6010Service;
//
//	public void runBatchLicenseList() throws IOException {
//		logger.info("Run Batch SystemUnworking ...");
//
//		LicFri6010Request request = new LicFri6010Request();
//		request.setOffcode("100300");
////		request.setYearMonthFrom("201801");
////		request.setYearMonthTo("201802");
//		request.setPageNo("0");
//		request.setDataPerPage("1000");
//		
//		for (int year = 8; year <= 9; year++) {
//            String yearString ="201"+Integer.toString(year);
//			for (int month = 1; month <= 12; month++) {
//			    String ym = yearString;
//				if(month<10){
//				    ym += "0" + Integer.toString(month);
//				} else {
//				    ym += Integer.toString(month);
//				}
//				request.setYearMonthFrom(ym);
//				request.setYearMonthTo(ym);
//				logger.info(ym);
//				List<IaCheckStatisticPaint> resList = new ArrayList<IaCheckStatisticPaint>();
//				List<LicenseList> req = licFri6010Service.postRestFul(request);
//				IaCheckStatisticPaint entity = null;
//				for (LicenseList licenseList : req) {
//					Date licDate = ConvertDateUtils.parseStringToDate(licenseList.getLicDate(), ConvertDateUtils.YYYYMMDD,ConvertDateUtils.LOCAL_EN);
//					Date expDate = ConvertDateUtils.parseStringToDate(licenseList.getLicDate(), ConvertDateUtils.YYYYMMDD,ConvertDateUtils.LOCAL_EN);
//					Date startDate = ConvertDateUtils.parseStringToDate(licenseList.getStartDate(), ConvertDateUtils.YYYYMMDD,ConvertDateUtils.LOCAL_EN);
//					Date sendDate = ConvertDateUtils.parseStringToDate(licenseList.getLicDate(), ConvertDateUtils.YYYYMMDD,ConvertDateUtils.LOCAL_EN);
//					entity = new IaCheckStatisticPaint();
//					entity.setOffcode(licenseList.getOffcode());
//					entity.setLicType(licenseList.getLicType());
//					entity.setLicCode(licenseList.getLicCode());
//					entity.setLicName(licenseList.getLicName());
//					entity.setLicFee(licenseList.getLicFee());
//					entity.setLicInterior(licenseList.getLicInterior());
//					entity.setLicPrice(licenseList.getLicPrice());
//					entity.setLicDate(licDate);
//					entity.setStartDate(startDate);
//					entity.setExpDate(expDate);
//					entity.setSendDate(sendDate);
//					entity.setPrintCount(licenseList.getPrintCount());
//					entity.setNid(licenseList.getNid());
//					entity.setNewregId(licenseList.getNewregId());
//					entity.setCusFullName(licenseList.getCusFullName());
//					entity.setFacFullName(licenseList.getFacFullName());
//					resList.add(entity);
////					iaCheckStatisticPaintRepository.save(entity);
//				}
//				iaCheckStatisticPaintJdbcRepository.batchInsert(resList);
//			}
//		}
//		List<LicenseList> req = licFri6010Service.postRestFul(request);
//		IaCheckStatisticPaint entity = null;
//		for (LicenseList licenseList : req) {
//			Date licDate = ConvertDateUtils.parseStringToDate(licenseList.getLicDate(), ConvertDateUtils.DD_MM_YYYY);
//			Date expDate = ConvertDateUtils.parseStringToDate(licenseList.getLicDate(), ConvertDateUtils.DD_MM_YYYY);
//			Date sendDate = ConvertDateUtils.parseStringToDate(licenseList.getLicDate(), ConvertDateUtils.DD_MM_YYYY);
//			entity = new IaCheckStatisticPaint();
//			entity.setOffcode(licenseList.getOffcode());
//			entity.setLicType(licenseList.getLicType());
//			entity.setLicCode(licenseList.getLicCode());
//			entity.setLicName(licenseList.getLicName());
//			entity.setLicFee(licenseList.getLicFee());
//			entity.setLicInterior(licenseList.getLicInterior());
//			entity.setLicPrice(licenseList.getLicPrice());
//			entity.setLicDate(licDate);
//			entity.setStartDate(licenseList.getStartDate());
//			entity.setExpDate(expDate);
//			entity.setSendDate(sendDate);
//			entity.setPrintCount(licenseList.getPrintCount());
//			entity.setNid(licenseList.getNid());
//			entity.setNewregId(licenseList.getNewregId());
//			entity.setCusFullName(licenseList.getCusFullName());
//			entity.setFacFullName(licenseList.getFacFullName());
//
//			iaCheckStatisticPaintRepository.save(entity);
//		}
		
		
//		if(req!=null&&req.getData().size()>0) {
//			for (DataList element : req.getData()) {
//				entity = new IaRiskSystemUnworking();
//				
//				entity.setYear(year);
//				entity.setMonth(month);
//				entity.setStatus(req.getStatus());
//				
//				entity.setSystemCode(element.getSystemCode());
//				entity.setSystemName(element.getSystemName());
//				entity.setCountAll(element.getCountAll());
//				entity.setCountNormal(element.getCountNormal());
//				entity.setCountError(element.getCountError());
//				entity.setStartDate(element.getStartDate());
//				entity.setEndDate(element.getEndDate());
//				
//				
//				iaRiskSystemUnworkingRepository.save(entity);
//			}
//		}

//	}

}
