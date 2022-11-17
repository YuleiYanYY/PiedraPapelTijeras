package com.example.piedrapapeltijeras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.piedrapapeltijeras.databinding.ActivityMenuInicioBinding;

public class ActivityMenuInicio extends AppCompatActivity {
    ActivityMenuInicioBinding b;

    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
        b = ActivityMenuInicioBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
    }

    public void entrar(View view) {
        Intent intent = new Intent(view.getContext(), ActivityJuego.class);
        intent.putExtra("nombre", b.etNombre.getText().toString());
        startActivity(intent);
        finish();
    }

    public void salir(View view) {
        finish();
    }
}