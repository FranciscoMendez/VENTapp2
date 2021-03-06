package com.example.francisco.ventapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import modelo.Propiedad;

public class DetallePropiedad extends AppCompatActivity implements OnClickListener{

    private TextView desc;
    private TextView precio;
    private TextView prop;
    private Button btnUbicarMapa;
    public Propiedad propiedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_propiedad);

        prop=findViewById(R.id.textpropietario);
        precio=findViewById(R.id.txtprecioprop);
        desc=findViewById(R.id.txtdescprop);
        btnUbicarMapa = findViewById(R.id.btnMapaUbicar);
        btnUbicarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), propiedadMapa.class);
                intent.putExtra("propiedadLat",propiedad.getLatitud());
                intent.putExtra("propiedadLong",propiedad.getLonguitud());
                intent.putExtra("propiedadNombre",propiedad.getPersona().getNombres()+" "+propiedad.getPersona().getApellido());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        importarInformacion();
    }

    public void cargar(Propiedad p) {
        if (p!=null) {
            propiedad=p;
            prop.setText(p.getPersona().getNombres()+" "+p.getPersona().getApellido());
            desc.setText("Descripcion: "+p.getDescripcion());
            precio.setText("Precio: "+p.getCosto());

//                    Log.i("TEST", persona.toString());
                }
    }

    public void importarInformacion() {
        Intent startingIntent = getIntent();
        if (startingIntent != null) {
            Bundle b = startingIntent.getBundleExtra("android.intent.extra.INTENT");
            //recibimos la lista de cines
            Propiedad lp = (Propiedad) getIntent().getSerializableExtra("pro");
            //recibimos la lista de peliculas
            cargar(lp);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
