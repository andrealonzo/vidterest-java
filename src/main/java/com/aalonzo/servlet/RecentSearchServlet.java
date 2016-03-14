package com.aalonzo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aalonzo.controller.ImageSearchController;
import com.aalonzo.model.SearchQuery;
import com.aalonzo.model.UrlError;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Displays the 10 most recent search queries 
 * @author aalonzo
 *
 */
public class RecentSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected ImageSearchController imageSearchController  = null;
    public RecentSearchServlet() throws ServletException {
		try {
			imageSearchController = new ImageSearchController();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new ServletException("Could not connect to database");
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//initialize JSON output writers 
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<SearchQuery> recentSearches = imageSearchController.getRecentSearches();		
			out.println(mapper.writeValueAsString(recentSearches));
		} catch (SQLException e) {
			out.println(mapper.writeValueAsString(new UrlError("Database Error")));
		}
	}


}
