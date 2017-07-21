package com.example.aman.twitter.Networks;

import android.util.Log;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Aman on 20-07-2017.
 */

public interface ApiInterface {
 @GET("statuses/home_timeline.json")
 Call<List<Tweet>> getTweets(@Query("id") String id,@Header("Authorization")String header);
}
