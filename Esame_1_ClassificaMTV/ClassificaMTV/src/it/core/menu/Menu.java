package it.core.menu;

import it.core.commonUtilities.Console;
import it.core.exceptions.InputNonValidException;


public class Menu {

    private final static String INTRO = """
            Ciao! Benvenuto nella mega app di classifiche MTV, che vuoi maneggiare?
            1) Classifiche
            2) Dischi
            3) Singoli
            4) Niente di tutto ciò, esplodi""";

    public static void main(String[] args) throws Exception {
        final SubmenuClassifica submenuClassifica = new SubmenuClassifica();
        final SubmenuDisco submenuDisco = new SubmenuDisco(submenuClassifica);
        final SubmenuSingolo submenuSingolo = new SubmenuSingolo(submenuDisco);
        starter(submenuClassifica, submenuDisco, submenuSingolo);
    }

    public static int isFeasible(String input) {
        try {
            if (Integer.parseInt(input) > 4 || Integer.parseInt(input) < 0) {
                throw new InputNonValidException();
            }
            return Integer.parseInt(input);
        } catch (final NumberFormatException | InputNonValidException e) {
            System.out.println("Inserisci uno di quei numeri dai su!\n");
            return 5;
        }
    }

    private static void starter(SubmenuClassifica submenuClassifica,
                                SubmenuDisco submenuDisco,
                                SubmenuSingolo submenuSingolo) throws Exception {

        while (true){
            System.out.println(INTRO);
            switch (isFeasible(Console.getConsole().readLine())) {
                case 1 -> submenuClassifica.pickOperation();
                case 2 -> submenuDisco.pickOperation();
                case 3 -> submenuSingolo.pickOperation();
                case 4 -> {
                    System.out.println("va bene ciaoooo");
                    System.exit(1);
                }
            }
        }
    }

}
