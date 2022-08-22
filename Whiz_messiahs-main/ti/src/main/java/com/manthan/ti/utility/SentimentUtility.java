package com.manthan.ti.utility;

import java.util.Map;

import com.blood.jonathan.sentiment.Sentiment;
import com.blood.jonathan.sentiment.model.SentimentResult;

public class SentimentUtility {

	public static void main(String[] args) {
		Sentiment sentiment = Sentiment.getInstance();
		SentimentResult result = sentiment.analyze("very worst celebrations");

		System.out.println("The score is: " + result.getScore());
		System.out.println("The state is: " + result.getState());
		System.out.println("The number of detected words are: " + result.getDetectedWords().size());
		for (Map.Entry<String,Integer> entry : result.getDetectedWords().entrySet()) {
			System.out.println(entry.getKey()+" - "+entry.getValue());
		}
	}
}
