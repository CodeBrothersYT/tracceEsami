package it.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Console {
    private final BufferedReader console = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    public static final Console INSTANCE = new Console();

    private Console() {
    }

    public String leggiLinea() {
        try {
            return console.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String leggiLinea(String prompt) {
        System.out.println(prompt);
        return leggiLinea();
    }
}
