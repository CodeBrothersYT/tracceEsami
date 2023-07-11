package com.ingegneria.core;

import com.ingegneria.core.classes.Cerchio;
import com.ingegneria.core.classes.Equilatero;
import com.ingegneria.core.classes.Quadrato;
import com.ingegneria.core.classes.Raccoglitore;
import com.ingegneria.core.interfaces.Forma;
import com.ingegneria.core.utility.RaggruppatoreDiForme;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RaggruppatoreTest {
    private Set<Forma> forme;
    private RaggruppatoreDiForme raggruppatore;

    @Before
    public void setUp() {
        this.forme = new HashSet<>();
        this.raggruppatore = new RaggruppatoreDiForme();
    }

    @Test
    public void testRaggruppamento() {
        List<Equilatero> equilateri = Arrays.asList(
                new Equilatero(3, " Rosso "),
                new Equilatero(2, " Rosso "));
        List<Cerchio> cerchi = Arrays.asList(
                new Cerchio(1, "Blu "));
        List<Quadrato> quadrati = Arrays.asList(
                new Quadrato(2, " Nero "),
                new Quadrato(4, " Marrone "));
        forme.addAll(equilateri);
        forme.addAll(cerchi);
        forme.addAll(quadrati);
        Raccoglitore raccoglitore = this.raggruppatore.raggruppa(forme);
        assertEquals(2, raccoglitore.getEquilateri().size());
        assertEquals(1, raccoglitore.getCerchi().size());
        assertEquals(2, raccoglitore.getQuadrati().size());
    }
}
