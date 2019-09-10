package th.co.baiwa.buckwaframework.common.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class LocalDateTimeConverter{
	
	public static Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
		return Optional.ofNullable(localDateTime)
			.map(Timestamp::valueOf)
			.orElse(null);
	}
	
	public static LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
		return Optional.ofNullable(timestamp)
			.map(Timestamp::toLocalDateTime)
			.orElse(null);
	}
	
}
