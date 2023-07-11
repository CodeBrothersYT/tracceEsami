package it.core;

import java.util.Map;

public interface Produzione {
    String getTitolo();

    static <T extends Produzione> void stampaProduzioni(Map<Integer, T> produzioni) {
        System.out.println("Dischi presenti nella classifica");
        produzioni.entrySet()
                .stream()
                .map(e -> "%d) %s".formatted(e.getKey(), e.getValue().getTitolo()))
                .forEach(System.out::println);
    }
}
