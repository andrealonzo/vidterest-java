package com.aalonzo.servlet;

// Import required java libraries
import java.io.*;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.lang3.math.NumberUtils;

import com.aalonzo.api.SearchOptions;
import com.aalonzo.controller.ImageSearchController;
import com.aalonzo.model.SearchResult;
import com.aalonzo.model.UrlError;
import com.fasterxml.jackson.databind.ObjectMapper;

// Extend HttpServlet class
public class ImageSearchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected ImageSearchController imageSearchController  = null;

	public void init() throws ServletException {

			try {
				imageSearchController = new ImageSearchController();
			} catch (ClassNotFoundException | SQLException e) {
				throw new ServletException("Could not connect to database");
			}
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// grabs url to be shortened and remove leading slash
			String query = request.getPathInfo().substring(1);


			//create default search options
			SearchOptions searchOptions = new SearchOptions();
			
			//gets the offset query string parameter
			String offsetQueryStr = request.getParameter("offset");
			
			//checks to see if offset is a valid number
			if(NumberUtils.isNumber(offsetQueryStr)){
				
				//if so, then convert it into a number and set the skip option
				int offset = Integer.parseInt(request.getParameter("offset"));
				searchOptions.setSkip(offset);
			}
			
			//perform search
			List<SearchResult> searchResults = imageSearchController.search(query, searchOptions);

			//output as JSON
			PrintWriter out = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			
			out.println(mapper.writeValueAsString(searchResults));
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

	public void destroy() {
		// do nothing.
	}

}