package com.sabeeldev.galleryapp.RetrofitUtils;

import com.sabeeldev.galleryapp.Model.Images;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {


    @GET("?")
    Call<Images> getAllImages(@Query("key") String key);

    @GET("?")
    Call<Images> getAllImages(@Query("key") String key,
                              @Query("q") String q,
                              @Query("image_type") String image_type);
}
