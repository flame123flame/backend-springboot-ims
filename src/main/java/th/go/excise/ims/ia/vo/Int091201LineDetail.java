package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class Int091201LineDetail {

	
	private String username;
	private String userLogin;
	private BigDecimal priceOfMonth;
	private List<Int091201ViewValue> value;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public List<Int091201ViewValue> getValue() {
		return value;
	}
	public void setValue(List<Int091201ViewValue> value) {
		this.value = value;
	}
	public byte byteValue() {
		return priceOfMonth.byteValue();
	}
	public short shortValue() {
		return priceOfMonth.shortValue();
	}
	public BigDecimal add(BigDecimal augend) {
		return priceOfMonth.add(augend);
	}
	public BigDecimal add(BigDecimal augend, MathContext mc) {
		return priceOfMonth.add(augend, mc);
	}
	public BigDecimal subtract(BigDecimal subtrahend) {
		return priceOfMonth.subtract(subtrahend);
	}
	public BigDecimal subtract(BigDecimal subtrahend, MathContext mc) {
		return priceOfMonth.subtract(subtrahend, mc);
	}
	public BigDecimal multiply(BigDecimal multiplicand) {
		return priceOfMonth.multiply(multiplicand);
	}
	public BigDecimal multiply(BigDecimal multiplicand, MathContext mc) {
		return priceOfMonth.multiply(multiplicand, mc);
	}
	public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode) {
		return priceOfMonth.divide(divisor, scale, roundingMode);
	}
	public BigDecimal divide(BigDecimal divisor, int scale, RoundingMode roundingMode) {
		return priceOfMonth.divide(divisor, scale, roundingMode);
	}
	public BigDecimal divide(BigDecimal divisor, int roundingMode) {
		return priceOfMonth.divide(divisor, roundingMode);
	}
	public BigDecimal divide(BigDecimal divisor, RoundingMode roundingMode) {
		return priceOfMonth.divide(divisor, roundingMode);
	}
	public BigDecimal divide(BigDecimal divisor) {
		return priceOfMonth.divide(divisor);
	}
	public BigDecimal divide(BigDecimal divisor, MathContext mc) {
		return priceOfMonth.divide(divisor, mc);
	}
	public BigDecimal divideToIntegralValue(BigDecimal divisor) {
		return priceOfMonth.divideToIntegralValue(divisor);
	}
	public BigDecimal divideToIntegralValue(BigDecimal divisor, MathContext mc) {
		return priceOfMonth.divideToIntegralValue(divisor, mc);
	}
	public BigDecimal remainder(BigDecimal divisor) {
		return priceOfMonth.remainder(divisor);
	}
	public BigDecimal remainder(BigDecimal divisor, MathContext mc) {
		return priceOfMonth.remainder(divisor, mc);
	}
	public BigDecimal[] divideAndRemainder(BigDecimal divisor) {
		return priceOfMonth.divideAndRemainder(divisor);
	}
	public BigDecimal[] divideAndRemainder(BigDecimal divisor, MathContext mc) {
		return priceOfMonth.divideAndRemainder(divisor, mc);
	}
	public BigDecimal pow(int n) {
		return priceOfMonth.pow(n);
	}
	public BigDecimal pow(int n, MathContext mc) {
		return priceOfMonth.pow(n, mc);
	}
	public BigDecimal abs() {
		return priceOfMonth.abs();
	}
	public BigDecimal abs(MathContext mc) {
		return priceOfMonth.abs(mc);
	}
	public BigDecimal negate() {
		return priceOfMonth.negate();
	}
	public BigDecimal negate(MathContext mc) {
		return priceOfMonth.negate(mc);
	}
	public BigDecimal plus() {
		return priceOfMonth.plus();
	}
	public BigDecimal plus(MathContext mc) {
		return priceOfMonth.plus(mc);
	}
	public int signum() {
		return priceOfMonth.signum();
	}
	public int scale() {
		return priceOfMonth.scale();
	}
	public int precision() {
		return priceOfMonth.precision();
	}
	public BigInteger unscaledValue() {
		return priceOfMonth.unscaledValue();
	}
	public BigDecimal round(MathContext mc) {
		return priceOfMonth.round(mc);
	}
	public BigDecimal setScale(int newScale, RoundingMode roundingMode) {
		return priceOfMonth.setScale(newScale, roundingMode);
	}
	public BigDecimal setScale(int newScale, int roundingMode) {
		return priceOfMonth.setScale(newScale, roundingMode);
	}
	public BigDecimal setScale(int newScale) {
		return priceOfMonth.setScale(newScale);
	}
	public BigDecimal movePointLeft(int n) {
		return priceOfMonth.movePointLeft(n);
	}
	public BigDecimal movePointRight(int n) {
		return priceOfMonth.movePointRight(n);
	}
	public BigDecimal scaleByPowerOfTen(int n) {
		return priceOfMonth.scaleByPowerOfTen(n);
	}
	public BigDecimal stripTrailingZeros() {
		return priceOfMonth.stripTrailingZeros();
	}
	public int compareTo(BigDecimal val) {
		return priceOfMonth.compareTo(val);
	}
	public boolean equals(Object x) {
		return priceOfMonth.equals(x);
	}
	public BigDecimal min(BigDecimal val) {
		return priceOfMonth.min(val);
	}
	public BigDecimal max(BigDecimal val) {
		return priceOfMonth.max(val);
	}
	public int hashCode() {
		return priceOfMonth.hashCode();
	}
	public String toString() {
		return priceOfMonth.toString();
	}
	public String toEngineeringString() {
		return priceOfMonth.toEngineeringString();
	}
	public String toPlainString() {
		return priceOfMonth.toPlainString();
	}
	public BigInteger toBigInteger() {
		return priceOfMonth.toBigInteger();
	}
	public BigInteger toBigIntegerExact() {
		return priceOfMonth.toBigIntegerExact();
	}
	public long longValue() {
		return priceOfMonth.longValue();
	}
	public long longValueExact() {
		return priceOfMonth.longValueExact();
	}
	public int intValue() {
		return priceOfMonth.intValue();
	}
	public int intValueExact() {
		return priceOfMonth.intValueExact();
	}
	public short shortValueExact() {
		return priceOfMonth.shortValueExact();
	}
	public byte byteValueExact() {
		return priceOfMonth.byteValueExact();
	}
	public float floatValue() {
		return priceOfMonth.floatValue();
	}
	public double doubleValue() {
		return priceOfMonth.doubleValue();
	}
	public BigDecimal ulp() {
		return priceOfMonth.ulp();
	}
	public BigDecimal getPriceOfMonth() {
		return priceOfMonth;
	}
	public void setPriceOfMonth(BigDecimal priceOfMonth) {
		this.priceOfMonth = priceOfMonth;
	}
	
	
}
