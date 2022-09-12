package it.core;


import it.core.classifica.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        final Map<String, Classifica> classifiche = new HashMap<>();
        final List<Operazione> operazioni = List.of(
                new CreazioneClassifica(classifiche),
                new ModificaClassifica(classifiche),
                new CancellazioneClassifica(classifiche),
                new StampaClassifica(classifiche));
        new MotoreOperazioni(operazioni).start();
    }

}
