package com.manthan.ti.utility;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterAPI {

	public Twitter getTwitterObj() {
		TwitterFactory factory = new TwitterFactory();
	    AccessToken accessToken = new AccessToken("2963519619-ezBmMgdb1tbVi37EUMZBIG7wEwYWe9XHJGOCiIP", "LgMnWFoYTU6pfLrPErIMj4nwvP60mpkMjOTCapIoGG6OK");
	    Twitter twitter = factory.getInstance();
	    twitter.setOAuthConsumer("9T9KBzdcdTDQvLWAoinDfGRXQ", "3ZxLMKSKx3ENq5dOJMMEfQemqzuZnyklzpYDfbZZAsauSeQZ2j");
	    twitter.setOAuthAccessToken(accessToken);
	    return twitter;
	}
}
