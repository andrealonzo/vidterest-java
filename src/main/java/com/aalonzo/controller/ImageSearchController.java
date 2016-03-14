package com.aalonzo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.aalonzo.api.Bing;
import com.aalonzo.api.SearchOptions;
import com.aalonzo.model.SearchQuery;
import com.aalonzo.model.SearchResult;
import com.aalonzo.persistence.SearchHistoryPersistence;
import com.aalonzo.util.PropertyHandler;

/**
 * Contains all the business logic for the app
 * @author aalonzo
 *
 */
public class ImageSearchController {
	
	SearchHistoryPersistence persistence = null;
	public ImageSearchController() throws ClassNotFoundException, SQLException, IOException{
		persistence = new SearchHistoryPersistence();
	}
	
	
	/**
	 * Queries an image database and returns the results.  This also saves the query that was entered.
	 * @param query
	 * @param options
	 * @return List of SearchResults
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public List<SearchResult> search(String query, SearchOptions options) throws ClassNotFoundException, SQLException, IOException {

		//gets the bing account key from the properties file
		String accKey = PropertyHandler.getInstance().getValue("BING_ACCOUNT_KEY");
		
		if(accKey == null){
			//gets the bing account key from the environment
			 accKey = System.getenv("BING_ACCOUNT_KEY");
		}
		
		//initialize Bing API image search helper
		Bing bing = new Bing(accKey);
			
		//Query the Bing Image Search
		List<SearchResult> searchResults = bing.images(query, options);
		
		//save search
		persistence.saveSearch(query);
		
		return searchResults;

	}


	public List<SearchQuery> getRecentSearches() throws SQLException {
		return persistence.getRecentSearches();
	}


}