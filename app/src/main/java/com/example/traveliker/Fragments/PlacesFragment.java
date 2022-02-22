package com.example.traveliker.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.traveliker.Adapters.PlacesAdapter;
import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.PlacesModel;
import com.example.traveliker.R;
import com.example.traveliker.Responses.GetPlacesResponses;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlacesFragment extends Fragment {

    private RecyclerView placeRecyclerView;
    private List<PlacesModel> placesModelList = new ArrayList<>();
    private PlacesAdapter placesAdapter;
    ApiInterface apiInterface;

    SearchView searchView;

    public PlacesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_places, container, false);

        placeRecyclerView = rootView.findViewById(R.id.places_recyclerView);
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
        searchView = rootView.findViewById(R.id.search_places);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                placesAdapter.getFilter().filter(newText);
                return false;
            }
        });

        //-----------To add data manually-----------//
//        placesModelList.add(new PlacesModel(R.drawable.ic_launcher_background, "Akshardham", "Ahmedabad", "This is Very Beautiful Place"));
//        placesModelList.add(new PlacesModel(R.drawable.ic_launcher_background, "Akshardham", "Ahmedabad", "This is Very Beautiful Place"));
//        placesModelList.add(new PlacesModel(R.drawable.ic_launcher_background, "Akshardham", "Ahmedabad", "This is Very Beautiful Place"));
//
//        PlacesAdapter placesAdapter = new PlacesAdapter(placesModelList, getActivity());
//
//        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getActivity());
//
//        placeRecyclerView.setLayoutManager(linearLayoutManager);
//
//        placeRecyclerView.setAdapter(placesAdapter);
        //-----------End of adding data Manually-----------//

        //----------Function Calling---------------//
        getdata();

        return rootView;
    }

    private void setadapter(List<PlacesModel> placesModels) {
        placesAdapter = new PlacesAdapter(placesModels, getActivity());

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());

        placeRecyclerView.setLayoutManager(layoutManager);
        placeRecyclerView.setAdapter(placesAdapter);
    }

    private void getdata() {
        Log.e("TAG", "Working");
        apiInterface.getplaces().enqueue(new Callback<GetPlacesResponses>() {
            @Override
            public void onResponse(@NonNull Call<GetPlacesResponses> call, @NonNull Response<GetPlacesResponses> response) {
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
            public void onFailure(Call<GetPlacesResponses> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Log.e("TAG", "Failure Working");
            }
        });

    }
}