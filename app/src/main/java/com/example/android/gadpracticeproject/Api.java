package com.example.android.gadpracticeproject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("viewform")
    Call<ResponseBody> viewform(
            @Field("Email Address") String email,
            @Field("Name") String firstName,
            @Field("Last Name") String lastName,
            @Field("Link to project") String link
    );
}
