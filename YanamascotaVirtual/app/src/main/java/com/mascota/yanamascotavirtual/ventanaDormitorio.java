package com.mascota.yanamascotavirtual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ventanaDormitorio extends AppCompatActivity {

    private boolean isDark = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_dormitorio);

        // Referencias a los elementos de la UI
        ConstraintLayout layout = findViewById(R.id.rootLayout);
        ImageView gataImageView = findViewById(R.id.gata);
        ImageView btnDormir = findViewById(R.id.btnJugar);
        View overlayView = findViewById(R.id.overlayView);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        ImageView btnComedor = findViewById(R.id.btnComedor);
        ImageView btnEstudio = findViewById(R.id.btnEstudio);
        ImageView btnDucha = findViewById(R.id.btnDucha);
        ImageView btnJuegos = findViewById(R.id.btnJuegos);
        ImageView btnDormitorio = findViewById(R.id.btnDormitorio);

        btnDormir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDark) {
                    // Cambia el fondo a oscuro y la imagen
                    overlayView.setAlpha(0.5f); // Superposición semi-transparente negra
                    gataImageView.setImageResource(R.drawable.yanadormida);

                    // Mostrar la barra de progreso y actualizarla durante 15 segundos
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(0);
                    Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        int progress = 0;

                        @Override
                        public void run() {
                            if (progress < 100) {
                                progress += 1;
                                progressBar.setProgress(progress);
                                handler.postDelayed(this, 150);
                            } else {
                                // Ocultar la barra de progreso cuando se complete
                                progressBar.setVisibility(View.GONE);

                                // Vuelve al estado normal
                                overlayView.setAlpha(0.0f);
                                gataImageView.setImageResource(R.drawable.yanabocaabierta);
                                isDark = false; // Restablece el estado oscuro
                            }
                        }
                    };
                    handler.post(runnable);
                }
                isDark = true; // Cambia el estado
            }
        });

        btnEstudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia a la actividad SalaDeEstudio
                Intent intent = new Intent(ventanaDormitorio.this, SalaDeEstudio.class);
                startActivity(intent);
            }
        });

        btnComedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia a la actividad ventanaComedor
                Intent intent = new Intent(ventanaDormitorio.this, ventanaComedor.class);
                startActivity(intent);
            }
        });

        btnDucha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia a la actividad ventanaDucha
                Intent intent = new Intent(ventanaDormitorio.this, ventanaDucha.class);
                startActivity(intent);
            }
        });

        btnJuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia a la actividad ventanaJuegos
                Intent intent = new Intent(ventanaDormitorio.this, vetanaJuegos.class);
                startActivity(intent);
            }
        });

        btnDormitorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambia a la actividad ventanaDormitorio
                Intent intent = new Intent(ventanaDormitorio.this, ventanaDormitorio.class);
                startActivity(intent);
            }
        });
    }
}
