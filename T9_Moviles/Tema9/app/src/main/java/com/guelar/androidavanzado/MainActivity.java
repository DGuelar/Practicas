package com.guelar.androidavanzado;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn93  = findViewById(R.id.btn93);
        Button btn95  = findViewById(R.id.btn95);
        Button btn96  = findViewById(R.id.btn96);
        Button btn98  = findViewById(R.id.btn98);
        Button btn910 = findViewById(R.id.btn910);

        btn93.setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad93.class)));
        btn95.setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad95.class)));
        btn96.setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad96.class)));
        btn98.setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad98.class)));
        btn910.setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad910.class)));
    }
}