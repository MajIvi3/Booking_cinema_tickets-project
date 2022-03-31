package com.example.oblig_3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class BillettController {

    @Autowired
    BillettRepository rep;



    @PostMapping("/innData")
    public void innKunder(Billett kunder) {rep.innKunder(kunder);}

    @GetMapping("/hentData")
    public List<Billett> hentKunder() {return rep.hentAlleKunder();}

    @GetMapping("/slettArray")
    public void slettArray(){ rep.slettAlle();}

}
