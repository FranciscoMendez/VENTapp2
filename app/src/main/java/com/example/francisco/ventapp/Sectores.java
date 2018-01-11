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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import modelo.Sector;
import utilidades.ClienteRest;
import utilidades.OnTaskCompleted;
import utilidades.Util;


public class Sectores extends AppCompatActivity implements OnTaskCompleted {

    private static final int SOLICITUD_SECTORES = 1;
    private static final int SOLICITUD_SECTOR = 2;

    private ListAdapterSectores sectores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sectores);
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
        Log.i("Sectores", "" + result);
        switch (idSolicitud){
            case SOLICITUD_SECTORES:
                if(result!=null){
                    try {
                        List<Sector> res =  ClienteRest.getResults(result, Sector.class);
                        mostrarSectores(res);
                    }catch (Exception e){
                        Log.i("Sectores", "Error en carga de sectores", e);
                        Util.showMensaje(this, R.string.msj_error_clienrest_formato);
                    }
                }else
                    Util.showMensaje(this, R.string.msj_error_clienrest);
                break;
            case SOLICITUD_SECTOR:
                if(result!=null){
                    try {
                        Sector res =  ClienteRest.getResult(result, Sector.class);
                        Intent intent = new Intent(getApplicationContext(), Propiedades.class);
                        intent.putExtra("lista_propiedades",(Serializable) res.getPropiedades());
                        startActivity(intent);


                        //Util.showMensaje(this, res.toString());
                    }catch (Exception e){
                        Log.i("Sectores", "Error en carga de categoria por ID", e);
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
        consultaListadoSectores();
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
     * Realiza la llamada al WS para consultar el listado de sectores
     */
    protected void consultaListadoSectores() {
        try {
            String URL = Util.URL_SRV + "propiedad/sectores";
            ClienteRest clienteRest = new ClienteRest(this);
            clienteRest.doGet(URL, "", SOLICITUD_SECTORES, true);
        }catch(Exception e){
            Util.showMensaje(this, R.string.msj_error_clienrest);
            e.printStackTrace();
        }
    }

    /**
     * Realiza la llamada al WS para consultar el listado de sectores
     */
    protected void consultaSector(int id) {
        try {
            String URL = Util.URL_SRV + "propiedad/sectorid";
            ClienteRest clienteRest = new ClienteRest(this);
            clienteRest.doGet(URL, "?id="+id, SOLICITUD_SECTOR, true);
        }catch(Exception e){
            Util.showMensaje(this, R.string.msj_error_clienrest);
            e.printStackTrace();
        }
    }

    public void mostrarSectores(List<Sector> list){
        ListView lista = (ListView) findViewById(R.id.lstSectores);
        sectores = new ListAdapterSectores(getApplicationContext(), new ArrayList<Sector>(list));
        lista.setAdapter(sectores);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                mostrarSector(position);
            }
        });
    }

    private void mostrarSector(int position){
        Sector s = sectores.getItem(position);
        consultaSector(s.getCodigo());
    }

    private void llamarCrearCategoria(){
        //Intent intent = new Intent(this, CategoriaActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //cierra cola de actividades
        // startActivity(intent);
    }

}
