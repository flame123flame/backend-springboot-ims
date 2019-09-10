package th.co.baiwa.buckwaframework.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import th.go.excise.ims.preferences.persistence.repository.ExciseHolidayRepository;

@Component
public class LocalDateUtils {
	
	private static ExciseHolidayRepository exciseHolidayRepository;
	
	@Autowired
	public LocalDateUtils(ExciseHolidayRepository exciseHolidayRepository) {
		LocalDateUtils.exciseHolidayRepository = exciseHolidayRepository;
	}
	
	public static List<LocalDate> getLocalDateRange(LocalDate dateStart, LocalDate dateEnd) {
		long numOfDaysBetween = ChronoUnit.DAYS.between(dateStart, dateEnd);
		List<LocalDate> dateList = IntStream.iterate(0, i -> i + 1)
			.limit(numOfDaysBetween + 1)
			.mapToObj(i -> {
				LocalDate nextLocalDate = dateStart.plusDays(i);
				return LocalDate.of(nextLocalDate.getYear(), nextLocalDate.getMonth(), 1);
			})
			.distinct()
			.collect(Collectors.toList());
		return dateList;
	}
	
	public static boolean isRange(LocalDate dateStart, LocalDate dateEnd, LocalDate localDate) {
		return (localDate.isEqual(dateStart) || localDate.isAfter(dateStart))
			&& (localDate.isEqual(dateEnd) || localDate.isBefore(dateEnd));
	}
	
	public static LocalDate thaiMonthYear2LocalDate(String inputDate) {
		String[] tmp = inputDate.split("/");
		return LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(tmp[1]), Integer.parseInt(tmp[0]), 1));
	}
	
	public static LocalDate thaiDateSlash2LocalDate(String inputDate) {
		String[] tmp = inputDate.split("/");
		return LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(tmp[2]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[0])));
	}
	
	public static List<LocalDate> getLocalDateListWithoutHolodays(LocalDate dateStart, LocalDate dateEnd) {
		long numOfDaysBetween = ChronoUnit.DAYS.between(dateStart, dateEnd);
		List<LocalDate> holidayList = exciseHolidayRepository.findByDateRange(dateStart, dateEnd).stream()
			.map(holiday -> {
				return holiday.getHolidayDate();
			})
			.collect(Collectors.toList());
		List<LocalDate> dateList = IntStream.iterate(0, i -> i + 1)
			.limit(numOfDaysBetween + 1)
			.mapToObj(i -> {
				return dateStart.plusDays(i);
			})
			.distinct()
			.filter(d -> {
				return d.getDayOfWeek() != DayOfWeek.SATURDAY && d.getDayOfWeek() != DayOfWeek.SUNDAY && !holidayList.contains(d);
			})
			.collect(Collectors.toList());
		return dateList;
	}
	
}
