package com.aalonzo.model;

public class SearchQuery {
	private String term;
	private String when;
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getWhen() {
		return when;
	}
	public void setWhen(String when) {
		this.when = when;
	}
	public SearchQuery(String term, String when) {
		super();
		this.term = term;
		this.when = when;
	}

}
