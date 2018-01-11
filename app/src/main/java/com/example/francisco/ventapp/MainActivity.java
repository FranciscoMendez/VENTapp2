package com.example.francisco.ventapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAceptar;
    private Button btnRegistro;

    EditText Password;
    EditText UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserName = (EditText) findViewById(R.id.txtNombre);
        Password = (EditText) findViewById(R.id.txtPassword);

        View boton = findViewById(R.id.btnAceptar);
        boton.setOnClickListener(this);

        View botonR = findViewById(R.id.btnRegistro);
        botonR.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==findViewById(R.id.btnAceptar).getId()){
            System.out.println("Ingreso el Usuario"+UserName.getText().toString()+" - "+Password.getText().toString());

            String res=clienteRest(UserName.getText().toString().trim().toLowerCase(),Password.getText().toString());
            System.out.println("Servidor responde con "+res);
            if(res.equals("index")) {
                System.out.println("Ingreso");
                Intent intent = new Intent(this, Menu.class);
                startActivity(intent);
            }else {
                System.out.println("usuario o contraseña incorrecta ");
            }
        } else if (view.getId()==findViewById(R.id.btnRegistro).getId()){
            Intent intent = new Intent(this, Registro.class);
            startActivity(intent);
        }
    }

    public String clienteRest(String correo, String clave){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {

            URL url = new URL("http://192.168.0.105:8080/Ventapp/ventapp/login/user?email="+correo+"&pass="+clave);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                return output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }

   /** public String clienteRest(String correo, String clave){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {

            URL url = new URL("http://192.168.1.2:8080/Ventapp/ventapp/login/user?email="+correo+"&pass="+clave);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                return output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }**/
}
