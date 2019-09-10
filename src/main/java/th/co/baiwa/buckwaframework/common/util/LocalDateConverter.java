package th.co.baiwa.buckwaframework.common.util;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public class LocalDateConverter {
	
	public static Date convertToDatabaseColumn(LocalDate localDate) {
		return Optional.ofNullable(localDate)
			.map(Date::valueOf)
			.orElse(null);
	}
	
	public static LocalDate convertToEntityAttribute(Date date) {
		return Optional.ofNullable(date)
			.map(Date::toLocalDate)
			.orElse(null);
	}
	
}
