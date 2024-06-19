package com.mascota.yanamascotavirtual;

public class Mascota {
    private int hambre;
    private int limpieza;
    private int diversion;

    public Mascota() {
        this.hambre = 100; // Nivel de hambre inicial
        this.limpieza = 100; // Nivel de limpieza inicial
        this.diversion = 100; // Nivel de diversión inicial
    }

    public int getHambre() {
        return hambre;
    }

    public int getLimpieza() {
        return limpieza;
    }

    public int getDiversion() {
        return diversion;
    }

    public void alimentar() {
        if (hambre > 0) {
            hambre -= 10; // Disminuye el hambre al alimentar
        }
    }

    public void duchar() {
        if (limpieza < 100) {
            limpieza += 20; // Aumenta la limpieza al duchar
            if (limpieza > 100) {
                limpieza = 100; // No sobrepasar el máximo de limpieza
            }
        }
    }

    public void jugar() {
        if (diversion < 100) {
            diversion += 20; // Aumenta la diversión al jugar
            if (diversion > 100) {
                diversion = 100; // No sobrepasar el máximo de diversión
            }
        }
    }

    public void pasarTiempo() {
        if (hambre < 100) {
            hambre += 1; // Aumenta el hambre con el tiempo
        }
        if (limpieza > 0) {
            limpieza -= 1; // Disminuye la limpieza con el tiempo
        }
        if (diversion > 0) {
            diversion -= 1; // Disminuye la diversión con el tiempo
        }
    }
}
