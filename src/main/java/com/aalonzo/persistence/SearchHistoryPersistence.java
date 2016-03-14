package com.aalonzo.persistence;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aalonzo.model.SearchQuery;
import com.aalonzo.util.PropertyHandler;


/**
 * Persistence layer for the app
 * @author aalonzo
 *
 */
public class SearchHistoryPersistence {

	private Connection c;

	/**
	 * Opens a new database connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public SearchHistoryPersistence() throws ClassNotFoundException, SQLException, IOException {

		Class.forName("org.postgresql.Driver");

		String dbUrl = null;
		String username = null;
		String password = null;
		try {
			//gets the database url from the properties file
			String dbUrlProp = PropertyHandler.getInstance().getValue("DATABASE_URL");
			
			if(dbUrlProp == null){
				// check for database url in environment
				dbUrlProp = System.getenv("DATABASE_URL");
			}
			
			if(dbUrlProp == null){
				throw new SQLException("No Database url found");
			}
			URI dbUri = new URI(dbUrlProp);
			username = dbUri.getUserInfo().split(":")[0];
			password = dbUri.getUserInfo().split(":")[1];
			dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c = DriverManager.getConnection(dbUrl, username, password);

	}

	/**
	 * Inserts the search query into the database
	 * @param query
	 * @throws SQLException
	 */
	public void saveSearch(String query) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "INSERT INTO \"Searches\" (term) VALUES ('" + query + "')";

		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	/**
	 * Gets the 10 most recent search queries in the database
	 * @return
	 * @throws SQLException
	 */
	public List<SearchQuery> getRecentSearches() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM \"Searches\" ORDER BY \"when\" DESC LIMIT 10";
		ResultSet rs = stmt.executeQuery(sql);
		List <SearchQuery> recentSearches = new ArrayList<SearchQuery>();
		while (rs.next()) {
			String term = rs.getString("term");
			String when = rs.getString("when");
			SearchQuery recentSearch = new SearchQuery(term, when);
			recentSearches.add(recentSearch);
		}
		rs.close();
		stmt.close();
		return recentSearches;
	}
	
	

}
