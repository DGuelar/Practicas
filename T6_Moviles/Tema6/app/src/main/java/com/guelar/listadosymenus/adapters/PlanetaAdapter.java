package com.guelar.listadosymenus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.guelar.listadosymenus.R;
import com.guelar.listadosymenus.pojos.Planeta;

import java.util.List;
public class PlanetaAdapter extends ArrayAdapter<Planeta> {

    public PlanetaAdapter(@NonNull Context context, @NonNull List<Planeta> planetas) {
        super(context, 0, planetas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_planetas, parent, false);

            holder = new ViewHolder();
            holder.imagen      = convertView.findViewById(R.id.ivPlaneta);
            holder.nombre      = convertView.findViewById(R.id.tvNombrePlaneta);
            holder.descripcion = convertView.findViewById(R.id.tvDescripcionPlaneta);
            holder.favorito    = convertView.findViewById(R.id.cbFavorito);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Planeta planetaActual = getItem(position);

        holder.nombre.setText(planetaActual.getNombre());
        holder.descripcion.setText(planetaActual.getDescripcion());
        holder.imagen.setImageResource(planetaActual.getIdImagen());

        holder.favorito.setOnCheckedChangeListener(null);
        holder.favorito.setChecked(planetaActual.isEsFavorito());

        holder.favorito.setOnCheckedChangeListener((buttonView, isChecked) -> {
            planetaActual.setEsFavorito(isChecked);
            String estado = isChecked ? "añadido a favoritos" : "quitado de favoritos";
            Toast.makeText(getContext(),
                    planetaActual.getNombre() + " " + estado,
                    Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
    private static class ViewHolder {
        ImageView imagen;
        TextView  nombre;
        TextView  descripcion;
        CheckBox  favorito;
    }
}