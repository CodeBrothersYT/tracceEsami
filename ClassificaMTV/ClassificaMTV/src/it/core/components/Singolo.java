package it.core.components;

public final class Singolo implements Produzione{

    private final String titolo;
    private final short anno;
    private final String artistaOBand;
    private final GenereMusicale genereMusicale;
    private final Disco discoAppartenenza;

    public Singolo(String titolo, short anno, String artistaOBand, GenereMusicale genereMusicale, Disco discoAppartenenza) {
        this.titolo = titolo;
        this.anno = anno;
        this.artistaOBand = artistaOBand;
        this.genereMusicale = genereMusicale;
        this.discoAppartenenza = discoAppartenenza;
    }

    public String getTitolo() {
        return titolo;
    }

    public short getAnno() {
        return anno;
    }

    public String getArtistaOBand() {
        return artistaOBand;
    }

    public GenereMusicale getGenereMusicale() {
        return genereMusicale;
    }

    public Disco getDiscoAppartenenza() {
        return discoAppartenenza;
    }

    @Override
    public String toString() {
        return "Singolo{" +
                "titolo='" + titolo + '\'' +
                ", anno=" + anno +
                ", artistaOBand='" + artistaOBand + '\'' +
                ", genereMusicale=" + genereMusicale +
                ", discoAppartenenza=" + discoAppartenenza +
                '}';
    }
}
