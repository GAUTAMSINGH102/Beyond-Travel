package com.example.traveliker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveliker.Activities.HotelDetail;
import com.example.traveliker.Activities.TransportDetail;
import com.example.traveliker.Activities.UserInformation;
import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.Models.TransportModel;
import com.example.traveliker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Retrofit;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.ViewHolder>{

    private List<TransportModel> transportModelList;
    private Context context;
    ApiInterface apiInterface;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_TRANSPORTID = "transportId";
    private static final String KEY_TRANSPORTNAME = "transportName";
    private static final String KEY_TRANSPORTPRICE = "transportPrice";

    public TransportAdapter(List<TransportModel> transportModelList, Context context) {
        this.transportModelList = transportModelList;
        this.context = context;
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transport_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TransportModel transportModel = transportModelList.get(position);

        // For Image
        Picasso.get().load(transportModelList.get(holder.getAdapterPosition()).getFromimage())
                .into(holder.fromimage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        Picasso.get().load(transportModelList.get(holder.getAdapterPosition()).getToimage())
                .into(holder.toimage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        Picasso.get().load(transportModelList.get(holder.getAdapterPosition()).getTransporttypeimage())
                .into(holder.transportTypeImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        holder.transportName.setText(transportModelList.get(holder.getAdapterPosition()).getTransportName());
        holder.transportType.setText(transportModelList.get(holder.getAdapterPosition()).getTransportType());
        holder.fromPlace.setText(transportModelList.get(holder.getAdapterPosition()).getFromplace());
        holder.toPlace.setText(transportModelList.get(holder.getAdapterPosition()).getToplace());
        holder.fromTime.setText(transportModelList.get(holder.getAdapterPosition()).getFromtime());
        holder.toTime.setText(transportModelList.get(holder.getAdapterPosition()).getTotime());
        holder.fromAirport.setText(transportModelList.get(holder.getAdapterPosition()).getFromairport());
        holder.toAirport.setText(transportModelList.get(holder.getAdapterPosition()).getToairport());
        holder.departureDate.setText(transportModelList.get(holder.getAdapterPosition()).getDepartureDate());
        holder.price.setText(transportModelList.get(holder.getAdapterPosition()).getPrice());

        holder.transportBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserInformation.class);

                String transportId = transportModelList.get(holder.getAdapterPosition()).getTransportId()+"";
                String transportName = transportModelList.get(holder.getAdapterPosition()).getTransportName();
                String transportPrice = transportModelList.get(holder.getAdapterPosition()).getPrice();

                sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_TRANSPORTID, transportId);
                editor.putString(KEY_TRANSPORTNAME, transportName);
                editor.putString(KEY_TRANSPORTPRICE, transportPrice);
                editor.apply();

                context.startActivity(intent);
            }
        });

        holder.transportDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TransportDetail.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return transportModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fromimage, toimage, transportTypeImage;
        TextView transportName, transportType, fromPlace, toPlace, fromTime, toTime, fromAirport, toAirport, departureDate, price;
        AppCompatButton transportBook, transportDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fromimage = itemView.findViewById(R.id.from_image);
            toimage = itemView.findViewById(R.id.to_image);
            transportTypeImage = itemView.findViewById(R.id.type_image);

            transportName = itemView.findViewById(R.id.transport_name);
            transportType = itemView.findViewById(R.id.transport_type);
            fromPlace = itemView.findViewById(R.id.from_name);
            toPlace = itemView.findViewById(R.id.to_name);
            fromTime = itemView.findViewById(R.id.from_time);
            toTime = itemView.findViewById(R.id.to_time);
            fromAirport = itemView.findViewById(R.id.from_airport);
            toAirport = itemView.findViewById(R.id.to_airport);
            departureDate = itemView.findViewById(R.id.departure_date);
            price = itemView.findViewById(R.id.price);
            transportBook = itemView.findViewById(R.id.transport_book);
            transportDetail = itemView.findViewById(R.id.transport_detail);
        }
    }
}
