package it.core;

import java.time.LocalDate;

public class Messaggio {

    private String email;
    private LocalDate orario;
    private String testo;

    public Messaggio(String email, LocalDate orario, String testo) {
        this.email = email;
        this.orario = orario;
        this.testo = testo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getOrario() {
        return orario;
    }

    public void setOrario(LocalDate orario) {
        this.orario = orario;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    @Override
    public String toString() {
        return "Messaggio{" +
                "contatto=" + email +
                ", orario=" + orario +
                ", testo='" + testo + '\'' +
                '}';
    }
}
