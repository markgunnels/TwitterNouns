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
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws TwitterException, java.io.IOException, TimeoutException{
        ConfigurationBuilder cf = new ConfigurationBuilder();

        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("nNMkFmpRgKJH9xeOLB9FhwOun")
                .setOAuthConsumerSecret("xaL4aQcroETUOxajQZqpzbBhEEQG19zZf0SYC3gykCoRpcstr0")
                .setOAuthAccessToken("798956880890626057-Bj19LnyuHaIsMxlWDX8sCSOV1HanP7b")
                .setOAuthAccessTokenSecret("nBOkD8UzQg4FNNeNfxewVmCeeM3WwsgZ5jMgGECRGFlAu");

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

