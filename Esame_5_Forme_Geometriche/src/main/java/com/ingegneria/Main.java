package com.ingegneria;

import com.ingegneria.core.classes.Cerchio;
import com.ingegneria.core.classes.Equilatero;
import com.ingegneria.core.classes.Quadrato;
import com.ingegneria.core.classes.Raccoglitore;
import com.ingegneria.core.interfaces.Forma;
import com.ingegneria.core.utility.RaggruppatoreDiForme;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Forma> forme = new HashSet<>();
        forme.add(new Equilatero(10, "rosso"));
        forme.add(new Equilatero(110, "verde"));
        forme.add(new Equilatero(40, "blu"));
        forme.add(new Cerchio(10, "blu"));
        forme.add(new Cerchio(20, "blu"));
        forme.add(new Cerchio(135, "verde"));
        forme.add(new Quadrato(10, "verde"));
        forme.add(new Quadrato(11, "rosso"));
        forme.add(new Quadrato(12, "rosso"));
        Raccoglitore raggruppa = new RaggruppatoreDiForme().raggruppa(forme);
        raggruppa.ordinaPerDimensione().forEach(System.out::println);
        raggruppa.raggruppaPerColore().forEach((k, v) -> System.out.println(k + " " + v));
    }
}