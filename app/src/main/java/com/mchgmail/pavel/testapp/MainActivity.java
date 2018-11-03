package com.mchgmail.pavel.testapp;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.mchgmail.pavel.testapp.adapters.TrendingAdapter;
import com.mchgmail.pavel.testapp.helper.Ajax;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class MainActivity extends AppCompatActivity {
    Boolean isEmptySearchView;
    RecyclerView recycler;
    TrendingAdapter trendingAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        recycler = findViewById(R.id.recycler);
///////////////////////////////////////////////////////////////////////////////////////
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        final SearchView searchView = findViewById(R.id.search);
        searchView.onActionViewExpanded();

        searchView.clearFocus();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                isEmptySearchView = query.trim().isEmpty();
                Search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                isEmptySearchView = s.trim().isEmpty();
                Search(s);
                return false;
            }
        });

///////////////////////////////////////////////////////////////////////////////////////
        recycler.setHasFixedSize(true);
        LinearLayoutManager trendingLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(trendingLayoutManager);
        StartTrending();
    }

    public void StartTrending(){


        new Ajax(new Ajax.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                ArrayList<String> images = new ArrayList<>();
                try {
                    JSONObject fieldJson = new JSONObject(output);
                    JSONArray jsonArray = new JSONArray(fieldJson.getString("data"));
                    for (int i = 0; i<jsonArray.length();i+=3){
                        JSONObject iobj0 = jsonArray.getJSONObject(i);
                        JSONObject image0 = new JSONObject(iobj0.getString("images"));
                        JSONObject original_still0 = new JSONObject(image0.getString("original_still"));
                        String url0 = original_still0.getString("url");
                        images.add(url0);

                        JSONObject iobj1 = jsonArray.getJSONObject(i+1);
                        JSONObject image1 = new JSONObject(iobj1.getString("images"));
                        JSONObject original_still1 = new JSONObject(image1.getString("original_still"));
                        String url1 = original_still1.getString("url");
                        images.add(url1);

                        JSONObject iobj2 = jsonArray.getJSONObject(i+2);
                        JSONObject image2 = new JSONObject(iobj2.getString("images"));
                        JSONObject original_still2 = new JSONObject(image2.getString("original_still"));
                        String url2 = original_still2.getString("url");
                        images.add(url2);

                    }
                    trendingAdapter = new TrendingAdapter(getApplicationContext(), images);
                    recycler.setAdapter(trendingAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, "trending", null).execute();


    }

    public void Search (String text){

//        String link = host+searchPath+"&api_key="+apikey+"&q="+text;


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url)

    }

}
