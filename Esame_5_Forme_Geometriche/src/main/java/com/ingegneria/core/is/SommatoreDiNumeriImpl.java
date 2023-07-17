package com.ingegneria.core.is;

public class SommatoreDiNumeriImpl extends SommatoreDiNumeri {

    private final int preferito;
    private final int altro;

    public SommatoreDiNumeriImpl(int preferito, int altro) {
        this.preferito = preferito;
        this.altro = altro;
    }

    @Override
    public int getNumeroPreferito() {
        return preferito;
    }

    @Override
    public int getAltroNumero() {
        return altro;
    }
}
