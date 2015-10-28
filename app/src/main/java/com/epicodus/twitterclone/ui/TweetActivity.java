package com.epicodus.twitterclone.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.twitterclone.R;
import com.epicodus.twitterclone.adapters.ReplyAdapter;
import com.epicodus.twitterclone.models.Reply;
import com.epicodus.twitterclone.models.Tweet;
import com.epicodus.twitterclone.models.User;

import java.util.ArrayList;

public class TweetActivity extends ListActivity {
    private SharedPreferences mPreferences;
    private Tweet mTweet;
    private User mUser;
    private TextView mTweetText;
    private TextView mNameText;
    private TextView mDateText;
    private Button mNewReplyButton;
    private EditText mNewReplyText;
    private ArrayList<Reply> mReplies;
    private ReplyAdapter mReplyAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        String tweetText = getIntent().getStringExtra("tweetText");
        mTweet = Tweet.find(tweetText);

        mTweetText = (TextView) findViewById(R.id.replyTweetText);
        mTweetText.setText(mTweet.getContent());

        mNameText = (TextView)findViewById(R.id.replyNameText);
        mNameText.setText(mTweet.getUser().getName());

        mDateText = (TextView)findViewById(R.id.replyDateText);
        mDateText.setText(mTweet.getFormattedTime());

        mNewReplyText = (EditText)findViewById(R.id.newReplyText);
        mNewReplyButton = (Button) findViewById(R.id.newReplyButton);
        mPreferences = getApplicationContext().getSharedPreferences("twitter", Context.MODE_PRIVATE);




        mReplies = (ArrayList) Reply.repliesToTweet(mTweet.getId());
        mReplyAdapter = new ReplyAdapter(this, mReplies);
        setListAdapter(mReplyAdapter);

        mNewReplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String newReplyUser = mPreferences.getString("username", mUser);
                Toast.makeText(TweetActivity.this, "TEST ", Toast.LENGTH_LONG).show();
                String stuff = mNewReplyText.getText().toString();
                long tweetId = mTweet.getId();
                Reply reply = new Reply(stuff, mTweet.getUser(), tweetId);
                reply.save();
                mReplies.add(reply);
                mReplyAdapter.notifyDataSetChanged();

                //Clears input
                mNewReplyText.setText("");
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });


    }


}
