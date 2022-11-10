package com.example.piedrapapeltijeras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityMenuInicio extends AppCompatActivity {
    Button bEntrar, bSalir;
    EditText etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
        inicializarVariables();
    }

    private void inicializarVariables() {
        bEntrar = findViewById(R.id.bEntrar);
        bSalir = findViewById(R.id.bSalir);
        etNombre = findViewById(R.id.etNombre);
    }

    public void entrar(View view) {
        Intent intent1 = new Intent(view.getContext(), ActivityJuego.class);

        intent1.putExtra("res1", etNombre.getText().toString());

        startActivityForResult(intent1, 0);
    }

    public void salir(View view) {
        finish();
    }
}