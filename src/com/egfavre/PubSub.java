package com.egfavre;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class PubSub {
    private final static String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws TwitterException, IOException, TimeoutException{
        ConfigurationBuilder cf = new ConfigurationBuilder();

        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("nNMkFmpRgKJH9xeOLB9FhwOun")
                .setOAuthConsumerSecret("xaL4aQcroETUOxajQZqpzbBhEEQG19zZf0SYC3gykCoRpcstr0")
                .setOAuthAccessToken("798956880890626057-Bj19LnyuHaIsMxlWDX8sCSOV1HanP7b")
                .setOAuthAccessTokenSecret("nBOkD8UzQg4FNNeNfxewVmCeeM3WwsgZ5jMgGECRGFlAu");

        TwitterFactory tf = new TwitterFactory(cf.build());
        Twitter twitter = tf.getInstance();

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

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = feed.toString();
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }


}

