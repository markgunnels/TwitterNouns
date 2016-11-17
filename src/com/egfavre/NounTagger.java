package com.egfavre;


import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.IOException;

/**
 * Created by user on 11/17/16.
 */
public class NounTagger {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {

        // Initialize the tagger
        MaxentTagger tagger = new MaxentTagger("/Users/user/IdeaProjects/TwitterNouns/models/english-bidirectional-distsim.tagger");

        // The sample string
        String sample = "This is a sample text";

        // The tagged string
        String tagged = tagger.tagString(sample);

        // Output the result
        System.out.println(tagged);
    }
}
