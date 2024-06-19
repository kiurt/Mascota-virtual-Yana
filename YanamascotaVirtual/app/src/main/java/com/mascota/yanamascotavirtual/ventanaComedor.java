package com.mascota.yanamascotavirtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ventanaComedor extends AppCompatActivity {

    private Mascota mascota;
    private TextView hambreTextView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_comedor);

        mascota = new Mascota();
        hambreTextView = findViewById(R.id.hambreTextView); // Asegúrate de agregar este TextView en tu layout XML

        ImageView gataImageView = findViewById(R.id.gata);
        ImageView btnComer = findViewById(R.id.btnJugar);

        ImageView btnComedor = findViewById(R.id.btnComedor);
        ImageView btnEstudio = findViewById(R.id.btnEstudio);
        ImageView btnDucha = findViewById(R.id.btnDucha);
        ImageView btnJuegos = findViewById(R.id.btnJuegos);
        ImageView btnDormitorio = findViewById(R.id.btnDormitorio);

        btnComer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mascota.alimentar();
                actualizarEstado();
            }
        });

        btnEstudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ventanaComedor.this, SalaDeEstudio.class);
                startActivity(intent);
            }
        });

        btnComedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ventanaComedor.this, ventanaComedor.class);
                startActivity(intent);
            }
        });

        btnDucha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ventanaComedor.this, ventanaDucha.class);
                startActivity(intent);
            }
        });

        btnJuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ventanaComedor.this, vetanaJuegos.class); // Corregido
                startActivity(intent);
            }
        });

        btnDormitorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ventanaComedor.this, ventanaDormitorio.class);
                startActivity(intent);
            }
        });

        // Inicia el ciclo de actualización de la mascota
        iniciarActualizacionEstado();
    }

    private void actualizarEstado() {
        hambreTextView.setText("Hambre: " + mascota.getHambre());
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
