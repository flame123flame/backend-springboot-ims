package th.co.baiwa.buckwaframework.common.persistence.util;

import org.springframework.data.domain.Pageable;

public class MySqlUtils {
	
	public static String countForDataTable(CharSequence sql) {
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT COUNT(1) FROM ( ");
		builder.append(sql);
		builder.append(" ) AS tmp_count_tb ");
		return builder.toString();
	}
	
	public static String limitForDataTable(CharSequence sql, long start, long length) {
		final StringBuilder builder = new StringBuilder(sql);
		builder.append(" LIMIT ").append(start).append(", ").append(length);
		return builder.toString();
	}
	
	public static String limitForDataTable(CharSequence sql, Pageable pageable) {
		long start = pageable.getOffset();
		long length = pageable.getPageSize();
		
		return limitForDataTable(sql, start, length);
	}
	
}
