package com.example.traveliker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.Models.ReviewModel;
import com.example.traveliker.R;

import java.util.List;

import retrofit2.Retrofit;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{

    private List<ReviewModel> reviewModelList;
    private Context context;
    ApiInterface apiInterface;

    public ReviewAdapter(List<ReviewModel> reviewModelList, Context context) {
        this.reviewModelList = reviewModelList;
        this.context = context;
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ReviewModel reviewModel = reviewModelList.get(position);

        holder.review.setText(reviewModelList.get(holder.getAdapterPosition()).getReview());
        holder.reviewTime.setText(reviewModelList.get(holder.getAdapterPosition()).getTime());
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView review, reviewTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            review = itemView.findViewById(R.id.review);
            reviewTime = itemView.findViewById(R.id.review_time);

        }
    }

}
