package com.example.francisco.ventapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import modelo.Persona;


public class Registro extends AppCompatActivity implements View.OnClickListener{

    private Button btnGuardar;
    private Button btnVolver;

    EditText nombre;
    EditText apellido;
    EditText fecha;
    EditText direccion;
    EditText email;
    EditText password;
    EditText telefono;
    EditText tipo;

    Map<String, String> params;

    SharedPreferences prefs;
    public static final String PREFS_NAME = "PumapungoAppPrefs";

    private String TAG = Persona.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        nombre = (EditText) findViewById(R.id.txtUserName);
        apellido = (EditText) findViewById(R.id.txtLastName);
        fecha = (EditText) findViewById(R.id.txtDate);
        direccion = (EditText) findViewById(R.id.txtDireccion);
        email = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtPassword);

        telefono = (EditText) findViewById(R.id.txtPhone);
        tipo = (EditText) findViewById(R.id.txtTipe);

        View botonG = findViewById(R.id.btnGuardarr);
        botonG.setOnClickListener(this);

        View botonV = findViewById(R.id.btnVolver);
        botonV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==findViewById(R.id.btnGuardarr).getId()){
            System.out.print(">>> "+nombre.getText().toString()+
                             ";"+apellido.getText().toString()+
                             ";"+fecha.getText().toString()+
                             ";"+direccion.getText().toString()+
                             ";"+email.getText().toString()+
                             ";"+password.getText().toString());
            registrarUsuario();
        }else if (v.getId()==findViewById(R.id.btnVolver).getId()){
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
        }
    }

    public void registrarUsuario(){
        try {
            Log.e(TAG, "Mapping object from register form");
            params = new HashMap<String, String>();
            params.put("nombres", nombre.getText().toString());
            params.put("apellido", apellido.getText().toString());
            params.put("fecha", fecha.getText().toString());
            params.put("direccion", direccion.getText().toString());
            params.put("email", email.getText().toString());
            params.put("password", password.getText().toString());

            JSONObject registerObject = null;

            try {
                registerObject = getJsonObjectFromMap(params);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.e(TAG, "JSON Object: "+registerObject.toString());
            //http://localhost:8080/Ventapp/ventapp/authentication/query?email={String}&pass={String}    "/" + prefs.getString("serverInicio","Pumapungo") +
            new HttpAsyncTask().execute("http://"+prefs.getString("serverAddress", "172.20.10.3:8080") + "/Ventapp/ventapp/user/register");
            Log.e(TAG, "Data sent");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static JSONObject getJsonObjectFromMap(Map params) throws JSONException {
        Iterator iter = params.entrySet().iterator();
        JSONObject holder = new JSONObject();
        while (iter.hasNext()) {
            Map.Entry pairs = (Map.Entry)iter.next();
            String key = (String)pairs.getKey();
            String data = (String)pairs.getValue();
            holder.put(key, data);
        }
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            Log.e(TAG, "Posting nacional: " + urls[0]);
            Log.e(TAG, "Parameters: " + params);
            return POST(urls[0], params);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            startActivity(new Intent(Registro.this, MainActivity.class));
            finish();
        }
    }

    public static String POST (String url, Map params) {
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);
            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = getJsonObjectFromMap(params);

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);
            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Error!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        Log.e(">>>", "Converting InputStream");
        while((line = bufferedReader.readLine()) != null) {
            Log.e(">>>", "\t" + line);
            result += line;
        }
        inputStream.close();
        return result;
    }

    }
