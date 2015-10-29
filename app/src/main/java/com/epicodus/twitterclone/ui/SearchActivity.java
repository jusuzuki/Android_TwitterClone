package com.epicodus.twitterclone.ui;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.twitterclone.R;
import com.epicodus.twitterclone.adapters.TweetAdapter;
import com.epicodus.twitterclone.models.Tag;
import com.epicodus.twitterclone.models.Tweet;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends ListActivity {

    private EditText mSearchText;
    private Button mSearchButton;
    private TextView mEmptyText;
    private TweetAdapter mAdapter;
    private ArrayList<Tweet> mTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchText = (EditText) findViewById(R.id.searchText);
        mSearchButton = (Button) findViewById(R.id.searchButton);
        mEmptyText = (TextView) findViewById(android.R.id.empty);
        mTweets = new ArrayList<>();
        mAdapter = new TweetAdapter(this, mTweets);
        setListAdapter(mAdapter);


        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = "#" + mSearchText.getText().toString();
                Tag hashtag = Tag.find(tag);

                mTweets.clear();

                List<Tweet> tweetsToAdd = hashtag.getTweets();
                for (Tweet tweet : tweetsToAdd) {
                    mTweets.add(tweet);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

    }
}
