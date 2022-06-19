package it.core.components;

import java.util.List;

public class Classifica {

    private final String titolo;
    private final String areaGeografica;
    private final GenereMusicale genereMusicale;
    private final List<Produzione> dischi;

    public Classifica(String titolo, String areaGeografica, GenereMusicale genereMusicale, List<Produzione> dischi) {
        this.titolo = titolo;
        this.areaGeografica = areaGeografica;
        this.genereMusicale = genereMusicale;
        this.dischi = dischi;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAreaGeografica() {
        return areaGeografica;
    }

    public GenereMusicale getGenereMusicale() {
        return genereMusicale;
    }

    public List<Produzione> getDischi() {
        return dischi;
    }

    @Override
    public String toString() {
        return "Classifica{" +
                "titolo='" + titolo + '\'' +
                ", areaGeografica='" + areaGeografica + '\'' +
                ", genere=" + genereMusicale +
                ", disco=" + dischi +
                '}';
    }
}
