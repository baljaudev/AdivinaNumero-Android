package com.example.adivinanumero2.data;

import java.util.Random;

public class Datos {
    public static final int NUM_INTENTOS = 5;
    public static final int MIN = 1;
    public static final int MAX = 100;

    public static int generarNumSecreto() {
        Random rd = new Random();
        return rd.nextInt(MAX - MIN + 1) + MIN;
    }
}
