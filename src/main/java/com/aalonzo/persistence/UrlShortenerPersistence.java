package com.aalonzo.persistence;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aalonzo.model.ShortenedUrl;
import com.aalonzo.util.BaseAlphaNumeric;

public class UrlShortenerPersistence {
	// String dbUrl =
	// "jdbc:postgres://postgres:ucirvine1@localhost:5432/testdb";

	Connection c = null;

	public UrlShortenerPersistence() throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");

		String dbUrl = null;
		String username = null;
		String password = null;
		try {
			// check for database url in environment
			URI dbUri = new URI(System.getenv("DATABASE_URL"));
			username = dbUri.getUserInfo().split(":")[0];
			password = dbUri.getUserInfo().split(":")[1];
			dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c = DriverManager.getConnection(dbUrl, username, password);
		//c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "ucirvine1");

	}

	/**
	 * 
	 * Gets the shortened Url from the database. If it doesn't exists, then
	 * insert one and return that one.
	 * 
	 * @param String
	 *            url
	 * @return ShortenedUrl
	 * @throws SQLException
	 */
	public ShortenedUrl getOrCreateShortenedUrl(String url) throws SQLException {
		ShortenedUrl shortenedUrl = getShortenedUrl(url);
		if (shortenedUrl != null) {
			return shortenedUrl;
		}

		// shortenedUrl was not found. Create one
		shortenedUrl = insertShortenedUrl(url);
		if (shortenedUrl == null) {
			// this should not be null
			throw new SQLException();
		}
		return shortenedUrl;
	}

	private ShortenedUrl getShortenedUrl(String url) throws SQLException {

		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM \"Url\" WHERE VALUE='" + url + "'";
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			// return found url
			int id = rs.getInt("id");
			String value = rs.getString("value");
			rs.close();
			stmt.close();
			c.close();
			return new ShortenedUrl(value, BaseAlphaNumeric.encode(id));
		}
		return null;
	}

	private ShortenedUrl insertShortenedUrl(String url) throws SQLException {

		Statement stmt = c.createStatement();
		String sql = "INSERT INTO \"Url\" (value) VALUES ('" + url + "')";

		stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if (generatedKeys.next()) {

			int id = generatedKeys.getInt(1);
			generatedKeys.close();
			stmt.close();
			c.close();
			return new ShortenedUrl(url, BaseAlphaNumeric.encode(id));
		}
		return null;
	}

	public String getOriginalUrl(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM \"Url\" WHERE id = " + id;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			// return found url
			String value = rs.getString("value");
			rs.close();
			stmt.close();
			c.close();
			return value;
		}
		return null;
	}

}
