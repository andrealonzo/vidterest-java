package com.aalonzo.servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.aalonzo.api.SearchOptions;
import com.aalonzo.controller.ImageSearchController;
import com.aalonzo.model.SearchResult;
import com.aalonzo.servlet.ImageSearchServlet;

public class ImageSearchServletTest {

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

	@Test
	public void testDoGet() throws ServletException, IOException, ClassNotFoundException, SQLException {

		ImageSearchController imageSearchController = mock(ImageSearchController.class);
		String query = "testquery";
		String url = "http://www.test.com";
		String context = "context";
		String snippet = "snippet";
		String thumbnail = "thumbnail";
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		searchResults.add(new SearchResult(url, context, snippet, thumbnail));
		
		SearchOptions searchOptions = new SearchOptions();
		
		String expectedOutput = "[{\"url\":\"" + url + "\",\"context\":\"" + context + "\",\"snippet\":\"" + snippet
				+ "\",\"thumbnail\":\"" + thumbnail + "\"}]\n";

		when(request.getPathInfo()).thenReturn("/" + query);
		
		when(imageSearchController.search(query, searchOptions)).thenReturn(searchResults);

		ImageSearchServlet servlet = new ImageSearchServlet();
		servlet.imageSearchController = imageSearchController;

		servlet.doGet(request, response);

		assertTrue(stringWriter.toString().equals(expectedOutput));

	}

}
