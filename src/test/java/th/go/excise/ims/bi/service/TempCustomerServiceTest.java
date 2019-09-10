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
import th.go.excise.ims.Application;
import th.go.excise.ims.bi.persistence.entity.TempCustomer;
import th.go.excise.ims.bi.persistence.repository.TempCustomerJdbcRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TempCustomerServiceTest {
	
	@Autowired
	private TempCustomerJdbcRepository tempCustomerJdbcRepository;
	
	@Test
	public void test_import() {
		String filePath = "E:\\Users\\SU\\Desktop\\data_bi\\TEMP_CUSTOMER.xlsx";
		List<TempCustomer> tempCustomerList = new ArrayList<>();
		
		try (
			InputStream is = new FileInputStream(new File(filePath));
			Workbook workbook = StreamingReader.builder()
				.rowCacheSize(100)
				.bufferSize(4096)
				.open(is);) {
			DataFormatter dataFormatter = new DataFormatter();
			TempCustomer tempCustomer = null;
			String cellValue = null;
			
			Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
				tempCustomer = new TempCustomer();
				for (Cell cell : row) {
					cellValue = dataFormatter.formatCellValue(cell);
					if (cell.getColumnIndex() == 1) {
						tempCustomer.setCusId(cellValue);
					} else if (cell.getColumnIndex() == 2) {
						tempCustomer.setCustomerName(cellValue);
					} else if (cell.getColumnIndex() == 3) {
						tempCustomer.setTin(cellValue);
					} else if (cell.getColumnIndex() == 4) {
						tempCustomer.setPinnitId(cellValue);
					}
				}
				tempCustomerList.add(tempCustomer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tempCustomerJdbcRepository.batchInsert(tempCustomerList);
	}
	
}
