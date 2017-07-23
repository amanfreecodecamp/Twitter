package com.example.aman.twitter.Networks;

import java.util.ArrayList;

import retrofit2.http.Url;

/**
 * Created by Aman on 22-07-2017.
 */

public class Trend  {
  public   ArrayList<trends> trends;

    public ArrayList<Trend.trends> getrends() {
        return trends;
    }

   public static class trends {
        String name;
        Url url;
        String query;
        int tweet_volume;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Url getUrl() {
            return url;
        }

        public void setUrl(Url url) {
            this.url = url;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public int getTweet_volume() {
            return tweet_volume;
        }

        public void setTweet_volume(int tweet_volume) {
            this.tweet_volume = tweet_volume;
        }
    }
}
