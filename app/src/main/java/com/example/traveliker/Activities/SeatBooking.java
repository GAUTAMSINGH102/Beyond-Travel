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

import com.example.traveliker.Adapters.UserInfoAdapter;
import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.UserModel;
import com.example.traveliker.R;
import com.example.traveliker.Responses.AddUserResponses;
import com.example.traveliker.Responses.GetUserResponse;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SeatBooking extends AppCompatActivity {

    AppCompatButton seatBook;
    TextInputEditText roomNo, noOfDays;
    private RecyclerView userRecyclerView;
    private List<UserModel> userModelList = new ArrayList<>();
    private UserInfoAdapter userInfoAdapter;
    ApiInterface apiInterface;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "keyName";
    private static final String KEY_EMAIL = "keyEmail";
    private static final String KEY_PASSWORD = "keyPassword";
    private static final String KEY_CONTACT = "keyContact";
    private static final String KEY_ADDRESS = "keyAddress";
    private static final String KEY_AGE = "keyAge";

    private static final String KEY_HOTELID = "hotelId";
    private static final String KEY_USERID = "userId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_booking);

        seatBook = findViewById(R.id.seat_book_submit);
        roomNo = findViewById(R.id.entering_roomNo);
        noOfDays = findViewById(R.id.entering_noOfDays);
        userRecyclerView = findViewById(R.id.user_info_recycler_view);
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String personName = sharedPreferences.getString(KEY_NAME, null);
        String personEmail = sharedPreferences.getString(KEY_EMAIL, null);
        String personPassword = sharedPreferences.getString(KEY_PASSWORD, null);
        String personContact = sharedPreferences.getString(KEY_CONTACT, null);
        String personAddress = sharedPreferences.getString(KEY_ADDRESS, null);
        String personAge = sharedPreferences.getString(KEY_AGE, null);

//        String hotelId = sharedPreferences.getString(KEY_HOTELID, null);
//        String userId = sharedPreferences.getString(KEY_USERID, null);
//
//        Toast.makeText(SeatBooking.this, hotelId, Toast.LENGTH_SHORT).show();
//        Toast.makeText(SeatBooking.this, userId, Toast.LENGTH_SHORT).show();

        getdata(personName, personEmail, personPassword, personContact, personAddress, personAge);

        String hotelId = sharedPreferences.getString(KEY_HOTELID, null);
        String userId = sharedPreferences.getString(KEY_USERID, null);

        Toast.makeText(SeatBooking.this, hotelId, Toast.LENGTH_SHORT).show();
        Toast.makeText(SeatBooking.this, userId, Toast.LENGTH_SHORT).show();

        seatBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noOfRoomString = roomNo.getText().toString();
                String noOfDaysString = noOfDays.getText().toString();

                if(noOfRoomString.equals("")) {
                    roomNo.setError("Field Is Empty");
                } else {
                    if(noOfDaysString.equals("")) {
                        noOfDays.setError("Field Is Empty");
                    } else {
                        storeData(noOfDaysString, noOfRoomString, hotelId, userId);
                        Toast.makeText(SeatBooking.this, "Storing Data", Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent = new Intent(SeatBooking.this, Razorpay.class);
                startActivity(intent);
            }
        });

    }

    private void storeData(String noOfDays, String noOfRoom, String hotelId, String userId) {

        apiInterface.addhotelbooking(noOfDays, noOfRoom, hotelId, userId).enqueue(new Callback<AddUserResponses>() {
            @Override
            public void onResponse(Call<AddUserResponses> call, Response<AddUserResponses> response) {
                try {
                    if(response.body().getStatus().equals("1")) {
                        Toast.makeText(SeatBooking.this, " Seat Detail Added Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(SeatBooking.this, "Seat Detail Not Added Successfully", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(SeatBooking.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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