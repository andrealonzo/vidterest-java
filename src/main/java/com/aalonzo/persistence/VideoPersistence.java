package com.aalonzo.persistence;

import com.aalonzo.model.User;
import com.aalonzo.model.Video;
import com.aalonzo.util.PropertyHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aalonzo on 10/31/16.
 */
public class VideoPersistence {
    private Connection c;

    /**
     * Opens a new database connection
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public VideoPersistence() throws ClassNotFoundException, SQLException, IOException {

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
     * Gets all videos from the database
     */
    public List<Video> getAll() throws SQLException {
        String url, source, embedCode, videoId;
        // user that added the video
        int id = 1;
        String email = "";
        String password = null;
        String imageUrl = "https://avatars.githubusercontent.com/u/15024773?v=3";
        String displayName = "Andre Alonzo";
        String city = null;
        String state = null;
        User addedBy = new User(id, email, password, imageUrl, displayName, city, state);


        List<Video> videos = new ArrayList<>();

        Statement stmt = c.createStatement();
        String sql = "SELECT * FROM \"video\"";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
             url = rs.getString("url");
             source = rs.getString("source");
             embedCode = rs.getString("embedCode");;
             videoId = rs.getString("videoId");
            Video video = new Video(url, source, embedCode, videoId, addedBy);
            videos.add(video);
        }
        rs.close();
        stmt.close();
        c.close();
        return videos;
    }



}
