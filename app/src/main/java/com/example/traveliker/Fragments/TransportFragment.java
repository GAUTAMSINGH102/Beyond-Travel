package com.example.traveliker.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.traveliker.Activities.TransportActivity;
import com.example.traveliker.Adapters.HotelAdapter;
import com.example.traveliker.Adapters.TransportAdapter;
import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.Models.TransportModel;
import com.example.traveliker.R;
import com.example.traveliker.Responses.GetHotelResponses;
import com.example.traveliker.Responses.GetTransportResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TransportFragment extends Fragment {

    private AppCompatButton searchTransport;

    private RecyclerView transportRecyclerView;
    private List<TransportModel> transportModelList = new ArrayList<>();
    private TransportAdapter transportAdapter;
    ApiInterface apiInterface;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_FROM = "keyFrom";
    private static final String KEY_TO = "keyTo";
    private static final String KEY_DATE = "keyDate";

    public TransportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_transport, container, false);

        searchTransport = rootView.findViewById(R.id.search_transport);

        transportRecyclerView = rootView.findViewById(R.id.allTransport_recyclerView);
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);

        searchTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransportActivity.class);
                startActivity(intent);
            }
        });

        getdata();

        return rootView;
    }

    private void setadapter(List<TransportModel> transportModels) {
        transportAdapter = new TransportAdapter(transportModels, getActivity());

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());

        transportRecyclerView.setLayoutManager(layoutManager);
        transportRecyclerView.setAdapter(transportAdapter);
    }
    //        apiInterface.gettransport(placefrom, placeto, placedate).enqueue(new Callback<GetTransportResponse>() {

    private void getdata() {

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String placefrom = sharedPreferences.getString(KEY_FROM,null);
        String placeto = sharedPreferences.getString(KEY_FROM,null);
        String placedate = sharedPreferences.getString(KEY_FROM,null);

        apiInterface.getalltransport().enqueue(new Callback<GetTransportResponse>() {

            @Override
            public void onResponse(@NonNull Call<GetTransportResponse> call, @NonNull Response<GetTransportResponse> response) {
                try {
                    if (response != null) {
                        if (response.body().getStatus().equals("1")) {
                            setadapter(response.body().getData());
                            Log.e("TAG", "try if Working");
                        } else {
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
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