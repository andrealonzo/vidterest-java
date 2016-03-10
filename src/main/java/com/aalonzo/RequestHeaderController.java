package com.aalonzo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.aalonzo.RequestHeader;


/**
 *  Contains the logic for creating a TimeStamp 
 * 
 **/
public class RequestHeaderController {
    public RequestHeaderController() {

    }
    public RequestHeader getRequestHeader(HttpServletRequest request) {
    	
    	//get software from user agent
    	String software = getSoftware(request);
    	
    	String ipAddress = getIpAddress(request);
    	String language = getAccessLanguage(request);
    	  	
    	return new RequestHeader(ipAddress, language, software);
    }
    
    private String getAccessLanguage(HttpServletRequest request) {
    	String acceptLanguage = request.getHeader("accept-language");
    	String language = null;
		if(acceptLanguage != "")
		{
			language = acceptLanguage.split(",")[0];
		}
		return language;
	}
	private String getIpAddress(HttpServletRequest request) {
    	//there are 2 place the IPaddress can be.  If it's not in x-forwarded-for, then it's in remote-addr
		String ipAddress = request.getHeader("x-forwarded-for");
		if(ipAddress!= null)
			return ipAddress;
		return request.getRemoteAddr();
	}
	private String getSoftware(HttpServletRequest request){
    	String userAgent = request.getHeader("user-agent");
    	//regular expression pattern to extract the software from the userAgent
    	String softwareRe = "[(](.*?)[)]";
    	
        // Create a Pattern object
        Pattern pattern = Pattern.compile(softwareRe);

        // Now create matcher object.
        Matcher matcher = pattern.matcher(userAgent);
        
        String software = null;
        if (matcher.find()) {
        	software = matcher.group(1);
        } 
        return software;
    }
    	
  
}