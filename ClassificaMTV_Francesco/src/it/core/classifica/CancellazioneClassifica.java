package it.core.classifica;

import it.core.Console;
import it.core.Operazione;

import java.util.Map;

public class CancellazioneClassifica implements Operazione {
    private final Map<String, Classifica> classifiche;

    public CancellazioneClassifica(Map<String, Classifica> classifiche) {
        this.classifiche = classifiche;
    }

    @Override
    public String getDescrizione() {
        return "Cancellazione classifica";
    }

    @Override
    public void esegui() {
        if(classifiche.isEmpty()){
            System.out.println("Non ci sono classifiche da cancellare");
            return;
        }
        System.out.println("-- INIZIO CANCELLAZIONE CLASSIFICA --");
        Classifiche.stampaTitoli(classifiche);
        final String titolo = Console.INSTANCE.leggiLinea("Inserisci il titolo della classifica");
        classifiche.remove(titolo);
        System.out.println("-- FINE CANCELLAZIONE CLASSIFICA --");
    }

}
