package com.example.aman.twitter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aman.twitter.Networks.ApiInterface;
import com.example.aman.twitter.Networks.RetrofitConnection;
import com.example.aman.twitter.Networks.Trend;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aman on 23-07-2017.
 */

public class TrendingTopics extends Fragment {
ListView listView;

    ArrayAdapter<String> adapter;
    ArrayList<String> Topics;
ArrayList<Trend> mTrendlist;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = this.getActivity();
        View v = inflater.inflate(R.layout.framents_trend, container, false);
        listView = (ListView) v.findViewById(R.id.Trendlist);
        Topics = new ArrayList<>();
        adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,Topics);
        listView.setAdapter(adapter);
        HeaderCreation headerCreation = new HeaderCreation("https://api.twitter.com/1.1/trends/place.json");
        String header = headerCreation.getHeader();
        ApiInterface apiInterface = RetrofitConnection.getInstance(context).create(ApiInterface.class);
        Call<ArrayList<Trend>> call = apiInterface.getTrends(mytweetactivity.id+"",header,1+""); //Is this Correct? this is link to get the trends name  https://dev.twitter.com/rest/reference/get/trends/place
        call.enqueue(new Callback<ArrayList<Trend>>() {
            @Override
            public void onResponse(Call<ArrayList<Trend>> call, Response<ArrayList<Trend>> response) {
                if(response.isSuccessful()) {   // Response is coming unsuccessful
                    mTrendlist = response.body();
                    Log.i("mTrendlistSize",mTrendlist.size()+"");
                    Trend trend = mTrendlist.get(0);
                    ArrayList<Trend.trends> list = trend.getrends();
                    for (int i = 0; i < list.size(); i++) {
                        String name = list.get(i).getName();
                        Topics.add(name);
                    }
                    listView.setAdapter(adapter);
                } else {
                    Log.e("Error Code", String.valueOf(response.code()));
                    Log.e("Error Code", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Trend>> call, Throwable t) {

            }
        });
        return v;
    }
}
