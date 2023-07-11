package it.core;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MotoreOperazioni {
    private static final String intestazione = "Che operazione vuoi effettuare?";
    private static final String opzioneDiUscita = "q";
    private static final String messaggioOpzioneDiUscita = "Per uscire premere %s".formatted(opzioneDiUscita);
    private static final String messaggioErroreTpl = "Il valore %s non è tra quelli previsti. Scegli un'altra opzione.\n";

    private final Map<Integer, Operazione> operazioni;

    public MotoreOperazioni(Operazione operazione) {
        this(Collections.singletonList(operazione));
    }

    public MotoreOperazioni(List<Operazione> operazioni) {
        this.operazioni = IntStream.range(0, operazioni.size()).boxed()
                .collect(Collectors.toMap(Function.identity(), operazioni::get));
    }

    public void start() {
        stampaMenuPrincipale();
        String selezione = Console.INSTANCE.leggiLinea();
        while (!selezione.equals(opzioneDiUscita)) {
            eseguiOperazione(selezione);
            stampaMenuPrincipale();
            selezione = Console.INSTANCE.leggiLinea();
        }
    }

    private void stampaMenuPrincipale() {
        final String menu = operazioni.entrySet().stream().reduce(
                intestazione,
                (menuText, entry) -> "%s\n%d) %s".formatted(menuText, entry.getKey(), entry.getValue().getDescrizione()),
                "%s\n%s"::formatted
        );
        System.out.println(menu);
        System.out.println(messaggioOpzioneDiUscita);
    }

    private void eseguiOperazione(String selezione) {
        final Optional<Integer> scelta = isNumeric(selezione) ? Optional.of(Integer.parseInt(selezione)) : Optional.empty();
        scelta.filter(i -> i < operazioni.size())
                .map(operazioni::get)
                .ifPresentOrElse(Operazione::esegui, () -> System.out.printf(messaggioErroreTpl, selezione));
    }

    private boolean isNumeric(String testo) {
        if (testo == null || testo.isBlank()) {
            return false;
        }
        return IntStream.range(0, testo.length()).mapToObj(testo::charAt).allMatch(Character::isDigit);
    }

}
