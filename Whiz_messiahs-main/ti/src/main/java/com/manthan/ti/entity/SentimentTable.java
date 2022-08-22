package com.manthan.ti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="sentiment")
public class SentimentTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "search_term_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	SearchTerm searchTerm;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "data_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	Data data;
	
	@Column(name="sentiment")
	private String sentiment;
	
	@Column(name="polarity")
	private int polarity;
	
	public SentimentTable() {}
	
	public SentimentTable(SearchTerm searchTerm, Data data, String sentiment, int polarity) {
		this.searchTerm = searchTerm;
		this.data = data;
		this.sentiment = sentiment;
		this.polarity = polarity;
	}

	

	public SearchTerm getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(SearchTerm searchTerm) {
		this.searchTerm = searchTerm;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public int getPolarity() {
		return polarity;
	}

	public void setPolarity(int polarity) {
		this.polarity = polarity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
