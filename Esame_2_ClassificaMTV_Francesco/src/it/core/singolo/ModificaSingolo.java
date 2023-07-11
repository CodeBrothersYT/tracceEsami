package it.core.singolo;

import it.core.Console;
import it.core.MotoreOperazioni;
import it.core.Operazione;
import it.core.Produzione;
import it.core.classifica.Classifica;
import it.core.disco.CreazioneDisco;
import it.core.disco.Disco;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class ModificaSingolo implements Operazione {
    private final Classifica classifica;

    public ModificaSingolo(Classifica classifica) {
        this.classifica = classifica;
    }

    @Override
    public String getDescrizione() {
        return "Modifica singolo";
    }

    @Override
    public void esegui() {
        final Map<Integer, Singolo> singoli = classifica.getProduzioni(Singolo.class);
        if(singoli.isEmpty()){
            System.out.println("Non ci sono singoli da modificare");
            return;
        }
        System.out.println("-- INIZIO MODIFICA SINGOLO --");
        Produzione.stampaProduzioni(singoli);
        final int indiceSingolo = Integer.parseInt(Console.INSTANCE.leggiLinea("Inserisci il numero del singolo da modificare"));
        final AtomicReference<Singolo> singolo = new AtomicReference<>();
        new MotoreOperazioni(new CreazioneSingolo(singolo::set)).start();
        classifica.setProduzione(indiceSingolo, singolo.get());
        System.out.printf("Il singolo in posizione %d è stato sostituito\n", indiceSingolo);
        System.out.println("-- FINE MODIFICA SINGOLO --");
    }
}
