package com.aalonzo.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aalonzo.model.SearchResult;

public class Bing {
	private String accKey;

	public Bing(String accKey) {
		super();
		//sets the BING account key
		this.accKey = accKey;
	}

	public List<SearchResult> images(String query, SearchOptions options) throws IOException {
		
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		
		//This url will be used to query bing
		String bingUrl = "https://api.datamarket.azure.com/Bing/Search/v1/Image?Query=%27" + URLEncoder.encode(query, "UTF-8") + "%27&Adult=%27Off%27&$top="+options.getTop()+"&$skip="+options.getSkip()+"&$format=json";
		
		//encode the account key
		String accountKeyEnc = Base64.getEncoder().encodeToString((accKey + ":" + accKey).getBytes());

		//create connection and auth
		URL url = new URL(bingUrl);
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);
		
		//read in the results of the query
		try (final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			//put results into a json object
			JSONObject json = new JSONObject(response.toString());
			
			//get the first level 
			JSONObject d = json.getJSONObject("d");
			//get the second level
			JSONArray results = d.getJSONArray("results");
			int resultsLength = results.length();
			//go through the results
			for (int i = 0; i < resultsLength; i++) {
				JSONObject aResult = results.getJSONObject(i);
				//grab the specific json values
				String resultUrl =  aResult.getString("MediaUrl");
				String context = aResult.getString("SourceUrl");
				String snippet = aResult.getString("Title");
				JSONObject jsonThumbnail = aResult.getJSONObject("Thumbnail");
				String thumbnail = jsonThumbnail.getString("MediaUrl");
				//put the values in a search result
				SearchResult searchResult = new SearchResult(resultUrl, context, snippet, thumbnail);
				
				//put them in the search results
				searchResults.add(searchResult);
			}
		}
		return  searchResults;
	}

}
