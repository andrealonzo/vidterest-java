package com.aalonzo;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;

// Extend HttpServlet class
public class RequestHeaderServlet extends HttpServlet {

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
        
        RequestHeaderController requestHeaderController = new RequestHeaderController();

        RequestHeader requestHeader = requestHeaderController.getRequestHeader(request);
        
        PrintWriter out = response.getWriter();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(requestHeader);
        
        out.println(mapper.writeValueAsString(requestHeader));
        }
        
    public void destroy() {
        // do nothing.
    }
}