package it.core.disco;

import it.core.Console;
import it.core.Operazione;
import it.core.Produzione;
import it.core.classifica.Classifica;
import it.core.classifica.Classifica.ClassificaBuilder;

import java.util.Map;

public class CancellazioneDisco implements Operazione {
    private final Classifica classifica;

    public CancellazioneDisco(Classifica classifica) {
        this.classifica = classifica;
    }


    @Override
    public String getDescrizione() {
        return "Cancellazione disco";
    }

    @Override
    public void esegui() {
        final Map<Integer, Disco> dischi = classifica.getProduzioni(Disco.class);
        if(dischi.isEmpty()){
            System.out.println("Non ci sono dischi da cancellare");
            return;
        }
        System.out.println("-- INIZIO CANCELLAZIONE DISCO --");
        Produzione.stampaProduzioni(dischi);
        final int indiceDisco = Integer.parseInt(Console.INSTANCE.leggiLinea("Inserisci il numero del disco da cancellare"));
        classifica.rimuoviProduzione(indiceDisco);
        System.out.printf("Il disco in posizione %d è stato rimosso\n", indiceDisco);
        System.out.println("-- FINE CANCELLAZIONE DISCO --");
    }
}
