package com.example.francisco.ventapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import modelo.Sector;

/**
 * Created by PUNTOCOM on 10/1/2018.
 */

class ListAdapterSectores extends ArrayAdapter<Sector> {
    //Contenxto de la aplicacion que relaciona al ListView y el Adpater
    private Context context;

    /**
     * Inicializacion
     *
     * @param context Contexto de la App desde la que se invoca
     * @param items   //Coleccion de objetos a presentar
     */
    public ListAdapterSectores(Context context, ArrayList<Sector> items) {
        super(context, R.layout.item_sector, items);
        this.context = context;
    }

    /**
     * View a presentar en pantalla correspondiente a un item de ListView
     *
     * @param position    //Indice del ListItem
     * @param convertView //Contexto o contenedor de View
     * @param parent      //Contendor padre
     * @return  Objeto View
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_sector, null);
        }

        //Objeto Categoria a visualizar segun position
        Sector item = getItem(position);
        if (item != null) {
            // Recupera los elementos de vista y setea en funcion de valores de objeto
            TextView titulo = (TextView) view.findViewById(R.id.txtSector);

            if (titulo != null) {
                titulo.setText(item.getNombre());
            }
        }
        return view;
    }
}
