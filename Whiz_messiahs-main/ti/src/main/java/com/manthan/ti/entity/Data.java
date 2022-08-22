package com.manthan.ti.entity;

import java.time.LocalDateTime;

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
@Table(name = "data")
public class Data {
	
	public Data(SearchTerm searchTerm, String data, String dateTime, String social) {
		this.searchTerm = searchTerm;
		this.data = data;
		this.dateTime = dateTime;
		this.social = social;
		this.createdAt = LocalDateTime.now();
	}
	
	public Data() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "data")
	private String data;
	
	@Column(name = "social")
	private String social;
	
	@Column(name = "date_time")
	private String dateTime;
	
	@Column(name = "ceated_at")
	private LocalDateTime createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "search_term_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	SearchTerm searchTerm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public SearchTerm getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(SearchTerm searchTerm) {
		this.searchTerm = searchTerm;
	}

	
}
