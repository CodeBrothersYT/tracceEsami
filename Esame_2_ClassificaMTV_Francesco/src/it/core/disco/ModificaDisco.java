package it.core.disco;

import it.core.Console;
import it.core.MotoreOperazioni;
import it.core.Operazione;
import it.core.Produzione;
import it.core.classifica.Classifica;

import java.lang.constant.DirectMethodHandleDesc;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Map.entry;

public class ModificaDisco implements Operazione {
    private final Classifica classifica;

    public ModificaDisco(Classifica classifica) {
        this.classifica = classifica;
    }

    @Override
    public String getDescrizione() {
        return "Modifica disco";
    }

    @Override
    public void esegui() {
        final Map<Integer, Disco> dischi = classifica.getProduzioni(Disco.class);
        if(dischi.isEmpty()){
            System.out.println("Non ci sono dischi da modificare");
            return;
        }
        System.out.println("-- INIZIO MODIFICA DISCO --");
        Produzione.stampaProduzioni(dischi);
        final int indiceDisco = Integer.parseInt(Console.INSTANCE.leggiLinea("Inserisci il numero del disco da modificare"));
        final AtomicReference<Disco> disco = new AtomicReference<>();
        new MotoreOperazioni(new CreazioneDisco(disco::set)).start();
        classifica.setProduzione(indiceDisco, disco.get());
        System.out.printf("Il disco in posizione %d è stato sostituito\n", indiceDisco);
        System.out.println("-- FINE MODIFICA DISCO --");
    }
}
