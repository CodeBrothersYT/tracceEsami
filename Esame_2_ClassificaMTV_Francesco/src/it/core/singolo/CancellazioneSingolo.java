package it.core.singolo;

import it.core.Console;
import it.core.Operazione;
import it.core.Produzione;
import it.core.classifica.Classifica;
import it.core.disco.Disco;

import java.util.Map;

public class CancellazioneSingolo implements Operazione {
    private final Classifica classifica;

    public CancellazioneSingolo(Classifica classifica) {
        this.classifica = classifica;
    }

    @Override
    public String getDescrizione() {
        return "Cancellazione singolo";
    }

    @Override
    public void esegui() {
        final Map<Integer, Singolo> singoli = classifica.getProduzioni(Singolo.class);
        if(singoli.isEmpty()){
            System.out.println("Non ci sono singoli da cancellare");
            return;
        }
        System.out.println("-- INIZIO CANCELLAZIONE SINGOLO --");
        Produzione.stampaProduzioni(singoli);
        final int indiceSingolo = Integer.parseInt(Console.INSTANCE.leggiLinea("Inserisci il numero del singolo da cancellare"));
        classifica.rimuoviProduzione(indiceSingolo);
        System.out.printf("Il singolo in posizione %d è stato rimosso\n", indiceSingolo);
        System.out.println("-- FINE CANCELLAZIONE SINGOLO --");
    }
}
