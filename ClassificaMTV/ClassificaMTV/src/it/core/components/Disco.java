package it.core.components;

public final class Disco implements Produzione{

    private final String titolo;
    private final String casaDiscografica;
    private final short anno;
    private final String artistaOBand;
    private final GenereMusicale genereMusicale;
    private final short numeroDiCanzoni;

    public Disco(String titolo, String casaDiscografica, short anno, String artistaOBand, GenereMusicale genereMusicale, short numeroDiCanzoni) {
        this.titolo = titolo;
        this.casaDiscografica = casaDiscografica;
        this.anno = anno;
        this.artistaOBand = artistaOBand;
        this.genereMusicale = genereMusicale;
        this.numeroDiCanzoni = numeroDiCanzoni;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getCasaDiscografica() {
        return casaDiscografica;
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

    public short getNumeroDiCanzoni() {
        return numeroDiCanzoni;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "titolo='" + titolo + '\'' +
                ", casaDiscografica='" + casaDiscografica + '\'' +
                ", anno=" + anno +
                ", artistaOBand='" + artistaOBand + '\'' +
                ", genereMusicale=" + genereMusicale +
                ", numeroDiCanzoni=" + numeroDiCanzoni +
                '}';
    }
}
