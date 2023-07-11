package it.core.singolo;

import it.core.GenereMusicale;
import it.core.Produzione;
import it.core.disco.Disco;

public record Singolo(String titolo, short anno, String artistaOBand,
                      GenereMusicale genereMusicale, Disco discoAppartenenza) implements Produzione {
    @Override
    public String getTitolo() {
        return titolo;
    }
}
