package com.example.francisco.ventapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainLogin extends AppCompatActivity {

    private Button btnAceptar;
    private Button btnRegistrar;
    private EditText extUsuario;
    private EditText extContrasena;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_login);
        btnAceptar = (Button)findViewById(R.id.btnAceptar1);
        btnRegistrar = (Button)findViewById(R.id.btnregistrar1);
        extUsuario = (EditText) findViewById(R.id.etxtUsuario);
        extContrasena = (EditText) findViewById(R.id.etxtContrasena);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("nuevo",extUsuario.getText().toString());
                if(extUsuario.getText().toString().equals("") || extContrasena.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(),"Datos Incompletos",Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("nuevo","fdsjkfhsdffsdjfhjsd");


                }
            }
        });
    }
}
