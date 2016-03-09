// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

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

        //output timestamp
        out.println("{\"unix\":" + timeStamp.getUnixDate() + ",\"natural\":" + timeStamp.getNaturalLanguageDate() + "}");
        }


    

    public void destroy() {
        // do nothing.
    }
}