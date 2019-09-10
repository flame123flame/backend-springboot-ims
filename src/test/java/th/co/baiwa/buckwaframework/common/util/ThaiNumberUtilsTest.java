package th.co.baiwa.buckwaframework.common.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ThaiNumberUtilsTest {

	@Test
	public void test_0_Baht() {
		assertEquals("ศูนย์บาท", ThaiNumberUtils.toThaiBaht("0"));
	}
	
	@Test
	public void test_0_Baht_0_Stang() {
		assertEquals("ศูนย์บาท", ThaiNumberUtils.toThaiBaht("0.0"));
	}
	
	@Test
	public void test_0_Baht_00_Stang() {
		assertEquals("ศูนย์บาท", ThaiNumberUtils.toThaiBaht("0.00"));
	}
	
	@Test
	public void test_0_Baht_01_Stang() {
		assertEquals("หนึ่งสตางค์", ThaiNumberUtils.toThaiBaht("0.01"));
	}
	
	@Test
	public void test_0_Baht_11_Stang() {
		assertEquals("สิบเอ็ดสตางค์", ThaiNumberUtils.toThaiBaht("0.11"));
	}
	
	@Test
	public void test_0_Baht_21_Stang() {
		assertEquals("ยี่สิบเอ็ดสตางค์", ThaiNumberUtils.toThaiBaht("0.21"));
	}
	
	@Test
	public void test_0_Baht_25_Stang() {
		assertEquals("ยี่สิบห้าสตางค์", ThaiNumberUtils.toThaiBaht("0.25"));
	}
	
	@Test
	public void test_0_Baht_50_Stang() {
		assertEquals("ห้าสิบสตางค์", ThaiNumberUtils.toThaiBaht("0.50"));
	}
	
	@Test
	public void test_0_Baht_75_Stang() {
		assertEquals("เจ็ดสิบห้าสตางค์", ThaiNumberUtils.toThaiBaht("0.75"));
	}
	
	@Test
	public void test_9_Baht_75_Stang() {
		assertEquals("เก้าบาทเจ็ดสิบห้าสตางค์", ThaiNumberUtils.toThaiBaht("9.75"));
	}
	
	@Test
	public void test_11_Baht_35_Stang() {
		assertEquals("สิบเอ็ดบาทสามสิบห้าสตางค์", ThaiNumberUtils.toThaiBaht("11.35"));
	}
	
	@Test
	public void test_20_Baht_45_Stang() {
		assertEquals("ยี่สิบบาทสี่สิบห้าสตางค์", ThaiNumberUtils.toThaiBaht("20.45"));
	}
	
	@Test
	public void test_21_Baht_55_Stang() {
		assertEquals("ยี่สิบเอ็ดบาทห้าสิบห้าสตางค์", ThaiNumberUtils.toThaiBaht("21.55"));
	}
	
	@Test
	public void test_29_Baht_65_Stang() {
		assertEquals("ยี่สิบเก้าบาทหกสิบห้าสตางค์", ThaiNumberUtils.toThaiBaht("29.65"));
	}
	
	@Test
	public void test_101_Baht() {
		assertEquals("หนึ่งร้อยเอ็ดบาท", ThaiNumberUtils.toThaiBaht("101"));
	}
	
	@Test
	public void test_102_Baht() {
		assertEquals("หนึ่งร้อยสองบาท", ThaiNumberUtils.toThaiBaht("102"));
	}
	
	@Test
	public void test_999_Baht() {
		assertEquals("เก้าร้อยเก้าสิบเก้าบาท", ThaiNumberUtils.toThaiBaht("999"));
	}
	
	@Test
	public void test_1_999_Baht() {
		assertEquals("หนึ่งพันเก้าร้อยเก้าสิบเก้าบาท", ThaiNumberUtils.toThaiBaht("1999"));
	}
	
	@Test
	public void test_25_185_Baht() {
		assertEquals("สองหมื่นห้าพันหนึ่งร้อยแปดสิบห้าบาท", ThaiNumberUtils.toThaiBaht("25185"));
	}
	
	@Test
	public void test_999_890_Baht() {
		assertEquals("เก้าแสนเก้าหมื่นเก้าพันแปดร้อยเก้าสิบบาท", ThaiNumberUtils.toThaiBaht("999890"));
	}
	
	@Test
	public void test_9_123_456_Baht() {
		assertEquals("เก้าล้านหนึ่งแสนสองหมื่นสามพันสี่ร้อยห้าสิบหกบาท", ThaiNumberUtils.toThaiBaht("9123456"));
		System.out.println(ThaiNumberUtils.toThaiBaht("9123456"));
	}
	
	@Test
	public void test_222_333_555_Baht() {
		assertEquals("สองร้อยยี่สิบสองล้านสามแสนสามหมื่นสามพันห้าร้อยห้าสิบห้าบาท", ThaiNumberUtils.toThaiBaht("222333555"));
	}
	
	@Test
	public void test_100_200_300_400_Baht() {
		assertEquals("หนึ่งแสนสองร้อยล้านสามแสนสี่ร้อยบาท", ThaiNumberUtils.toThaiBaht("100200300400"));
	}
	
	@Test
	public void test_9_100_200_300_400_Baht() {
		assertEquals("เก้าล้านหนึ่งแสนสองร้อยล้านสามแสนสี่ร้อยบาท", ThaiNumberUtils.toThaiBaht("9100200300400"));
	}

}
