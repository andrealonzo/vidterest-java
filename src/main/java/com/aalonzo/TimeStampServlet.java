package com.aalonzo;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;

// Extend HttpServlet class
public class TimeStampServlet extends HttpServlet {

    public void init() throws ServletException {
    }

    public void doGet(HttpServletRequest request,
        HttpServletResponse response)
    throws ServletException, IOException {
        // Set response content type
        response.setContentType("application/json");
        
        TimeStampController timeStampController = new TimeStampController();

        //grabs the date from the url
        String encodedStrDate = request.getPathInfo().replace("/", "");
        String strDate = java.net.URLDecoder.decode(encodedStrDate, "UTF-8");
        System.out.println(strDate);
        
        //gets the timestamp
        TimeStamp timeStamp = timeStampController.getTimeStamp(strDate);
        
        PrintWriter out = response.getWriter();

        //needed to convert TimeStamp into JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(timeStamp);
        //output timestamp
        
        out.println(mapper.writeValueAsString(timeStamp));
        }
        
    public void destroy() {
        // do nothing.
    }
}