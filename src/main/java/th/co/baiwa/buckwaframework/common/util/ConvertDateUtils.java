package th.co.baiwa.buckwaframework.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ConvertDateUtils {

	private static final Logger logger = LoggerFactory.getLogger(ConvertDateUtils.class);

	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMM = "yyyyMM";
	public static final String DD = "dd";
	public static final String MMM = "MMM";
	public static final String MM = "MM";
	public static final String MMMM = "MMMM";
	public static final String YYYY = "yyyy";
	public static final String MM_YYYY = "MM/yyyy";
	public static final String MMM_YYYY_SPAC = "MMM yyyy";
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String DD_MM_YYYY_DOT = "dd.MM.yyyy";
	public static final String DD_MMM_YYYY_SPAC = "dd MMM yyyy";
	public static final String DD_MMMM_YYYY_SPAC = "dd MMMM yyyy";
	public static final String DD_MM_YY = "dd/MM/yy";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DD_MM_YYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";

	public static final Locale LOCAL_TH = new Locale("th", "TH");
	public static final Locale LOCAL_EN = new Locale("en", "US");

	public static final String FULL_MONTH = "FULL_MONTH";
	public static final String SHORT_MONTH = "SHORT_MONTH";

	public static final String[] fullMonth = { "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม" };
	public static final String[] shortMonth = { "ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.", "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค." };

	public static String getMonthThai(int month, String typeMonth) {
		if (FULL_MONTH.equals(typeMonth)) {
			return fullMonth[month].toString();
		}
		if (SHORT_MONTH.equals(typeMonth)) {
			return shortMonth[month].toString();
		}
		return null;
	}

	public static String formatDateToString(Date date, String patten, Locale locale) {
		String dateString = "";
		if (date != null) {
			dateString = DateFormatUtils.format(date, patten, locale);
		}
		return dateString;
	}

	public static String formatDateToString(Date date, String patten) {
		String dateString = "";
		if (date != null) {
			dateString = DateFormatUtils.format(date, patten, LOCAL_TH);
		}
		return dateString;
	}

	public static Date parseStringToDate(String strDate, String patten, Locale locale) {
		Date dateString = null;
		try {
			if (StringUtils.isNotBlank(strDate)) {
				dateString = DateUtils.parseDate(strDate, locale, patten);
			}
		} catch (ParseException e) {
			logger.warn(e.getMessage());
		}
		return dateString;
	}

	public static Date parseStringToDate(String strDate, String patten) {
		Date dateString = null;
		try {
			if (StringUtils.isNotBlank(strDate)) {
				dateString = DateUtils.parseDate(strDate, LOCAL_TH, patten);
			}
		} catch (ParseException e) {
			logger.warn(e.getMessage());
		}
		return dateString;
	}

	// TODO local datetime, locale date

	// TODO Local data
	public static LocalDate parseStringToLocalDate(String strDate, String patten) {
		return parseLocalDate(strDate, patten, LOCAL_TH);
	}

	public static LocalDate parseStringToLocalDate(String strDate, String patten, Locale locale) {
		return parseLocalDate(strDate, patten, locale);
	}

	private static LocalDate parseLocalDate(String strDate, String patten, Locale locale) {
		if (StringUtils.isNotBlank(strDate)) {
			LocalDate localDate = LocalDate.parse(strDate, DateTimeFormatter.ofPattern(patten));
			if (LOCAL_TH.equals(locale)) {
				ThaiBuddhistDate thaiDate = ThaiBuddhistDate.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
				return LocalDate.from(thaiDate);
			} else {
				return localDate;
			}
		} else {
			return LocalDate.now();
		}
	}

	public static String formatLocalDateToString(LocalDate date, String patten) {
		if (date != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patten).withChronology(ThaiBuddhistChronology.INSTANCE);
			return date.format(formatter);
		} else {
			return "";
		}
	}

	public static String formatLocalDateToString(LocalDate date, String patten, Locale locale) {
		if (date != null) {
			if (LOCAL_TH.equals(locale)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patten).withChronology(ThaiBuddhistChronology.INSTANCE);
				return date.format(formatter);
			} else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patten);
				return date.format(formatter);
			}
		} else {
			return "";
		}
	}

	// TODO LocalDateTime
	public static LocalDateTime parseStringToLocalDateTime(String strDate, String patten) {
		return parseLocalDateTime(strDate, patten, LOCAL_TH);
	}

	public static LocalDateTime parseStringToLocalDateTime(String strDate, String patten, Locale locale) {
		return parseLocalDateTime(strDate, patten, locale);
	}

	private static LocalDateTime parseLocalDateTime(String strDate, String patten, Locale locale) {
		if (StringUtils.isNotBlank(strDate)) {
			if (LOCAL_TH.equals(locale)) {
				LocalDate localeEn = parseStringToLocalDate(strDate, patten);
				LocalTime time = LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern(patten)).toLocalTime();
				return LocalDateTime.of(localeEn, time);
			} else {
				return LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern(patten));
			}
		} else {
			return LocalDateTime.now();
		}
	}

	public static String formatLocalDateTimeToString(LocalDateTime date, String patten) {
		if (date != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patten).withChronology(ThaiBuddhistChronology.INSTANCE);
			return date.format(formatter);
		} else {
			return "";
		}
	}

	public static String formatLocalDateTimeToString(LocalDateTime date, String patten, Locale locale) {
		if (date != null) {
			if (LOCAL_TH.equals(locale)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patten).withChronology(ThaiBuddhistChronology.INSTANCE);
				return date.format(formatter);
			} else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patten);
				return date.format(formatter);
			}
		} else {
			return "";
		}
	}
	
	public static String changPaettleStringDate(String strDate , String pattenFrom , String pattenTo , Locale localeFrom , Locale localeTo) {
		Date date = parseStringToDate(strDate, pattenFrom, localeFrom);
		return formatDateToString(date, pattenTo, localeTo);
	}

}
