package com.example.aman.twitter.Networks;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Aman on 20-07-2017.
 */

public interface ApiInterface {
 @GET("statuses/home_timeline.json")
 Call<List<Tweet>> getTweets(@Header("Authorization")String header, @Query("count")int count);

@GET("trends/place.json")
Call<ArrayList<Trend>> getTrends(@Header("Authorization")String header, @Query("id") String id);

@POST("statuses/update.json")
 Call<Tweet> getTweet(@Header("Authorization")String header,@Query("status")String status);

}
