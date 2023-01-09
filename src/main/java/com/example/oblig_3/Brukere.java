package com.example.oblig_3;

public class Brukere {

    private int id;
    private String passord;
    private String brukerNavn;



    public Brukere(int id,String passord, String brukerNavn) {
        this.id = id;
        this.passord = passord;
        this.brukerNavn = brukerNavn;
    }

    public Brukere(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBrukerNavn() {
        return brukerNavn;
    }

    public void setBrukerNavn(String brukerNavn) {
        this.brukerNavn = brukerNavn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }




}
