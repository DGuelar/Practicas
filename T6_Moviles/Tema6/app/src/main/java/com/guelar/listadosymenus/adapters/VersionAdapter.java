package com.guelar.listadosymenus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.guelar.listadosymenus.R;
import com.guelar.listadosymenus.pojos.Encapsulador;

import java.util.ArrayList;
public class VersionAdapter extends BaseAdapter {

    private final Context              context;
    private final ArrayList<Encapsulador> datos;
    private final LayoutInflater       inflater;

    public VersionAdapter(Context context, ArrayList<Encapsulador> datos) {
        this.context  = context;
        this.datos    = datos;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return datos.size(); }

    @Override
    public Object getItem(int position) { return datos.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_version, parent, false);
            holder = new ViewHolder();
            holder.imagen      = convertView.findViewById(R.id.ivVersion);
            holder.radioButton = convertView.findViewById(R.id.rbVersion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Encapsulador versionActual = datos.get(position);
        holder.imagen.setImageResource(versionActual.getIdImagen());
        holder.radioButton.setText(versionActual.getTitulo());

        holder.radioButton.setChecked(versionActual.isSeleccionado());

        return convertView;
    }
    private static class ViewHolder {
        ImageView   imagen;
        RadioButton radioButton;
    }
}