package edu.cftic.fichapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.cftic.fichapp.bean.Empleado;
import edu.cftic.fichapp.persistencia.DB;
import edu.cftic.fichapp.util.Constantes;

public class LoginActivity extends AppCompatActivity  {

    EditText usuario = null;
    EditText contraseña = null;
    private DB database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*    setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.usuario);
        contraseña = findViewById(R.id.contraseña);*/


    }

    /**
     * TODO COTEJAR LOS DATOS INTRODUCIDOS CON LA BASE DE DATOS (USUARIO Y CONTRASEÑA)
     * @return
     */
    public void entrar(View view) {

        String nombre = usuario.getText().toString();
        String cont = contraseña.getText().toString();
        Empleado u = database.empleados.getEmpleadoUsuarioClave(nombre,cont);

        if(u==null){

         //   TextView incorrecto = findViewById(R.id.incorrecto);

          //  limpiarText(usuario,contraseña);

            // incorrecto.setText("Usuario y/o contraseña incorrectos, \n Contacte con el administrador");

        }
        //TODO SALTAR AL MENU DE TRABAJADOR O GESTOR

        if (u.getRol()== Constantes.ROL_EMPLEADO ){
           /*
            Intent intent = new Intent(this,MenuEmpleado.class);
             intent.putExtra("ID_EMPLEADO",u.getId_empleado());
            intent.putExtra("ROL_EMPLEADO",u.getRol());
             */



        }else if(u.getRol()==Constantes.ROL_GESTOR ){
           /*
            Intent intent = new Intent(this,MenuGestor.class);
             intent.putExtra("ID_EMPLEADO",u.getId_empleado());
            intent.putExtra("ROL_EMPLEADO",u.getRol());
             */

        }
        /*
        if( null != intent) {

            startActivity( intent);
        }*/
    }


    public void limpiarText(EditText ... array ) {//VARARGS

        for (EditText e : array) {

            e.setText("");
            //e.clearFocus();

        }
    }

}

