package it.core.disco;

import it.core.Console;
import it.core.GenereMusicale;
import it.core.Operazione;
import it.core.singolo.Singolo;

import java.util.function.Consumer;

import static it.core.classifica.Classifica.ClassificaBuilder;

public class CreazioneDisco implements Operazione {
    private final Consumer<? super Disco> consumer;

    public CreazioneDisco(Consumer<? super Disco> consumer) {
        this.consumer = consumer;
    }

    @Override
    public String getDescrizione() {
        return "Creazione disco";
    }

    @Override
    public void esegui() {
        System.out.println("-- INIZIO CREAZIONE DISCO --");
        final String titolo = Console.INSTANCE.leggiLinea("Inserisci il titolo");
        final String areaGeografica = Console.INSTANCE.leggiLinea("Inserisci la casa discografica");
        final String anno = Console.INSTANCE.leggiLinea("Inserisci l'anno");
        final String artistaOBand = Console.INSTANCE.leggiLinea("Inserisci l'artista o band");
        final String genere = Console.INSTANCE.leggiLinea("Inserisci il genere (POP, RAP o TRAP)");
        final String numeroCanzoni = Console.INSTANCE.leggiLinea("Inserisci il numero di canzoni");
        final Disco disco = new Disco(titolo, areaGeografica, Short.parseShort(anno), artistaOBand, GenereMusicale.valueOf(genere), Short.parseShort(numeroCanzoni));
        consumer.accept(disco);
        System.out.println("-- FINE CREAZIONE DISCO --");
    }
}
