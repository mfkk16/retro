package com.example.calsys.retroapi.config;

import com.example.calsys.retroapi.config.response.Tutorsubjectlistresponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIinterface {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("tutors?")
    Call<Tutorsubjectlistresponse> get_tutors(@Query("id") int sub_id, @Query("cat") int cat);
}