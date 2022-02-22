package com.example.traveliker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.HotelBookingModel;
import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.R;

import java.util.List;

import retrofit2.Retrofit;

public class HotelBookingAdapter extends RecyclerView.Adapter<HotelBookingAdapter.ViewHolder>{

    private List<HotelBookingModel> hotelBookingModelList;
    private Context context;
    ApiInterface apiInterface;


    public HotelBookingAdapter(List<HotelBookingModel> hotelBookingModelList, Context context) {
        this.hotelBookingModelList = hotelBookingModelList;
        this.context = context;
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_ticket_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HotelBookingModel hotelBookingModel = hotelBookingModelList.get(position);

//        holder.bookingUserName.setText(hotelBookingModelList.get(holder.getAdapterPosition()).get());

    }

    @Override
    public int getItemCount() {
        return hotelBookingModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView bookingUserName, bookingHotelName, bookingNoOfDays;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            bookingUserName = itemView.findViewById(R.id.booking_userName);
//            bookingHotelName = itemView.findViewById(R.id.booking_hotelName);
//            bookingNoOfDays = itemView.findViewById(R.id.booking_noOfDays);

        }
    }
}
