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
		UrlShortenerController urlShortenerController;

		try {
			urlShortenerController = new UrlShortenerController();
			doGet(request, response, urlShortenerController);
		} catch (ClassNotFoundException | SQLException e) {
			PrintWriter out = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			out.println(mapper.writeValueAsString(new UrlError("Database error")));
		} catch (MalformedURLException e) {

			PrintWriter out = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			out.println(mapper.writeValueAsString(new UrlError("URL invalid")));
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response,
			UrlShortenerController urlShortenerController)
			throws ServletException, IOException, ClassNotFoundException, SQLException {

		// grabs url to be shortened and remove leading slash
		String url = request.getPathInfo().substring(1);

		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		ShortenedUrl shortenedUrl = urlShortenerController.getShortenedUrl(url);

		// prepend server path to short url
		String absoluteShortUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/s/" + shortenedUrl.getShortUrl();
		shortenedUrl.setShortUrl(absoluteShortUrl);

		out.println(mapper.writeValueAsString(shortenedUrl));
	}

	public void destroy() {
		// do nothing.
	}
}