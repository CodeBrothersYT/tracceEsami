package it.core.singolo;

import it.core.Console;
import it.core.GenereMusicale;
import it.core.MotoreOperazioni;
import it.core.Operazione;
import it.core.disco.CreazioneDisco;
import it.core.disco.Disco;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class CreazioneSingolo implements Operazione {
    private final Consumer<? super Singolo> consumer;

    public CreazioneSingolo(Consumer<? super Singolo> consumer) {
        this.consumer = consumer;
    }

    @Override
    public String getDescrizione() {
        return "Creazione singolo";
    }

    @Override
    public void esegui() {
        System.out.println("-- INIZIO CREAZIONE SINGOLO --");
        final String titolo = Console.INSTANCE.leggiLinea("Inserisci il titolo");
        final String anno = Console.INSTANCE.leggiLinea("Inserisci l'anno");
        final String artistaOBand = Console.INSTANCE.leggiLinea("Inserisci l'artista o band");
        final String genere = Console.INSTANCE.leggiLinea("Inserisci il genere (POP, RAP o TRAP)");
        System.out.println("Inserisci i dettagli del disco di appartenenza");
        final AtomicReference<Disco> disco = new AtomicReference<>();
        new MotoreOperazioni(new CreazioneDisco(disco::set)).start();
        final Singolo singolo = new Singolo(titolo, Short.parseShort(anno), artistaOBand, GenereMusicale.valueOf(genere), disco.get());
        consumer.accept(singolo);
        System.out.println("-- FINE CREAZIONE SINGOLO --");
    }
}
