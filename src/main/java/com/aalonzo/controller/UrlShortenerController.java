package com.aalonzo.controller;

import java.net.MalformedURLException;
import java.sql.SQLException;

import org.apache.commons.validator.routines.UrlValidator;

import com.aalonzo.model.ShortenedUrl;
import com.aalonzo.persistence.UrlShortenerPersistence;
import com.aalonzo.util.BaseAlphaNumeric;

public class UrlShortenerController {
	
	UrlShortenerPersistence persistence;
	public UrlShortenerController() throws ClassNotFoundException, SQLException{
		persistence = new UrlShortenerPersistence();
	}
	
	
	public ShortenedUrl getShortenedUrl(String url) throws MalformedURLException, ClassNotFoundException, SQLException {
		UrlValidator urlValidator = new UrlValidator();
		if (!urlValidator.isValid(url)) {
			throw new MalformedURLException();
		}

		//get shortened url from database
		return persistence.getOrCreateShortenedUrl(url);

	}

	/**
	 * Gets the original url given the short url
	 * @param shortUrl
	 * @return originalUrl
	 * @throws SQLException 
	 */
	public String getOriginalUrl(String shortUrl) throws SQLException {
		int id = BaseAlphaNumeric.decode(shortUrl);
		return persistence.getOriginalUrl(id);
	}

}