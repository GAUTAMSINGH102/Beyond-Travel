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
import android.widget.Button;
import android.widget.Toast;

import com.example.traveliker.Adapters.HotelAdapter;
import com.example.traveliker.Adapters.UserInfoAdapter;
import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.Models.UserModel;
import com.example.traveliker.R;
import com.example.traveliker.Responses.GetUserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HotelBook extends AppCompatActivity {

    private RecyclerView userRecyclerView;
    private List<UserModel> userModelList = new ArrayList<>();
    private UserInfoAdapter userInfoAdapter;
    ApiInterface apiInterface;
    Button proceedFurther;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "keyName";
    private static final String KEY_EMAIL = "keyEmail";
    private static final String KEY_PASSWORD = "keyPassword";
    private static final String KEY_CONTACT = "keyContact";
    private static final String KEY_ADDRESS = "keyAddress";
    private static final String KEY_AGE = "keyAge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_book);

        userRecyclerView = findViewById(R.id.user_info_recycler_view_hotel);
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
        proceedFurther = findViewById(R.id.next_activity);

//        String personName = getIntent().getStringExtra("personName");
//        String personEmail = getIntent().getStringExtra("personEmail");
//        String personPassword = getIntent().getStringExtra("personPassword");
//        String personContact = getIntent().getStringExtra("personContact");
//        String personAddress = getIntent().getStringExtra("personAddress");
//        String personAge = getIntent().getStringExtra("personAge");

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String personName = sharedPreferences.getString(KEY_NAME, null);
        String personEmail = sharedPreferences.getString(KEY_EMAIL, null);
        String personPassword = sharedPreferences.getString(KEY_PASSWORD, null);
        String personContact = sharedPreferences.getString(KEY_CONTACT, null);
        String personAddress = sharedPreferences.getString(KEY_ADDRESS, null);
        String personAge = sharedPreferences.getString(KEY_AGE, null);

        getdata(personName, personEmail, personPassword, personContact, personAddress, personAge);

        proceedFurther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelBook.this, Razorpay.class);
                startActivity(intent);
            }
        });
    }

    private void setadapter(List<UserModel> userModels) {
        userInfoAdapter = new UserInfoAdapter(userModels, this);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        userRecyclerView.setLayoutManager(layoutManager);
        userRecyclerView.setAdapter(userInfoAdapter);
    }

    private void getdata(String userName, String userEmail, String userPassword, String userContact, String userAddress, String userAge) {
        Log.e("TAG", "Working");

        apiInterface.getuser(userName, userEmail, userPassword, userContact, userAddress, userAge).enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetUserResponse> call, @NonNull Response<GetUserResponse> response) {
                try {
                    if (response != null) {
                        if (response.body().getStatus().equals("1")) {
                            setadapter(response.body().getData());
                            Log.e("TAG", "try if Working");
                        } else {
                            Toast.makeText(HotelBook.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "try else Working");
                        }
                    }
                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                    Log.e("TAG", "catch Working");
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Log.e("TAG", "Failure Working");
            }
        });

    }
}