package th.go.excise.ims.bi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
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
import th.go.excise.ims.bi.persistence.entity.TempRegDuty;
import th.go.excise.ims.bi.persistence.repository.TempRegDutyJdbcRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TempRegDutyServiceTest {
	
	@Autowired
	private TempRegDutyJdbcRepository tempRegDutyJdbcRepository;
	
	@Test
	public void test_import() {
		String filePath = "E:\\Users\\SU\\Desktop\\data_bi\\TEMP_REG_DUTY.xlsx";
		List<TempRegDuty> tempRegDutyList = new ArrayList<>();
		
		try (
			InputStream is = new FileInputStream(new File(filePath));
			Workbook workbook = StreamingReader.builder()
				.rowCacheSize(100)
				.bufferSize(4096)
				.open(is);) {
			DataFormatter dataFormatter = new DataFormatter();
			TempRegDuty tempRegDuty = null;
			String cellValue = null;
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.US);
			
			Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
				tempRegDuty = new TempRegDuty();
				for (Cell cell : row) {
					cellValue = dataFormatter.formatCellValue(cell);
					if (cell.getColumnIndex() == 0) {
						tempRegDuty.setNewregId(cellValue);
					} else if (cell.getColumnIndex() == 1) {
						tempRegDuty.setRegId(cellValue);
					} else if (cell.getColumnIndex() == 2) {
						tempRegDuty.setGroupId(cellValue);
					} else if (cell.getColumnIndex() == 3) {
						tempRegDuty.setPayrtnFlag(cellValue);
					} else if (cell.getColumnIndex() == 4) {
						tempRegDuty.setFactypeOil(StringUtils.trim(cellValue));
					} else if (cell.getColumnIndex() == 5) {
						cellValue = cellValue.substring(0, 4) + cellValue.substring(4, 6).toLowerCase() + cellValue.substring(6);
						tempRegDuty.setRegDate(LocalDate.parse(cellValue, dateFormatter));
					} else if (cell.getColumnIndex() == 6) {
						cellValue = cellValue.substring(0, 4) + cellValue.substring(4, 6).toLowerCase() + cellValue.substring(6);
						tempRegDuty.setProdDate(LocalDate.parse(cellValue, dateFormatter));
					} else if (cell.getColumnIndex() == 7) {
						tempRegDuty.setActiveFlag(cellValue);
					}
				}
				tempRegDutyList.add(tempRegDuty);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tempRegDutyJdbcRepository.batchInsert(tempRegDutyList);
	}
	
}
