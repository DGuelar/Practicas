package com.guelar.androidavanzado;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentoDetalle extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private Lista_entrada mItem;

    public static FragmentoDetalle newInstance(String id) {
        FragmentoDetalle frag = new FragmentoDetalle();
        Bundle args = new Bundle();
        args.putString(ARG_ITEM_ID, id);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = Contenido.ENT_MAPA.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detalle, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.txtDetalleTitulo)).setText(mItem.getTextoEncima());
            ((TextView) rootView.findViewById(R.id.txtDetalleContenido)).setText(mItem.getTextoDebajo());
            ((ImageView) rootView.findViewById(R.id.imgDetalle)).setImageResource(mItem.getIdImagen());

            GradientDrawable barra = new GradientDrawable();
            barra.setColor(mItem.getColorFondo());
            barra.setCornerRadius(24f);
            rootView.findViewById(R.id.barraColor).setBackground(barra);
        }

        return rootView;
    }
}