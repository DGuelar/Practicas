package com.guelar.androidavanzado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private final Context context;
    private final ArrayList<Lista_entrada> datos;

    public Adaptador(Context context, ArrayList<Lista_entrada> datos) {
        this.context = context;
        this.datos = datos;
    }

    @Override public int getCount()                { return datos.size(); }
    @Override public Object getItem(int pos)       { return datos.get(pos); }
    @Override public long getItemId(int pos)       { return pos; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_listado, parent, false);
            holder = new ViewHolder();
            holder.imagen = convertView.findViewById(R.id.imgListado);
            holder.titulo = convertView.findViewById(R.id.txtListadoTitulo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Lista_entrada entrada = datos.get(position);
        holder.imagen.setImageResource(entrada.getIdImagen());
        holder.titulo.setText(entrada.getTextoEncima());
        return convertView;
    }

    private static class ViewHolder {
        ImageView imagen;
        TextView titulo;
    }
}