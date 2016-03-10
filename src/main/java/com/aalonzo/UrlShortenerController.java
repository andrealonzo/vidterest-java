package com.aalonzo;

import org.apache.commons.validator.routines.UrlValidator;

import com.aalonzo.ShortenedUrl;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class UrlShortenerController {
    public UrlShortenerController() {

    }
    public ShortenedUrl getShortenedUrl(String url)  {
    	UrlValidator urlValidator = new UrlValidator();
//    	if(!urlValidator.isValid(url)){
//    		return new ShortenedUrl(url,null);  		
//    	}
       // URI dbUri = new URI(System.getenv("DATABASE_URL"));
    	String databaseUrl = "postgres://ijwpchemwfwwlu:Un8TiAMcebu25sss-zWJk9x2_K@ec2-54-83-57-25.compute-1.amazonaws.com:5432/dffme48imn1300?sslmode=disable";
    	URI dbUri=  null;
    	try {
        	 dbUri = new URI(databaseUrl);
           } catch (Exception e) {
              e.printStackTrace();
              System.err.println(e.getClass().getName()+": "+e.getMessage());
            //  System.exit(0);
           }
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
   //     String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
     //   String dbUrl = "jdbc:postgresql://host:port/database?user=username&password=secret&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"
        String dbUrl = "jdbc:postgres://postgres:ucirvine1@localhost:5432/testdb";
       // String dbUrl = "jdbc:postgres://ec2-54-83-57-25.compute-1.amazonaws.com:5432/dffme48imn1300?user=ijwpchemwfwwlu&password=Un8TiAMcebu25sss-zWJk9x2_K&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    	

    	  Connection c = null;
    	  Statement stmt = null;
          try {
             Class.forName("org.postgresql.Driver");
             c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/testdb",
                "postgres", "ucirvine1");
            // c = DriverManager.getConnection(dbUrl, username, password);
         
          
          
          System.out.println("Opened database successfully");
           //check if url exists
          
          stmt = c.createStatement();
          String sql = null;
          sql = "SELECT * FROM \"Url\" WHERE VALUE='"+ url +"'";
          ResultSet rs = stmt.executeQuery( sql );

          if ( rs.next() ) {
        	 //return found url
             String id = rs.getString("id");
             String  value = rs.getString("value");
             rs.close();
             stmt.close();
             c.close();
             return new ShortenedUrl(value, id);
          }
          
          // put a new url in the database
          sql = "INSERT INTO \"Url\" (value) VALUES ('" + url + "')";

          int updatedRows = stmt.executeUpdate( sql,Statement.RETURN_GENERATED_KEYS );
          ResultSet generatedKeys = stmt.getGeneratedKeys();
          if (generatedKeys.next()) {

              String id = generatedKeys.getString(1);
                      rs.close();
                      stmt.close();
                      c.close();
              return new ShortenedUrl(url,id);
          }

          } catch (Exception e) {
              e.printStackTrace();
              System.err.println(e.getClass().getName()+": "+e.getMessage());
              //System.exit(0);
           }
//           Url.findOne({
//               value: url
//           }, function(error, existingUrl) {
//               if (existingUrl) {
//                   //url exists
//                   existingUrl.code = base.encode(existingUrl.id);
//                   return done(existingUrl);
//               }
//
//               //save new url
//               var newUrl = new Url();
//               newUrl.value = url;
//               newUrl.save(function(err) {
//                   if (err) {
//                       return done(null);
//                   }
//                   newUrl.code = base.encode(newUrl.id);
//                   return done(newUrl);
//               }.bind(this));
//
//           });
    	return new ShortenedUrl(null, null);
    }
    
  
}