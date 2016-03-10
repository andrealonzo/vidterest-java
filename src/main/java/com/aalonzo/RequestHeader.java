package com.aalonzo;


/**
 *  RequestHeader Model POJO
 * 
 **/
 
public class RequestHeader{
	String ipaddress;
    String language;
    String software;
    public RequestHeader(String ipaddress, String language, String software) {
		this.ipaddress = ipaddress;
		this.language = language;
		this.software = software;
	}
    public RequestHeader(){
		this.ipaddress = null;
		this.language = null;
		this.software = null;
    }
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSoftware() {
		return software;
	}
	public void setSoftware(String software) {
		this.software = software;
	}
}