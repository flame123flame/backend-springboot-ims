package th.co.baiwa.buckwaframework.common.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

public class NumberUtils {
	
	public static BigDecimal toBigDecimal(String input) {
		if (StringUtils.isNotBlank(input)) {
			return new BigDecimal(input.replaceAll(",", ""));
		} else {
			return null;
		}
	}
	
	public static BigDecimal nullToZero(BigDecimal val){
		return (val != null) ? val : BigDecimal.ZERO;
	}
	
	public static BigDecimal ZeroToOne(BigDecimal val){
		return (val.compareTo(BigDecimal.ZERO) == 0) ? BigDecimal.ONE : val;
	}
	
	public static boolean isGreaterThan(BigDecimal compareBase, BigDecimal compareValue) {
        return compareValue.compareTo(compareBase) > 0;
    }

	public static boolean isGreaterThanOrEquals(BigDecimal compareBase, BigDecimal compareValue) {
        return compareValue.compareTo(compareBase) >= 0;
    }

	public static boolean isEquals(BigDecimal compareBase, BigDecimal compareValue) {
        return compareValue.compareTo(compareBase) == 0;
    }

	public static boolean isLessThanOrEquals(BigDecimal compareBase, BigDecimal compareValue) {
        return compareValue.compareTo(compareBase) <= 0;
    }

	public static boolean isLessThan(BigDecimal compareBase, BigDecimal compareValue) {
        return compareValue.compareTo(compareBase) < 0;
    }
	
	public static BigDecimal calculatePercent(BigDecimal baseBigDecimal, BigDecimal compareBigDecimal) {
		BigDecimal percent = null;
		if ((baseBigDecimal.compareTo(BigDecimal.ZERO) == 0) && (compareBigDecimal.compareTo(BigDecimal.ZERO) == 0)) {
			percent = BigDecimal.ZERO;
		} else if (compareBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
			percent = new BigDecimal("100");
		} else if (baseBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
			percent = new BigDecimal("-100");
		} else {
			percent = (baseBigDecimal.subtract(compareBigDecimal)).multiply(new BigDecimal("100")).divide(NumberUtils.ZeroToOne(compareBigDecimal), 2, BigDecimal.ROUND_HALF_UP);
		}
		return percent;
	}
	
	public static BigDecimal min(BigDecimal... bigDecimals) {
		return min(Arrays.asList(bigDecimals));
	}
	
	public static BigDecimal min(List<BigDecimal> bigDecimalList) {
		Optional<BigDecimal> min = bigDecimalList
			.stream()
			.min(Comparator.naturalOrder());
		
		return min.get();
	}
	
	public static BigDecimal max(BigDecimal... bigDecimals) {
		return max(Arrays.asList(bigDecimals));
	}
	
	public static BigDecimal max(List<BigDecimal> bigDecimalList) {
		Optional<BigDecimal> max = bigDecimalList
			.stream()
			.max(Comparator.naturalOrder());
		
		return max.get();
	}
	
}
