package edu.cftic.fichapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import edu.cftic.fichapp.bean.Fichaje;

public class AdapterFichaje extends RecyclerView.Adapter <AdapterFichaje.ViewHolderFichaje> {


    private ArrayList<Fichaje> listaFichajes; // datos a visualizar
    private LayoutInflater inflador;


    public AdapterFichaje(Context contexto, ArrayList<Fichaje> listaFichajes) {
        inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.listaFichajes = listaFichajes;
    }

    public class ViewHolderFichaje extends RecyclerView.ViewHolder {

        TextView hora, mensaje;
        ImageView iconoEntradaSalida;

        public ViewHolderFichaje(@NonNull View itemView) {
            super(itemView);
            hora = itemView.findViewById(R.id.textoHora);
            mensaje = itemView.findViewById(R.id.textoMensaje);
            iconoEntradaSalida = itemView.findViewById(R.id.imgIO);

        }
    }

    @NonNull
    @Override
    public ViewHolderFichaje onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_fichaje,null,false);

        return new ViewHolderFichaje(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFichaje holder, int posicion) {



      // Si es fichaje de entrada
        if (listaFichajes.get(posicion).getFechafin().equals(new Timestamp(0))) {


            Timestamp ts = listaFichajes.get(posicion).getFechainicio();
            Date fecha = new Date();
            fecha.setTime(ts.getTime());
            String fechaFormateada = new SimpleDateFormat("HH:mm").format(fecha);


            holder.hora.setText(fechaFormateada);
            holder.mensaje.setText(listaFichajes.get(posicion).getMensaje());
            holder.iconoEntradaSalida.setImageResource(R.drawable.entrada);
        } else {
            // Si es fichaje de salida


            Timestamp ts = listaFichajes.get(posicion).getFechafin();
            Date fecha = new Date();
            fecha.setTime(ts.getTime());
            String fechaFormateada = new SimpleDateFormat("HH:mm").format(fecha);


            holder.hora.setText(fechaFormateada);
            holder.mensaje.setText(listaFichajes.get(posicion).getMensaje());
            holder.iconoEntradaSalida.setImageResource(R.drawable.salida);
        }

    }

    @Override
    public int getItemCount() {

        return listaFichajes.size();
    }





    //MÉTODO PARA CONVERTIR UN TIMESPAMP EN UN CHARSEQUENCE

  /*  public static CharSequence crearFecha (long timestamp) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTimeInMillis(timestamp);

        Date d = calendario.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(d);
    }

    public static String getDateTimeFromTimeStamp(Long time, String mDateFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(mDateFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date dateTime = new Date(time);
        return dateFormat.format(dateTime);
    }*/


}
