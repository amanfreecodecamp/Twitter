package com.example.aman.twitter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aman.twitter.Networks.ApiInterface;
import com.example.aman.twitter.Networks.RetrofitConnection;
import com.twitter.sdk.android.core.OAuthSigning;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aman on 22-07-2017.
 */

public class MytweetFragment extends Fragment implements OnTweetClickedListener {
    ListView listView;
    TimeLine timeLineAdapter;
    public static long id;
    FixedTweetTimeline fixedTweetTimeline;
    List<Tweet> hometimeline;
    Context context = null;   // why context is null



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        context = this.getActivity();
        long id = mytweetactivity.id;   // Using Bundle is creating Error
      //  long id = this.getArguments().getLong("Session_id",-1);
        View v = inflater.inflate(R.layout.fragments_hometimeline, container, false);
        listView = (ListView) v.findViewById(R.id.tweetlist);
        HeaderCreation headerCreation = new HeaderCreation("https://api.twitter.com/1.1/statuses/home_timeline.json");
        String header = headerCreation.getHeader();
//        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
//        TwitterAuthConfig authConfig = TwitterCore.getInstance().getAuthConfig();
//        TwitterAuthToken authToken = session.getAuthToken();
//        OAuthSigning authSigning = new OAuthSigning(authConfig, authToken);
//        HashMap<String, String> params = new HashMap<>();
//        params.put("id", String.valueOf(session.getUserId()));
//        //   Map<String, String> authHeaders = authSigning.getOAuthEchoHeadersForVerifyCredentials();
//      String header = authSigning.getAuthorizationHeader("GET", "https://api.twitter.com/1.1/statuses/home_timeline.json", params);
        Log.i("Header", header);
        ApiInterface apiInterface = RetrofitConnection.getInstance(context).create(ApiInterface.class);
        Call<List<Tweet>> call = apiInterface.getTweets(String.valueOf(id), header,50);
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
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        });


        //  FixedTweetTimeline fixedTweetTimeline = new FixedTweetTimeline.Builder().setTweets(hometimeline).build();
            /*    final UserTimeline userTimeline = new UserTimeline.Builder()
                .userId(id)
                .build();*/
     /*   final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();*/
        /*timeLineAdapter = new TimeLine(this,fixedTweetTimeline);
        listView = (ListView)findViewById(R.id.tweetlist);
       listView.setAdapter(timeLineAdapter);*/
        //  timeLineAdapter.notifyDataSetChanged();
        //setListAdapter(adapter);
        return v;
    }

    void settingFixedTimeline(List<Tweet> hometimeline) {
        fixedTweetTimeline = new FixedTweetTimeline.Builder().setTweets(hometimeline).build();
        timeLineAdapter = new TimeLine(context, fixedTweetTimeline,this );
    //    listView = (ListView) findViewById(R.id.tweetlist);
        listView.setAdapter(timeLineAdapter);
    }

    @Override
    public void onTweetClicked(int pos, Tweet tweet) {
        Toast.makeText(context, "Position clicked" + tweet.idStr, Toast.LENGTH_SHORT).show();
    }
}
