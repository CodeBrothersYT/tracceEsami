package com.ingegneria.core.utility;

import com.ingegneria.core.classes.Cerchio;
import com.ingegneria.core.classes.Equilatero;
import com.ingegneria.core.classes.Quadrato;
import com.ingegneria.core.classes.Raccoglitore;
import com.ingegneria.core.interfaces.Forma;

import java.util.Set;

public class RaggruppatoreDiForme {

    private Raccoglitore raccoglitore;

    public void raggruppa(Cerchio c) {
        this.raccoglitore.addCerchio(c);
    }

    public void raggruppa(Equilatero e) {
        this.raccoglitore.addEquilatero(e);
    }

    public void raggruppa(Quadrato q) {
        this.raccoglitore.addQuadrato(q);
    }

    public Raccoglitore raggruppa(Set<Forma> forme) {
        this.raccoglitore = new Raccoglitore();
        for (Forma forma : forme) {
            forma.accetta(this);
        }
        return this.raccoglitore;
    }
}