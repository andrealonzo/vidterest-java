package com.aalonzo;

import java.net.MalformedURLException;
import java.sql.SQLException;

import org.apache.commons.validator.routines.UrlValidator;

import com.aalonzo.persistence.UrlShortenerPersistence;

public class UrlShortenerController {

	public ShortenedUrl getShortenedUrl(String url) throws MalformedURLException, ClassNotFoundException, SQLException {
		UrlValidator urlValidator = new UrlValidator();
		if (!urlValidator.isValid(url)) {
			throw new MalformedURLException();
		}

		UrlShortenerPersistence persistence = null;
		persistence = new UrlShortenerPersistence();

		//get shortened url from database
		return persistence.getOrCreateShortenedUrl(url);

	}

}