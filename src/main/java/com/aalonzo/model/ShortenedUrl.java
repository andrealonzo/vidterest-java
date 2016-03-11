package com.aalonzo.model;

public class ShortenedUrl{

	private String originalUrl;
    private String shortUrl;
	public ShortenedUrl(String originalUrl, String shortUrl) {
		super();
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
}