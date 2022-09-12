package it.core.classifica;

import it.core.Console;
import it.core.MotoreOperazioni;
import it.core.Operazione;
import it.core.disco.CreazioneDisco;
import it.core.singolo.CreazioneSingolo;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static it.core.classifica.Classifica.ClassificaBuilder;

public class CreazioneClassifica implements Operazione {
    private final Map<String, Classifica> classifiche;

    public CreazioneClassifica(Map<String, Classifica> classifiche) {
        this.classifiche = classifiche;
    }

    @Override
    public String getDescrizione() {
        return "Creazione classifica";
    }

    @Override
    public void esegui() {
        System.out.println("-- INIZIO CREAZIONE CLASSIFICA --");
        final String titolo = Console.INSTANCE.leggiLinea("Inserisci il titolo");
        final String areaGeografica = Console.INSTANCE.leggiLinea("Inserisci l'area geografica");
        final String genere = Console.INSTANCE.leggiLinea("Inserisci il genere (POP, RAP o TRAP)");
        final ClassificaBuilder builder = ClassificaBuilder.builder(titolo, areaGeografica, genere);
        new MotoreOperazioni(creaOperazioni(builder)).start();
        final Classifica classifica = builder.build();
        classifiche.put(classifica.getTitolo(), classifica);
        System.out.println("-- FINE CREAZIONE CLASSIFICA --");
    }

    private List<Operazione> creaOperazioni(ClassificaBuilder builder) {
        return List.of(new CreazioneDisco(builder::disco), new CreazioneSingolo(builder::singolo));
    }
}
