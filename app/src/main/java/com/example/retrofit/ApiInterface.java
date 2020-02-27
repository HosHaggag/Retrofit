package com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

interface ApiInterface {
    @GET("posts/1")
    Call<json> gets();
}
