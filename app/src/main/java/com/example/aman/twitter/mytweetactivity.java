package com.example.aman.twitter;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aman.twitter.Networks.ApiInterface;
import com.example.aman.twitter.Networks.RetrofitConnection;
import com.twitter.sdk.android.core.OAuthSigning;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mytweetactivity extends AppCompatActivity{
    ListView listView;
    TimeLine timeLineAdapter;
    public static long id;
    FixedTweetTimeline fixedTweetTimeline;
    List<Tweet> hometimeline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  Twitter.initialize(this);
        Intent i = getIntent();
        id = i.getLongExtra("User_id", -1);
        setContentView(R.layout.activity_mytweetactivity);

        Log.i("User_id", id + "");
       /* MytweetFragment mytweetFragment = new MytweetFragment();
        Bundle b = new Bundle();
        b.putLong("Session_id",id);
        mytweetFragment.setArguments(b);
        getFragmentManager().beginTransaction().add(R.id.containerLayout,mytweetFragment).commit();*/
      /*  TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        TwitterAuthConfig authConfig = TwitterCore.getInstance().getAuthConfig();
        TwitterAuthToken authToken = session.getAuthToken();
        OAuthSigning authSigning = new OAuthSigning(authConfig, authToken);
        HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(session.getUserId()));
        //   Map<String, String> authHeaders = authSigning.getOAuthEchoHeadersForVerifyCredentials();
        String header = authSigning.getAuthorizationHeader("GET", "https://api.twitter.com/1.1/statuses/home_timeline.json", params);
        Log.i("Header", header);
        ApiInterface apiInterface = RetrofitConnection.getInstance(this).create(ApiInterface.class);
        Call<List<Tweet>> call = apiInterface.getTweets(String.valueOf(id), header);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                if (response.isSuccessful()) {
                    hometimeline = response.body();
                    Log.i("homeTimeline", hometimeline.size() + "");
                    settingFixedTimeline(hometimeline);
                } else {
                    Log.e("Error Code", String.valueOf(response.code()));
                    Log.e("Error Code", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                Toast.makeText(mytweetactivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });


        //  FixedTweetTimeline fixedTweetTimeline = new FixedTweetTimeline.Builder().setTweets(hometimeline).build();
            *//*    final UserTimeline userTimeline = new UserTimeline.Builder()
                .userId(id)
                .build();*//*
     *//*   final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();*//*
        *//*timeLineAdapter = new TimeLine(this,fixedTweetTimeline);
        listView = (ListView)findViewById(R.id.tweetlist);
       listView.setAdapter(timeLineAdapter);*//*
        //  timeLineAdapter.notifyDataSetChanged();
        //setListAdapter(adapter);
    }

    void settingFixedTimeline(List<Tweet> hometimeline) {
        fixedTweetTimeline = new FixedTweetTimeline.Builder().setTweets(hometimeline).build();
        timeLineAdapter = new TimeLine(mytweetactivity.this, fixedTweetTimeline, this);
        listView = (ListView) findViewById(R.id.tweetlist);
        listView.setAdapter(timeLineAdapter);
    }

    @Override
    public void onTweetClicked(int pos, Tweet tweet) {
        Toast.makeText(this, "Position clicked" + tweet.idStr, Toast.LENGTH_SHORT).show();
    }*/

    }
}
