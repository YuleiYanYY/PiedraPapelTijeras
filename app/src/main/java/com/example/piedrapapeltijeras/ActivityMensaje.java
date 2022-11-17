package com.example.piedrapapeltijeras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMensaje extends AppCompatActivity {
    EditText etNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);
        etNumero = findViewById(R.id.etNumero);
    }

    public void enviarMensaje(View view) {
        Intent intentJuego = getIntent();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + etNumero.getText().toString()));
        intent.putExtra("sms_body", intentJuego.getStringExtra("puntuacion") + "  " + intentJuego.getStringExtra("pGanadas"));
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
        else
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}