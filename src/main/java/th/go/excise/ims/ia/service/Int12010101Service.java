package th.go.excise.ims.ia.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaStampDetail;
import th.go.excise.ims.ia.persistence.entity.IaStampFile;
import th.go.excise.ims.ia.persistence.entity.IaStampGenre;
import th.go.excise.ims.ia.persistence.entity.IaStampType;
import th.go.excise.ims.ia.persistence.repository.IaStampDetailRepository;
import th.go.excise.ims.ia.persistence.repository.IaStampFileRepository;
import th.go.excise.ims.ia.persistence.repository.IaStampGenreRepository;
import th.go.excise.ims.ia.persistence.repository.IaStampTypeRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaStampTypeJdbcRepository;
import th.go.excise.ims.ia.vo.ExciseFile;
import th.go.excise.ims.ia.vo.Int12010101Vo;


@Service
public class Int12010101Service {

	private Logger logger = LoggerFactory.getLogger(Int12010101Service.class);
	
	@Autowired
	private IaStampDetailRepository iaStamDetailRepository;
	
	@Autowired
	private IaStampFileRepository iaStampFileRepository;
	
	@Autowired
    private IaStampTypeJdbcRepository iaStampTypeJdbcRepository;

	@Autowired
    private IaStampGenreRepository iaStamGenreRepository;
	
    @Value("${app.path.upload}")
    private String pathed;

	
	@Transactional
	public void save(List<Int12010101Vo> formVos) {
        for (Int12010101Vo form:formVos) {           
        	
        	/*upload file*/
        	uploadFile("FileUpload",form.getFile());
            IaStampDetail entity = new IaStampDetail();
            
            /*get sector*/
            String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();          
            entity.setOfficeCode(officeCode);     
                        
//            Lov lov = lovRepository.findBySubType(officeCode);
//            if (lov != null) {
//				entity.setOfficeDesc(lov.getSubTypeDescription());
//			}
            /* set sector area and branch*/
            entity.setDateOfPay(ConvertDateUtils.parseStringToDate(form.getDateOfPay(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH)); 
            entity.setDepartmentName(form.getDepartmentName());
            entity.setStatus(form.getStatus());            
            entity.setBookNumberWithdrawStamp(form.getBookNumberWithdrawStamp());
            entity.setDateWithdrawStamp(ConvertDateUtils.parseStringToDate(form.getDateWithdrawStamp(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
            entity.setBookNumberDeliverStamp(form.getBookNumberDeliverStamp());
            entity.setDateDeliverStamp(ConvertDateUtils.parseStringToDate(form.getDateDeliverStamp(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
            entity.setFivePartNumber(form.getFivePartNumber());
            entity.setFivePartDate(ConvertDateUtils.parseStringToDate(form.getFivePartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
            entity.setStampCheckDate(ConvertDateUtils.parseStringToDate(form.getStampCheckDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
            entity.setStampChecker(form.getStampChecker());
            entity.setStampChecker2(form.getStampChecker2());
            entity.setStampChecker3(form.getStampChecker3());
            entity.setStampType(form.getStampType());
            entity.setStampBrand(form.getStampBrand());
            entity.setNumberOfBook(form.getNumberOfBook());
            BigDecimal numberOfStamp = new BigDecimal(form.getNumberOfStamp());
            entity.setNumberOfStamp(numberOfStamp);
            entity.setValueOfStampPrinted(form.getValueOfStampPrinted());
            entity.setSumOfValue(form.getSumOfValue());
            entity.setTaxStamp(form.getTaxStamp());
            entity.setStampCodeStart(form.getStampCodeStart());
            entity.setStampCodeEnd(form.getStampCodeEnd());
            entity.setNote(form.getNote());
            entity.setCreatedDate(ConvertDateUtils.parseStringToLocalDateTime(form.getCreatedDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
            BigDecimal stampTypeId = new BigDecimal(form.getStampTypeId());
            entity.setStampTypeId(stampTypeId);
            BigDecimal stampBrandId = new BigDecimal(form.getStampBrandId());
            entity.setStampBrandId(stampBrandId);
            IaStampDetail detailId = iaStamDetailRepository.save(entity);
            
            /*insert table file*/
            List<IaStampFile> listFile = new ArrayList<IaStampFile>();
            if (form.getFile()!=null) {
            	for ( ExciseFile file : form.getFile()) {
                	IaStampFile fileEntity = new IaStampFile();
                	fileEntity.setDetailId(detailId.getWorkSheetDetailId());
                	fileEntity.setFileName(file.getName());  
                	listFile.add(fileEntity);
    			}
            	iaStampFileRepository.saveAll(listFile);
			}
            
        }
	}
	
	public List<IaStampType> stamTypes(){
		
        return iaStampTypeJdbcRepository.getDataAll();
    }

    public List<IaStampGenre> stamGenres(String stamTypeId){
	    return iaStamGenreRepository.findByStampTypeId(stamTypeId);
    }
	
	
	 public void uploadFile(String exciseId, ExciseFile[] files){
	    	if (files !=null) {
	    		ArrayList<ExciseFile> file = new ArrayList<>();
	            for(ExciseFile fs: files) {
	                if (fs.getName() != null) {
	                    file.add(fs);
	                }
	            }
	            File f = new File(pathed + exciseId); // initial file (folder)
	            if (!f.exists()) { // check folder exists
	                if (f.mkdirs()) {
	                    logger.info("Directory is created!");
	                } else {
	                    logger.error("Failed to create directory!");
	                }
	            }
	            
	            for(ExciseFile fi: file) {
	        		Date in = new Date(); // current date
	            	String ext =  FilenameUtils.getExtension(fi.getType()); // get extension
	        		byte[] data = Base64.getDecoder().decode(fi.getValue().split(",")[1]); // get data from base64
	        		
	        		// set path
	        		String path = pathed + exciseId + "/" + fi.getName().toUpperCase() + '-';
	        		path += new SimpleDateFormat("dd-MM-yyyy").format(in) + "." + ext;
	        		
	                try (OutputStream stream = new FileOutputStream(path)) {
	        		    stream.write(data);
	            		logger.info("Created file: " + path);
	        		} catch (Exception e) {
	        			e.printStackTrace();
	        		}
	            }
			}
	        
	    }
}
