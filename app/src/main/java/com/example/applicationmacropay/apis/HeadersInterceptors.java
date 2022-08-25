package com.example.applicationmacropay.apis;

import android.text.TextUtils;

import com.example.applicationmacropay.ApplicationContextProvider;
import com.example.applicationmacropay.views.BaseActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class HeadersInterceptors extends BaseActivity implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();


        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder();

        if(!TextUtils.isEmpty(ApplicationContextProvider.getToken()) ){
            requestBuilder.addHeader("Authorization", getTokenParam());
        }

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
