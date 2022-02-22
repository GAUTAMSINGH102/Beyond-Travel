package com.example.traveliker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.UserModel;
import com.example.traveliker.R;
import com.example.traveliker.Responses.AddUserResponses;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddReview extends AppCompatActivity {

    TextInputEditText addReview;
    AppCompatButton submitReview;
    ApiInterface apiInterface;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_ID = "placeid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        addReview = findViewById(R.id.writing_review);
        submitReview = findViewById(R.id.review_submit);

        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String placeId = sharedPreferences.getString(KEY_ID, null);

        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placeReview = addReview.getText().toString();

                if(placeReview.equals("")) {
                    addReview.setError("Field Is Empty");
                } else {
                    storeData(placeReview, placeId);
                    Toast.makeText(AddReview.this, "Storing Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void storeData(String placeReview, String placeId) {

        apiInterface.addreview(placeReview, placeId).enqueue(new Callback<AddUserResponses>() {
            @Override
            public void onResponse(Call<AddUserResponses> call, Response<AddUserResponses> response) {
                try {
                    if(response.body().getStatus().equals("1")) {
                        Toast.makeText(AddReview.this, "Review Added Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddReview.this, "Review Not Added Successfully", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e) {
                    Log.e("AddExp", e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<AddUserResponses> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });
    }
}