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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aman.twitter.Networks.ApiInterface;
import com.example.aman.twitter.Networks.RetrofitConnection;
import com.example.aman.twitter.Networks.Trend;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aman on 23-07-2017.
 */

public class TrendingTopics extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {
    ListView listView;

    ArrayAdapter<String> adapter;
    ArrayList<String> Topics;
    ArrayList<Trend> mTrendlist;
    Context context;
    Trend trend;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<Trend.TrendObject> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = this.getActivity();
        View v = inflater.inflate(R.layout.framents_trend, container, false);
        listView = (ListView) v.findViewById(R.id.Trendlist);
        swipeRefreshLayout = v.findViewById(R.id.swipe_refresh_layout_Trend);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh, R.color.refresh1, R.color.refresh2);
        Topics = new ArrayList<>();
        adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, Topics);
        listView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                fetchTrends();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                String query = list.get(pos).query;
                Toast.makeText(context, pos+"", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    @Override
    public void onRefresh() {
        fetchTrends();
    }

    public void fetchTrends() {
        swipeRefreshLayout.setRefreshing(true);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "23424848"); //Ideally you should use yahoo earth api to get the WOEID of Your location
        HeaderCreation headerCreation = new HeaderCreation("https://api.twitter.com/1.1/trends/place.json", map);
        String header = headerCreation.getHeader();

        ApiInterface apiInterface = RetrofitConnection.getInstance(context).create(ApiInterface.class);
        Call<ArrayList<Trend>> call = apiInterface.getTrends(header, "23424848"); //Is this Correct? this is link to get the TrendObject name  https://dev.twitter.com/rest/reference/get/trends/place
        call.enqueue(new Callback<ArrayList<Trend>>() {
            @Override
            public void onResponse(Call<ArrayList<Trend>> call, Response<ArrayList<Trend>> response) {
                if (response.isSuccessful()) {   // Response is coming unsuccessful
                    mTrendlist = response.body();
                    Log.i("mTrendlistSize", mTrendlist.size() + "");
                    trend = mTrendlist.get(0);
                    list = trend.trends;
                    Log.i("List Size",list.size()+"");
                    Topics.clear();
                    for (int i = 0; i < list.size(); i++) {
                        String name = list.get(i).name;
                        Topics.add(name);
                    }
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    Log.e("Error Code", String.valueOf(response.code()));
                    Log.e("Error Code", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Trend>> call, Throwable t) {
                Log.d("failure", t.getMessage());
            }
        });
    }
}
