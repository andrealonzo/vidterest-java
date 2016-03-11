package com.aalonzo.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class BaseAlphaTest {

	@Test
	public void testEncode1() {
		int input = 0;
		String expectedEncoded = "a";
		String encoded = BaseAlphaNumeric.encode(input);

        assertTrue(encoded.equals(expectedEncoded)); 
	}	
	@Test
	public void testEncode2() {
		int input = 62;
		String expectedEncoded = "ab";
		String encoded = BaseAlphaNumeric.encode(input);

        assertTrue(encoded.equals(expectedEncoded)); 
	}	
	@Test
	public void testDecodeA() {
		String input = "a";
		int expectedDecoded = 0;
		int decoded = BaseAlphaNumeric.decode(input);

        assertTrue(decoded==expectedDecoded); 
	}
	@Test
	public void testDecodeBA() {
		String input = "ab";
		int expectedDecoded =62;
		int decoded = BaseAlphaNumeric.decode(input);

        assertTrue(decoded==expectedDecoded); 
	}
	
	

}
