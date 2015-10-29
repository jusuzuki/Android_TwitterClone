package com.epicodus.twitterclone.ui;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.epicodus.twitterclone.R;
import com.epicodus.twitterclone.adapters.TweetAdapter;
import com.epicodus.twitterclone.models.Tweet;
import com.epicodus.twitterclone.models.User;

import java.util.ArrayList;

public class MainActivity extends ListActivity {
    public static String TAG = MainActivity.class.getSimpleName();

    private SharedPreferences mPreferences;
    private User mUser;
    private EditText mTweetText;
    private Button mSubmitButton;
    private ArrayList<Tweet> mTweets;
    private TweetAdapter mAdapter;
    private ImageView mSearchImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getApplicationContext().getSharedPreferences("twitter", Context.MODE_PRIVATE);

        mTweetText = (EditText) findViewById(R.id.newTweetEdit);
        mSubmitButton = (Button) findViewById(R.id.tweetSubmitButton);
        mTweets = (ArrayList) Tweet.all();
        mAdapter = new TweetAdapter(this, mTweets);
        setListAdapter(mAdapter);

        mSearchImage = (ImageView) findViewById(R.id.searchButtonImage);

        if( !isRegistered()){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tweetContent = mTweetText.getText().toString();
                Tweet tweet = new Tweet(tweetContent, mUser);
                tweet.save();
                tweet.parseHashTags();
                mTweets.add(tweet);
                mAdapter.notifyDataSetChanged();

                //Clears input
                mTweetText.setText("");
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        mSearchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }


    private boolean isRegistered(){
        String username = mPreferences.getString("username", null);
        if(username == null) {
            return false;
        } else {
            setUser(username);
            return true;
        }
    }

    private void setUser(String username) {
        User user = User.find(username);
        if(user != null) {
            mUser = user;
        } else {
            mUser = new User(username);
            mUser.save();
        }
        Toast.makeText(this, "Welcome " + mUser.getName(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void  onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l,v, position, id);
        Tweet singleTweet = mTweets.get(position);
        String tweetContent = singleTweet.getContent();
        Intent intent = new Intent(this, TweetActivity.class);
        intent.putExtra("tweetText", tweetContent);
        startActivity(intent);
    }

}
