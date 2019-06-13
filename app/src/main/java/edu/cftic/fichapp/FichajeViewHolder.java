package edu.cftic.fichapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class FichajeViewHolder extends RecyclerView.ViewHolder {

    TextView hora, mensaje;
    ImageView iconoEntradaSalida;

    public FichajeViewHolder(@NonNull View itemView) {
        super(itemView);
        hora = itemView.findViewById(R.id.textoHora);
        mensaje = itemView.findViewById(R.id.textoMensaje);
        iconoEntradaSalida = itemView.findViewById(R.id.imgIO);

    }
}

