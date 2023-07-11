package it.core.classifica;

import it.core.Console;
import it.core.MotoreOperazioni;
import it.core.Operazione;
import it.core.disco.CancellazioneDisco;
import it.core.disco.CreazioneDisco;
import it.core.disco.ModificaDisco;
import it.core.singolo.CancellazioneSingolo;
import it.core.singolo.CreazioneSingolo;
import it.core.singolo.ModificaSingolo;

import java.util.List;
import java.util.Map;

public class ModificaClassifica implements Operazione {
    private final Map<String, Classifica> classifiche;

    public ModificaClassifica(Map<String, Classifica> classifiche) {
        this.classifiche = classifiche;
    }

    @Override
    public String getDescrizione() {
        return "Modifica classifica";
    }

    @Override
    public void esegui() {
        if(classifiche.isEmpty()){
            System.out.println("Non ci sono classifiche da modificare");
            return;
        }
        System.out.println("-- INIZIO MODIFICA CLASSIFICA --");
        Classifiche.stampaTitoli(classifiche);
        final String titolo = Console.INSTANCE.leggiLinea("Inserisci il titolo della classifica");
        new MotoreOperazioni(creaOperazioni(classifiche.get(titolo))).start();
        System.out.println("-- FINE MODIFICA CLASSIFICA --");
    }

    private List<Operazione> creaOperazioni(Classifica classifica) {
        return List.of(new CreazioneDisco(classifica::addProduzione), new ModificaDisco(classifica), new CancellazioneDisco(classifica),
                new CreazioneSingolo(classifica::addProduzione), new ModificaSingolo(classifica), new CancellazioneSingolo(classifica));
    }
}
