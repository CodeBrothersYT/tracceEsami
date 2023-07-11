package it.core.classifica;

import it.core.GenereMusicale;
import it.core.Produzione;
import it.core.disco.Disco;
import it.core.singolo.Singolo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Map.entry;

public class Classifica {
    private final String titolo;
    private final String areaGeografica;
    private final GenereMusicale genere;
    private final List<Produzione> produzioni;

    private Classifica(String titolo, String areaGeografica, GenereMusicale genere, List<Produzione> produzioni) {
        this.titolo = titolo;
        this.areaGeografica = areaGeografica;
        this.genere = genere;
        this.produzioni = produzioni;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAreaGeografica() {
        return areaGeografica;
    }

    public GenereMusicale getGenere() {
        return genere;
    }

    public List<Produzione> getProduzioni() {
        return Collections.unmodifiableList(produzioni);
    }

    public <T extends Produzione> Map<Integer, T> getProduzioni(Class<T> produzione){
        return IntStream.range(0, produzioni.size())
                .mapToObj(idx -> entry(idx, produzioni.get(idx)))
                .filter(e -> produzione.isAssignableFrom(e.getValue().getClass()))
                .map(e -> entry(e.getKey(), (T) e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void addProduzione(Produzione produzione) {
        produzioni.add(produzione);
    }

    public void setProduzione(int index, Produzione produzione) {
        produzioni.set(index, produzione);
    }

    public void rimuoviProduzione(int index){
        produzioni.remove(index);
    }

    @Override
    public String toString() {
        return "Classifica{" +
                "titolo='" + titolo + '\'' +
                ", areaGeografica='" + areaGeografica + '\'' +
                ", genere=" + genere +
                ", produzioni=" + produzioni +
                '}';
    }

    public static class ClassificaBuilder {
        private final String titolo;
        private final String areaGeografica;
        private final GenereMusicale genere;
        private final List<Produzione> produzioni = new ArrayList<>();

        private ClassificaBuilder(String titolo, String areaGeografica, GenereMusicale genere) {
            this.titolo = titolo;
            this.areaGeografica = areaGeografica;
            this.genere = genere;
        }

        public static ClassificaBuilder builder(String titolo, String areaGeografica, String genere) {
            return new ClassificaBuilder(titolo, areaGeografica, GenereMusicale.valueOf(genere));
        }

        public ClassificaBuilder disco(Disco disco) {
            produzioni.add(disco);
            return this;
        }

        public ClassificaBuilder singolo(Singolo singolo) {
            produzioni.add(singolo);
            return this;
        }

        public Classifica build() {
            return new Classifica(titolo, areaGeografica, genere, produzioni);
        }

    }
}
