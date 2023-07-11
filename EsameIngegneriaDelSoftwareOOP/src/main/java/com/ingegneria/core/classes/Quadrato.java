package com.ingegneria.core.classes;

import com.ingegneria.core.abstractclasses.AbstractForma;
import com.ingegneria.core.interfaces.Forma;
import com.ingegneria.core.utility.RaggruppatoreDiForme;

public class Quadrato extends AbstractForma implements Forma {

    public Quadrato(int dimensione, String colore) {
        super(dimensione,colore);
    }

    @Override
    public void accetta(RaggruppatoreDiForme raggruppatore) {
        raggruppatore.raggruppa(this);
    }
}
