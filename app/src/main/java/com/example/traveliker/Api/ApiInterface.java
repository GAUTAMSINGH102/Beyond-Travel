package com.example.traveliker.Api;

import com.example.traveliker.Responses.AddUserResponses;
import com.example.traveliker.Responses.GetHotelResponses;
import com.example.traveliker.Responses.GetPlacesResponses;
import com.example.traveliker.Responses.GetReviewResponse;
import com.example.traveliker.Responses.GetTransportResponse;
import com.example.traveliker.Responses.GetUserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("getplaces.php")
    Call<GetPlacesResponses> getplaces();

    @GET("getallhotel.php")
    Call<GetHotelResponses> getallhotel();

    @GET("getalltransport.php")
    Call<GetTransportResponse> getalltransport();

    @FormUrlEncoded
    @POST("gettransport.php")
    Call<GetTransportResponse> gettransport(@Field("fromplace") String fromplace, @Field("toplace") String toplace, @Field("deptDate") String departureDate);

    @FormUrlEncoded
    @POST("getreview.php")
    Call<GetReviewResponse> getreview(@Field("name") String placeName);

    @FormUrlEncoded
    @POST("adduser.php")
    Call<AddUserResponses> adduser(@Field("userName") String userName, @Field("userEmail") String userEmail, @Field("userPassword") String userPassword, @Field("userMobile") String userMobile, @Field("userAddress") String userAddress, @Field("userAge") String userAge);

    @FormUrlEncoded
    @POST("getuser.php")
    Call<GetUserResponse> getuser(@Field("userName") String userName, @Field("userEmail") String userEmail, @Field("userPassword") String userPassword, @Field("userMobile") String userMobile, @Field("userAddress") String userAddress, @Field("userAge") String userAge);

    @FormUrlEncoded
    @POST("addreview.php")
    Call<AddUserResponses> addreview(@Field("review") String review, @Field("placeid") String placeid);

    @FormUrlEncoded
    @POST("addhotelbooking.php")
    Call<AddUserResponses> addhotelbooking(@Field("days") String days, @Field("roomNo") String roomNo, @Field("hotelId") String hotel_id, @Field("userId") String userId);

}
