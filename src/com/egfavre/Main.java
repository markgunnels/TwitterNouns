package com.egfavre;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws TwitterException{
        ConfigurationBuilder cf = new ConfigurationBuilder();

        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("nNMkFmpRgKJH9xeOLB9FhwOun")
                .setOAuthConsumerSecret("xaL4aQcroETUOxajQZqpzbBhEEQG19zZf0SYC3gykCoRpcstr0")
                .setOAuthAccessToken("798956880890626057-Bj19LnyuHaIsMxlWDX8sCSOV1HanP7b")
                .setOAuthAccessTokenSecret("nBOkD8UzQg4FNNeNfxewVmCeeM3WwsgZ5jMgGECRGFlAu");

        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twitter = tf.getInstance();

        List <Status> status = twitter.getHomeTimeline();
        for (Status st: status){
            System.out.println(st.getUser().getName() + "---" + st.getText());
        }


    }
}
