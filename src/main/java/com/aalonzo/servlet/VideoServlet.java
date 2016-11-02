package com.aalonzo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aalonzo.controller.VideoController;
import com.aalonzo.model.Video;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		VideoController videoController = new VideoController();

		// initialize JSON output writers
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		//gets the timestamp
		List<Video> videos = null;
		try {
			videos = videoController.getVideos();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		out.println(mapper.writeValueAsString(videos));
	}

}
