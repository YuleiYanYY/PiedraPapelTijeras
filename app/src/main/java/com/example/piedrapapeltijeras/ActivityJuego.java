package com.example.piedrapapeltijeras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import java.nio.file.Files;

public class ActivityJuego extends AppCompatActivity {
    TextView tvBienvenida, tvOponente, tvTurno, tvTu;
    ImageButton ibIntentoOponente, ibIntentoJugador, ibPiedra, ibPapel, ibTijeras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        iniciarlizarVariables();
        Bundle bundle = getIntent().getExtras();
        tvBienvenida.setText(tvBienvenida.getText().toString() + bundle.getString("res1"));
    }

    private void iniciarlizarVariables() {
        tvBienvenida = findViewById(R.id.tvBienvenida);
        tvOponente = findViewById(R.id.tvOponente);
        tvTurno = findViewById(R.id.tvTurno);
        tvTu = findViewById(R.id.tvTu);
        ibIntentoOponente = findViewById(R.id.ibIntentoOponente);
        ibIntentoJugador = findViewById(R.id.ibIntentoJugador);
        ibPiedra = findViewById(R.id.ibPiedra);
        ibPapel = findViewById(R.id.ibPapel);
        ibTijeras = findViewById(R.id.ibTijeras);
    }
}