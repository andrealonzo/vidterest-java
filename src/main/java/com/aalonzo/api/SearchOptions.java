package com.aalonzo.api;

public class SearchOptions {
	private int top;
	private int skip;
	public SearchOptions() {
		this.top = 10;
		this.skip = 0;
	}
	public SearchOptions(int top, int skip) {
		super();
		this.top = top;
		this.skip = skip*10;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getSkip() {
		return skip;
	}
	public void setSkip(int skip) {
		this.skip = skip*10;
	}
	
}
