package com.example.traveliker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Fragments.TransportFragment;
import com.example.traveliker.R;
import com.example.traveliker.Responses.GetTransportResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TransportActivity extends AppCompatActivity {

    TextInputEditText from_search,to_search, date_search;
//    TextInputEditText to_search;
    AppCompatButton search;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_FROM = "keyFrom";
    private static final String KEY_TO = "keyTo";
    private static final String KEY_DATE = "keyDate";

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        from_search = findViewById(R.id.from_search);
        to_search = findViewById(R.id.to_search);
        date_search = findViewById(R.id.date_search);
        search = findViewById(R.id.search);

        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String take_from = from_search.getText().toString();
                String take_to = to_search.getText().toString();
                String take_date = date_search.getText().toString();

                sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_FROM, take_from);
                editor.putString(KEY_TO, take_to);
                editor.putString(KEY_DATE, take_date);
                editor.apply();

                getParticularTransport(take_from,take_to,take_date);
            }
        });
    }

    private void getParticularTransport(String take_from, String take_to, String take_date) {
        apiInterface.gettransport(take_from,take_to,take_date).enqueue(new Callback<GetTransportResponse>() {
            @Override
            public void onResponse(Call<GetTransportResponse> call, Response<GetTransportResponse> response) {
                try {
                    if (response != null) {
                        Toast.makeText(TransportActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if (response.body().getStatus().equals("1")) {

                            Intent intent = new Intent(TransportActivity.this, DeleteActivity.class);
                            startActivity(intent);
                        }
                    }
                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<GetTransportResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });
    }

}