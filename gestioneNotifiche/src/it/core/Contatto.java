package it.core;


import it.enums.TipoContatto;

public class Contatto extends Persona {

    private String email;
    private TipoContatto tipoContatto;
    private String password;


    public Contatto(String nome, String cognome, String codFiscale, String email, TipoContatto tipoContatto, String password) {
        super(nome, cognome, codFiscale);
        this.email = email;
        this.tipoContatto = tipoContatto;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoContatto getTipoContatto() {
        return tipoContatto;
    }

    public void setTipoContatto(TipoContatto tipoContatto) {
        this.tipoContatto = tipoContatto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Contatto{" +
                "email='" + email + '\'' +
                ", tipoContatto='" + tipoContatto + '\'' +
                ", passsword='" + password + '\'' +
                '}';
    }
}
