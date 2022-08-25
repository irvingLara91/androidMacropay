package com.example.applicationmacropay;

import android.app.Application;
import android.content.Context;

import com.example.applicationmacropay.utils.SharedPreferencesUtil;

/**
 * Created by Irving Lara.
 */

public class ApplicationContextProvider extends Application {
    /**
     * Keeps a reference of the application context
     */

    private static Context sContext;
    static Context ctx = ApplicationContextProvider.getContext();
    static SharedPreferencesUtil spu;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        spu = new SharedPreferencesUtil(sContext);

    }

    /**
     * Returns the application context
     *
     * @return application context
     */
    public static Context getContext() {
        return sContext;
    }

    public static void setToken(String token){
        spu.saveStrSharedPreference("token", token);
    }
    public static String getToken(){
        return spu.getStringSharedPreference("token");
    }


    public static void setLogin(boolean isLogin){
        spu.saveBooleanSharedPreference("is_login", isLogin);
    }

    public static boolean isLogin(){
        return spu.getBooleanSharedPreference("is_login");

    }

}

