package com.manthan.ti.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blood.jonathan.sentiment.Sentiment;
import com.manthan.ti.entity.Data;
import com.manthan.ti.entity.SearchTerm;
import com.manthan.ti.entity.SentimentTable;
import com.manthan.ti.entity.Token;
import com.manthan.ti.model.AnalyzeModal;
import com.manthan.ti.repository.DataRepo;
import com.manthan.ti.repository.SearchTermRepo;
import com.manthan.ti.repository.SentimentTableRepository;
import com.manthan.ti.repository.TokenRepository;

@Service
public class TiDAO implements ITiDAO {

	@Autowired
	SearchTermRepo searchTermRepo;
	
	@Autowired
	DataRepo dataRepo;
	
	@Autowired
	TokenRepository tokenRepo;
	
	@Autowired
	SentimentTableRepository sentimentRepo;
	
	@Override
	public SearchTerm getSearchTerm(Long searchTermId) {
		System.out.println("Search Term Id -> "+searchTermId);
		SearchTerm searchTerm = searchTermRepo.findById(searchTermId).get();
		return searchTerm;		
	}

	@Override
	public void AddData(SearchTerm searchTerm, String PostData, String dateTime, String social) {
		
		dataRepo.save(new Data(searchTerm, PostData, dateTime, social));
	}

	@Override
	public List<Data> retriveData(SearchTerm searchTerm) {
		List<Data> datas = new ArrayList<>();
		List<Long> ids = dataRepo.findBySearchTermId(searchTerm.getId());
		for (Long id : ids) {
			System.out.println("ids -> "+id);
		}
		for (Long id : ids) {
			System.out.println("ids -> "+id);
			datas.add(dataRepo.findById(id).get());
		}
		return datas;
	}

	@Override
	public SearchTerm addSearchTerm(String searchTerm) {
		
		return searchTermRepo.save(new SearchTerm(searchTerm));
	}

	@Override
	public void addToken(SearchTerm searchTerm, Data data, SentimentTable sentiment, String token) {
		
		tokenRepo.save(new Token(searchTerm, data, sentiment, token));
	}

	@Override
	public SentimentTable addSentiment(SearchTerm searchTerm, Data data, String sentiment, int polarity) {
		
		return sentimentRepo.save(new SentimentTable(searchTerm, data, sentiment, polarity));
	}

	@Override
	public List<AnalyzeModal> findResultBySearchTermId(Long searchTermId) {
		List<AnalyzeModal> analyzeModals = new ArrayList<>();
		
		List<Token> tokens = new ArrayList<>();
		List<Long> ids = tokenRepo.findBySearchTermId(searchTermId);
		for (Long id : ids) {
			tokens.add(tokenRepo.findById(id).get());
		}
		
		List<SentimentTable> sentiments = new ArrayList<>();
		List<Long> s_ids = sentimentRepo.findBySearchTermId(searchTermId);
		for (Long id : s_ids) {
			sentiments.add(sentimentRepo.findById(id).get());
		}
		
		Map<Long, List<String>> tokenMap = new HashMap<Long, List<String>>(); 
		for (Token token : tokens) {
			if (!tokenMap.containsKey(token.getData().getId())) {
				List<String> t = new ArrayList<String>();
				t.add(token.getToken());
				tokenMap.put(token.getData().getId(), t);
			} else {
				List<String> t = tokenMap.get(token.getData().getId());
				t.add(token.getToken());
				tokenMap.put(token.getData().getId(), t);
				
			}
		}
		
		for (SentimentTable sen : sentiments) {
			analyzeModals.add(new AnalyzeModal(sen.getData().getData(), sen.getData().getDateTime(), tokenMap.get(sen.getData().getId()), sen.getSentiment()));
		}
		return analyzeModals;
	}

}
