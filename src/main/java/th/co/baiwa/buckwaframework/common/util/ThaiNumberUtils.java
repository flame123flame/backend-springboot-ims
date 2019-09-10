package th.co.baiwa.buckwaframework.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

public class ThaiNumberUtils {
	
	private static final String DECIMAL_PATTERN = "0.00";
	
	private static final String[] DIGIT_TH = { "", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า", "สิบ" };

	private static final String[] SCALE_TH = { "", "", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน" };
	
	private static final String[] SYMBOLS_TH = { "ลบ", "บาท", "ถ้วน", "สตางค์" ,"เอ็ด", "ยี่","ศูนย์" };
	
	private static final String[] NUMBER_TH = { "๑", "๒", "๓", "๔" ,"๕", "๖", "๗", "๘", "๙", "๐" };
	
	private static final String[] NUMBER_EN = { "1", "2", "3", "4" ,"5", "6", "7", "8", "9", "0" };

	private static String getWord(String input) {

		StringBuilder word = new StringBuilder();
		String stang;
		String baht;
		char fristBaht;
		int idx = input.indexOf(".");

		baht = input.substring(0, idx);
		fristBaht = baht.charAt(0);
		stang = input.substring(idx + 1, input.length());

		if (fristBaht != '0') {
			int len = (baht.length() - 1) / 6;
			for (int i = 0; i <= len; i++) {
				if (i < len) {
					word = addBaht(baht.substring(0, baht.length() - ((len - i) * 6)), word);
					baht = baht.substring(baht.length() - ((len - i) * 6));
					if (i < len) {
						word.append(SCALE_TH[7]);
					}
				} else {
					addBaht(baht, word);
				}
			}
			word.append(SYMBOLS_TH[1]);
		}
		else if(fristBaht == '0' && stang.equals("00")){
			word.append(SYMBOLS_TH[6]);
			word.append(SYMBOLS_TH[1]);
		}
		
		
		if ( stang.equals("01")) {
			word.append(DIGIT_TH[1]);
			word.append(SYMBOLS_TH[3]);
		}
		else if (!stang.equals("00")) {
			word = addBaht(stang, word);
			word.append(SYMBOLS_TH[3]);
		}
		
		else {
			//word.append("ถ้วน");
		}

		return word.toString();
	}

	private static StringBuilder addBaht(String str, StringBuilder word) {
		int countDown = str.length();
		int idxInc = 0;
		while (countDown > 0) {
			char g = str.charAt(idxInc);
			if (g != '0') {
				word = increBaht(g, countDown, idxInc, word);
			}
			countDown--;
			idxInc++;
		}
		return word;
	}

	private static StringBuilder increBaht(char g, int countDown, int index, StringBuilder word) {

		switch (g) {
		case '1':
			if (countDown == 1 && index > 0) {
				word.append(SYMBOLS_TH[4]);
			} else if (countDown > 2 || countDown == 1) {
				word.append(DIGIT_TH[1]);
			}
			break;
		case '2':
			if (countDown != 2) {
				word.append(DIGIT_TH[2]);
			} else {
				word.append(SYMBOLS_TH[5]);
			}
			break;
		default:
			word.append(DIGIT_TH[(int) g - 48]);
			break;
		}
		word.append(SCALE_TH[countDown]);

		return word;
	}

	public static String toThaiBaht(String input) {
		DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_PATTERN);
		return getWord(decimalFormat.format(new BigDecimal(input)));
	}
	
	public static String toThaiNumber(String input) {
		return StringUtils.replaceEach(input, NUMBER_EN, NUMBER_TH);
	}
	
}