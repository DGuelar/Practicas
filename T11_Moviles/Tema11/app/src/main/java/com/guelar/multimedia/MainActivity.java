package com.guelar.multimedia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn111).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad11_1.class)));
        findViewById(R.id.btn112).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad11_2.class)));
        findViewById(R.id.btn113).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad11_3.class)));
        findViewById(R.id.btn114).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad11_4.class)));
        findViewById(R.id.btn115).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad11_5.class)));
        findViewById(R.id.btn116).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad11_6.class)));
        findViewById(R.id.btn117).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad11_7.class)));
        findViewById(R.id.btn118).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad11_8.class)));
        findViewById(R.id.btn119).setOnClickListener(v ->
                startActivity(new Intent(this, MainActividad11_9.class)));
    }
}