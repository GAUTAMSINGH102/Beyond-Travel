package com.example.traveliker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.traveliker.Adapters.ReviewAdapter;
import com.example.traveliker.Adapters.TransportAdapter;
import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.ReviewModel;
import com.example.traveliker.Models.TransportModel;
import com.example.traveliker.R;
import com.example.traveliker.Responses.GetReviewResponse;
import com.example.traveliker.Responses.GetTransportResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlaceDetailReview extends AppCompatActivity {

    private RecyclerView reviewRecyclerView;
    private List<ReviewModel> reviewModelList = new ArrayList<>();
    private ReviewAdapter reviewAdapter;
    ApiInterface apiInterface;
    AppCompatButton addReview;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail_review);

        addReview = findViewById(R.id.add_review);

        reviewRecyclerView = findViewById(R.id.review_recyclerView);
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);

        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceDetailReview.this, AddReview.class);
                startActivity(intent);
            }
        });

        getdata();
    }

    private void setadapter(List<ReviewModel> reviewModels) {
        reviewAdapter = new ReviewAdapter(reviewModels, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        reviewRecyclerView.setLayoutManager(layoutManager);
        reviewRecyclerView.setAdapter(reviewAdapter);
    }

    private void getdata() {
        Log.e("TAG", "Working");

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String placename = sharedPreferences.getString(KEY_NAME, null);

        apiInterface.getreview(placename).enqueue(new Callback<GetReviewResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetReviewResponse> call, @NonNull Response<GetReviewResponse> response) {
                try {
                    if (response != null) {
                        if (response.body().getStatus().equals("1")) {
                            setadapter(response.body().getData());
                            Log.e("TAG", "try if Working");
                        } else {
                            Toast.makeText(PlaceDetailReview.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "try else Working");
                        }
                    }
                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                    Log.e("TAG", "catch Working");
                }
            }

            @Override
            public void onFailure(Call<GetReviewResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Log.e("TAG", "Failure Working");
            }
        });
    }
}