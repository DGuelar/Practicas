package com.guelar.androidavanzado;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class FragmentosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detalle);

        if (getIntent().hasExtra(FragmentoDetalle.ARG_ITEM_ID)) {
            String itemId = getIntent().getStringExtra(FragmentoDetalle.ARG_ITEM_ID);
            FragmentoDetalle frag = FragmentoDetalle.newInstance(itemId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, frag)
                    .commit();
        }
    }
}