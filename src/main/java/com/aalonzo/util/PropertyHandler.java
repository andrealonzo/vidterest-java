package com.aalonzo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Singleton object that loads the config property file
 * @author aalonzo
 *
 */
public class PropertyHandler {
	private static PropertyHandler instance = null;
	private Properties props = null;
	private PropertyHandler() throws IOException{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("config.properties");
		if(input != null){
			props = new Properties();
			props.load(input);
		}
	}
	public static synchronized PropertyHandler getInstance() throws IOException{
		if (instance == null)
			instance = new PropertyHandler();
		return instance;
	}
	public String getValue(String propKey){
		if(this.props==null)
			return null;
		return this.props.getProperty(propKey);
	}
}
