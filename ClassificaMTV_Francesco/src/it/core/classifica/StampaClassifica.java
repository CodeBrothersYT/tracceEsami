package it.core.classifica;

import it.core.Console;
import it.core.Operazione;

import java.util.Map;

public class StampaClassifica implements Operazione {
    private final Map<String, Classifica> classifiche;

    public StampaClassifica(Map<String, Classifica> classifiche) {
        this.classifiche = classifiche;
    }

    @Override
    public String getDescrizione() {
        return "Stampa classifica";
    }

    @Override
    public void esegui() {
        if(classifiche.isEmpty()){
            System.out.println("Non ci sono classifiche da visualizzare");
            return;
        }
        Classifiche.stampaTitoli(classifiche);
        final String titolo = Console.INSTANCE.leggiLinea("Inserisci il titolo della classifica");
        System.out.println(classifiche.get(titolo));
    }
}
