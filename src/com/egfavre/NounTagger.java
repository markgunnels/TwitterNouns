package com.egfavre;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.IOException;

import static edu.stanford.nlp.tagger.maxent.MaxentTagger.DEFAULT_NLP_GROUP_MODEL_PATH;

/**
 * Created by user on 11/17/16.
 */
public class NounTagger {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {

        // Initialize the tagger
        MaxentTagger tagger = new MaxentTagger("models/bidirectional-distsim-wsj-0-18.tagger");

        // The sample string
        String sample = "This is a sample text";

        // The tagged string
        String tagged = tagger.tagString(sample);

        // Output the result
        System.out.println(tagged);
    }
}
