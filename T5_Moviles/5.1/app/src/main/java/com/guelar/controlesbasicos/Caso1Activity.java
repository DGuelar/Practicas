package com.guelar.controlesbasicos;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Caso1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // No necesitamos nada más: todo está definido en el XML
        setContentView(R.layout.activity_caso1);
    }
}