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
    int vOponente, vJugador, puntuacion, pGanadas;
    StringBuilder sbPuntuacion, sbPGanadas;
    String[] opciones;
    Toast toast;


    AlertDialog.Builder adBuilder;

    TextView tvBienvenida, tvPuntuacion, tvPGanadas;
    ImageView ivIntentoOponente, ivIntentoJugador;
    ImageButton ibPiedra, ibPapel, ibTijeras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        iniciarlizarVariables();

    }

    private void iniciarlizarVariables() {
        sbPuntuacion = new StringBuilder(getString(R.string.tvPuntuacion));
        sbPGanadas = new StringBuilder(getString(R.string.tvPGanadas));
        opciones = new String[]{"Jugar otra partida", "Reiniciar", "Salir del juego"};
        tvBienvenida = findViewById(R.id.tvBienvenida);
        tvPuntuacion = findViewById(R.id.tvPuntuacion);
        tvPGanadas = findViewById(R.id.tvPGanadas);
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
        juegaOponente();
        calcular();
    }

    public void papel(View view) {
        vJugador = 1;
        ivIntentoJugador.setImageResource(R.drawable.papel);
        juegaOponente();
        calcular();

    }

    public void tijeras(View view) {
        vJugador = 2;
        ivIntentoJugador.setImageResource(R.drawable.tijeras);
        juegaOponente();
        calcular();
    }

    private void juegaOponente() {
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

        actualizarMarcador();
        calcularGanador();
    }

    private void setToast(String mensaje) {
        toast = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 170);
        toast.show();
    }

    /**
     * Menú que aparece cuando la puntuación se inferior o superior a cierta cifra
     */
    private void calcularGanador() {
        if (puntuacion >= 2 || puntuacion <= -2) {
            if (puntuacion <= -2)
                pGanadas -= 1;
            if (puntuacion >= 2)
                pGanadas += 1;
            adBuilder.setTitle("Elige una opción");
            adBuilder.setItems(opciones, (dialog, opcion) -> {
                switch (opcion) {
                    case 0:
                        jugarOtra();
                        break;
                    case 1:
                        reiniciar();
                        break;
                    case 2:
                        finish();
                        break;
                }
            });
            AlertDialog ad = adBuilder.create();
            ad.show();
        }

    }

    /**
     * Los StringBuilder son inicializados al entrar a los valores de R.string
     * En este método primero se le suman los valores de los int, seguidamente
     * sus valores son asignados a los TextViews, tras ello se reasignan los valores
     * de los StringBuilders a los valores de R.String para limpiarlos.
     *
     */
    private void actualizarMarcador() {
        sbPuntuacion.append(puntuacion);
        sbPGanadas.append(pGanadas);
        tvPuntuacion.setText(sbPuntuacion);
        tvPGanadas.setText(sbPGanadas);
        sbPuntuacion = new StringBuilder(getString(R.string.tvPuntuacion));
        sbPGanadas = new StringBuilder(getString(R.string.tvPGanadas));
    }

    private void jugarOtra() {
        toast.cancel();
        puntuacion = 0;
        actualizarMarcador();
    }

    private void reiniciar() {
        toast.cancel();
        puntuacion = 0;
        pGanadas = 0;
        actualizarMarcador();
    }


}