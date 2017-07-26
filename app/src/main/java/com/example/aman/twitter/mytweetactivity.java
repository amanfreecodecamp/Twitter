package com.example.aman.twitter;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

public class mytweetactivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener,ViewPager.OnPageChangeListener{
    ViewPager vp;
    TabLayout tabLayout;
    FragmentTabHost fragmentTabHost;
    public static final long id = getId();

    private static long getId() {
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        TwitterAuthToken authToken = session.getAuthToken();
        String token = authToken.token;
        Log.i("Token is", token);
        String secret = authToken.secret;
        Log.i("Secret is", secret);
        long id = session.getUserId();
        Log.i("ID IS", id + "");
        return id;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  Twitter.initialize(this);

        // id = i.getLongExtra("User_id", -1);
        setContentView(R.layout.activity_mytweetactivity);

        Log.i("User_id", id + "");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //VIEWPAGER
        vp= (ViewPager) findViewById(R.id.container);
        this.addPages();
        //TABLAYOUT
        tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(vp);
        tabLayout.setOnTabSelectedListener(this);
    }
    private void addPages()
    {
        PageAdapter pagerAdapter=new PageAdapter(this.getSupportFragmentManager());
        pagerAdapter.addFragment(new MytweetFragment());
     //   pagerAdapter.addFragment(new Test());
        pagerAdapter.addFragment(new TrendingTopics());
       // pagerAdapter.addFragment(new DocumentaryFragment());
        //SET ADAPTER TO VP
        vp.setAdapter(pagerAdapter);
    }
    public void onTabSelected(TabLayout.Tab tab) {
        vp.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
       /* MytweetFragment mytweetFragment = new MytweetFragment();
        Bundle b = new Bundle();
        b.putLong("Session_id",id);
        mytweetFragment.setArguments(b);
        getFragmentManager().beginTransaction().add(R.id.containerLayout,mytweetFragment).commit();*/

    }



