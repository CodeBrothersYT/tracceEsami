package it.core;

import it.enums.TipoContatto;
import it.exceptions.BufferVuotoException;
import java.util.*;
import java.util.stream.IntStream;

public class App {

    //Mostra i messaggi dal più recente fino a un max di 3 notifiche per tipo contatto
    public static class Visualizzazione {
        private final Map<TipoContatto, Queue<Messaggio>> messagesAndTypesToStore = new HashMap<>();

        public void visualizzazione(TipoContatto contatto, Map<TipoContatto, List<Messaggio>> inputMessages) {
            if (inputMessages.get(contatto) == null)
                throw new BufferVuotoException("Non ci sono messaggi in sto buffer");

            messagesAndTypesToStore
                    .computeIfAbsent(contatto, k -> new PriorityQueue<>(Comparator.comparing(Messaggio::getOrario)))
                    .addAll(inputMessages.get(contatto));

            if (messagesAndTypesToStore.get(contatto).size() > 3) {
                messagesAndTypesToStore.clear();
                throw new BufferVuotoException("Troppi messaggi in una sola volta");
            }
        }

        public void leggi(TipoContatto contatto) throws Exception {
            final Queue<Messaggio> messaggesToRead = messagesAndTypesToStore.get(contatto);
            if(messaggesToRead == null)
                throw new Exception("Zio chiamami prima la visualizzazione sennò son vuoto");
            IntStream.range(0, messaggesToRead.size()).forEach(message -> System.out.println(messaggesToRead.poll()));
            messaggesToRead.clear();
        }

    }

    //Ospiterà in un buffer i messaggi ancora non mostrati a schermo
    public static class Ricezione {
        private final Map<TipoContatto, List<Messaggio>> bufferRicezione = new HashMap<>();
        private final Visualizzazione visualizzazione;

        public Ricezione(Visualizzazione visualizzazione) {
            this.visualizzazione = visualizzazione;
        }
        //un messaggio viene inserito nel buffer di ricezione
        public void ricezione(Messaggio messaggio, Contatto contatto) throws Exception {
            //Se mettessi soltanto "TipoContatto"
            //Potrei decidere io il tipo di contatto di questo mittente da fuori
            if(!messaggio.getEmail().equals(contatto.getEmail()))
                throw new Exception("Stai aggiungendo due cose diverse oh!");
            bufferRicezione.computeIfAbsent(contatto.getTipoContatto(), k -> new ArrayList<>()).add(messaggio);
        }
        //i messaggi sono estratti e memorizzati nella apposita struttura che poi si occuperà di mostrarli
        //a meno delle 2 eccezioni
        public void visualizzazione(TipoContatto contatto) {
            System.out.println("Visualizzo i messaggi");
            final HashMap<TipoContatto, List<Messaggio>> messagesReceivedToPass = new HashMap<>(bufferRicezione);
            visualizzazione.visualizzazione(contatto, messagesReceivedToPass);
            bufferRicezione.clear();
        }
        //stampa a video le notifiche e rimuove il mex dalla struttura di visualizzazione a seconda del tipo
        public void lettura(TipoContatto tipoContatto) throws Exception {
            visualizzazione.leggi(tipoContatto);
        }
    }

    public App() {
    }

    @Override
    public String toString() {
        return "App{}";
    }
}

