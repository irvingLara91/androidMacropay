package com.example.applicationmacropay.apis;
import android.widget.Toast;

import com.example.applicationmacropay.ApplicationContextProvider;
import com.example.applicationmacropay.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by IrvingLara
 */

public class ApiAdapter {

    private static ApiService API_SERVICE;
    public static Retrofit retrofit;

    public static ApiService getApiService(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(120,TimeUnit.SECONDS)
                .writeTimeout(120,TimeUnit.SECONDS)
                .readTimeout(120,TimeUnit.SECONDS);
        httpClient.addNetworkInterceptor(logging);
        httpClient.addInterceptor(new HeadersInterceptors());
        OkHttpClient client = httpClient.build();

        String baseUrl = ApplicationContextProvider.getContext().getResources().getString(R.string.urlLogin);


        if(API_SERVICE == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            API_SERVICE = retrofit.create(ApiService.class);
        }

        return API_SERVICE;
    }


    /**
     * Procesa errores obteniendo como parametro un errorResponse
     * @param errorResponse
     */
    public static void processError(ResponseBody errorResponse){
        if(errorResponse!=null){
            InputStream i = errorResponse.byteStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(i));
            StringBuilder errorResult = new StringBuilder();
            String line;
            try {
                while ((line = r.readLine()) != null) {
                    errorResult.append(line).append('\n');
                }

                JSONObject json = new JSONObject(errorResult.toString());
                if(json.has("detail")){
                    Toast.makeText(ApplicationContextProvider.getContext(), json.getString("detail"), Toast.LENGTH_SHORT).show();
                    return;
                }

                Iterator keys = json.keys();

                while(keys.hasNext()){
                    String currentDynamicKey = (String)keys.next();
                    Toast.makeText(ApplicationContextProvider.getContext(), json.getString(currentDynamicKey), Toast.LENGTH_SHORT).show();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                Toast.makeText(ApplicationContextProvider.getContext(), errorResult, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
