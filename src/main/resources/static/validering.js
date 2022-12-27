
//Lager validering i egen fil fordi det er en funksjon som skal brukes på flere steder

function  validerNavn(navn){
    const regexp = /^[a-zæøåA-ZÆØÅ. \-]{2,30}$/;
    const ok = regexp.test(navn);

    if(!ok){
        $("#feilNavnValid").html("Navn må bestå av 2 til 30 bokstaver");
        return false;
    }else {
        $("#feilNavnValid").html("")
        return true;
    }
}

function validerTelefonnr(telefonnr){

    const regexp = /^[0-9. \-]{8,12}$/;

    const ok = regexp.test(telefonnr);

    if(!ok){
        $("#feilTelefonnrValid").html("Telefonnummer må bestå av 8 til 12 tall");
        return false;
    }else {
        $("#feilTelefonnrValid").html("")
        return true;
    }
}

function validerEpost(epost){

    const regexp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

    const ok = regexp.test(epost);

    if(!ok){
        $("#feilEpostValid").html("Epost må besto av tall, bokstaver og @");
        return false;
    }else {
        $("#feilEpostValid").html("")
        return true;
    }
}

function validerPassord(passord){
    var regexp = /(?=.*[a-zA-ZæøåÆØÅ])(?=.*\d)[a-zA-ZæøåÆØÅ\d]{8,}/;
    var ok = regexp.test(passord);
    if(!ok){
        $("#feilPassord").html("Passordet må være minimum 8 tegn, et av de en bokstav og et tall");
        return false;
    }
    else{
        $("#feilPassord").html("");
        return true;
    }
}