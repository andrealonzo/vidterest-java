// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import org.apache.commons.lang3.*;

// Extend HttpServlet class
public class TimeStamp extends HttpServlet {

    public void init() throws ServletException {
    }

    public void doGet(HttpServletRequest request,
        HttpServletResponse response)
    throws ServletException, IOException {
        // Set response content type
        response.setContentType("application/json");

        //grabs the date from the url
        String encodedStrDate = request.getRequestURI().replace("/", "");
        String strDate = java.net.URLDecoder.decode(encodedStrDate, "UTF-8");
        System.out.println(strDate);
        PrintWriter out = response.getWriter();

        String naturalLanguageDate = null;
        Long unixDate = null;
        Date date = null;
        if(strDate=="") {
            
            out.println("{unix:" + unixDate + ",natural:" + naturalLanguageDate + "}");
        }
        else {
            if (StringUtils.isNumeric(strDate)) {
                System.out.println("unix timestamp entered");
                //unix timestamp
                try{
                    
                    date = new Date(Long.parseLong(strDate)*1000);
                }
                catch(NumberFormatException e){
                    date = null;
                }

            }
            else{
                
                System.out.println("natural language timestamp entered");
                //natural language
                DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
                try{
                    
                 date = format.parse(strDate);
                }
                catch(ParseException ex){
                    date = null;
                }
            }
            
            if(date != null){
    			unixDate = date.getTime()/1000;
    			naturalLanguageDate = new SimpleDateFormat("MMMM d, yyyy").format(date.getTime());
            }
            if(naturalLanguageDate != null){
                naturalLanguageDate = "\"" +naturalLanguageDate+ "\"";
            }
            //convert date
            out.println("{\"unix\":" + unixDate + ",\"natural\":" + naturalLanguageDate + "}");
        }


    }

    public void destroy() {
        // do nothing.
    }
}