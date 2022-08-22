package com.manthan.ti.model;

import java.util.List;

public class AnalyzeModal {

	public AnalyzeModal() {
		
	}
	public AnalyzeModal(String data, String dateTime, List<String> token, String result) {
		this.data = data;
		this.dateTime = dateTime;
		this.token = token;
		this.result = result;
	}
	private String data;
	private String dateTime;
	private List<String> token;
	private String result;
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
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<String> getToken() {
		return token;
	}
	public void setToken(List<String> token) {
		this.token = token;
	}
	
	
}
