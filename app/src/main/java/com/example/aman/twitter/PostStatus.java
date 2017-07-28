package com.example.aman.twitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aman.twitter.Networks.ApiInterface;
import com.example.aman.twitter.Networks.RetrofitConnection;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostStatus extends AppCompatActivity {
Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_status);
        editText = (EditText) findViewById(R.id.editText);
        Log.i("Post status","Inside Me");
        button = (Button) findViewById(R.id.update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("Status",text);
             HeaderCreation headerCreation = new HeaderCreation("https://api.twitter.com/1.1/statuses/update.json",map);
                String header = headerCreation.getHeader();
                ApiInterface apiInterface = RetrofitConnection.getInstance();
                Call<Tweet> call = apiInterface.getTweet(header,text);
call.enqueue(new Callback<Tweet>() {
    @Override
    public void onResponse(Call<Tweet> call, Response<Tweet> response) {
        if(response.isSuccessful()) {
            Toast.makeText(PostStatus.this,"Posted",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(PostStatus.this,"Failed",Toast.LENGTH_SHORT).show();
            Log.e("Error Code",response.code()+"");
            Log.e("Error Code",response.body()+"");
        }
    }

    @Override
    public void onFailure(Call<Tweet> call, Throwable t) {
            Log.e("Failed",t.getMessage());
    }
});
                PostStatus.this.finish();
            }
        });
    }
}
