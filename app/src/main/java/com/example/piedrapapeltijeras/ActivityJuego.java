package com.example.piedrapapeltijeras;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ActivityJuego extends AppCompatActivity {
    int vOponente, vJugador, puntuacion = 0;
    String[] opciones;
    Toast toast;

    AlertDialog.Builder adBuilder;

    TextView tvBienvenida, tvPuntuacion;
    ImageView ivIntentoOponente, ivIntentoJugador;
    ImageButton ibPiedra, ibPapel, ibTijeras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        iniciarlizarVariables();

    }

    private void iniciarlizarVariables() {
        opciones = new String[]{"Reiniciar", "Jugar otra partida", "Salir del juego"};
        tvBienvenida = findViewById(R.id.tvBienvenida);
        tvPuntuacion = findViewById(R.id.tvPuntuacion);
        ivIntentoOponente = findViewById(R.id.ivIntentoOponente);
        ivIntentoJugador = findViewById(R.id.ivIntentoJugador);
        ibPiedra = findViewById(R.id.ibPiedra);
        ibPapel = findViewById(R.id.ibPapel);
        ibTijeras = findViewById(R.id.ibTijeras);

        Bundle bundle = getIntent().getExtras();
        tvBienvenida.setText(tvBienvenida.getText().toString() + " " + bundle.getString("res1"));

        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        adBuilder = new AlertDialog.Builder(this);
        adBuilder.setCancelable(false);


    }

    public void piedra(View view) {
        vJugador = 0;
        ivIntentoJugador.setImageResource(R.drawable.piedra);
        oponente();
        calcular();
    }

    public void papel(View view) {
        vJugador = 1;
        ivIntentoJugador.setImageResource(R.drawable.papel);
        oponente();
        calcular();

    }

    public void tijeras(View view) {
        vJugador = 2;
        ivIntentoJugador.setImageResource(R.drawable.tijeras);
        oponente();
        calcular();
    }

    private void oponente() {
        Random r = new Random();
        int i = r.nextInt(3);
        switch (i) {
            case 0:
                vOponente = 0;
                ivIntentoOponente.setImageResource(R.drawable.piedra);
                ivIntentoOponente.setRotation(180);
                break;
            case 1:
                vOponente = 1;
                ivIntentoOponente.setImageResource(R.drawable.papel);
                ivIntentoOponente.setRotation(180);
                break;
            case 2:
                vOponente = 2;
                ivIntentoOponente.setImageResource(R.drawable.tijeras);
                ivIntentoOponente.setRotation(180);
                break;
        }
    }

    private void calcular() {
        if (vOponente == vJugador) {
            toast.cancel();
            setToast("Empate");
        } else if (vJugador == 0 && vOponente == 2 || vJugador == 1 && vOponente == 0 || vJugador == 2 && vOponente == 1) {
            {
                puntuacion += 1;
                toast.cancel();
                setToast("Puntuación + 1");
            }
        } else {
            puntuacion -= 1;
            toast.cancel();
            setToast("Puntuación - 1");
        }

        tvPuntuacion.setText("Puntuacion: " + puntuacion);
        calcularGanador();
    }

    private void setToast(String mensaje) {
        toast = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 170);
        toast.show();
    }

    private void calcularGanador() {
        if (puntuacion >= 2 || puntuacion <= -2) {
            adBuilder.setTitle("Elige una opción");
            adBuilder.setItems(opciones, (dialog, opcion) -> {
                switch (opcion) {
                    case 0:
                        reiniciar();
                        break;
                    case 1:
                        jugarOtra();
                        break;
                    case 2:
                        finish();
                        break;
                }
            });
            AlertDialog ad= adBuilder.create();
            ad.show();
        }

    }

    private void jugarOtra() {
    }

    private void reiniciar() {
    }


}