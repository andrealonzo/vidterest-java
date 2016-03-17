package com.aalonzo.model;

public class Video {
	  String url;
	  String source;
	  String embedCode;
	  String videoId;
	  //user that added the video
	  User addedBy;
	public Video(String url, String source, String embedCode, String videoId, User addedBy) {
		super();
		this.url = url;
		this.source = source;
		this.embedCode = embedCode;
		this.videoId = videoId;
		this.addedBy = addedBy;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getEmbedCode() {
		return embedCode;
	}
	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public User getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}
}
