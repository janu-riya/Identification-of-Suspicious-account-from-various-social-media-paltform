package com.manthan.ti.dao;

import java.util.List;
import java.util.Optional;

import com.manthan.ti.entity.Data;
import com.manthan.ti.entity.SearchTerm;
import com.manthan.ti.entity.SentimentTable;
import com.manthan.ti.model.AnalyzeModal;

public interface ITiDAO {

	public SearchTerm addSearchTerm(String searchTerm);
	public SearchTerm getSearchTerm(Long searchTermId);
	public void AddData(SearchTerm searchTerm, String Data, String dateTime, String social);
	public List<Data> retriveData(SearchTerm searchTerm);
	public void addToken(SearchTerm searchTerm, Data data, SentimentTable sentiment, String token);
	public SentimentTable addSentiment(SearchTerm searchTerm, Data data, String sentiment, int polarity);
	public List<AnalyzeModal> findResultBySearchTermId(Long searchTermId);
}
