package com.aalonzo;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;

// Extend HttpServlet class
public class UrlShortenerServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
    }

    public void doGet(HttpServletRequest request,
        HttpServletResponse response)
    throws ServletException, IOException {
        // Set response content type
        response.setContentType("application/json");
        
        UrlShortenerController urlShortenerController = new UrlShortenerController();
        
        //grabs url to be shortened and remove leading slash
        String url = request.getPathInfo().substring(1);
        
        ShortenedUrl shortenedUrl = urlShortenerController.getShortenedUrl(url);
        
        PrintWriter out = response.getWriter();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(shortenedUrl);
        
        out.println(mapper.writeValueAsString(shortenedUrl));
        }
        
    public void destroy() {
        // do nothing.
    }
}