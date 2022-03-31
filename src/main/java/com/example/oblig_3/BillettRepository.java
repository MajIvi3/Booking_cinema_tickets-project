package com.example.oblig_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillettRepository {

    @Autowired
    private JdbcTemplate db;

    public void innKunder(Billett kunde){

        String sql = "INSERT INTO Billett (film, navn, etternavn, telefon, epost, antal) VALUES (?,?,?,?,?,?)";

        db.update(sql,kunde.getFilm(), kunde.getNavn(), kunde.getEtternavn(), kunde.getTelefon(),
                kunde.getEpost(), kunde.getAntal());

    }

    public List<Billett> hentAlleKunder(){

        String sql = "SELECT * FROM Billett";

        List<Billett> hentAlle = db.query(sql, new BeanPropertyRowMapper<>(Billett.class));

        return hentAlle;

    }
    public void slettAlle(){
        String sql = "DELETE FROM Billett";

        db.update(sql);
    }
}
