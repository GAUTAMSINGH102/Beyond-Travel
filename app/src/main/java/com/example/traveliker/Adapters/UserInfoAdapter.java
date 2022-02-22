package com.example.traveliker.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveliker.Api.ApiClient;
import com.example.traveliker.Api.ApiInterface;
import com.example.traveliker.Models.HotelModel;
import com.example.traveliker.Models.UserModel;
import com.example.traveliker.R;

import java.util.List;

import retrofit2.Retrofit;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.ViewHolder>{

    private List<UserModel> userModelList;
    private Context context;
    ApiInterface apiInterface;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USERID = "userId";


    public UserInfoAdapter(List<UserModel> userModelList, Context context) {
        this.userModelList = userModelList;
        this.context = context;
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_info_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserModel userModel = userModelList.get(position);

        String userId = userModelList.get(holder.getAdapterPosition()).getUserId()+"";

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERID, userId);
        editor.apply();

        holder.userIdLayout.setText(userId);
        holder.userNameLayout.setText(userModelList.get(holder.getAdapterPosition()).getName());
        holder.userEmailLayout.setText(userModelList.get(holder.getAdapterPosition()).getEmail_id());
        holder.userAddressLayout.setText(userModelList.get(holder.getAdapterPosition()).getAddress());
        holder.userAgeLayout.setText(userModelList.get(holder.getAdapterPosition()).getAge());

    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView userIdLayout, userNameLayout, userEmailLayout, userAddressLayout, userAgeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userIdLayout = itemView.findViewById(R.id.user_id_layout);
            userNameLayout = itemView.findViewById(R.id.user_name_layout);
            userEmailLayout = itemView.findViewById(R.id.user_email_layout);
            userAddressLayout = itemView.findViewById(R.id.user_address_layout);
            userAgeLayout = itemView.findViewById(R.id.user_age_layout);
        }
    }
    
}
