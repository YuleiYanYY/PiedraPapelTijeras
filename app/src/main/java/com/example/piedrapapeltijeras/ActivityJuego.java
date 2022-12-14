package com.example.piedrapapeltijeras;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.piedrapapeltijeras.databinding.ActivityJuegoBinding;
import java.util.Random;


public class ActivityJuego extends AppCompatActivity {

    ActivityJuegoBinding b;

    int vOponente, vJugador, puntuacion, pGanadas;
    StringBuilder sbPuntuacion, sbPGanadas;
    String[] opciones;

    TextView tvBienvenida;
    Toast toast;


    AlertDialog.Builder adBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        b = ActivityJuegoBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        iniciarlizarVariables();
    }

    private void iniciarlizarVariables() {
        sbPuntuacion = new StringBuilder(getString(R.string.tvPuntuacion));
        sbPGanadas = new StringBuilder(getString(R.string.tvPGanadas));
        opciones = new String[]{"Salir del juego", "Jugar otra partida", "Enviar resultado a un contacto", "Reiniciar"};

        Intent intentAMEnuInicio = getIntent();

        tvBienvenida = findViewById(R.id.tvBienvenida);
        tvBienvenida.setText(getString(R.string.tvBienvenida) +  intentAMEnuInicio.getStringExtra("nombre"));

        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        adBuilder = new AlertDialog.Builder(this);
        adBuilder.setCancelable(false);
    }

    public void piedra(View view) {
        vJugador = 0;
        b.ivIntentoJugador.setImageResource(R.drawable.piedra);
        juegaOponente();
        calcular();
    }

    public void papel(View view) {
        vJugador = 1;
        b.ivIntentoJugador.setImageResource(R.drawable.papel);
        juegaOponente();
        calcular();

    }

    public void tijeras(View view) {
        vJugador = 2;
        b.ivIntentoJugador.setImageResource(R.drawable.tijeras);
        juegaOponente();
        calcular();
    }

    private void juegaOponente() {
        Random r = new Random();
        int i = r.nextInt(3);
        switch (i) {
            case 0:
                vOponente = 0;
                b.ivIntentoOponente.setImageResource(R.drawable.piedra);
                b.ivIntentoOponente.setRotation(180);
                break;
            case 1:
                vOponente = 1;
                b.ivIntentoOponente.setImageResource(R.drawable.papel);
                b.ivIntentoOponente.setRotation(180);
                break;
            case 2:
                vOponente = 2;
                b.ivIntentoOponente.setImageResource(R.drawable.tijeras);
                b.ivIntentoOponente.setRotation(180);
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
                setToast("Puntuaci??n + 1");
            }
        } else {
            puntuacion -= 1;
            toast.cancel();
            setToast("Puntuaci??n - 1");
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
     * Men?? que aparece cuando la puntuaci??n se inferior o superior a cierta cifra
     */
    private void calcularGanador() {
        if (puntuacion >= 2 || puntuacion <= -2) {
            if (puntuacion <= -2)
                pGanadas -= 1;
            if (puntuacion >= 2)
                pGanadas += 1;
            adBuilder.setTitle("Elige una opci??n");
            adBuilder.setItems(opciones, (dialog, opcion) -> {
                switch (opcion) {
                    case 0:
                        finish();
                        break;

                    case 1:
                        jugarOtra();
                        break;

                    case 2:
                        enviarMensaje();
                        break;

                    case 3:
                        reiniciar();
                        break;
                }
            });
            AlertDialog ad = adBuilder.create();
            ad.show();
        }

    }

    private void enviarMensaje() {
        Intent intentJuego =new Intent(getBaseContext(), ActivityMensaje.class);
        intentJuego.putExtra("puntuacion", b.tvPuntuacion.getText().toString());
        intentJuego.putExtra("pGanadas", b.tvPGanadas.getText().toString());
        startActivity(intentJuego);
    }


    /**
     * Los StringBuilder son inicializados al entrar a los valores de R.string
     * En este m??todo primero se le suman los valores de los int, seguidamente
     * sus valores son asignados a los TextViews, tras ello se reasignan los valores
     * de los StringBuilders a los valores de R.String para limpiarlos.
     */
    private void actualizarMarcador() {
        sbPuntuacion.append(puntuacion);
        sbPGanadas.append(pGanadas);
        b.tvPuntuacion.setText(sbPuntuacion);
        b.tvPGanadas.setText(sbPGanadas);
        reiniciarSB();

    }

    /**
     * Reasigna de forma eficiente los StringBuilders a los valores de R.string
     */
    private void reiniciarSB() {
        sbPuntuacion.delete(0, sbPuntuacion.length());
        sbPGanadas.delete(0, sbPGanadas.length());
        sbPuntuacion.append(getString(R.string.tvPuntuacion));
        sbPGanadas.append(getString(R.string.tvPGanadas));
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

