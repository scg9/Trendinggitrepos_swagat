package com.webkeens.kutumbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements SwipeRefreshLayout.OnRefreshListener{

    final String URL_GET_DATA = "https://gh-trending-api.herokuapp.com/repositories";
    RecyclerView recyclerView;
    RepositoryAdapter adapter;
    List<Repository> repositoryList;
   // ProgressBar progressBar;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
       // progressBar=findViewById(R.id.progressBar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // SwipeRefreshLayout
        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.purple_200,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(true);

                // Fetching data from server
                loadRepository();
            }
        });

        repositoryList = new ArrayList<>();

        loadRepository();
    }
    @Override
    public void onRefresh() {

        // Fetching data from server
        loadRepository();
    }
    private void loadRepository() {

        //progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.GONE);
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);


                                String avatar="";

                                JSONArray jsonArraybuiltBy =obj.getJSONArray("builtBy");
                                if(jsonArraybuiltBy.length()>0)
                                    avatar=jsonArraybuiltBy.getJSONObject(0).getString("avatar");

                                Repository repository = new Repository(
                                        obj.getInt("rank"),
                                        obj.getString("username"),
                                        obj.getString("repositoryName"),
                                        obj.getString("description"),
                                        obj.getString("language"),
                                        avatar,
                                        obj.getInt("totalStars"),
                                        obj.getInt("forks")
                                );

                                repositoryList.add(repository);
                            }

                            adapter = new RepositoryAdapter(repositoryList, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            mSwipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}