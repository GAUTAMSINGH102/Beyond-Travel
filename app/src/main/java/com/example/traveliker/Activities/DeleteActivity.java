package com.example.traveliker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.traveliker.Adapters.TransportAdapter;
import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.TransportModel;
import com.example.traveliker.R;
import com.example.traveliker.Responses.GetTransportResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DeleteActivity extends AppCompatActivity {

    private RecyclerView deleteRecyclerView;
    private List<TransportModel> transportModelList = new ArrayList<>();
    private TransportAdapter transportAdapter;
    ApiInterface apiInterface;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_FROM = "keyFrom";
    private static final String KEY_TO = "keyTo";
    private static final String KEY_DATE = "keyDate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        deleteRecyclerView = findViewById(R.id.delete_recyclerView);
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);

        getdata();
    }

    private void setadapter(List<TransportModel> transportModels) {
        transportAdapter = new TransportAdapter(transportModels, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        deleteRecyclerView.setLayoutManager(layoutManager);
        deleteRecyclerView.setAdapter(transportAdapter);
    }

    private void getdata() {
        Log.e("TAG", "Working");

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String placefrom = sharedPreferences.getString(KEY_FROM, null);
        String placeto = sharedPreferences.getString(KEY_TO, null);
        String placedate = sharedPreferences.getString(KEY_DATE, null);

//        apiInterface.getalltransport().enqueue(new Callback<GetTransportResponse>() {
        apiInterface.gettransport(placefrom, placeto, placedate).enqueue(new Callback<GetTransportResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetTransportResponse> call, @NonNull Response<GetTransportResponse> response) {
                try {
                    if (response != null) {
                        if (response.body().getStatus().equals("1")) {
                            setadapter(response.body().getData());
                            Log.e("TAG", "try if Working");
                            Log.e("TAG", "dELETE aCTIVITY");
                        } else {
                            Toast.makeText(DeleteActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "try else Working");
                        }
                    }
                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                    Log.e("TAG", "catch Working");
                }
            }

            @Override
            public void onFailure(Call<GetTransportResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Log.e("TAG", "Failure Working");
            }
        });
    }
}