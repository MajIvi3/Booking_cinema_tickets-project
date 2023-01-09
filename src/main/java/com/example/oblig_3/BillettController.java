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

    //server-side validation
    private boolean validerKunde(Billett kunde) {

        //The test must be the same as in validation from JS
        String regexpNavn = "[a-zæøåA-ZÆØÅ. \\-]{2,30}";
        String regexpTelefonnr ="[0-9. \\-]{8,12}";
        String regxEpost = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";

        //checking if correct
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

    // Saving customers who "buy ticket"
    @PostMapping("/innData")
    public void innKunder(Billett kunder, HttpServletResponse response) throws IOException {

        //input validation
        if (!validerKunde(kunder)){
            response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
        }
        else {

            //Error handling and logging in the database
            if(!rep.innKunder(kunder)){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Feil i database - prøv igjen senere");
            }
        }

    }

    // lists out all customers
    @GetMapping("/hentData")
    public List<Billett> hentKunder(HttpServletResponse response) throws IOException{

        //Error handling and logging in the database in via array
        if(rep.hentAlleKunder()== null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Feil i /hentData - prøv igjen senere");
        }
        return rep.hentAlleKunder();
    }

    // Deleting customers who "bought a ticket"
    @GetMapping("/slettArray")
    public void slettArray(){ rep.slettAlle();}

    //Sessions
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
