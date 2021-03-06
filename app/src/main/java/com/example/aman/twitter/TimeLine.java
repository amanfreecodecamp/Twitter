package com.example.aman.twitter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.Timeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

/**
 * Created by Aman on 20-07-2017.
 */

public class TimeLine extends TweetTimelineListAdapter {
    /**
     * Constructs a TweetTimelineListAdapter for the given Tweet Timeline.
     *
     * @param context  the context for row views.
     * @param timeline a Timeline&lt;Tweet&gt; providing access to Tweet data items.
     * @throws IllegalArgumentException if timeline is null
     */
    Context context;
    OnTweetClickedListener onTweetClickedListener;
    public TimeLine(Context context, Timeline<Tweet> timeline,OnTweetClickedListener onTweetClickedListener) {
        super(context, timeline);
        this.context = context;
        this.onTweetClickedListener = onTweetClickedListener;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        Log.i("I am in view","Method");
        final int pos = position;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onTweetClickedListener !=null) {
                 onTweetClickedListener.onTweetClicked(pos,getItem(pos));
                }


//                onTweetClicked(pos, tweet);
            }
        });

        return view;
    }


}
 interface OnTweetClickedListener{
    void onTweetClicked(int pos, Tweet tweet);
}
