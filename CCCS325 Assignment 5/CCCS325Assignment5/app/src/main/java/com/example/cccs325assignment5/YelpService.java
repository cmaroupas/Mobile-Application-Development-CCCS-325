//Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.cccs325assignment5;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



    public interface YelpService {
        @GET("businesses/search")
        Call<RestaurantResponse> getRestaurants(
                @Query("term") String term,
                @Query("location") String location,
                @Query("sort_by") String sortCriteria
        );
    }
