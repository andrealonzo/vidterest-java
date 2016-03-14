package com.aalonzo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {
	private static PropertyHandler instance = null;
	private Properties props = null;
	private PropertyHandler() throws IOException{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("config.properties");
		props = new Properties();
		props.load(input);
	}
	public static synchronized PropertyHandler getInstance() throws IOException{
		if (instance == null)
			instance = new PropertyHandler();
		return instance;
	}
	public String getValue(String propKey){
		return this.props.getProperty(propKey);
	}
}
