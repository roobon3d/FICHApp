package edu.cftic.fichapp;

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
    private RecyclerView recyclerFichajes;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichaje);

        fichajesPruebas();
        construirRecycler();
        Log.d("FichApp", "construir recycler");
    }

    private void construirRecycler() {



        recyclerFichajes = (RecyclerView) findViewById(R.id.recyclerFichajesId);

        recyclerFichajes.setLayoutManager(new LinearLayoutManager(this));

        AdapterFichaje adapter = new AdapterFichaje(this, listaFichajes);

        recyclerFichajes.setAdapter(adapter);

    }

    private void fichajesPruebas()
    {

        //TODO Crear fichajes para pruebas

/*
        Empresa em = new Empresa("B123456", "XYZYZ SA", "T T", "xyz@xyz.com");
        //boolean v = DB.empresas.nuevo(em);
        Empleado nu = DB.empleados.getEmpleadoUsuarioClave("", "");
        ArrayList<Empresa> ae = (ArrayList<Empresa>) DB.empresas.getEmpresas();
        em = DB.empresas.ultimo();
        Empleado tr = new Empleado("JUAN YONG 2", "JYON3", "12345", "B", false, em);
        boolean t = DB.empleados.nuevo(tr);
        ArrayList<Empleado> at = (ArrayList<Empleado>) DB.empleados.getEmpleados();
        Empleado tr = new Empleado("JUAN YONG 2", "JYON3", "12345", "B", false, new Empresa("B123456", "XYZYZ SA", "T T", "xyz@xyz.com"));
        tr = DB.empleados.ultimo();
        Log.i(Constantes.TAG_APP, "E: " + tr);

        ArrayList<Empleado> at = (ArrayList<Empleado>) DB.empleados.getEmpleados();*/


        Empleado tr = new Empleado("JUAN YONG 2", "JYON3", "12345", "B", false, new Empresa("B123456", "XYZYZ SA", "T T", "xyz@xyz.com"));

        Timestamp de = new Timestamp(new Date().getTime());
        Timestamp hasta = new Timestamp(new Date().getTime());


        listaFichajes = new ArrayList<>();

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
        listaFichajes.add(new Fichaje(tr, de, null, "Mensaje de entrada"));
        listaFichajes.add(new Fichaje(tr, de, hasta, "Mensaje de salida"));




    }

}

