package com.epicodus.twitterclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.epicodus.twitterclone.R;
import com.epicodus.twitterclone.models.Reply;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Guest on 10/27/15.
 */
public class ReplyAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<Reply>mReplies;

    public ReplyAdapter(Context context, ArrayList<Reply> replies){
        mReplies = replies;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mReplies.size();
    }

    @Override
    public Object getItem(int position) {
        return mReplies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.reply_list_item, null);
            holder = new ViewHolder();
            holder.nameLabel = (TextView) convertView.findViewById(R.id.replyNameText);
            holder.dateLabel = (TextView) convertView.findViewById(R.id.replyDateText);
            holder.replyLabel = (TextView) convertView.findViewById(R.id.replyTweetText);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


    Reply reply = mReplies.get(position);

    holder.dateLabel.setText(reply.getFormattedTime());
    holder.nameLabel.setText("By: " + reply.getReplyUser().getName());
    holder.replyLabel.setText(reply.getReplyContent());

    return convertView;
    }

    private static class ViewHolder{
        TextView nameLabel;
        TextView replyLabel;
        TextView dateLabel;
    }
}


