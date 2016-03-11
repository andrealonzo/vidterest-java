package com.aalonzo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aalonzo.controller.UrlShortenerController;
import com.aalonzo.model.UrlError;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class LoadUrlServlet
 */
public class LoadUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadUrlServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			UrlShortenerController urlShortenerController = new UrlShortenerController();
			doGet(request, response, urlShortenerController);
		} catch (ClassNotFoundException | SQLException e) {
			PrintWriter out = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			out.println(mapper.writeValueAsString(new UrlError("Database error")));
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response,
			UrlShortenerController urlShortenerController) throws ServletException, IOException, SQLException {
		// grabs short url remove leading slash
		String shortUrl = request.getPathInfo().substring(1);

		String originalUrl = urlShortenerController.getOriginalUrl(shortUrl);
		if (originalUrl == null) {
			throw new SQLException();
		}

		response.sendRedirect(originalUrl);
	}

}
