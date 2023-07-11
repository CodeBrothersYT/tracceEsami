package it;

import it.core.App;
import it.core.Contatto;
import it.core.Messaggio;
import it.enums.TipoContatto;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws Exception {
        final LocalDate date = LocalDate.ofEpochDay(759);
        final LocalDate date1 = LocalDate.ofEpochDay(359);
        final LocalDate date2 = LocalDate.ofEpochDay(509);
        final LocalDate date3 = LocalDate.ofEpochDay(20);
        final LocalDate date4 = LocalDate.ofEpochDay(19);
        final Contatto contatto1 = new Contatto("Folder", "M", "M", "folder1@", TipoContatto.AMICI,"");
        final Contatto contatto2 = new Contatto("Folder", "M", "M", "folder2@", TipoContatto.AMICI,"");
        final Contatto contatto3 = new Contatto("Folder", "M", "M", "folder3@", TipoContatto.AMICI,"");
        final Contatto contatto4 = new Contatto("Folder", "M", "M", "poldo4@", TipoContatto.CONOSCENTI,"");
        final Contatto contatto5 = new Contatto("Folder", "M", "M", "poldo5@", TipoContatto.CONOSCENTI,"");
        final Messaggio messaggio1 = new Messaggio("folder1@", date, "ciao1");
        final Messaggio messaggio2 = new Messaggio("folder2@", date1, "ciao2");
        final Messaggio messaggio3 = new Messaggio("folder3@", date2, "ciao3");
        final Messaggio messaggio4 = new Messaggio("poldo4@", date3, "ciao4");
        final Messaggio messaggio5 = new Messaggio("poldo5@", date4, "ciao5");
        final App.Visualizzazione visualizzazione = new App.Visualizzazione();
        final App.Ricezione ricezione = new App.Ricezione(visualizzazione);
        ricezione.lettura(TipoContatto.AMICI);
        ricezione.visualizzazione(TipoContatto.AMICI);
        ricezione.ricezione(messaggio1,contatto1);
        ricezione.ricezione(messaggio2,contatto2);
        ricezione.ricezione(messaggio3,contatto3);
        ricezione.ricezione(messaggio4,contatto4);
        ricezione.ricezione(messaggio5,contatto5);
        ricezione.visualizzazione(TipoContatto.CONOSCENTI);
        ricezione.lettura(TipoContatto.CONOSCENTI);
    }

}
