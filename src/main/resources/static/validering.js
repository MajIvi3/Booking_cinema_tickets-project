
//Creating validation in its own file because it is a feature that will be used in multiple locations

// Name
function  validerNavn(navn){
    const regexp = /^[a-zæøåA-ZÆØÅ. \-]{2,30}$/;
    const ok = regexp.test(navn);

    if(!ok){
        $("#feilNavnValid").html("Navnet må bestå av minst 2 bokstaver");
        return false;
    }else {
        $("#feilNavnValid").html("")
        return true;
    }
}

// Phone number
function validerTelefonnr(telefonnr){

    const regexp = /^[0-9. \-]{8,12}$/;

    const ok = regexp.test(telefonnr);

    if(!ok){
        $("#feilTelefonnrValid").html("Telefonnummeret må bestå av minst 8 tall");
        return false;
    }else {
        $("#feilTelefonnrValid").html("")
        return true;
    }
}

// Email
function validerEpost(epost){

    const regexp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

    const ok = regexp.test(epost);

    if(!ok){
        $("#feilEpostValid").html("E-post må bestå av tall, bokstaver og @");
        return false;
    }else {
        $("#feilEpostValid").html("")
        return true;
    }
}

// Password
function validerPassord(passord){
    var regexp = /(?=.*[a-zA-ZæøåÆØÅ])(?=.*\d)[a-zA-ZæøåÆØÅ\d]{8,}/;
    var ok = regexp.test(passord);
    if(!ok){
        $("#feilPassord").html("Passordet må bestå av minimum 8 tegn og ett tall");
        return false;
    }
    else{
        $("#feilPassord").html("");
        return true;
    }
}