package it.core.menu;

import it.core.commonUtilities.Console;
import it.core.components.Classifica;
import it.core.components.Disco;
import it.core.components.GenereMusicale;
import it.core.exceptions.GenreNonExistingException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class SubmenuDisco {

    private class DatiDisco {

        private String titolo;
        String casaDiscografica;
        short anno;
        String artistaOBand;
        GenereMusicale genereMusicale;
        short numeroDiCanzoni;

        public DatiDisco(String titolo, String casaDiscografica, String anno, String artistaOBand, String genereMusicale, String numeroDiCanzoni) {
            try {
                GenereMusicale.valueOf(genereMusicale.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException ex) {
                throw new GenreNonExistingException("Sei un folle, ma che genere hai inserito");
            }
            this.titolo = titolo;
            this.casaDiscografica = casaDiscografica;
            this.anno = Short.parseShort(anno);
            this.artistaOBand = artistaOBand;
            this.genereMusicale = GenereMusicale.valueOf(genereMusicale.toUpperCase(Locale.ROOT));
            this.numeroDiCanzoni = Short.parseShort(numeroDiCanzoni);
        }

    }

    private final String ISTRUZIONI_INSERIMENTO_DISCO = "Inserisci in ordine il titolo, la casa discografica, l'anno, artista o band, genere musicale e numero di canzoni";
    public static final List<Disco> DISCHI = new ArrayList<>();
    private final List<Classifica> classifiche;

    public SubmenuDisco(SubmenuClassifica submenuClassifica) {
        this.classifiche = SubmenuClassifica.CLASSIFICHE;
    }

    public void pickOperation() throws Exception {
        String intro = """
                Che operazione vuoi effettuare sul Disco?
                1) Inserisci un nuovo disco
                2) Cancella un disco
                3) Modifica un disco
                4) Inserisci un disco in una classifica
                5) Mostra i dischi italiani trap del 2020""";
        System.out.println(intro);

        final String choice = Console.getConsole().readLine();
        switch (Integer.parseInt(choice)) {
            case 1 -> inserisciDisco();
            case 2 -> cancellaDisco();
            case 3 -> modificaDisco();
            case 4 -> inserisciDiscoInClassifica();
            case 5 -> showDischi2020();
        }

    }

    public void inserisciDisco() throws Exception {
        System.out.println("Perfetto, verrà creato un nuovo disco!");
        final Disco discoDaAggiungere = makeDisco();
        DISCHI.add(discoDaAggiungere);
        System.out.println("Il disco è stato inserito correttamente, ecco quelli presenti:");
        showDischi();
    }

    public void inserisciDiscoInClassifica() throws IOException {
        System.out.println("Questi sono i dischi attualmente in catalogo");
        showDischi();
        System.out.println("Queste sono le classifiche attualmente in catalogo");
        showClassifiche();
        System.out.println("Inserisci i numeri dei dischi da inserire nella classifica di riferimento");
        final List<String> indiciDischi = Arrays.stream(Console.getConsole().readLine().split("[ ,]+")).map(String::trim).toList();
        System.out.println("Inserisci i numeri delle classifiche");
        final List<String> indiciClassifiche = Arrays.stream(Console.getConsole().readLine().split("[ ,]+")).map(String::trim).toList();
        final List<Classifica> classificheProposteUtente = indiciClassifiche.stream()
                .map(index -> classifiche.get(Integer.parseInt(index) - 1))
                .toList();

        Map<GenereMusicale, HashSet<Disco>> dischiAggiunti = new HashMap<>();

        for (Classifica c : classificheProposteUtente) {
            //Qua c'era scritto di lanciare una eccezione, in caso cambi lo stream con un for..
            indiciDischi.stream()
                    .map(index -> DISCHI.get(Integer.parseInt(index) - 1))
                    .filter(disco -> disco.getGenereMusicale() == c.getGenereMusicale())
                    .forEach(disco -> {
                        c.getDischi().add(disco);
                        dischiAggiunti.computeIfAbsent(disco.getGenereMusicale(), k -> new HashSet<>()).add(disco);
                    });

        }
        System.out.println("Perfetto, i seguenti dischi son stati aggiunti con successo alle classifiche:");
        showDischi(dischiAggiunti);
    }

    public void cancellaDisco() throws IOException {
        System.out.println("Inserisci il numero del disco da cancellare");
        showDischi();
        final String indexToRemove = Console.getConsole().readLine();
        DISCHI.remove(Integer.parseInt(indexToRemove) - 1);
        System.out.println("Ottimo! Il disco è stato rimosso con successo, ecco quelli presenti:");
        showDischi();
    }

    public void modificaDisco() throws Exception {
        System.out.println("Inserisci il numero del disco da modificare");
        showDischi();
        final String indexToRemove = Console.getConsole().readLine();
        final Disco discoDaInserire = makeDisco();
        if (Integer.parseInt(indexToRemove) > DISCHI.size()) {
            throw new IndexOutOfBoundsException("Hai inserito un indice che non ci sta ma sei fuori");
        }
        DISCHI.set(Integer.parseInt(indexToRemove) - 1, discoDaInserire);
    }

    private Disco makeDisco() throws Exception {
        System.out.println(ISTRUZIONI_INSERIMENTO_DISCO);
        final List<String> params = Arrays.stream(Console.getConsole().readLine().split("[ ,]+")).map(String::trim).toList();
        //Testo che mi arrivino veramente 3 parametri di cui uno un possibile genere
        if (params.size() != 6) {
            throw new Exception("una cosa dovevi fare, manco quella.");
        }
        final DatiDisco dati = new DatiDisco(params.get(0), params.get(1), params.get(2), params.get(3), params.get(4), params.get(5));

        //Creo e inserisco il disco
        return new Disco(dati.titolo, dati.casaDiscografica, dati.anno, dati.artistaOBand, dati.genereMusicale, dati.numeroDiCanzoni);
    }

    private void showDischi() {
        AtomicReference<Integer> index = new AtomicReference<>(1);
        DISCHI.forEach(disco -> {
            System.out.printf("%s) %s%n", index.get(), disco.toString());
            index.getAndSet(index.get() + 1);
        });
    }

    private void showDischi(Map<GenereMusicale, HashSet<Disco>> dischi) {
        AtomicReference<Integer> index = new AtomicReference<>(1);
        dischi.forEach((k, v) -> {
            System.out.printf("%s) %s%n%n", index.get(), v.toString());
            index.getAndSet(index.get() + 1);
        });
    }


    private void showClassifiche() {
        AtomicReference<Integer> index = new AtomicReference<>(1);
        classifiche.forEach(classifica -> {
            System.out.printf("%s) %s%n", index.get(), classifica.toString());
            index.getAndSet(index.get() + 1);
        });
    }

    private void showDischi2020(){
        final long dischi = classifiche.stream()
                .filter(classifica -> classifica.getAreaGeografica().toLowerCase(Locale.ROOT).equals("italia"))
                .map(Classifica::getDischi)
                .flatMap(Collection::stream)
                .filter(produzione -> produzione.getAnno() == 2020 && produzione.getGenereMusicale() == GenereMusicale.TRAP)
                .count();
        System.out.printf("I dischi sono: %s%n",dischi);
    }
}
