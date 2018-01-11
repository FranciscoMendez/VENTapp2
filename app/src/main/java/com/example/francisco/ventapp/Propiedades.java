package com.example.francisco.ventapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Propiedad;
import utilidades.ClienteRest;
import utilidades.OnTaskCompleted;
import utilidades.Util;

public class Propiedades extends AppCompatActivity implements OnTaskCompleted {

    private static final int SOLICITUD_PROPIEDADES = 1;
    private static final int SOLICITUD_PROPIEDAD = 2;

    private ListAdapterPropiedades propiedades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propiedades);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamarCrearCategoria();
            }
        });
    }

    @Override
    public void onTaskCompleted(int idSolicitud, String result) {
        Log.i("Propiedades", "" + result);
        switch (idSolicitud){
            case SOLICITUD_PROPIEDADES:
                if(result!=null){
                    try {
                        List<Propiedad> res =  ClienteRest.getResults(result, Propiedad.class);
                        mostrarPropiedades(res);
                    }catch (Exception e){
                        Log.i("Propiedades", "Error en carga de categorias", e);
                        Util.showMensaje(this, R.string.msj_error_clienrest_formato);
                    }
                }else
                    Util.showMensaje(this, R.string.msj_error_clienrest);
                break;
            case SOLICITUD_PROPIEDAD:
                if(result!=null){
                    try {
                        Categoria res =  ClienteRest.getResult(result, Categoria.class);
                        Util.showMensaje(this, res.toString());
                    }catch (Exception e){
                        Log.i("Propiedades", "Error en carga de categoria por ID", e);
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
    public void onResume() {
        super.onResume();
        importarInformacion();
    }

    /*@Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Realiza la llamada al WS para consultar el listado de Categorias
     */
    protected void consultaListadoPropiedades() {
        try {
            String URL = Util.URL_SRV + "propiedad/categorias";
            ClienteRest clienteRest = new ClienteRest(this);
            clienteRest.doGet(URL, "", SOLICITUD_PROPIEDADES, true);
        }catch(Exception e){
            Util.showMensaje(this, R.string.msj_error_clienrest);
            e.printStackTrace();
        }
    }

    /**
     * Realiza la llamada al WS para consultar el listado de Categorias
     */
    protected void consultaPropiedad(int id) {
        try {
            String URL = Util.URL_SRV + "propiedad/categoriaid";
            ClienteRest clienteRest = new ClienteRest(this);
            clienteRest.doGet(URL, "?id="+id, SOLICITUD_PROPIEDAD, true);
        }catch(Exception e){
            Util.showMensaje(this, R.string.msj_error_clienrest);
            e.printStackTrace();
        }
    }

    public void mostrarPropiedades(List<Propiedad> list){
        ListView lista = (ListView) findViewById(R.id.lstPropiedades);
        propiedades = new ListAdapterPropiedades(getApplicationContext(), new ArrayList<Propiedad>(list));
        lista.setAdapter(propiedades);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
               // mostrarCategoria(position);
            }
        });
    }

    /*private void mostrarCategoria(int position){
        Categoria cat = categorias.getItem(position);
        consultaCategoria(cat.getCodigo());
    }*/

    private void llamarCrearCategoria(){
        //Intent intent = new Intent(this, CategoriaActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //cierra cola de actividades
        // startActivity(intent);
    }

    public void importarInformacion() {
        Intent startingIntent = getIntent();
        if (startingIntent != null) {
            Bundle b = startingIntent.getBundleExtra("android.intent.extra.INTENT");
            //recibimos la lista de cines
            List<Propiedad> lp = (List<Propiedad>) getIntent().getSerializableExtra("lista_propiedades");
            //recibimos la lista de peliculas
            mostrarPropiedades(lp);
        }
    }
}
