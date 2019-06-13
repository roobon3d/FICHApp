package edu.cftic.fichapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.cftic.fichapp.bean.Empleado;
import edu.cftic.fichapp.bean.Empresa;
import edu.cftic.fichapp.bean.Fichaje;
import edu.cftic.fichapp.persistencia.DB;
import edu.cftic.fichapp.util.Constantes;

public class MainActivity extends AppCompatActivity {

//TODO HACER LAS VISTAS DE LOS MENUS PARA IMPLEMENTAR CON LA DB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Comprobar si hay un empleado que sea el Responsable
        //Empleado empleadoControlador = new Empleado(this);
       /* List<String> roles = (ArrayList)DB.empleados.getRoles();

        Intent intent = null;
        if( roles == null || !roles.contains(Constantes.ROL_GESTOR)) {
            // No hay empleados registrados o ninguno de los
            //    empleados registrados es un gestor.
            // Llamar a la Actividad de dar de alta Empleados,
            //        forzando que sea un gestor o responsable.
//            intent = new Intent(this, RegistroGestorActivity.class);


        } else {
            // Ya hay empleados y hay, al menos, un GESTOR.

            // Comprobar si hay dada de alta al menos una Empresa
            //Empresa empresaControlador = new EmpresaControlador(this);
            Empresa empresa = DB.empresas.primero();
            if( null == empresa) {
                // No hay empresas en la Base de datos, hay que dar de alta.
                // TODO Llamar a la Actividad de dar de alta Empresas.
//                intent = new Intent(this, RegistroEmpresaActivity.class);
            } else {
                // TODO Saltar al Login.
                intent = new Intent( this, LoginActivity.class);
            }
        }
        if( null != intent) {
            startActivity( intent);
        }*/


        //ejemplo de uso de la base de datos
        Empresa em = new Empresa("B123456","XYZYZ SA","T T","xyz@xyz.com");
        //boolean v = DB.empresas.nuevo(em);
        Empleado nu = DB.empleados.getEmpleadoUsuarioClave("","");
        ArrayList<Empresa> ae = (ArrayList<Empresa>) DB.empresas.getEmpresas();
        em = DB.empresas.ultimo();

        Log.i(Constantes.TAG_APP, "u: "+ em);

        Empleado tr = new Empleado("JUAN YONG 2","JYON4","12345","B", false, em);
        boolean t = DB.empleados.nuevo(tr);
        ArrayList<Empleado> at = (ArrayList<Empleado>) DB.empleados.getEmpleados();

        tr = DB.empleados.ultimo();
        Log.i(Constantes.TAG_APP, "E: "+tr);
        for(Empleado es : at){
            Log.i(Constantes.TAG_APP, "= "+es);
        }
        at = (ArrayList<Empleado>) DB.empleados.getEmpleados();

        Timestamp de = new Timestamp(new Date().getTime());
        Timestamp hasta = new Timestamp(0);

        Fichaje fe = new Fichaje(tr, de, hasta, "Mensaje");
        Log.i(Constantes.TAG_APP, "F: "+fe);
        boolean d = DB.fichar.nuevo(fe);
        ArrayList<Fichaje> af = (ArrayList<Fichaje>) DB.fichar.getFicheje(tr.getId_empleado());

        for(Fichaje es : af){
            Log.i(Constantes.TAG_APP, "= "+es);
        }

        ArrayList<String> rol = (ArrayList<String>) DB.empleados.getRoles();
        for(String es : rol){
            Log.i(Constantes.TAG_APP, "R:: "+es);
        }

        Fichaje ul = DB.fichar.getFichajeUltimo(tr.getId_empleado());
        Log.i(Constantes.TAG_APP, ""+ul.toString());
    }
}
