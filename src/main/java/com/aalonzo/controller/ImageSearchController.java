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
		String accKey = "2SFuLUdLiSDnParcELfDCMn0uYtu8ErtdevllcDs5wk";
		Bing bing = new Bing(accKey);
			
		List<SearchResult> searchResults = bing.images(query, options);;
		return searchResults;

	}


}