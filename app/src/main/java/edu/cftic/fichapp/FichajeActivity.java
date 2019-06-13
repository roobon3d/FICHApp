package edu.cftic.fichapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import edu.cftic.fichapp.bean.Empleado;
import edu.cftic.fichapp.bean.Empresa;
import edu.cftic.fichapp.bean.Fichaje;
import edu.cftic.fichapp.persistencia.DB;
import edu.cftic.fichapp.util.Constantes;

public class FichajeActivity extends AppCompatActivity {

    private ArrayList<Fichaje> listaFichajes;
    private ArrayList<Fichaje> listaFichajeAuxiliar;

    private RecyclerView recyclerFichajes;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichaje);

        Empleado u = null;


       // u = (Empleado) getIntent().getExtras().get(Constantes.EMPLEADO);

        u = DB.empleados.ultimo();
        int empleadoId = u.getId_empleado();


        listaFichajes = (ArrayList<Fichaje>) DB.fichar.getFicheje(empleadoId);

       // fichajesDesdeDB(u);

        

        ArrayList<Fichaje> af = (ArrayList<Fichaje>) DB.fichar.getFicheje(u.getId_empleado());

        for(Fichaje f: af){
            Log.d(Constantes.TAG_APP, "F = "+f);
        }

        construirRecycler();
        Log.d("FichApp", "construir recycler");
    }

    private void fichajesDesdeDB(Empleado usuario) {

        for(int i=0;i<=10;i++){

            DB.fichar.nuevo(new Fichaje(usuario,new Timestamp(new Date().getTime()),new Timestamp(0),"Entrada"));
            DB.fichar.nuevo(new Fichaje(usuario,new Timestamp(new Date().getTime()),new Timestamp(new Date().getTime()),"Salida"));

        }

    }


    private void construirRecycler() {



        recyclerFichajes = (RecyclerView) findViewById(R.id.recyclerFichajesId);

        recyclerFichajes.setLayoutManager(new LinearLayoutManager(this));

        AdapterFichaje adapter = new AdapterFichaje(this, listaFichajes);

        recyclerFichajes.setAdapter(adapter);

    }

  /*  private void fichajesPruebas()
    {

        //TODO Crear fichajes para pruebas




        Empleado tr = new Empleado("JUAN YONG 2", "JYON3", "12345", "B", false, new Empresa("B123456", "XYZYZ SA", "T T", "xyz@xyz.com"));

        Timestamp de = new Timestamp(new Date().getTime());
        Timestamp hasta = new Timestamp(new Date().getTime());


        listaFichajes = new ArrayList<>();

        listaFichajes.add(new Fichaje(tr, de, new Timestamp (0), "Mensaje de entrada"));
        listaFichajes.add(new Fichaje(tr, de, hasta, "Mensaje de salida"));
        listaFichajes.add(new Fichaje(tr, de, null, "Mensaje de entrada"));
        listaFichajes.add(new Fichaje(tr, de, hasta, "Mensaje de salida"));
        listaFichajes.add(new Fichaje(tr, de, null, "Mensaje de entrada"));
        listaFichajes.add(new Fichaje(tr, de, hasta, "Mensaje de salida"));
        listaFichajes.add(new Fichaje(tr, de, null, "Mensaje de entrada"));
        listaFichajes.add(new Fichaje(tr, de, hasta, "Mensaje de salida"));
        listaFichajes.add(new Fichaje(tr, de, null, "Mensaje de entrada"));
        listaFichajes.add(new Fichaje(tr, de, hasta, "Mensaje de salida"));
        listaFichajes.add(new Fichaje(tr, de, null, "Mensaje de entrada"));
        listaFichajes.add(new Fichaje(tr, de, hasta, "Mensaje de salida"));
        listaFichajes.add(new Fichaje(tr, de, null, "Mensaje de entrada"));
        listaFichajes.add(new Fichaje(tr, de, hasta, "Mensaje de salida"));
        listaFichajes.add(new Fichaje(tr, de, null, "Mensaje de entrada"));
        listaFichajes.add(new Fichaje(tr, de, hasta, "Mensaje de salida"));




    }*/

}

