package it.core.menu;

import it.core.commonUtilities.Console;
import it.core.components.Classifica;
import it.core.components.GenereMusicale;
import it.core.components.Produzione;
import it.core.exceptions.GenreNonExistingException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SubmenuClassifica {

    private record DatiClassifica(String titolo, String areaGeografica, String genereMusicale) {
        public DatiClassifica {
            try {
                GenereMusicale.valueOf(genereMusicale.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException ex) {
                throw new GenreNonExistingException("Sei un folle, ma che genere hai inserito");
            }
        }
    }

    public static final List<Classifica> CLASSIFICHE = new ArrayList<>();
    private static final String INTRO = """
                Che operazione vuoi effettuare sulla classifica?
                1) Inserisci una nuova classifica
                2) Cancella una classifica
                3) Modifica una classifica
                4) Numero di artisti presenti in più di 5 classifiche
                5) Mostra classifiche in catalogo""";

    public void pickOperation() throws Exception {
        System.out.println(INTRO);
        final String choice = Console.getConsole().readLine();
        switch (Integer.parseInt(choice)) {
            case 1 -> inserisciClassifica();
            case 2 -> cancellaClassifica();
            case 3 -> modificaClassifica();
            case 4 -> findFamousArtists();
            case 5 -> showClassifiche();
        }

    }

    public void inserisciClassifica() throws Exception {
        System.out.println("Perfetto, verrà creata una nuova classifica!");
        final Classifica classificaDaAggiungere = makeClassifica();
        if (CLASSIFICHE.size() <= 10) {
            CLASSIFICHE.add(classificaDaAggiungere);
        } else {
            System.out.println("Non puoi aggiungere più di 10 classifiche, cancellane prima una.");
            return;
        }
        System.out.println("La classifica è stata inserita correttamente, grazie!");
        showClassifiche();
    }

    public void cancellaClassifica() throws IOException {
        System.out.println("Inserisci il numero della classifica da cancellare");
        showClassifiche();
        final String indexToRemove = Console.getConsole().readLine();
        CLASSIFICHE.remove(Integer.parseInt(indexToRemove) - 1);
        System.out.println("Ottimo! La classifica è stata rimossa con successo, ecco quelle presenti: \n");
        showClassifiche();
    }

    public void modificaClassifica() throws Exception {
        System.out.println("Inserisci il numero della classifica da modificare");
        showClassifiche();
        final String indexToModify = Console.getConsole().readLine();
        final Classifica classificaDaInserire = makeClassifica();
        if (Integer.parseInt(indexToModify) > CLASSIFICHE.size()) {
            throw new IndexOutOfBoundsException("Hai inserito un indice che non ci sta ma sei fuori");
        }
        CLASSIFICHE.set(Integer.parseInt(indexToModify) - 1, classificaDaInserire);
    }

    private Classifica makeClassifica() throws Exception {
        System.out.println("Inserisci in ordine il titolo, l'area geografica e il genere");
        final List<String> params = Arrays.stream(Console.getConsole().readLine().split("[ ,]+")).map(String::trim).toList();
        //Testo che mi arrivino veramente 3 parametri di cui uno un possibile genere
        if (params.size() != 3) {
            throw new Exception("una cosa dovevi fare, manco quella.");
        }
        final DatiClassifica dati = new DatiClassifica(params.get(0), params.get(1), params.get(2));

        //Creo la classifica
        return new Classifica(dati.titolo, dati.areaGeografica, GenereMusicale.valueOf(dati.genereMusicale.toUpperCase(Locale.ROOT)), new ArrayList<>());
    }

    private void showClassifiche() {
        System.out.println("Le classifiche in catalogo sono:");
        AtomicReference<Integer> index = new AtomicReference<>(1);
        CLASSIFICHE.forEach(classifica -> {
            System.out.printf("%s) %s%n", index.get(), classifica.toString());
            index.getAndSet(index.get() + 1);
        });
    }

    public void findFamousArtists() {
        final HashMap<String, Long> aristsToCheck = CLASSIFICHE.stream()
                .map(Classifica::getDischi)
                .flatMap(Collection::stream)
                .map(Produzione::getArtistaOBand)
                //La Function prende in sto caso delle stringhe e torna un K.
                //Gli devo dire per cosa gruppare.
                //Poi gli devo dire dove mettermi ste cose che gruppa.
                //Infine cosa devo farci con ste cose gruppate
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));

        aristsToCheck.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 5)
                .forEach(artistFound -> System.out.printf("Gli artisti famosi sono: %s%n",artistFound.getKey()));
    }
}
