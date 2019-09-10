package th.go.excise.ims.common.util;

import org.junit.Test;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConvertDateUtilsTest {

    @Test
    public void TestConvertDate() {
        LocalDate date1 = ConvertDateUtils.parseStringToLocalDate("12/03/2562", ConvertDateUtils.DD_MM_YYYY);
        LocalDate date2En = ConvertDateUtils.parseStringToLocalDate("12/03/2019", ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
        LocalDate date2Th = ConvertDateUtils.parseStringToLocalDate("12/03/2562", ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
        System.out.println("==========================parseStringToLocalDate========================");
        System.out.println("parseStringToLocalDate {StrDate} {Patten}: " + date1);
        System.out.println("parseStringToLocalDate {StrDate} {Patten} {Locale}: " + date2En);
        System.out.println("parseStringToLocalDate {StrDate} {Patten} {Locale}: " + date2Th);

        String str1 = ConvertDateUtils.formatLocalDateToString(LocalDate.now(), ConvertDateUtils.DD_MM_YYYY);
        String str2En = ConvertDateUtils.formatLocalDateToString(LocalDate.now(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
        String str2Th = ConvertDateUtils.formatLocalDateToString(LocalDate.now(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
        System.out.println("\n==========================formatLocalDateToString========================");
        System.out.println("formatLocalDateToString {StrDate} {Patten}: " + str1);
        System.out.println("formatLocalDateToString {StrDate} {Patten} {Locale}: " + str2En);
        System.out.println("formatLocalDateToString {StrDate} {Patten} {Locale}: " + str2En);
        System.out.println("formatLocalDateToString {StrDate} {Patten} {Locale}: " + str2Th);

        LocalDateTime dateTime1 = ConvertDateUtils.parseStringToLocalDateTime("12/03/2562 20:20:34", ConvertDateUtils.DD_MM_YYYY_HHMMSS);
        LocalDateTime dateTime2En = ConvertDateUtils.parseStringToLocalDateTime("12/03/2019 20:20:34", ConvertDateUtils.DD_MM_YYYY_HHMMSS, ConvertDateUtils.LOCAL_EN);
        LocalDateTime dateTime2Th = ConvertDateUtils.parseStringToLocalDateTime("12/03/2562 20:20:34", ConvertDateUtils.DD_MM_YYYY_HHMMSS, ConvertDateUtils.LOCAL_TH);
        System.out.println("\n==========================parseStringToLocalDateTime========================");
        System.out.println("parseStringToLocalDate {StrDate} {Patten}: " + dateTime1);
        System.out.println("parseStringToLocalDate {StrDate} {Patten} {Locale}: " + dateTime2En);
        System.out.println("parseStringToLocalDate {StrDate} {Patten} {Locale}: " + dateTime2Th);

        String strdateTime1 = ConvertDateUtils.formatLocalDateTimeToString(LocalDateTime.now(), ConvertDateUtils.DD_MM_YYYY);
        String strdateTime2En = ConvertDateUtils.formatLocalDateTimeToString(LocalDateTime.now(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
        String strdateTime2Th = ConvertDateUtils.formatLocalDateTimeToString(LocalDateTime.now(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
        System.out.println("\n==========================formatLocalDateTimeToString========================");
        System.out.println("formatLocalDateToString {StrDate} {Patten}: " + str1);
        System.out.println("formatLocalDateToString {StrDate} {Patten} {Locale}: " + strdateTime1);
        System.out.println("formatLocalDateToString {StrDate} {Patten} {Locale}: " + strdateTime2En);
        System.out.println("formatLocalDateToString {StrDate} {Patten} {Locale}: " + strdateTime2Th);
    }
}
