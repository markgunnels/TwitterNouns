package com.egfavre;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class Main {

    public static void main(String[] args) throws TwitterException{
        ConfigurationBuilder cf = new ConfigurationBuilder();

        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("LHwwCO6Ti83khRDL5VOXCxHxf")
                .setOAuthConsumerSecret("1UzGeUYaILaENN7Bq3Sn5TVJrTGyQYmlQDygWMnGfohW2bKNls")
                .setOAuthAccessToken("798956880890626057-dErWME4TYvbduVPpd6jQ7Y5lHdfcOgr")
                .setOAuthAccessTokenSecret("\trBHQvmWgiOTzAgG3vIVUvskSGgoASAXytYXTnHtw6xSkV");

        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twitter = tf.getInstance();

        List <Status> status = twitter.getHomeTimeline();
        for (Status st: status){
            System.out.println(st.getUser().getName() + "---" + st.getText());
        }


    }
}
