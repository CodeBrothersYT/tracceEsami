package com.ingegneria.core.is;

import is.SommatoreDiNumeriImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SommatoreDiNumeriImplTest {

    @Test
    public void inputZeroDanno12(){
        final SommatoreDiNumeriImpl sommatoreDiNumeri = new SommatoreDiNumeriImpl(0, 0);
        int got = sommatoreDiNumeri.sommaDiNumeri();
        assertEquals(12, got);
    }

    @Test
    public void sceltoUnPreferitoVieneAggiuntoA12(){
        final SommatoreDiNumeriImpl sommatoreDiNumeri = new SommatoreDiNumeriImpl(10, 0);
        int got = sommatoreDiNumeri.sommaDiNumeri();
        assertEquals(22, got);
    }

    @Test
    public void sceltoUnAltroVieneAggiuntoA12(){
        final SommatoreDiNumeriImpl sommatoreDiNumeri = new SommatoreDiNumeriImpl(0, 11);
        int got = sommatoreDiNumeri.sommaDiNumeri();
        assertEquals(23, got);
    }

    @Test
    public void sceltoPreferitoEdAltroVengonoAggiuntiA12(){
        final SommatoreDiNumeriImpl sommatoreDiNumeri = new SommatoreDiNumeriImpl(10, 11);
        int got = sommatoreDiNumeri.sommaDiNumeri();
        assertEquals(33, got);
    }

}