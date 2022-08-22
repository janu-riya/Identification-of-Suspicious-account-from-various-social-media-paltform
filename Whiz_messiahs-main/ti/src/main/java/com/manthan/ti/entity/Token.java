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
@Table(name="token")
public class Token {

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
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sentiment_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	SentimentTable sentiment;
	
	@Column(name="token")
	private String token;
	
	public Token() { }
	
	public Token(SearchTerm searchTerm, Data data, SentimentTable sentiment, String token) { 
		this.searchTerm = searchTerm;
		this.data = data;
		this.sentiment = sentiment;
		this.token = token;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SentimentTable getSentiment() {
		return sentiment;
	}

	public void setSentiment(SentimentTable sentiment) {
		this.sentiment = sentiment;
	}
	
	
}
