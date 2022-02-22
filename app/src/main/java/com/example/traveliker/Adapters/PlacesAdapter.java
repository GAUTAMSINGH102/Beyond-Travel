package com.example.traveliker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveliker.Activities.PlaceDetailReview;
import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.Models.PlacesModel;
import com.example.traveliker.R;
import com.example.traveliker.Responses.GetReviewResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> implements Filterable {

    private List<PlacesModel> placesModelList;
    List<PlacesModel> placesFilterData;
    private Context context;
    ApiInterface apiInterface;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "placeid";

    public PlacesAdapter(List<PlacesModel> placesModelList, Context context) {
        this.placesModelList = placesModelList;
        this.context = context;
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
        placesFilterData = new ArrayList<>(placesModelList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlacesModel placesModel = placesModelList.get(position);

//        holder.place_image.setImageResource(placesModel.getPlace());
//        holder.place_name.setText(placesModel.getName());
//        holder.place_location.setText(placesModel.getLocation());
//        holder.place_info.setText(placesModel.getInformation());


        // For Image
        Picasso.get().load(placesModelList.get(holder.getAdapterPosition()).getPhoto())
                .into(holder.place_image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        holder.place_name.setText(placesModelList.get(holder.getAdapterPosition()).getName());
        holder.place_location.setText(placesModelList.get(holder.getAdapterPosition()).getLocation());
        holder.place_info.setText(placesModelList.get(holder.getAdapterPosition()).getInformation());

        holder.placeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String placename = placesModelList.get(holder.getAdapterPosition()).getName();
                String placeId = placesModelList.get(holder.getAdapterPosition()).getPlaceid()+"";

                sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, placename);
                editor.putString(KEY_ID, placeId);
                editor.apply();

                getReview(placename, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return placesModelList.size();
    }

    @Override
    public Filter getFilter() {
        return placeFilterSearch;
    }

    private Filter placeFilterSearch = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<PlacesModel> filterList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0) {
                filterList.addAll(placesFilterData);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(PlacesModel item: placesFilterData) {
                    if((item.getLocation().toLowerCase().contains(filterPattern)) || (item.getName().toLowerCase().contains(filterPattern))) {
                        filterList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            placesModelList.clear();
            placesModelList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView place_image;
        TextView place_name, place_location, place_info;
        AppCompatButton placeDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            place_image = itemView.findViewById(R.id.place_image);
            place_name = itemView.findViewById(R.id.place_name);
            place_location = itemView.findViewById(R.id.place_location);
            place_info = itemView.findViewById(R.id.place_info);
            placeDetail = itemView.findViewById(R.id.placeDetail_button);
        }
    }

    private void getReview(String placename, int pose) {
        apiInterface.getreview(placename).enqueue(new Callback<GetReviewResponse>() {
            @Override
            public void onResponse(Call<GetReviewResponse> call, Response<GetReviewResponse> response) {
                try {
                    if (response != null) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if (response.body().getStatus().equals("1")) {

                            Intent intent = new Intent(context, PlaceDetailReview.class);
                            context.startActivity(intent);
                        }
                    }
                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<GetReviewResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });
    }

}
