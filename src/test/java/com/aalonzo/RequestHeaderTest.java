package com.aalonzo;

import static org.junit.Assert.*;

import org.junit.Test;

public class RequestHeaderTest {

	@Test
	public void testBlankConstructor() {
		RequestHeader rh= new RequestHeader();
		assertTrue(rh.getIpaddress() == null);
		assertTrue(rh.getLanguage() == null);
		assertTrue(rh.getSoftware() == null);
	}
	
	@Test
	public void testConstructor() {
		String ipaddress = "1.1.1.1";
		String software = "macos";
		String language = "en-us";
		RequestHeader rh= new RequestHeader(ipaddress, language, software);
		assertTrue(rh.getIpaddress() == ipaddress);
		assertTrue(rh.getLanguage() == language);
		assertTrue(rh.getSoftware() == software);
	}

}
