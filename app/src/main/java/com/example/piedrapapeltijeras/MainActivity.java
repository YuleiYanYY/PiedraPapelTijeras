package com.example.piedrapapeltijeras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bEntrar, bSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVariables();
    }

    private void inicializarVariables() {
        bEntrar = findViewById(R.id.bEntrar);
        bSalir = findViewById(R.id.bSalir);
    }

    public void entrar(View view) {

    }

    public void salir(View view) {
        finish();
    }
}