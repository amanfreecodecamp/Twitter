package com.example.aman.twitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity {
    TwitterLoginButton loginButton;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Twitter.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (TwitterLoginButton) findViewById(R.id.login);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                Log.i("Token is",token);
                String secret = authToken.secret;
                Log.i("Secret is", secret);
                id = session.getUserId();
                Log.i("ID IS", id+"");


              //  getSupportFragmentManager().beginTransaction().replace(R.id., mytweetFragment).commit();

/*
CourseDetailFragment courseDetailFragment = new CourseDetailFragment();
            Bundle b = new Bundle();
            b.putSerializable("course", c);
            courseDetailFragment.setArguments(b);

 */
               Intent i = new Intent(MainActivity.this,mytweetactivity.class);
                i.putExtra("User_id",id);
                startActivity(i);
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(MainActivity.this,"Connection failed",Toast.LENGTH_SHORT).show();
            }
        });


//        TwitterAuthConfig authConfig = TwitterCore.getInstance().getAuthConfig();
//        TwitterAuthToken authToken = session.getAuthToken();
//        OAuthSigning oauthSigning = new OAuthSigning(authConfig, authToken);
//        Map<String, String> authHeaders = oauthSigning.getOAuthEchoHeadersForVerifyCredentials();
//        URL url = null;
//        try {
//            url = new URL("http://api.yourbackend.com/check_credentials.json");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        HttpsURLConnection connection = null;
//        try {
//            connection = (HttpsURLConnection)url.openConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            connection.setRequestMethod("GET");
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        }
//
//// Add OAuth Echo headers to request
//        for (Map.Entry<String, String> entry : authHeaders.entrySet()) {
//            connection.setRequestProperty(entry.getKey(), entry.getValue());
//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.

        loginButton.onActivityResult(requestCode, resultCode, data);



    }
}
