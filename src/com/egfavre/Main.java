package com.egfavre;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.util.concurrent.TimeoutException;

public class Main {
    private final static String QUEUE_NAME = System.getenv("QUEUE_NAME");
    public static final String OAUTH_CONSUMER_KEY = System.getenv("OAUTH_CONSUMER_KEY");
    public static final String OAUTH_CONSUMER_SECRET = System.getenv("OAUTH_CONSUMER_SECRET");
    public static final String OAUTH_ACCESS_TOKEN = System.getenv("OAUTH_ACCESS_TOKEN");
    public static final String OAUTH_ACCESS_TOKEN_SECRET = System.getenv("OAUTH_ACCESS_TOKEN_SECRET");
    
    public static void main(String[] args) throws TwitterException, java.io.IOException, TimeoutException{
        ConfigurationBuilder cf = new ConfigurationBuilder();

        cf.setDebugEnabled(true)
            .setOAuthConsumerKey(OAUTH_CONSUMER_KEY)
            .setOAuthConsumerSecret(OAUTH_CONSUMER_SECRET)
            .setOAuthAccessToken(OAUTH_ACCESS_TOKEN)
            .setOAuthAccessTokenSecret(OAUTH_ACCESS_TOKEN_SECRET);

        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twitter = tf.getInstance();

        List <Status> status = twitter.getHomeTimeline();
        ArrayList<String> feed = new ArrayList<>();

        for (Status st: status){
            String line = (st.getUser().getName() + "---" + st.getText() + System.lineSeparator());
            feed.add(line);
        }

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost" );
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = feed.toString();
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }


}

