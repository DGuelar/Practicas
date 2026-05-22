package com.guelar.androidavanzado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActividad93 extends AppCompatActivity
        implements FragmentoLista.Callbacks {
    private boolean mDosPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad93);

        mDosPanel = getResources().getBoolean(R.bool.dos_paneles);

        if (mDosPanel && savedInstanceState == null) {
            FragmentoDetalle detalle = FragmentoDetalle.newInstance(
                    Contenido.ENT_LISTA.get(0).getId());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contenedorDetalle, detalle)
                    .commit();
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (mDosPanel) {
            FragmentoDetalle detalle = FragmentoDetalle.newInstance(id);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedorDetalle, detalle)
                    .commit();
        } else {
            Intent intent = new Intent(this, FragmentosActivity.class);
            intent.putExtra(FragmentoDetalle.ARG_ITEM_ID, id);
            startActivity(intent);
        }
    }
}