package edu.cftic.fichapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import edu.cftic.fichapp.bean.Empleado;
import edu.cftic.fichapp.bean.Fichaje;
import edu.cftic.fichapp.persistencia.DB;
import edu.cftic.fichapp.util.Constantes;
import edu.cftic.fichapp.util.Fecha;

public class ConsultaFichajeActivity extends AppCompatActivity {

    private ArrayList<Fichaje> listaFichajes;
    private Map<String, ArrayList<Fichaje>> porDia;


    private RecyclerView recyclerFichajes;
    private RecyclerView.LayoutManager layoutManager;

    private EditText fechaInicioEdTxt;
    private EditText fechaFinEdTxt;
    private Timestamp de , hasta;
    private Button botonConsultar;
    private Empleado u = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_fichaje);




        fechaInicioEdTxt = findViewById(R.id.fechaInicio);
        fechaFinEdTxt = findViewById(R.id.fechaFin);
        botonConsultar = findViewById(R.id.consultarBtn);
        botonConsultar.setEnabled(false);
        recyclerFichajes = (RecyclerView) findViewById(R.id.recyclerFichajesId);

        u = DB.empleados.getEmpleadoId(2); // comentar esta linea y descomentar la siguiente cuando se integre el proyecto
        // u = (Empleado) getIntent().getExtras().get(Constantes.EMPLEADO);


        fechaInicioEdTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // +1 porque Enero es cero
                        final String selectedDate = day + " / " + (month+1) + " / " + year;
                        fechaInicioEdTxt.setText(selectedDate);

                        de = new Timestamp(new Date().getTime());

                        Calendar cc = new GregorianCalendar();
                        cc.clear();
                        cc.setTimeInMillis(de.getTime());
                        cc.set(Calendar.YEAR,year);
                        cc.set(Calendar.MONTH,month);
                        cc.set(Calendar.DAY_OF_MONTH,day);

                       de = Fecha.inicio(new Timestamp(cc.getTimeInMillis()));

                       Log.d(Constantes.TAG_APP, " De = "+de);

                       botonConsultar.setEnabled (!fechaInicioEdTxt.getText().toString().isEmpty() && !fechaFinEdTxt.getText().toString().isEmpty());


                    }
                });
                newFragment.show(getSupportFragmentManager(), "datePicker");


            }
        });

        fechaFinEdTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // +1 porque Enero es cero
                        final String selectedDate = day + " / " + (month+1) + " / " + year;
                        fechaFinEdTxt.setText(selectedDate);

                        hasta = new Timestamp(new Date().getTime());

                        Calendar cc = new GregorianCalendar();
                        cc.clear();
                        cc.setTimeInMillis(hasta.getTime());
                        cc.set(Calendar.YEAR,year);
                        cc.set(Calendar.MONTH,month);
                        cc.set(Calendar.DAY_OF_MONTH,day);

                        hasta = Fecha.fin(new Timestamp(cc.getTimeInMillis()));

                        Log.d(Constantes.TAG_APP, " Hasta = "+hasta);

                        botonConsultar.setEnabled (!fechaInicioEdTxt.getText().toString().isEmpty() && !fechaFinEdTxt.getText().toString().isEmpty());
                    }
                });
                newFragment.show(getSupportFragmentManager(), "datePicker");


            }
        });



        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (de.after(hasta)){

                    Toast.makeText(ConsultaFichajeActivity.this, "Fecha inicio debe ser anterior a Fecha fin", Toast.LENGTH_LONG).show();

                    return;

                }

                listaFichajes = (ArrayList<Fichaje>) DB.fichar.getFichaje(u.getId_empleado(),de,hasta);
                construirRecycler();


                Log.d("FichApp", "construir recycler");

            }
        });



       /*int empleadoId = u.getId_empleado();

        Fichaje ultimoFichaje = DB.fichar.getFichajeUltimo(empleadoId);
        Timestamp de = ultimoFichaje.getFechainicio();
        Timestamp hasta = ultimoFichaje.getFechainicio();


       fichajesDesdeDB(u);

        ArrayList<Fichaje> fee = (ArrayList<Fichaje>) DB.fichar.getFichaje(empleadoId, de, hasta);
        for(Fichaje es : fee){
            Log.i("APPK", "Fichaje :: "+es);
        }*/


    }



    private void fichajesDesdeDB(Empleado usuario) {

        for(int i=0;i<=10;i++){

            DB.fichar.nuevo(new Fichaje(usuario,new Timestamp(new Date().getTime()),new Timestamp(0),"Entrada x"));
            DB.fichar.nuevo(new Fichaje(usuario,new Timestamp(new Date().getTime()),new Timestamp(new Date().getTime()),"Salida x"));

        }

    }



    private void construirRecycler() {

       Log.d (Constantes.TAG_APP,"fichajes= "+listaFichajes.size());

       // SimpleDateFormat sf = new SimpleDateFormat("d MMM yyyy, EEEE");
        SimpleDateFormat sfd =  new SimpleDateFormat("yyyyMMdd");

        porDia = new TreeMap<String, ArrayList<Fichaje>>();
        String dia;
        ArrayList<Fichaje> tmpFichaje = new ArrayList<>();
       for(Fichaje cadaFichaje : listaFichajes){
          dia = sfd.format(cadaFichaje.getFechainicio().getTime());
          if(porDia.containsKey(dia)){
              tmpFichaje.add(cadaFichaje);
           } else {
              tmpFichaje = new ArrayList<>();
          }
          porDia.put(dia, tmpFichaje );
       }

       // List<Person> peopleByAge = new ArrayList<>(people.values());


        recyclerFichajes.setLayoutManager(new LinearLayoutManager(this));

        AdapterFecha adapter = new AdapterFecha(this, porDia, ConsultaFichajeActivity.this);

        recyclerFichajes.setAdapter(adapter);

    }

    public void añadirFichajeClick(View view) {

        //TODO hacer intent a activity fichar
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


