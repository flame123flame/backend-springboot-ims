package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFileUpload;
import th.go.excise.ims.ta.persistence.repository.TaFileUploadRepository;
import th.go.excise.ims.ta.vo.FileUploadFormVo;
import th.go.excise.ims.ta.vo.FileUploadVo;

@Service
public class TaFileUploadService {

	private static final Logger logger = LoggerFactory.getLogger(TaFileUploadService.class);

	@Value("${app.path.upload}")
	private String uploadPath;

	private static final String TA_DIC = "ta/";

	@Autowired
	private TaFileUploadSequenceService taFileUploadSequenceService;
	@Autowired
	private TaFileUploadRepository taFileUploadRepository;

	@Transactional(rollbackOn = { Exception.class })
	public String upload(FileUploadFormVo formVo) throws IOException {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = null;
		if (StringUtils.isNotEmpty(formVo.getBudgetYear())) {
			budgetYear = formVo.getBudgetYear();
		} else {
			budgetYear = ExciseUtils.getCurrentBudgetYear();
		}
		String uploadFileNumber = taFileUploadSequenceService.getUploadNumber(officeCode, budgetYear);
		logger.info("upload moduleCode={}, refNo={}, fileName={}, uploadFileNumber={}", formVo.getModuleCode(), formVo.getRefNo(), formVo.getFile().getOriginalFilename(), uploadFileNumber);

		formVo.setUploadNo(uploadFileNumber);
		String fullUploadFileName = writeUploadFile(formVo);

		TaFileUpload entity = new TaFileUpload();
		entity.setUploadNo(uploadFileNumber);
		entity.setModuleCode(formVo.getModuleCode());
		entity.setRefNo(formVo.getRefNo());
		entity.setFilePath(FilenameUtils.getPath(fullUploadFileName));
		entity.setFileName(formVo.getFile().getOriginalFilename());
		taFileUploadRepository.save(entity);

		return uploadFileNumber;
	}

	private String writeUploadFile(FileUploadFormVo formVo) throws IOException {
		logger.info("writeUploadFile");

		String orgFileName = formVo.getFile().getOriginalFilename();
		String fullUploadFilePath = uploadPath + TA_DIC;
		String fullUploadFileName = fullUploadFilePath + formVo.getUploadNo() + "." + FilenameUtils.getExtension(orgFileName);

		File fullUploadFileDic = new File(fullUploadFilePath);
		if (fullUploadFileDic.mkdirs()) {
			logger.info("Create Path={}", fullUploadFileDic.getAbsolutePath());
		}

		FileCopyUtils.copy(formVo.getFile().getInputStream(), new FileOutputStream(new File(fullUploadFileName)));

		return fullUploadFileName;
	}

	public List<FileUploadVo> getUploadFileList(FileUploadFormVo formVo) {
		logger.info("getUploadFileList refNo={}", formVo.getRefNo());
		List<FileUploadVo> voList = taFileUploadRepository.findVoListByCriteria(formVo);
		return voList;
	}

	@Transactional(rollbackOn = { Exception.class })
	public void deleteUploadFile(String uploadNumber) {
		logger.info("getUploadFile uploadNumber={}", uploadNumber);

		TaFileUpload taFileUpload = taFileUploadRepository.findByUploadNo(uploadNumber);
		String fullDeleteFilePath = File.separator + taFileUpload.getFilePath() + taFileUpload.getUploadNo() + "." + FilenameUtils.getExtension(taFileUpload.getFileName());

		File file = new File(fullDeleteFilePath);
		logger.info("fullDeleteFilePath={}", fullDeleteFilePath);
		if (file.delete()) {
			logger.info("Delete File Success");
		}

		taFileUploadRepository.deleteByUploadNo(uploadNumber);

	}

	public FileUploadVo getUploadFile(String uploadNumber) throws IOException {
		logger.info("getUploadFile uploadNumber={}", uploadNumber);

		TaFileUpload taFileUpload = taFileUploadRepository.findByUploadNo(uploadNumber);
		String downloadFileName = taFileUpload.getUploadNo() + "." + FilenameUtils.getExtension(taFileUpload.getFileName());

		FileUploadVo vo = new FileUploadVo();
		vo.setUploadNo(taFileUpload.getUploadNo());
		vo.setModuleCode(taFileUpload.getModuleCode());
		vo.setRefNo(taFileUpload.getRefNo());
		vo.setFileName(taFileUpload.getFileName());
		vo.setBytes(FileCopyUtils.copyToByteArray(new File(uploadPath + TA_DIC + downloadFileName)));

		return vo;
	}

}
