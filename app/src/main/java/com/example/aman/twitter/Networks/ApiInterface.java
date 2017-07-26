package com.example.aman.twitter.Networks;

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
 Call<List<Tweet>> getTweets(@Header("Authorization")String header, @Query("count")int count); // the json is not creating due to this count. Link is https://api.twitter.com/1.1/statuses/home_timeline.json?count=50

@GET("trends/place.json")
Call<ArrayList<Trend>> getTrends(@Header("Authorization")String header, @Query("id") String id);

}
