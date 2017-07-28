package com.example.aman.twitter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aman.twitter.Networks.ApiInterface;
import com.example.aman.twitter.Networks.RetrofitConnection;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.TimelineResult;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aman on 22-07-2017.
 */

public class MytweetFragment extends android.support.v4.app.Fragment implements OnTweetClickedListener, SwipeRefreshLayout.OnRefreshListener {
    ListView listView;
    TimeLine timeLineAdapter;
    public static long id;
    FixedTweetTimeline fixedTweetTimeline;
    List<Tweet> hometimeline;
    SwipeRefreshLayout swipeRefreshLayout;
    Context context = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        context = this.getActivity();
        long id = mytweetactivity.id;   // Using Bundle is creating Error
        //  long id = this.getArguments().getLong("Session_id",-1);
        View v = inflater.inflate(R.layout.fragments_hometimeline, container, false);
        listView = v.findViewById(R.id.tweetlist);
        swipeRefreshLayout = v.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh, R.color.refresh1, R.color.refresh2);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                fetchTweets();
            }
        });
        return v;
    }


    void settingFixedTimeline(List<Tweet> hometimeline) {
        fixedTweetTimeline = new FixedTweetTimeline.Builder().setTweets(hometimeline).build();
        timeLineAdapter = new TimeLine(context, fixedTweetTimeline, this);
        //    listView = (ListView) findViewById(R.id.tweetlist);
        listView.setAdapter(timeLineAdapter);
    }

    @Override
    public void onTweetClicked(int pos, Tweet tweet) {
        Toast.makeText(context, "Position clicked" + tweet.idStr, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        timeLineAdapter.refresh(new com.twitter.sdk.android.core.Callback<TimelineResult<Tweet>>() {
            @Override
            public void success(Result<TimelineResult<Tweet>> result) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(context, "Refreshed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
    }

    public void fetchTweets() {
        swipeRefreshLayout.setRefreshing(true);
        HashMap<String, String> map = new HashMap<>();
        map.put("count", 25 + "");
        HeaderCreation headerCreation = new HeaderCreation("https://api.twitter.com/1.1/statuses/home_timeline.json", map);
        String header = headerCreation.getHeader();
        Log.i("Header", header);
        ApiInterface apiInterface = RetrofitConnection.getInstance();
        Call<List<Tweet>> call = apiInterface.getTweets(header, 25);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                if (response.isSuccessful()) {
                    hometimeline = response.body();
                    Log.i("homeTimeline", hometimeline.size() + "");
                    settingFixedTimeline(hometimeline);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    Log.e("Error Code", String.valueOf(response.code()));
                    Log.e("Error Code", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
