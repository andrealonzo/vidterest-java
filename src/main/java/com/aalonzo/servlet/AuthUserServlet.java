package com.aalonzo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aalonzo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// initialize JSON output writers
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		int id = 1;
		String email = "aalonzo@gmail.com";
		String password = null;
		String imageUrl = "https://avatars.githubusercontent.com/u/15024773?v=3";
		String displayName = "Andre Alonzo";
		String city = null;
		String state = null;
		User user = new User(id, email, password, imageUrl, displayName, city, state);

		//output as JSON
		out.println(mapper.writeValueAsString(user));
	}

}
