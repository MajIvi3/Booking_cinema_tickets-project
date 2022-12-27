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

    //bytter metoden fra void til boolean
    public boolean innKunder(Billett kunde){
                 //Legget inn feil (LL på slutten) for ikke skrevet ut feil til bruker
        String sql = "INSERT INTO Billett (film, navn, etternavn, telefon, epost, antal) VALUES (?,?,?,?,?,?)";

        //Feilhåndtering og loggføring i databasen
        try {
            db.update(sql, kunde.getFilm(), kunde.getNavn(), kunde.getEtternavn(), kunde.getTelefon(),
                    kunde.getEpost(), kunde.getAntal());
            return true;
        } catch (Exception e){
            logger.error("Feil i lagreKunde(): " + e);
            return false;
        }
    }
    //Feilhåndtering og loggføring i databasen med array
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
    public void slettAlle(){
        String sql = "DELETE FROM Billett";

        db.update(sql);
    }
    //Sesjoner
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
