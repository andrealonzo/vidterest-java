package com.aalonzo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.aalonzo.api.Bing;
import com.aalonzo.api.SearchOptions;
import com.aalonzo.model.SearchResult;
import com.aalonzo.persistence.ImageSearchPersistence;

public class ImageSearchController {
	
	ImageSearchPersistence persistence = null;
	public ImageSearchController() throws ClassNotFoundException, SQLException{
		//persistence = new ImageSearchPersistence();
	}
	
	
	public List<SearchResult> search(String query, SearchOptions options) throws ClassNotFoundException, SQLException, IOException {
		//gets the bing account key from the environment
		String accKey = System.getenv("BING_ACCOUNT_KEY");
		
		//initialize Bing API image search helper
		Bing bing = new Bing(accKey);
			
		//Query the Bing Image Search
		List<SearchResult> searchResults = bing.images(query, options);
		
		return searchResults;

	}


}