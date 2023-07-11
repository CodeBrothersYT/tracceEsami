package com.ingegneria.core.abstractclasses;

import com.ingegneria.core.interfaces.Forma;

import java.util.Objects;

public abstract class AbstractForma implements Forma {
    protected int dimensione;
    protected String colore;

    public AbstractForma(int dimensione, String colore) {
        this.dimensione = dimensione;
        this.colore = colore;
    }

    public int getDimensione() {
        return dimensione;
    }

    public void setDimensione(int dimensione) {
        this.dimensione = dimensione;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractForma that = (AbstractForma) o;
        return dimensione == that.dimensione && Objects.equals(colore, that.colore);
    }

    public int hashCode() {
        return Objects.hash(dimensione, colore);
    }

    public String toString() {
        return String.format("%s{" +
                "dimensione=" + dimensione +
                ", colore='" + colore + '\'' +
                '}', this.getClass().getSimpleName());
    }
}
