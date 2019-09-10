package th.go.excise.ims.ia.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.IaJdbcRepository;

@Service
public class IaCommonService {

	@Autowired
	private IaJdbcRepository iaJdbcRepository;

	public long findSeqBySeqName(String seqName) {
		return iaJdbcRepository.findOracleSeqBySeqName(seqName);
	}

	/**
	 *********************** example *************************
	 * prefix = "P" or "p" 
	 * OfficeCode = "010100" 
	 * seqName = "YOUR_SEQUENCE_SEQ" 
	 * length = 8 (length of leftpad default 0)
	 ************** result after call method ***************** 
	 * P-010100/00000001
	 **/
	public String autoGetRunAuditNoBySeqName(String prefix, String OfficeCode, String seqName, int length) {
		return StringUtils.upperCase(prefix).concat("-").concat(OfficeCode).concat("/").concat(
				StringUtils.leftPad(String.valueOf(iaJdbcRepository.findOracleSeqBySeqName(seqName)), length, "0"));
	}
}
