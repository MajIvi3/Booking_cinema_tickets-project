package com.example.oblig_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class BillettController {

    @Autowired
    BillettRepository rep;

    //validering på server siden
    private boolean validerKunde(Billett kunde) {
        //Testen må være lik som i validering fra JS
        String regexpNavn = "[a-zæøåA-ZÆØÅ. \\-]{2,30}";
        String regexpTelefonnr ="[0-9. \\-]{8,12}";
        String regxEpost = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";

        boolean navnOK = kunde.getNavn().matches(regexpNavn);
        boolean telefonnrOK = kunde.getTelefon().matches(regexpTelefonnr);
        boolean epostOK = kunde.getEpost().matches(regxEpost);

        if (navnOK && telefonnrOK && epostOK){
            return true;
        }
        else {
            return false;
        }
    }

    @PostMapping("/innData")// lagrer kunder
    public void innKunder(Billett kunder, HttpServletResponse response) throws IOException {
        //input validering
        if (!validerKunde(kunder)){
            response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
        }
        else {
            //Feilhåndtering og loggføring i databasen
            if(!rep.innKunder(kunder)){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Feil i database - prøv igjen senere");
            }
        }

    }

    @GetMapping("/hentData")// setter kunder
    public List<Billett> hentKunder(HttpServletResponse response) throws IOException{
        //Feilhåndtering og loggføring i databasen for en array
        if(rep.hentAlleKunder()== null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Feil i /hentData - prøv igjen senere");
        }
        return rep.hentAlleKunder();
    }

    @GetMapping("/slettArray")// sletter kunnder
    public void slettArray(){ rep.slettAlle();}

    //Sesjoner
    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public boolean login(Billett kunde) {

        if(rep.sjekkNavnOgPassord(kunde)){
            session.setAttribute("Innlogget",kunde);
            return true;
        }
        return false;
    }

    @GetMapping("/logout")
    public void logout() {
        session.removeAttribute("Innlogget");
    }

}
