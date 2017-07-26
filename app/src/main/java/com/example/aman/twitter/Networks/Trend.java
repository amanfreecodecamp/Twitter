package com.example.aman.twitter.Networks;

import java.util.ArrayList;

import retrofit2.http.Url;

/**
 * Created by Aman on 22-07-2017.
 */

public class Trend {
    public Trend() {
    }

    public ArrayList<TrendObject> trends;

    public static class TrendObject {

        public TrendObject() {
        }

        public String name;
        public String url;
        public String query;
        public int tweet_volume;
    }
}
