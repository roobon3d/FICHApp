package edu.cftic.fichapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterFichaje extends RecyclerView.Adapter <AdapterFichaje.ViewHolderFichaje> {


    ArrayList<Fichaje> listaFichajes;

    public AdapterFichaje(ArrayList<Fichaje> listaFichajes) {
        this.listaFichajes = listaFichajes;
    }

    @NonNull
    @Override
    public ViewHolderFichaje onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_fichaje,null,false);

        return new ViewHolderFichaje(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFichaje holder, int i) {

        holder.mensaje.setText(listaFichajes.get(i).getMensaje());

        /*
        //TODO ver como hacer esto
        // Si es fichaje de entrada
        holder.hora.setText(listaFichajes.get(i).getHoraEntrada());
        holder.iconoEntradaSalida.setImageResource(R.drawable.entrada);
        // Si es fichaje de salida
        holder.hora.setText(listaFichajes.get(i).getHoraSalida());
        holder.iconoEntradaSalida.setImageResource(R.drawable.salida);
        */
    }

    @Override
    public int getItemCount() {
        return listaFichajes.size();
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
}
