package com.example.applicationmacropay.views;

import static java.nio.charset.StandardCharsets.UTF_8;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmacropay.ApplicationContextProvider;
import com.example.applicationmacropay.utils.SharedPreferencesUtil;


/**
 * Created by Irving Lara.
 */

public class BaseActivity extends AppCompatActivity {
    ApplicationContextProvider app;

    Context ctx = ApplicationContextProvider.getContext();
    SharedPreferencesUtil spu = new SharedPreferencesUtil();



    private static final int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter mBluetoothAdapter;


    public Context getContext(){
        return ctx;
    }


    public String getTokenParam(){
        return ApplicationContextProvider.getToken();
    }







}
