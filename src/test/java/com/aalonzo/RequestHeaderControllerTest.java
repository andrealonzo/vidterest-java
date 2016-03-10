package com.aalonzo;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class RequestHeaderControllerTest {

	@Test
	public void testNullUserAgent() {
		String userAgent = null;
		RequestHeaderController controller= new RequestHeaderController();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(request.getHeader("user-agent")).thenReturn(userAgent);
		
		RequestHeader rh= controller.getRequestHeader(request);

		assertTrue(rh.getSoftware()==null);		
	}
	@Test
	public void testGoodUserAgent() {
		String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36";
		String expectedSoftware = "Macintosh; Intel Mac OS X 10_11_3";
		RequestHeaderController controller= new RequestHeaderController();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(request.getHeader("user-agent")).thenReturn(userAgent);
		
		RequestHeader rh= controller.getRequestHeader(request);

		assertTrue(rh.getSoftware().equals( expectedSoftware));		
	}

	@Test
	public void testBadUserAgent() {
		String userAgent = "Mozilla/5.0";
		RequestHeaderController controller= new RequestHeaderController();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(request.getHeader("user-agent")).thenReturn(userAgent);
		
		RequestHeader rh= controller.getRequestHeader(request);

		assertTrue(rh.getSoftware()==null);		
	}
	@Test
	public void testNullXForwardedFor() {
		String ipAddress = null;
		RequestHeaderController controller= new RequestHeaderController();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(request.getHeader("x-forwarded-for")).thenReturn(ipAddress);
		
		RequestHeader rh= controller.getRequestHeader(request);

		assertTrue(rh.getIpaddress()==ipAddress);		
	}

	@Test
	public void testGoodXForwardedFor() {
		String ipAddress = "192.168.1.1";
		RequestHeaderController controller= new RequestHeaderController();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(request.getHeader("x-forwarded-for")).thenReturn(ipAddress);
		
		RequestHeader rh= controller.getRequestHeader(request);

		assertTrue(rh.getIpaddress().equals(ipAddress));		
	}
	@Test
	public void testNullRemoteAddr() {
		String ipAddress = null;
		RequestHeaderController controller= new RequestHeaderController();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(request.getRemoteAddr()).thenReturn(ipAddress);
		
		RequestHeader rh= controller.getRequestHeader(request);

		assertTrue(rh.getIpaddress()==ipAddress);		
	}

	@Test
	public void testGoodRemoteAddr() {
		String ipAddress = "192.168.1.1";
		RequestHeaderController controller= new RequestHeaderController();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(request.getRemoteAddr()).thenReturn(ipAddress);
		
		RequestHeader rh= controller.getRequestHeader(request);

		assertTrue(rh.getIpaddress().equals(ipAddress));		
	}

	@Test
	public void testGoodAccessLanguage() {
		String acceptLanguage = "en-US,en;q=0.8";
		String expectedLanguage = "en-US";
		
		RequestHeaderController controller= new RequestHeaderController();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(request.getHeader("accept-language")).thenReturn(acceptLanguage);
		
		RequestHeader rh= controller.getRequestHeader(request);

		assertTrue(rh.getLanguage().equals(expectedLanguage));		
	}
	

	@Test
	public void testNullAccessLanguage() {
		String acceptLanguage = null;
		String expectedLanguage = null;
		
		RequestHeaderController controller= new RequestHeaderController();
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(request.getHeader("accept-language")).thenReturn(acceptLanguage);
		
		RequestHeader rh= controller.getRequestHeader(request);

		assertTrue(rh.getLanguage()==expectedLanguage);		
	}

}
