package com.example.traveliker.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.traveliker.Adapters.HotelAdapter;
import com.example.traveliker.Adapters.PlacesAdapter;
import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.Models.PlacesModel;
import com.example.traveliker.R;
import com.example.traveliker.Responses.GetHotelResponses;
import com.example.traveliker.Responses.GetPlacesResponses;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HotelFragment extends Fragment {

    private RecyclerView hotelRecyclerView;
    private List<HotelModel> hotelModelList = new ArrayList<>();
    private HotelAdapter hotelAdapter;
    ApiInterface apiInterface;

    SearchView searchView;

    public HotelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hotel, container, false);

        hotelRecyclerView = rootView.findViewById(R.id.allHotel_recyclerView);
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
        searchView = rootView.findViewById(R.id.search_hotel);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                hotelAdapter.getFilter().filter(newText);
                return false;
            }
        });

        getdata();

        return rootView;
    }

    private void setadapter(List<HotelModel> hotelModels) {
        hotelAdapter = new HotelAdapter(hotelModels, getActivity());

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());

        hotelRecyclerView.setLayoutManager(layoutManager);
        hotelRecyclerView.setAdapter(hotelAdapter);
    }

    private void getdata() {
        Log.e("TAG", "Working");
        apiInterface.getallhotel().enqueue(new Callback<GetHotelResponses>() {
            @Override
            public void onResponse(@NonNull Call<GetHotelResponses> call, @NonNull Response<GetHotelResponses> response) {
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
            public void onFailure(Call<GetHotelResponses> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Log.e("TAG", "Failure Working");
            }
        });

    }
}