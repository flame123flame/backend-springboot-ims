package th.go.excise.ims.ta.persistence.repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaWorksheetHdrRepositoryTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TaWorksheetHdrRepositoryTest.class);
	@Autowired
	private TaWorksheetHdrRepository taWorksheetHdrRepository;
	
	@Test
	public void testFindBySubCondition() {
		
//		try {
//			List<TaWorksheetHdr> taWorksheetHdrList = taWorksheetHdrRepository.findSubConditionRegCapital(BigDecimal.valueOf(19000), null);
//			for (TaWorksheetHdr taWorksheetHdr : taWorksheetHdrList) {
//				logger.info(taWorksheetHdr.getNewRegId());
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
