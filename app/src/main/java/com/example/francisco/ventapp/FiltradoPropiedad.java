package com.example.francisco.ventapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.List;

import modelo.Propiedad;
import utilidades.ClienteRest;
import utilidades.OnTaskCompleted;
import utilidades.Util;

public class FiltradoPropiedad extends Activity implements View.OnClickListener, OnTaskCompleted {

    private Button btnCat;
    private Button btnSect;
    private Button btnTodo;

    private static final int SOLICITUD_Propiedad = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrado_propiedad);

        View botonC = findViewById(R.id.btnCategoria);
        botonC.setOnClickListener(this);

        View botonS = findViewById(R.id.btnSectores);
        botonS.setOnClickListener(this);
        View botonT = findViewById(R.id.btnTodo);
        botonT.setOnClickListener(this);


    }

    @Override
    public void onTaskCompleted(int idSolicitud, String result) {
        Log.i("FiltradoPropiedad", "" + result);
        switch (idSolicitud){
            case SOLICITUD_Propiedad:
                if(result!=null){
                    try {
                        List<Propiedad> res =  ClienteRest.getResults(result, Propiedad.class);
                        Intent intent = new Intent(getApplicationContext(), Propiedades.class);
                        intent.putExtra("lista_propiedades",(Serializable) res);
                        startActivity(intent);

                    }catch (Exception e){
                        Log.i("FiltradoPropiedad", "Error en carga de categorias", e);
                        Util.showMensaje(this, R.string.msj_error_clienrest_formato);
                    }
                }else
                    Util.showMensaje(this, R.string.msj_error_clienrest);
                break;

            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCategoria :
                Intent intent = new Intent(this, Categorias.class);
                startActivity(intent);;
                break;
            case R.id.btnSectores :
                Intent inten = new Intent(this, Sectores.class);
                startActivity(inten);
                break;
            case R.id.btnTodo :
                consultaListadoPropiedades();
                break;
            default:
                break;
        }
    }

    /**
     * Realiza la llamada al WS para consultar el listado de Categorias
     */
    protected void consultaListadoPropiedades() {
        try {
            String URL = Util.URL_SRV + "propiedad/propiedades";
            ClienteRest clienteRest = new ClienteRest(this);
            clienteRest.doGet(URL, "", SOLICITUD_Propiedad, true);
        }catch(Exception e){
            Util.showMensaje(this, R.string.msj_error_clienrest);
            e.printStackTrace();
        }
    }

}
