package com.ingegneria.core.is;

public abstract class SommatoreDiNumeri {

    protected abstract int getNumeroPreferito();

    protected int getAltroNumero() {
        return 42;
    }

    protected final int getNumeroDodici() {
        return 12;
    }

    public final int sommaDiNumeri() {
        int preferito = getNumeroPreferito();
        int altro = getAltroNumero();
        int dodici = getNumeroDodici();
        return preferito + altro + dodici;
    }
}
