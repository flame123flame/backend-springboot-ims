package th.co.baiwa.buckwaframework.common.util;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;


public class LocalTimeConverter{
	
	public static Time convertToDatabaseColumn(LocalTime localTime) {
		return Optional.ofNullable(localTime)
			.map(Time::valueOf)
			.orElse(null);
	}
	
	public static LocalTime convertToEntityAttribute(Time time) {
		return Optional.ofNullable(time)
			.map(Time::toLocalTime)
			.orElse(null);
	}
	
}
