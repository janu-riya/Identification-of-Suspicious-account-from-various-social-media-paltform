package com.manthan.ti.model;

import java.util.List;

import com.manthan.ti.entity.SearchTerm;

public class Page2Model {

	List<String> website;
	List<String> social;
	SearchTerm searchTerm;
	
	public SearchTerm getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(SearchTerm searchTerm) {
		this.searchTerm = searchTerm;
	}

	public Page2Model () {
		
	}
	
	public Page2Model (List<String> website, List<String> social, SearchTerm searchTerm) {
		this.website = website;
		this.social = social;
		this.searchTerm = searchTerm;
	}

	public List<String> getWebsite() {
		return website;
	}

	public void setWebsite(List<String> website) {
		this.website = website;
	}

	public List<String> getSocial() {
		return social;
	}

	public void setSocial(List<String> social) {
		this.social = social;
	}
	
	
}
