package com.example.traveliker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveliker.Activities.HotelDetail;
import com.example.traveliker.Activities.UserInformation;
import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.Models.PlacesModel;
import com.example.traveliker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> implements Filterable {

    private List<HotelModel> hotelModelList;
    List<HotelModel> hotelFilterData;
    private Context context;
    ApiInterface apiInterface;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_HOTELID = "hotelId";
    private static final String KEY_HOTELNAME = "hotelName";
    private static final String KEY_HOTELPRICE = "hotelPrice";

    public HotelAdapter(List<HotelModel> hotelModelList, Context context) {
        this.hotelModelList = hotelModelList;
        this.context = context;
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
        hotelFilterData = new ArrayList<>(hotelModelList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HotelModel hotelModel = hotelModelList.get(position);

        // For Image
        Picasso.get().load(hotelModelList.get(holder.getAdapterPosition()).getPhoto())
                .into(holder.hotelImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        holder.hotelName.setText(hotelModelList.get(holder.getAdapterPosition()).getName());
        holder.hotelLocation.setText(hotelModelList.get(holder.getAdapterPosition()).getLocation());
        holder.hotelInfo.setText(hotelModelList.get(holder.getAdapterPosition()).getInformation());
        holder.hotelPrice.setText(hotelModelList.get(holder.getAdapterPosition()).getPrice());

//        String hotelId = hotelModelList.get(holder.getAdapterPosition()).getHotel_id()+"";
//        String hotelName = hotelModelList.get(holder.getAdapterPosition()).getName();
//
//        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(KEY_HOTELID, hotelId);
//        editor.putString(KEY_HOTELNAME, hotelName);
//        editor.apply();

        holder.hotelBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserInformation.class);
//                intent.putExtra("h_id", hotelModelList.get(holder.getAdapterPosition()).getHotel_id());
//                intent.putExtra("h_name", hotelModelList.get(holder.getAdapterPosition()).getName());
                String hotelId = hotelModelList.get(holder.getAdapterPosition()).getHotel_id()+"";
                String hotelName = hotelModelList.get(holder.getAdapterPosition()).getName();
                String hotelPrice = hotelModelList.get(holder.getAdapterPosition()).getPrice();

                sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_HOTELID, hotelId);
                editor.putString(KEY_HOTELNAME, hotelName);
                editor.putString(KEY_HOTELPRICE, hotelPrice);
                editor.apply();
                context.startActivity(intent);
            }
        });

        holder.hotelDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HotelDetail.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelModelList.size();
    }

    @Override
    public Filter getFilter() {
        return filterSearch;
    }

    private Filter filterSearch = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<HotelModel> filterList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0) {
                filterList.addAll(hotelFilterData);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(HotelModel item: hotelFilterData) {
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
            hotelModelList.clear();
            hotelModelList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView hotelImage;
        TextView hotelName, hotelLocation, hotelPrice, hotelInfo;
        AppCompatButton hotelBook, hotelDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hotelImage = itemView.findViewById(R.id.hotel_image);
            hotelName = itemView.findViewById(R.id.hotel_name);
            hotelLocation = itemView.findViewById(R.id.hotel_location);
            hotelPrice = itemView.findViewById(R.id.hotel_price);
            hotelInfo = itemView.findViewById(R.id.hotel_info);
            hotelBook = itemView.findViewById(R.id.hotel_book);
            hotelDetail = itemView.findViewById(R.id.hotel_detail);
        }
    }
}
