package edu.cftic.fichapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import edu.cftic.fichapp.bean.Fichaje;

public class FichajeActivity extends AppCompatActivity {

    ArrayList<Fichaje> listaFichajes;
    RecyclerView recyclerFichajes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichaje);


        construirRecycler();
        Log.d ("FichApp","construir recycler");
    }

    private void construirRecycler() {

        listaFichajes = new ArrayList<>();

        recyclerFichajes = (RecyclerView) findViewById(R.id.recyclerFichajesId);

        recyclerFichajes.setLayoutManager(new LinearLayoutManager(this));

        AdapterFichaje adapter = new AdapterFichaje(listaFichajes);

        recyclerFichajes.setAdapter(adapter);

    }


}

