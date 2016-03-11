package com.aalonzo.servlet;

// Import required java libraries
import java.io.*;
import java.net.MalformedURLException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.aalonzo.controller.UrlShortenerController;
import com.aalonzo.model.ShortenedUrl;
import com.aalonzo.model.UrlError;
import com.fasterxml.jackson.databind.ObjectMapper;

// Extend HttpServlet class
public class UrlShortenerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UrlShortenerController urlShortenerController = new UrlShortenerController();
		doGet(request, response, urlShortenerController);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response, UrlShortenerController urlShortenerController) throws ServletException, IOException {
		// Set response content type
		response.setContentType("application/json");

		// grabs url to be shortened and remove leading slash
		String url = request.getPathInfo().substring(1);

		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		Object output = null;
		try {
			ShortenedUrl shortenedUrl = urlShortenerController.getShortenedUrl(url);

			// prepend server path to short url
			String absoluteShortUrl = request.getScheme() + "://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath() + "/s/" + shortenedUrl.getShortUrl();
			shortenedUrl.setShortUrl(absoluteShortUrl);
			output = shortenedUrl;

		} catch (MalformedURLException e) {
			output = new UrlError("URL invalid");
		} catch (ClassNotFoundException e) {
			output = new UrlError("Database class not found");
		} catch (SQLException e) {
			output = new UrlError("Error querying database");
		}

		mapper.writeValueAsString(output);
		out.println(mapper.writeValueAsString(output));
	}
	
	

	public void destroy() {
		// do nothing.
	}
}