package com.aalonzo.controller;

import com.aalonzo.model.User;
import com.aalonzo.model.Video;
import com.aalonzo.persistence.VideoPersistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aalonzo on 10/31/16.
 */
public class VideoController {
    public List<Video> getVideos() throws SQLException, IOException, ClassNotFoundException {
        VideoPersistence persistence = new VideoPersistence();

        return persistence.getAll();
    }

    public List<Video> getUserVideos(int userId) throws SQLException, IOException, ClassNotFoundException {
        return getVideos();
    }
}
