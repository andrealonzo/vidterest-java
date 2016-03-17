package com.aalonzo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aalonzo.model.User;
import com.aalonzo.model.Video;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// initialize JSON output writers
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		String url = "https://vimeo.com/155043659";
		String source = "vimeo";
		String embedCode = "<iframe src=\"https://player.vimeo.com/video/155043659?byline=0&portrait=0\" frameborder=\"0\" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>";
		String videoId = "155043659";
		// user that added the video
		int id = 1;
		String email = "aalonzo@gmail.com";
		String password = null;
		String imageUrl = "https://avatars.githubusercontent.com/u/15024773?v=3";
		String displayName = "Andre Alonzo";
		String city = null;
		String state = null;
		User addedBy = new User(id, email, password, imageUrl, displayName, city, state);
		Video video = new Video(url, source, embedCode, videoId, addedBy);

		List<Video> videos = new ArrayList<Video>();
		
		videos.add(video);
		out.println(mapper.writeValueAsString(videos));
	}

}
