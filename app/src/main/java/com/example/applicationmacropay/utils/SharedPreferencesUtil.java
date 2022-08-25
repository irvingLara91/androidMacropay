package com.example.applicationmacropay.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.example.applicationmacropay.ApplicationContextProvider;
import java.util.ArrayList;

/**
 * Esta clase permite el manejo simple de SharedPreferences
 * Created by IrvingLara
 */
public class SharedPreferencesUtil {

    Context context;

    public SharedPreferencesUtil(Context ctx){
        this.context = ctx;
    }

    public SharedPreferencesUtil(){
        this.context = ApplicationContextProvider.getContext();
    }


    /**
     * Permite almacenar preferencias de tipo String
     * @param key
     * @param data
     */
    public void saveStrSharedPreference(String key, String data){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, data);
        editor.apply();
    }

    /**
     * Permite obtener las preferencias de tipo String
     * @param key
     * @return
     */
    public String getStringSharedPreference(String key){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(key, "");
    }

    /**
     * Permite eliminar todas las preferencias del sistema
     */
    public void deleteAllSharedPreference(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit().clear().apply();
    }

    /**
     * Permite almacenar preferencias de tipo booleano (TRUE/FALSE)
     * @param key
     * @param data
     */
    public  void  saveBooleanSharedPreference(String key, boolean data){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, data);
        editor.apply();
    }

    /**
     * Permite obtener una preferencia de tipo booleano  (TRUE/FALSE)
     * @param key
     * @return
     */
    public boolean getBooleanSharedPreference(String key){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return  sharedPref.getBoolean(key, false);
    }


    /**
     * Permite almacenar preferencias de tipo flotante
     * @param key
     * @param data
     */
    public  void saveFloatSharedPreference(String key, float data){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(key, data);
        editor.apply();
    }

    /**
     * Permite obtener una preferencia de tipo flotante
     * @param key
     * @return
     */
    public float getFloatSharedPreference(String key){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getFloat(key, -1);
    }


    /**
     * Permite almacenar preferencias de tipo entero
     * @param key
     * @param data
     */
    public  void saveIntSharedPreference(String key, int data){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, data);
        editor.apply();
    }

    /**
     * Permite obtener una preferencia de tipo entero
     * @param key
     * @return
     */
    public int getIntSharedPreference(String key){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getInt(key, -1);
    }


    /**
     * Permite almacenar preferencias de tipo long
     * @param key
     * @param data
     */
    public  void saveLongSharedPreference(String key, int data){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, data);
        editor.apply();
    }

    /**
     * Permite obtener una preferencia de tipo long
     * @param key
     * @return
     */
    public long getLongSharedPreference(String key){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getLong(key, -1);
    }

    /**
     * Permite guardar una preferencia de tipo Array String
     * @param array
     * @param arrayName
     * @return
     */
    public boolean saveArraySharedPreference(ArrayList<String> array, String arrayName) {
        SharedPreferences prefs =  PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        return editor.commit();
    }


    /**
     * Permite obtener una preferencia de tipo Array String
     * @param arrayName
     * @return
     */
    public ArrayList<String> getArraySharedPreference(String arrayName)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>();
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }






}
