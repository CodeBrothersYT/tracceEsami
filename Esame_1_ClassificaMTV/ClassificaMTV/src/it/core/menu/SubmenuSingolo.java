package it.core.menu;

import it.core.commonUtilities.Console;
import it.core.components.GenereMusicale;
import it.core.components.Singolo;
import it.core.exceptions.GenreNonExistingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class SubmenuSingolo {

    private static final String ISTRUZIONI_INSERIMENTO_SINGOLO = "Inserisci in ordine il titolo, l'anno, artista o band, genere musicale e titolo del disco di appartenenza \n" +
            "I titoli dei dischi attualmente in catalogo sono: \n";
    private static final String INTRO = """
                Che operazione vuoi effettuare sui singoli?
                1) Inserisci un nuovo singolo
                2) Cancella un singolo
                3) Modifica un singolo""";
    private static final List<Singolo> SINGOLI = new ArrayList<>();
    private final SubmenuDisco submenuDisco;

    public SubmenuSingolo(SubmenuDisco submenuDisco) {
        this.submenuDisco = submenuDisco;
    }

    private class DatiSingolo {

        private String titolo;
        short anno;
        String artistaOBand;
        GenereMusicale genereMusicale;
        String discoDiAppartenenza;

        public DatiSingolo(String titolo, String anno, String artistaOBand, String genereMusicale, String discoDiAppartenenza) {
            try {
                GenereMusicale.valueOf(genereMusicale.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException ex) {
                throw new GenreNonExistingException("Sei un folle, ma che genere hai inserito");
            }
            this.titolo = titolo;
            this.anno = Short.parseShort(anno);
            this.artistaOBand = artistaOBand;
            this.genereMusicale = GenereMusicale.valueOf(genereMusicale.toUpperCase(Locale.ROOT));
            this.discoDiAppartenenza = discoDiAppartenenza;
        }

    }

    public void pickOperation() throws Exception {

        System.out.println(INTRO);

        final String choice = Console.getConsole().readLine();
        switch (Integer.parseInt(choice)) {
            case 1 -> inserisciSingolo();
            case 2 -> cancellaSingolo();
            case 3 -> modificaSingolo();
        }

    }

    public void inserisciSingolo() throws Exception {
        System.out.println("Perfetto, verrà creato un nuovo singolo!");
        final Singolo discoDaAggiungere = makeSingolo();
        SINGOLI.add(discoDaAggiungere);
        System.out.println("Il singolo è stato inserito correttamente, ecco quelli presenti:");
        showSingoli();
    }

    public void cancellaSingolo() throws IOException {
        System.out.println("Inserisci il numero del singolo da cancellare");
        showSingoli();
        final String indexToRemove = Console.getConsole().readLine();
        SINGOLI.remove(Integer.parseInt(indexToRemove) - 1);
        System.out.println("Ottimo! Il singolo è stato rimosso con successo, ecco quelli presenti:");
        showSingoli();
    }

    public void modificaSingolo() throws Exception {
        System.out.println("Inserisci il numero del singolo da modificare");
        showSingoli();
        final String indexToRemove = Console.getConsole().readLine();
        final Singolo singoloDaInserire = makeSingolo();
        if (Integer.parseInt(indexToRemove) > SINGOLI.size()) {
            throw new IndexOutOfBoundsException("Hai inserito un indice che non ci sta ma sei fuori");
        }
        SINGOLI.set(Integer.parseInt(indexToRemove) - 1, singoloDaInserire);
    }

    private Singolo makeSingolo() throws Exception {
        System.out.println(ISTRUZIONI_INSERIMENTO_SINGOLO);
        SubmenuDisco.DISCHI.forEach(disco -> System.out.println(disco.getTitolo()));
        final List<String> params = Arrays.stream(Console.getConsole().readLine().split("[ ,]+")).map(String::trim).toList();
        //Testo che mi arrivino veramente 5 parametri di cui uno un possibile genere
        if (params.size() != 5) {
            throw new Exception("una cosa dovevi fare, manco quella.");
        }
        final DatiSingolo dati = new DatiSingolo(params.get(0), params.get(1), params.get(2), params.get(3), params.get(4));
        return SubmenuDisco.DISCHI.stream()
                .filter(disco -> dati.discoDiAppartenenza.toLowerCase(Locale.ROOT).equals(disco.getTitolo().toLowerCase(Locale.ROOT)))
                //Controllo se questo singolo effettivamente ha quel disco di appartenenza
                .filter(disco -> disco.getGenereMusicale() == dati.genereMusicale && (disco.getAnno() == dati.anno))
                .map(disco -> new Singolo(dati.titolo, dati.anno, dati.artistaOBand, dati.genereMusicale, disco))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private void showSingoli() {
        AtomicReference<Integer> index = new AtomicReference<>(1);
        SINGOLI.forEach(disco -> {
            System.out.printf("%s) %s%n", index.get(), disco.toString());
            index.getAndSet(index.get() + 1);
        });
    }

}
