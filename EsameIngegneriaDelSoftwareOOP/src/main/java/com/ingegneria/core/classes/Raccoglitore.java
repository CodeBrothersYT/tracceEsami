package com.ingegneria.core.classes;

import com.ingegneria.core.interfaces.Forma;

import java.util.*;
import java.util.stream.Collectors;

public class Raccoglitore {
    private List<Cerchio> cerchi;
    private List<Equilatero> equilateri;
    private List<Quadrato> quadrati;

    public Raccoglitore() {
        this.cerchi = new LinkedList<>();
        this.equilateri = new LinkedList<>();
        this.quadrati = new LinkedList<>();
    }

    public List<Cerchio> getCerchi() {
        return this.cerchi;
    }

    public List<Equilatero> getEquilateri() {
        return this.equilateri;
    }

    public List<Quadrato> getQuadrati() {
        return this.quadrati;
    }

    public void addCerchio(Cerchio c) {
        this.cerchi.add(c);
    }

    public void addEquilatero(Equilatero e) {
        this.equilateri.add(e);
    }

    public void addQuadrato(Quadrato q) {
        this.quadrati.add(q);
    }

    public List<Forma> ordinaPerDimensione() {
        List<Forma> forme = new ArrayList<>();
        forme.addAll(this.cerchi);
        forme.addAll(this.equilateri);
        forme.addAll(this.quadrati);
        forme.sort(Comparator.comparingInt(Forma::getDimensione));
        return forme;
    }

    public Map<String, Set<Forma>> raggruppaPerColore() {
        List<Forma> forme = new ArrayList<>();
        forme.addAll(this.cerchi);
        forme.addAll(this.equilateri);
        forme.addAll(this.quadrati);
        return forme.stream().collect(Collectors.groupingBy(Forma::getColore, Collectors.toSet()));
    }
}