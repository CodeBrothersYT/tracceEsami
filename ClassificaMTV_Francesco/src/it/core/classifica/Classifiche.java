package it.core.classifica;

import java.util.Map;

public class Classifiche {
    public static void stampaTitoli(Map<String, Classifica> classifiche) {
        System.out.println("Titoli disponibili:");
        classifiche.keySet().forEach(titolo -> System.out.println("- " + titolo));
    }
}
