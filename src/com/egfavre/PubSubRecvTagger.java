package com.egfavre;

/**
 * Created by user on 11/16/16.
 */
import com.rabbitmq.client.*;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.IOException;
import java.io.StringReader;

public class PubSubRecvTagger {

    private final static String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception, IOException, ClassNotFoundException {
        MaxentTagger tagger = new MaxentTagger("/Users/user/IdeaProjects/TwitterNouns/models/english-bidirectional-distsim.tagger");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                String tagged = tagger.tagString(message);

                String findStr = "_NN";
                int lastIndex = 0;
                int count = 0;

                while(lastIndex != -1){

                    lastIndex = tagged.indexOf(findStr,lastIndex);

                    if(lastIndex != -1){
                        count ++;
                        lastIndex += findStr.length();
                    }
                }
                System.out.println("This stream contains " + count + " nouns.");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}




