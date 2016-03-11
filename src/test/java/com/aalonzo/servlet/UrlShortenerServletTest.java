package com.aalonzo.servlet;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.aalonzo.controller.UrlShortenerController;
import com.aalonzo.model.ShortenedUrl;
import com.aalonzo.servlet.UrlShortenerServlet;

public class UrlShortenerServletTest {
	HttpServletResponse response;
	HttpServletRequest request;
	StringWriter stringWriter;
	PrintWriter writer;

	@Before
	public void setUp() throws IOException {
		// create mock objects
		response = mock(HttpServletResponse.class);
		request = mock(HttpServletRequest.class);
		stringWriter = new StringWriter();
		writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
	}

	@Test(expected=MalformedURLException.class)
	public void testBadUrl() throws ServletException, IOException, ClassNotFoundException, SQLException {
		UrlShortenerController urlShortenerController = mock(UrlShortenerController.class);
		String url = "thisisabadurl";
		when(request.getPathInfo()).thenReturn("/" + url);

		when(urlShortenerController.getShortenedUrl(url)).thenCallRealMethod();

		// call servlet with mock objects
		new UrlShortenerServlet().doGet(request, response, urlShortenerController);

	}

	@Test
	public void testGoodUrl() throws ServletException, IOException, ClassNotFoundException, SQLException {
		UrlShortenerController urlShortenerController = mock(UrlShortenerController.class);

		String url = "http://www.newurl.com";
		String shortUrl = "shorturl";
		String scheme = "http";
		String serverName = "localhost";
		int serverPort = 8080;

		String contextPath = "";

		when(urlShortenerController.getShortenedUrl(url)).thenReturn(new ShortenedUrl(url, shortUrl));

		when(request.getScheme()).thenReturn(scheme);
		when(request.getServerName()).thenReturn(serverName);
		when(request.getServerPort()).thenReturn(serverPort);
		when(request.getContextPath()).thenReturn(contextPath);
		String expectedOutput = "{\"originalUrl\":\"" + url + "\",\"shortUrl\":\"" + scheme + "://" + serverName + ":"
				+ String.valueOf(serverPort) + "/s/" + shortUrl + "\"}\n";

		when(request.getPathInfo()).thenReturn("/" + url);

		// call servlet with mock objects
		new UrlShortenerServlet().doGet(request, response, urlShortenerController);

		assertTrue(stringWriter.toString().equals(expectedOutput));
	}
}
