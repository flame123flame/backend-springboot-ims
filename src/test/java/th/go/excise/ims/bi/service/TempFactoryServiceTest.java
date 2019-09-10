package th.go.excise.ims.bi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.monitorjbl.xlsx.StreamingReader;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.Application;
import th.go.excise.ims.bi.persistence.entity.TempFactory;
import th.go.excise.ims.bi.persistence.repository.TempFactoryJdbcRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TempFactoryServiceTest {
	
	@Autowired
	private TempFactoryJdbcRepository tempFactoryJdbcRepository;
	
	@Test
	public void test_import() {
		String filePath = "E:\\Users\\SU\\Desktop\\data_bi\\TEMP_FACTORY.xlsx";
		List<TempFactory> tempFactoryList = new ArrayList<>();
		
		try (
			InputStream is = new FileInputStream(new File(filePath));
			Workbook workbook = StreamingReader.builder()
				.rowCacheSize(100)
				.bufferSize(4096)
				.open(is);) {
			DataFormatter dataFormatter = new DataFormatter();
			TempFactory tempFactory = null;
			String cellValue = null;
			
			Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
				tempFactory = new TempFactory();
				for (Cell cell : row) {
					cellValue = dataFormatter.formatCellValue(cell);
					if (cell.getColumnIndex() == 1) {
						tempFactory.setFacId(cellValue);
					} else if (cell.getColumnIndex() == 2) {
						tempFactory.setCusId(cellValue);
					} else if (cell.getColumnIndex() == 3) {
						tempFactory.setFactoryName(cellValue);
					} else if (cell.getColumnIndex() == 4) {
						tempFactory.setCapital(NumberUtils.toBigDecimal(cellValue));
					} else if (cell.getColumnIndex() == 5) {
						tempFactory.setEmpTot(cellValue);
					} else if (cell.getColumnIndex() == 6) {
						tempFactory.setFacAddrseq(cellValue);
					} else if (cell.getColumnIndex() == 7) {
						tempFactory.setCusAddrseq(cellValue);
					} else if (cell.getColumnIndex() == 8) {
						tempFactory.setFacSeq(cellValue);
					} else if (cell.getColumnIndex() == 9) {
						tempFactory.setCusSeq(cellValue);
					}
				}
				tempFactoryList.add(tempFactory);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tempFactoryJdbcRepository.batchInsert(tempFactoryList);
	}
	
}
