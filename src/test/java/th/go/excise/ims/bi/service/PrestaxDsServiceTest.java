package th.go.excise.ims.bi.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.bi.persistence.repository.PrestaxDsJdbcRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class PrestaxDsServiceTest {
	
	@Autowired
	private PrestaxDsJdbcRepository prestaxDsJdbcRepository;
	
	@Test
	public void test_import() throws Exception {
		File file = new File("E://tmp//dataBi//prestax_ds.tsv");
		
		System.out.println("Before Read File");
//		List<String> lineList = FileUtils.readLines(file, "UTF-8");
//		lineList.remove(0);
		
		String line = null;
		List<String> lineList = new ArrayList<>();
		
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");
		try {
			int i = 0;
			while (it.hasNext()) {
				if (i == 0) {
					i++;
					continue;
				}
				line = it.nextLine();
				lineList.add(line);
//				if (i == 50000) {
//					break;
//				}
				i++;
			}
		} finally {
			LineIterator.closeQuietly(it);
		}
		
		System.out.println("After Read File, File size=" + lineList.size());
		
		prestaxDsJdbcRepository.batchInsert(lineList);
	}
	
}
