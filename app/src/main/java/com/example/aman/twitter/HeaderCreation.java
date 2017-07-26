package com.example.aman.twitter;

import com.twitter.sdk.android.core.OAuthSigning;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.HashMap;

/**
 * Created by Aman on 23-07-2017.
 */

public class HeaderCreation {
    String url;
    HashMap<String, String> map;

    HeaderCreation(String url, HashMap<String, String> map) {
        this.url = url;
        this.map = map;
    }

    public String getHeader() {
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        TwitterAuthConfig authConfig = TwitterCore.getInstance().getAuthConfig();
        TwitterAuthToken authToken = session.getAuthToken();
        OAuthSigning authSigning = new OAuthSigning(authConfig, authToken);
        // HashMap<String, String> params = new HashMap<>();
//        params.put("id", String.valueOf(session.getUserId()));
        //   Map<String, String> authHeaders = authSigning.getOAuthEchoHeadersForVerifyCredentials();
        String header = authSigning.getAuthorizationHeader("GET", url, map);
        return header;
    }
}
