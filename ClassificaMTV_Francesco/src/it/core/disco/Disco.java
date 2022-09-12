package it.core.disco;

import it.core.GenereMusicale;
import it.core.Produzione;

public record Disco(String titolo, String casaDiscografica, short anno, String artistaOBand,
                    GenereMusicale genereMusicale, short numeroDiCanzoni) implements Produzione {
    @Override
    public String getTitolo() {
        return titolo;
    }
}
