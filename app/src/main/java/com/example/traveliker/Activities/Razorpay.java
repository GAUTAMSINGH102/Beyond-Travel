package com.example.traveliker.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.traveliker.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;


public class Razorpay extends AppCompatActivity implements PaymentResultListener {

    Button pay;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "keyName";
    private static final String KEY_EMAIL = "keyEmail";
    private static final String KEY_CONTACT = "keyContact";

    private static final String KEY_HOTELPRICE = "hotelPrice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razorpay);

        pay = findViewById(R.id.razorpay);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String personName = sharedPreferences.getString(KEY_NAME, null);
        String personEmail = sharedPreferences.getString(KEY_EMAIL, null);
        String personContact = sharedPreferences.getString(KEY_CONTACT, null);

        String hotelPrice = sharedPreferences.getString(KEY_HOTELPRICE, null);

        String samount = "10021";

        int amount = Math.round(Float.parseFloat(samount) * 100);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_18idz9CGsJ5YkA");
                checkout.setImage(R.drawable.razorpay);

                JSONObject object = new JSONObject();

                try {
                    object.put("name", personName);
                    object.put("description", "Test Payment");
                    object.put("theme.color", "#0093DD");
                    object.put("currency", "INR");
                    object.put("currency", "INR");
                    object.put("amount", amount);
                    object.put("prefill.contact", personContact);
                    object.put("prefill.email", personEmail);
                    checkout.open(Razorpay.this,object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment Id");
        builder.setMessage(s);
        builder.show();

//            Intent intent = new Intent(Razorpay.this, TransportTicket.class);
//            startActivity(intent);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}