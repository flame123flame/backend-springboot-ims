package th.go.excise.ims.bi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import th.go.excise.ims.bi.persistence.entity.TempRegist;
import th.go.excise.ims.bi.persistence.repository.TempRegistJdbcRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TempRegistServiceTest {
	
	@Autowired
	private TempRegistJdbcRepository tempRegistJdbcRepository;
	
	@Test
	public void test_import() {
		String filePath = "E:\\Users\\SU\\Desktop\\data_bi\\TEMP_REGIST.xlsx";
		List<TempRegist> tempRegistList = new ArrayList<>();
		
		try (
			InputStream is = new FileInputStream(new File(filePath));
			Workbook workbook = StreamingReader.builder()
				.rowCacheSize(100)
				.bufferSize(4096)
				.open(is);) {
			DataFormatter dataFormatter = new DataFormatter();
			TempRegist tempRegist = null;
			String cellValue = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm:ss", Locale.US);
			
			Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
				tempRegist = new TempRegist();
				for (Cell cell : row) {
					cellValue = dataFormatter.formatCellValue(cell);
					if (cell.getColumnIndex() == 1) {
						tempRegist.setRegId(cellValue);
					} else if (cell.getColumnIndex() == 2) {
						tempRegist.setNewregId(cellValue);
					} else if (cell.getColumnIndex() == 3) {
						tempRegist.setFacId(cellValue);
					} else if (cell.getColumnIndex() == 4) {
						tempRegist.setFacSeq(cellValue);
					} else if (cell.getColumnIndex() == 5) {
						tempRegist.setFacAddrseq(cellValue);
					} else if (cell.getColumnIndex() == 6) {
						tempRegist.setCusSeq(cellValue);
					} else if (cell.getColumnIndex() == 7) {
						tempRegist.setCusAddrseq(cellValue);
					} else if (cell.getColumnIndex() == 8) {
						tempRegist.setDivcode(cellValue);
					} else if (cell.getColumnIndex() == 9) {
						tempRegist.setOffcode(cellValue);
					} else if (cell.getColumnIndex() == 10) {
						tempRegist.setRegReason(cellValue);
					} else if (cell.getColumnIndex() == 11) {
						tempRegist.setRegStatus(cellValue);
					} else if (cell.getColumnIndex() == 12) {
						tempRegist.setStatusDate(LocalDate.parse(dateFormat.format(cell.getDateCellValue()), DateTimeFormatter.BASIC_ISO_DATE));
					} else if (cell.getColumnIndex() == 13) {
						tempRegist.setActiveFlag(cellValue);
					} else if (cell.getColumnIndex() == 14) {
						tempRegist.setUpdDate(LocalDate.parse(cellValue, dateFormatter));
					} else if (cell.getColumnIndex() == 15) {
						tempRegist.setCusId(cellValue);
					}
				}
				tempRegistList.add(tempRegist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tempRegistJdbcRepository.batchInsert(tempRegistList);
	}
	
}
