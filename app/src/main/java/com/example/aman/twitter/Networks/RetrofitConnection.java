package com.example.aman.twitter.Networks;

import android.content.Context;

import com.example.aman.twitter.mytweetactivity;
import com.twitter.sdk.android.core.OAuthSigning;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aman on 20-07-2017.
 */

public class RetrofitConnection {

    static Retrofit INSTANCE;


    public static Retrofit getInstance(Context context) {
        if(INSTANCE== null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl("https://api.twitter.com/1.1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            // ApiInterface apiInterface =  retrofit.create(ApiInterface.class);
        }
        return INSTANCE;
    }
}
