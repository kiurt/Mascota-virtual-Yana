package com.mascota.yanamascotavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class vetanaJuegos extends AppCompatActivity {

    private Mascota mascota;
    private TextView diversionTextView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vetana_juegos);

        mascota = new Mascota();
        diversionTextView = findViewById(R.id.diversionTextView); // Asegúrate de agregar este TextView en tu layout XML

        ImageView gataImageView = findViewById(R.id.gata);
        ImageView btnJugar = findViewById(R.id.btnJugar);

        ImageView btnComedor = findViewById(R.id.btnComedor);
        ImageView btnEstudio = findViewById(R.id.btnEstudio);
        ImageView btnDucha = findViewById(R.id.btnDucha);
        ImageView btnJuegos = findViewById(R.id.btnJuegos);
        ImageView btnDormitorio = findViewById(R.id.btnDormitorio);

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mascota.jugar();
                actualizarEstado();
            }
        });

        btnEstudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vetanaJuegos.this, SalaDeEstudio.class);
                startActivity(intent);
            }
        });

        btnComedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vetanaJuegos.this, ventanaComedor.class);
                startActivity(intent);
            }
        });

        btnDucha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vetanaJuegos.this, ventanaDucha.class);
                startActivity(intent);
            }
        });

        btnJuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vetanaJuegos.this, vetanaJuegos.class);
                startActivity(intent);
            }
        });

        btnDormitorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vetanaJuegos.this, ventanaDormitorio.class);
                startActivity(intent);
            }
        });

        // Inicia el ciclo de actualización de la mascota
        iniciarActualizacionEstado();
    }

    private void actualizarEstado() {
        diversionTextView.setText("Diversión: " + mascota.getDiversion());
    }

    private void iniciarActualizacionEstado() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mascota.pasarTiempo();
                actualizarEstado();
                handler.postDelayed(this, 1000); // Actualiza cada segundo
            }
        }, 1000);
    }
}
