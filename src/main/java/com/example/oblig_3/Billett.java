package com.example.oblig_3;

public class Billett {

    private String film;
    private String navn;
    private String etternavn;
    private String telefon;
    private String epost;
    private String antal;
    private String passord;

    public Billett(String film, String navn, String etternavn,
                   String telefon, String epost, String antal, String passord){
        this.film =film;
        this.navn =navn;
        this.etternavn = etternavn;
        this.telefon = telefon;
        this.epost = epost;
        this.antal = antal;
        this.passord = passord;

    }
    public Billett(){}

    public String getFilm () {return film; }
    public void setFilm (String film) { this.film =film; }

    public String getNavn () { return navn; }
    public void setNavn (String navn) {this.navn =navn; }

    public String getEtternavn () { return etternavn; }
    public void setEtternavn (String etternavn) { this.etternavn = etternavn; }

    public String getTelefon () { return telefon; }
    public void setTelefon (String telefon) {this.telefon = telefon; }

    public String getEpost () { return epost; }
    public void setEpost (String epost) {this.epost = epost; }

    public String getAntal () { return antal; }
    public void setAntal (String antal) {this.antal = antal; }

    public String getPassord () {return passord; }
    public void setPassord (String passord) { this.passord = passord; }
}
