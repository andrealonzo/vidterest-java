package com.aalonzo.api;

/**
 * Used with Bing Search
 * @author aalonzo
 *
 */
public class SearchOptions {
	private int top;
	private int skip;
	
	/**
	 * Default options
	 */
	public SearchOptions() {
		this.top = 10;
		this.skip = 0;
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
