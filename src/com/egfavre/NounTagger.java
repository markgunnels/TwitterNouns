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
        String sample = "Grimm---.@NBCChicagoPD, got it! Adalind's passing the ball to you, @HairsprayLive.  #TNF #TNFHandoff https://t.co/kq0YhRKRYj\n" +
                ", Neil Gaiman---RT @RebekahStaton: As of Sunday, there will be no more Raised By Wolves unless we hit our goal on Kickstarter #SavetheWolves https://t.co/k…\n" +
                ", Neil Gaiman---RT @pageturner: Friday night at @NYPL, James McBride and @PGourevitch discussing the legacy of James Brown: https://t.co/5MO9TkNezZ\n" +
                ", Neil Gaiman---RT @AWInglis: This reminded me of @neilhimself and #AmericanGods #NoSpoiler https://t.co/pTIYcA0jyz\n" +
                ", Neil Gaiman---RT @marcelocordova: Before #HarryPotter : Meet the Other British Boys Who Explored the World of Magic https://t.co/iA5lojDbIk cc @neilhimse…\n" +
                ", The Post and Courier---RT @skropf47: Haley's office confirms meeting with Trump but little else https://t.co/oHouP0Hryt via @postandcourier\n" +
                ", The Post and Courier---RT @AP: BREAKING: Federal appeals court blocks release of Wisconsin inmate featured in the Netflix series `Making a Murderer'\n" +
                ", The Post and Courier---\"A lot of the ladies just went to tears and said this happens in other places, it doesn't happen here\"… https://t.co/5fvGDQ4SjV\n" +
                ", The Post and Courier---RT @Emma_Dumain: Graham on Haley: \"I think (Trump) should consider her for some prominent role bc she is a Republican woman who is talented…\n" +
                ", The Post and Courier---RT @Emma_Dumain: .@LindseyGrahamSC tells @postandcourier on Capitol Hill that @nikkihaley \"has the talent it takes to be good at any job as…\n" +
                ", The Post and Courier---RT @byjohnmcdermott: Property insurer Allstate pegs total Oct. catastrophic losses, including Hurricane Matthew damage in SC, NC. Fla, Ga.…\n" +
                ", The Post and Courier---RT @brindge: Court now having audio difficulties with Fredericks' presentation, lawyer Don McCune says. #chs #chsnews\n" +
                ", The Post and Courier---RT @Clyburn: No one with well-documented ties to white nationalism like Steve Bannon should be working in the White House. #NoHateinWhiteHo…\n" +
                ", The Post and Courier---RT @Clyburn: Pleased to join 168 colleagues in signing the @RepCicilline letter. #NoHateinWhiteHouse\n" +
                ", The Post and Courier---RT @brindge: Jury now back in as forensic analyst Grant Fredericks continues with chapter 3 of his 8 chapter presentation. #chs #chsnews\n" +
                ", The Post and Courier---RT @brindge: Judge Newman rules that the defense can't publish hearsay to the jury. #chs #chsnews\n" +
                ", The Post and Courier---RT @brindge: As lawyers argue, Savage says, \"We never thought he’d (Williams) be qualified by this court as an expert...We were shocked.\" #…\n" +
                ", The Post and Courier---\"She just went up there to meet now.\" @KellyannePolls says @nikkihaley is currently talking with @realDonaldTrump. https://t.co/7OwgxeouMA\n" +
                ", The Post and Courier---RT @brindge: Court has been in a holding pattern for a bit now with the jury out as lawyers argue providing/not providing transcripts for v…\n" +
                "]'";

        // The tagged string
        String tagged = tagger.tagString(sample);

        // Output the result
        System.out.println(tagged);

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
        System.out.println(count);
        System.out.println("Done");




    }

}
