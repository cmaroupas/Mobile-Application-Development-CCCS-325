// Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.cccs325assignment5;

import android.util.Log;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class YelpRepository {
    private static final String BASE_URL = "https://api.yelp.com/v3/";
    private static final String API_KEY = "Bearer maB8nLWoNgr3fLJ5Nx3vVvKPUJ_d2GaG0OHnsUGFLD8bYSrTu93Szlh5Tf0epU0Vxb7ySyW1fdaeotk9kH5mg4icqlQJRqZE-B29q-K1zg5sjIOB4qVOdrs0kX-CZHYx";

    private YelpService yelpService;

    public YelpRepository() {
        // Create OkHttpClient with logging interceptor
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        // Create a new Interceptor for adding the Authorization header to each request
        Interceptor headerAuthorizationInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", API_KEY)
                        .method(original.method(), original.body())
                        .build();
                Log.d("API_REQUEST", "Headers: " + request.headers());
                return chain.proceed(request);
            }
        };

        // Add the interceptors to OkHttpClient
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);
        clientBuilder.addInterceptor(headerAuthorizationInterceptor);

        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build()) // Set the OkHttpClient with logging interceptor
                .build();

        // Create the YelpService interface implementation using Retrofit
        yelpService = retrofit.create(YelpService.class);
    }

    public Call<RestaurantResponse> getRestaurants(String term, String location, String sort_by) {
        return yelpService.getRestaurants(term, location, sort_by);
    }

    public Call<RestaurantResponse> getRestaurants(String term, String location) {
        return getRestaurants(term, location, "best_match"); // Default sort criteria is "best_match"
    }
}
