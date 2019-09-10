package th.co.baiwa.buckwaframework.common.persistence.util;

import org.springframework.data.domain.Pageable;

public class OracleUtils {

	public static String countForDataTable(String sql) {
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT COUNT(1) FROM ( ");
		builder.append(sql);
		builder.append(" ) TMP_COUNT_TB ");
		return builder.toString();
	}

	/*
	 * Reference:
	 * http://www.oracle.com/technetwork/issue-archive/2006/06-sep/o56asktom
	 * -086197.html
	 */
	public static String limit(String sql, int min, int max) {
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT * ");
		builder.append(" FROM ( ");
		builder.append("   SELECT /*+ FIRST_ROWS(N) */ A.*, ROWNUM RNUM ");
		builder.append("   FROM ( ");
		builder.append(sql);
		builder.append("   ) A ");
		builder.append("   WHERE ROWNUM <= ").append(max);
		builder.append(" ) ");
		builder.append(" WHERE RNUM >= ").append(min);

		return builder.toString();
	}

	public static String limitForDatable(String sql, int start, int length) {
		if (start == 0 && length == 0) {
			return sql;
		} else {
			int min = start + 1;
			int max = start + length;
			return limit(sql, min, max);

		}
	}

	public static String limit(String sql, Pageable pageable) {
		long min = pageable.getOffset() + 1;
		long max = pageable.getOffset() + pageable.getPageSize();

		final StringBuilder pagingSelect = new StringBuilder(sql.length() + 120);
		pagingSelect.append(" select * from (select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ where rownum <= ").append(max).append(") where rownum_ >= ").append(min);

		return pagingSelect.toString();
	}

}
