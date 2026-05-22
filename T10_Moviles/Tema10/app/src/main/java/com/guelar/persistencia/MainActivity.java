package com.guelar.persistencia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn102).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad102.class)));
        findViewById(R.id.btn104).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad104.class)));
        findViewById(R.id.btn105).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad105.class)));
        findViewById(R.id.btn108).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad108.class)));
        findViewById(R.id.btn109).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad109.class)));
    }
}