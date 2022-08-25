package com.example.applicationmacropay.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.applicationmacropay.ApplicationContextProvider;
import com.example.applicationmacropay.R;
import com.example.applicationmacropay.apis.ApiAdapter;
import com.example.applicationmacropay.apis.request.LoginRequest;
import com.example.applicationmacropay.apis.response.LoginResponse;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {


    EditText edUsuario,edPassword;
    Button btnIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadDataUser();
    }

    public void loadDataUser(){
        edUsuario = findViewById (R.id.edUsuario);
        edPassword = findViewById (R.id.edPassword);
        btnIniciar = findViewById (R.id.btnIniciar);

        //edPassword.setText("Admin1");
        //edUsuario.setText("admin@macropay.mx");

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setConfig(v);
            }
        });
    }


    /**
     * Nos sirve para almacenar los datos de configuración del kiosco
     * @param view
     */
    public void setConfig(View view) {

       if(formValido()){

           RequestBody requestBody = new MultipartBody.Builder()
                   .setType(MultipartBody.FORM)
                   .addFormDataPart("email", edUsuario.getText().toString().trim())
                   .addFormDataPart("password", edPassword.getText().toString())
                   .build();

            Call<LoginResponse> call = ApiAdapter.getApiService().loginApp2(requestBody);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    // respuesta exitosa
                    if(response.isSuccessful()){
                        // respuesta exitosa
                        LoginResponse loginResponse = response.body();
                        ApplicationContextProvider.setToken(loginResponse.getToken());
                        Log.e("response",loginResponse.getToken());
                        ApplicationContextProvider.setLogin(true);
                        goMain();
                        //Toast.makeText(LoginActivity.this, ""+loginResponse.getToken(), Toast.LENGTH_SHORT).show();
                    }else{
                        // puede ser algo malo
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();

                        ApiAdapter.processError(response.errorBody());
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    // fallas del server
                    Toast.makeText(LoginActivity.this, "Algo salió mal, intenta mas tarde", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(ctx, "Ingrese las credenciales correctas", Toast.LENGTH_SHORT).show();
        }
    }



    public boolean formValido(){
        if(TextUtils.isEmpty(edPassword.getText())){
            return false;
        }

        if(TextUtils.isEmpty(edPassword.getText())){
            return false;
        }
        return true;
    }


    public void goMain(){
        Intent mainIntent;
        mainIntent = new Intent(LoginActivity.this, DashboardActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        LoginActivity.this.startActivity(mainIntent);
        LoginActivity.this.finish();
    }
}