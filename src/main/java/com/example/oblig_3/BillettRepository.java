package com.example.oblig_3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillettRepository {

    @Autowired
    private JdbcTemplate db;
    private Logger logger = LoggerFactory.getLogger(BillettRepository.class);


    public boolean innKunder(Billett kunde){
                 //Data in
        String sql = "INSERT INTO Billett (film, navn, etternavn, telefon, epost, antal) VALUES (?,?,?,?,?,?)";

        //Error handling and logging in the database
        try {
            db.update(sql, kunde.getFilm(), kunde.getNavn(), kunde.getEtternavn(), kunde.getTelefon(),
                    kunde.getEpost(), kunde.getAntal());
            return true;
        } catch (Exception e){
            logger.error("Feil i lagreKunde(): " + e);
            return false;
        }
    }
    //Error handling and logging in the array database
    public List<Billett> hentAlleKunder(){

        String sql = "SELECT * FROM Billett";
        try {
            List<Billett> hentAlle = db.query(sql, new BeanPropertyRowMapper<>(Billett.class));
            return hentAlle;
        } catch (Exception e){
            logger.error("Feil i hentAlleKunder" + e);
            return null;
        }

    }
    // Delete everything from database
    public void slettAlle(){
        String sql = "DELETE FROM Billett";

        db.update(sql);
    }
    //Sessions
    public boolean sjekkNavnOgPassord (Billett kunde) {

        Object[] param = new Object[]{kunde.getNavn(),kunde.getPassord()};
        String sql = "SELECT COUNT(*) FROM Kunde WHERE navn=? AND passord=?";
        try{
            int antall = db.queryForObject(sql,param,Integer.class);
            if (antall>0){
                return true;
            }
            return false;
        }
        catch (Exception e){
            logger.error("Feil i sjekkNavnOgPassord : "+e);
            return false;
        }
    }
}
