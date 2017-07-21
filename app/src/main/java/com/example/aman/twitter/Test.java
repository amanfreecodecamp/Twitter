package com.example.aman.twitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_test);

        ListView listView = (ListView) findViewById(android.R.id.list);

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .userId(539487832448843776L)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
//        timeLineAdapter = new TimeLine(this,userTimeline);

        listView.setAdapter(adapter);
    }
}
