package com.epicodus.twitterclone.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.epicodus.twitterclone.R;
import com.epicodus.twitterclone.models.Tweet;
import com.epicodus.twitterclone.models.User;

public class TweetActivity extends AppCompatActivity {
    private Tweet mTweet;
    private TextView mTweetText;
    private TextView mNameText;
    private TextView mDateText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        String tweetText = getIntent().getStringExtra("tweetText");
        mTweet = Tweet.find(tweetText);

        mTweetText = (TextView) findViewById(R.id.singleTweetText);
        mTweetText.setText(mTweet.getContent());

        mNameText = (TextView)findViewById(R.id.singleNameText);
        mNameText.setText(mTweet.getUser().getName());

        mDateText = (TextView)findViewById(R.id.singleDateText);
        mDateText.setText(mTweet.getFormattedTime());



    }


}
