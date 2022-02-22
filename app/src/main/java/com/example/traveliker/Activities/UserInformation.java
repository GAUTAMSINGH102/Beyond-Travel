package com.example.traveliker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.TransportModel;
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

public class UserInformation extends AppCompatActivity {

    TextView hotelNameInUserInfo;
    TextInputEditText userName, userPassword, userContact, userEmail, userAddress, userAge;
    AppCompatButton userSubmit;
    private List<UserModel> userModelList = new ArrayList<>();
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
    private static final String KEY_HOTELNAME = "hotelName";

    private static final String KEY_TRANSPORTID = "transportId";
    private static final String KEY_TRANSPORTNAME = "transportName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        userName = findViewById(R.id.user_name);
        userPassword = findViewById(R.id.user_password);
        userContact = findViewById(R.id.user_contact);
        userEmail = findViewById(R.id.user_email);
        userAddress = findViewById(R.id.user_address);
        userAge = findViewById(R.id.user_age);
        userSubmit = findViewById(R.id.user_submit);
        hotelNameInUserInfo = findViewById(R.id.hotel_name_userInfo);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//        String hotelId = sharedPreferences.getString(KEY_HOTELID, null);
//        String hotelName = sharedPreferences.getString(KEY_HOTELNAME, null);

        String transportId = sharedPreferences.getString(KEY_TRANSPORTID, null);
        String transportName = sharedPreferences.getString(KEY_TRANSPORTNAME, null);

//        int hotelId = getIntent().getIntExtra("h_id",0);
//        String hotelName = getIntent().getStringExtra("h_name");
//        hotelNameInUserInfo.setText(hotelName);
//        hotelNameInUserInfo.setText(transportName);

//        Toast.makeText(UserInformation.this, hotelId+"", Toast.LENGTH_SHORT).show();
//        Toast.makeText(UserInformation.this, hotelName, Toast.LENGTH_SHORT).show();

        Toast.makeText(UserInformation.this, transportId+"", Toast.LENGTH_SHORT).show();
        Toast.makeText(UserInformation.this, transportName, Toast.LENGTH_SHORT).show();

        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);

        userSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameString = userName.getText().toString();
                String userPasswordString = userPassword.getText().toString();
                String userContactString = userContact.getText().toString();
                String userEmailString = userEmail.getText().toString();
                String userAddressString = userAddress.getText().toString();
                String userAgeString = userAge.getText().toString();

                if(userNameString.equals("")) {
                    userName.setError("Field is Empty");
                }else {
                    if(userPasswordString.equals("")) {
                        userPassword.setError("Field Is Empty");
                    } else {
                        if(userContactString.equals("")) {
                            userContact.setError("Field Is Empty");
                        } else {
                            if(userEmailString.equals("")) {
                                userEmail.setError("Field Is Empty");
                            } else {
                                if(userAddressString.equals("")) {
                                    userAddress.setError("Field Is Empty");
                                } else {
                                    if(userAgeString.equals("")) {
                                        userAge.setError("Field Is Empty");
                                    } else {
                                        storeData(userNameString, userPasswordString, userContactString, userEmailString, userAddressString, userAgeString);
//                                        getdata(userNameString, userPasswordString, userContactString, userEmailString, userAddressString, userAgeString);
                                        Toast.makeText(UserInformation.this, "Storing Data", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(UserInformation.this, HotelBook.class);
//                                        intent.putExtra("personName", userNameString);
//                                        intent.putExtra("personEmail", userEmailString);
//                                        intent.putExtra("personPassword", userPasswordString);
//                                        intent.putExtra("personContact", userContactString);
//                                        intent.putExtra("personAddress", userAddressString);
//                                        intent.putExtra("personAge", userAgeString);
                                        startActivity(intent);

                                        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(KEY_NAME, userNameString);
                                        editor.putString(KEY_EMAIL, userEmailString);
                                        editor.putString(KEY_PASSWORD, userPasswordString);
                                        editor.putString(KEY_CONTACT, userContactString);
                                        editor.putString(KEY_ADDRESS, userAddressString);
                                        editor.putString(KEY_AGE, userAgeString);
                                        editor.apply();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private void storeData(String userName, String userPassword, String userContact, String userEmail, String userAddress, String userAge) {

        apiInterface.adduser(userName, userEmail, userPassword, userContact, userAddress, userAge).enqueue(new Callback<AddUserResponses>() {
            @Override
            public void onResponse(Call<AddUserResponses> call, Response<AddUserResponses> response) {
                try {
                    if(response.body().getStatus().equals("1")) {
                        Toast.makeText(UserInformation.this, "Add Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(UserInformation.this, "Not Added Successfully", Toast.LENGTH_SHORT).show();
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

//    private void setadapter(List<UserModel> userModels) {
//        userModels = new ArrayList<>();
////      UserModel userid = userModels.get(0);
//        Log.e("TAG", String.valueOf(userModels.get(0)));
//    }
//
//    private void getdata(String userName, String userPassword, String userContact, String userEmail, String userAddress, String userAge) {
//        Log.e("TAG", "Working");
//
//        apiInterface.getuser(userName, userEmail, userPassword, userContact, userAddress, userAge).enqueue(new Callback<GetUserResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<GetUserResponse> call, @NonNull Response<GetUserResponse> response) {
//                try {
//                    if (response != null) {
//                        if (response.body().getStatus().equals("1")) {
//                            setadapter(response.body().getData());
//                            Log.e("TAG", "try if Working");
//                        } else {
//                            Toast.makeText(UserInformation.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                            Log.e("TAG", "try else Working");
//                        }
//                    }
//                } catch (Exception e) {
//                    Log.e("exp", e.getLocalizedMessage());
//                    Log.e("TAG", "catch Working");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetUserResponse> call, Throwable t) {
//                Log.e("failure", t.getLocalizedMessage());
//                Log.e("TAG", "Failure Working");
//            }
//        });
//
//    }
}