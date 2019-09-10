package th.co.baiwa.buckwaframework.common.persistence.util;

import java.util.Collection;

import org.springframework.util.CollectionUtils;

public class SqlGeneratorUtils {
	
	public static String genSqlSelect(String tableName, Collection<String> selectFieldNameList, Collection<String> conditionFieldNameList) {
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT ");
		builder.append(org.springframework.util.StringUtils.collectionToCommaDelimitedString(selectFieldNameList));
		builder.append(" FROM ").append(tableName);
		if (!CollectionUtils.isEmpty(conditionFieldNameList)) {
			builder.append(" WHERE 1 = 1 ");
			builder.append(org.springframework.util.StringUtils.collectionToDelimitedString(conditionFieldNameList, " ", "AND ", " = ?"));
		}
		return builder.toString();
	}
	
	public static String genSqlCount(String tableName, Collection<String> conditionFieldNameList) {
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT COUNT(1) FROM ").append(tableName);
		if (!CollectionUtils.isEmpty(conditionFieldNameList)) {
			builder.append(" WHERE 1 = 1 ");
			builder.append(org.springframework.util.StringUtils.collectionToDelimitedString(conditionFieldNameList, " ", "AND ", " = ?"));
		}
		return builder.toString();
	}
	
	public static String genSqlInsert(String tableName, Collection<String> fieldNameList) {
		StringBuilder builder = new StringBuilder();
		builder.append(" INSERT INTO ").append(tableName);
		builder.append(" (").append(org.springframework.util.StringUtils.collectionToCommaDelimitedString(fieldNameList)).append(")");
		builder.append(" VALUES (").append(org.apache.commons.lang3.StringUtils.repeat("?", ",", fieldNameList.size())).append(")");
		return builder.toString();
	}
	
	public static String genSqlInsert(String tableName, Collection<String> fieldNameList, String seqName) {
		return genSqlInsert(tableName, fieldNameList).replace("(?", "("+seqName + ".NEXTVAL");
	}
	
	public static String genSqlUpdate(String tableName, Collection<String> fieldNameList, Collection<String> conditionFieldNameList) {
		StringBuilder builder = new StringBuilder();
		builder.append(" UPDATE ").append(tableName);
		builder.append(" SET ").append(org.springframework.util.StringUtils.collectionToDelimitedString(fieldNameList, ",", "", " = ?"));
		if (!CollectionUtils.isEmpty(conditionFieldNameList)) {
			builder.append(" WHERE 1 = 1 ");
			builder.append(org.springframework.util.StringUtils.collectionToDelimitedString(conditionFieldNameList, " ", "AND ", " = ?"));
		}
		return builder.toString();
	}
	
}
