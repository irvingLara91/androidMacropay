package com.example.applicationmacropay.views;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.example.applicationmacropay.ApplicationContextProvider;
import com.example.applicationmacropay.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACVerifier;



public class DashboardActivity extends BaseActivity {
    String fff= "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aXR1bGFyIjoibWlndWVsIGFuZ2VsIiwidXJsIjoiYWRtaW4uY29tIiwiZW1haWwiOiJtaWd1ZWxhbmdlbHBnQGhvdG1haWwuY29tIiwic29saWNpdHVkIjoiU0wyMDIxMDAwNDI3ODYifQ.dXXI1FZF5k_M1sYMVNN3uS8wkeEWhRRweSwfRWxn7Jw";
    TextView nameUser,tokenString;
    private ImageView qrCodeIV;
    AlertDialog.Builder builder;
    String name = "";



    public void logout(){
        Intent mainIntent;
        mainIntent = new Intent(DashboardActivity.this, LoginActivity.class);
        ApplicationContextProvider.setLogin(false);
        ApplicationContextProvider.setToken("");
        Toast.makeText(ctx, "Sesión cerrada.", Toast.LENGTH_SHORT).show();
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        DashboardActivity.this.startActivity(mainIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        nameUser = findViewById (R.id.nameUser);
        tokenString = findViewById (R.id.tokenString);
        qrCodeIV = findViewById(R.id.idIVQrcode);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarmain);
        //setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);



        builder = new AlertDialog.Builder(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                builder.setMessage("¿Deseas cerrar sesión?")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                logout();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Hola "+name);
                alert.show();
            }
        });


        loadDataUser();


    }

    /**
     *  Hacer split al TOken para obtener los caracteres del primer punto.
     **/
    public String getTokenHash(String Token){
        if(!Token.isEmpty()){
            String[] parts = Token.split("\\.");
            String part1 = parts[0]; // inicio string
            return part1;
        }else {
            return "Error";
        }
    }


    public void loadDataUser() {

        String token = ApplicationContextProvider.getToken();
        /**
         * Decode JWT
         **/
        try {
            Verifier verifier = HMACVerifier.newVerifier("ejercicio1");
            // Verify and decode the encoded string JWT to a rich object
            JWT jwt = JWT.getDecoder().decode(token, verifier);
            //Log.e("DECODE", String.valueOf(jwt.getString("titular")));
            nameUser.setText(jwt.getString("titular"));
            name = jwt.getString("titular");
        } catch (Exception exception){
            //Invalid signature/claims
            Log.e("ERROR",exception.getMessage());
        }
        /**
         * setear y visualizar el token
         **/
        String splitStringToken = getTokenHash(token);
        tokenString.setText(splitStringToken);
        /**
         * Create QR para visualizar.
         **/
        MultiFormatWriter mWriter = new MultiFormatWriter();
        try {
            //BitMatrix class to encode entered text and set Width & Height
            BitMatrix mMatrix = mWriter.encode(splitStringToken, BarcodeFormat.QR_CODE, 400,400);
            BarcodeEncoder mEncoder = new BarcodeEncoder();
            Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
            qrCodeIV.setImageBitmap(mBitmap);//Setting generated QR code to imageView
            // to hide the keyboard
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
           // manager.hideSoftInputFromWindow(etText.getApplicationWindowToken(), 0);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}