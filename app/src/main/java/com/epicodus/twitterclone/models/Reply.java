package com.epicodus.twitterclone.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Guest on 10/27/15.
 */
@Table(name = "Replies", id = "_id")
public class Reply extends Model {

    @Column(name = "replyContent")
    private String mReplyContent;

    @Column(name = "replyUser")
    private User mReplyUser;

    @Column(name = "replyDate")
    private long mReplyDate;

    @Column(name = "tweetId")
    private long mTweetId;

    public Reply() {
        super();
    }

    public Reply(String replyContent, User replyUser, long tweetId) {
        super();
        mReplyContent = replyContent;
        mReplyUser = replyUser;
        mReplyDate = new Date().getTime();
        mTweetId = tweetId;
    }

    public String getReplyContent() {
        return mReplyContent;
    }

    public void setReplyContent(String replyContent) {
        mReplyContent = replyContent;
    }

    public User getReplyUser() {
        return mReplyUser;
    }

    public void setReplyUser(User replyUser) {
        mReplyUser = replyUser;
    }

    public long getReplyDate() {
        return mReplyDate;
    }

    public void setReplyDate(long replyDate) {
        mReplyDate = replyDate;
    }

    public long getTweetId() {
        return mTweetId;
    }

    public void setTweetId(long tweetId) {
        mTweetId = tweetId;
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM d 'at' h:mm");
        formatter.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        return formatter.format(mReplyDate);
    }


    public static List<Reply> all() {
        return new Select()
                .from(Reply.class)
                .execute();
    }
}
