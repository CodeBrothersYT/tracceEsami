package com.ingegneria.core;

import com.ingegneria.core.classes.Cerchio;
import com.ingegneria.core.classes.Equilatero;
import com.ingegneria.core.classes.Quadrato;
import com.ingegneria.core.interfaces.Forma;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class CerchioTest {
    private Set<Forma > set;

    @Before
    public void setUp() {
        this.set = new HashSet<>();
    }
    @Test
    public void test() {

        Forma r1 = new Equilatero( 1, " Rosso "); // r1 ed ...
        Forma r2 = new Equilatero ( 1, " Rosso "); // .. r2 " contano " 1
        Forma r3 = new Quadrato( 2, " Nero ");
        Forma r4 = new Cerchio( 2, " Nero ");
        set.add(r1);
        set.add(r2);
        set.add(r3);
        set.add(r4);
        assertEquals (3, set.size ()); /* N.B. 3 e NON 4 */ // <----------
    }
}