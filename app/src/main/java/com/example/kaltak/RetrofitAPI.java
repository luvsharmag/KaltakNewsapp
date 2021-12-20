package com.example.kaltak;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {
    @GET
    Call<NewsModal> getAllnews(@Url String url);

    @GET
    Call<NewsModal> getNewsByCategory(@Url String url);
}
