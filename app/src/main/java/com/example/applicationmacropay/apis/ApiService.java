package com.example.applicationmacropay.apis;

import com.example.applicationmacropay.apis.request.LoginRequest;
import com.example.applicationmacropay.apis.response.LoginResponse;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by IrvingLara.
 */

public interface ApiService {

    @POST("/")
    Call<LoginResponse> loginApp2(@Body RequestBody body);

}