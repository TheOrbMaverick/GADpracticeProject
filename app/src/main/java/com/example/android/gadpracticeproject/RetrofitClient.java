package com.example.android.gadpracticeproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public  static synchronized RetrofitClient getInstance(){

        if(mInstance == null){
            mInstance = new RetrofitClient();
        }

        return mInstance;

    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }

}
