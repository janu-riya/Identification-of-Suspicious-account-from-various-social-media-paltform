package com.manthan.ti.bo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blood.jonathan.sentiment.Sentiment;
import com.blood.jonathan.sentiment.model.SentimentResult;
import com.manthan.ti.dao.ITiDAO;
import com.manthan.ti.entity.Data;
import com.manthan.ti.entity.SearchTerm;
import com.manthan.ti.entity.SentimentTable;
import com.manthan.ti.model.AnalyzeModal;
import com.manthan.ti.model.Page2Model;
import com.manthan.ti.utility.TwitterAPI;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Service
public class TiBo implements ITiBo {

	@Autowired
	ITiDAO iTiDAO;
	
	@Override
	public Page2Model getWebsitesSocials(String searchTerm) {

		List<String> websites = new ArrayList<String>();
		List<String> social = new ArrayList<String>();
		
//		websites.add("www.google.com/"+searchTerm);
//		websites.add("www.yahoo.com/"+searchTerm);
//		websites.add("www.ping.com/"+searchTerm);
		
		social.add("https://www.facebook.com/profile/"+searchTerm);
		social.add("https://twitter.com/"+searchTerm);
		social.add("https://www.linkedin.com/in/"+searchTerm);
		social.add("https://www.instagram.com/p/"+searchTerm);
		social.add("https://www.kooapp.com/profile/"+searchTerm);
		
		Page2Model page2Model = new Page2Model(websites, social, iTiDAO.addSearchTerm(searchTerm));
		return page2Model;
	}

	@Override
	public List<AnalyzeModal> analyze(Long searchTermId) {
		
		SearchTerm searchTermObj = iTiDAO.getSearchTerm(searchTermId);
		System.out.println("Search Term -> "+searchTermObj.getSearchKey());
		fetchTwitter(searchTermObj);
		
//		executePython("python3 tes.py");
		executePython("python3 kooScrap.py "+searchTermObj.getSearchKey());
		executePython("python3 instaScrap.py "+searchTermObj.getSearchKey());
		List<Data> datas = iTiDAO.retriveData(searchTermObj);
		Sentiment sentiment = Sentiment.getInstance();
		for(Data data : datas) {
			SentimentResult result = sentiment.analyze(data.getData());
			SentimentTable sentimentTable = iTiDAO.addSentiment(searchTermObj, data, result.getState(), result.getScore());
			for (Map.Entry<String,Integer> entry : result.getDetectedWords().entrySet()) {
				iTiDAO.addToken(searchTermObj, data, sentimentTable, entry.getKey());
			}
		}
		return iTiDAO.findResultBySearchTermId(searchTermId);
	}
	
	
	public void fetchTwitter(SearchTerm searchTerm) {
		TwitterAPI twitterAPI = new TwitterAPI();
		Twitter twitter = twitterAPI.getTwitterObj();
		Query query = new Query("from:"+searchTerm.getSearchKey());
	    QueryResult result;
		try {
			result = twitter.search(query);
			for (Status status : result.getTweets()) {
		        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
		        if ((status.getText()).length() < 240) {
		        	iTiDAO.AddData(searchTerm, status.getText(), status.getCreatedAt().toString(), "Twitter");
		        }
		        
		    }
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	    
	}
	
	public void executePython(String command) {
//		String command = "cmd /c python <command to execute or script to run>";
		System.out.println("Script Calling -> "+command);
		try {
			Process p = Runtime.getRuntime().exec(command);
		    p.waitFor();
		    BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		          String line;
		        while ((line = bri.readLine()) != null) {
		            System.out.println(line);
		            break;
		          }
		          bri.close();
		          while ((line = bre.readLine()) != null) {
		            System.out.println(line);
		            break;
		          }
		          bre.close();
		          System.out.println("Done.");
	
		    p.destroy();
		} catch (Exception e) {
			
		}
		    
	}
}
