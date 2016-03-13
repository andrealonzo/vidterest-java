package com.aalonzo.model;

public class SearchResult{
	String url;
	String context;
	String snippet;
	String thumbnail;
	public SearchResult(String url, String context, String snippet, String thumbnail) {
		this.url = url;
		this.context = context;
		this.snippet = snippet;
		this.thumbnail = thumbnail;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
}