

function visTxt(){

    // IF ALL input boxes are NOT filled in- show error messages to user
    if ( $("#film option:selected").val()==="" || $("#navn").val()==="" || $("#etteravn").val()==="" ||
        $("#tel").val()==="" ||$("#epost").val()==="" || $("#nr").val()==="")
    {
        //If user fills in all the input-box, the error field resets
        $("#feilFilm").html("");
        $("#feilNavn").html("");
        $("#feilEtternav").html("");
        $("#feilTel").html("");
        $("#feilPost").html("");
        $("#feilAntal").html("");

        //error message prints out
        if ($("#film option:selected").val() ===""){$("#feilFilm").html("<strong>"+"Må velge noe film"+"</strong>");}
        if ($("#navn").val() ===""){$("#feilNavn").html("<strong>"+"Må skrive noe inn i fornavn"+"</strong>");}
        if ($("#etteravn").val() ===""){$("#feilEtternav").html("<strong>"+"Må skrive noe inn etternavn"+"</strong>");}
        if ($("#tel").val() ===""){$("#feilTel").html("<strong>"+"Må skrive noe inn i telefonnr"+"</strong>");}
        if ($("#epost").val() === ""){$("#feilPost").html("<strong>"+"Må skrive noe inn i epost"+"</strong>");}
        if ($("#nr").val() === ""){$("#feilAntal").html("<strong>"+"Må skrive noe inn i antal"+"</strong>");}
        else {}

        //The color of the error message
        feilFilm.style.color ="crimson";
        feilNavn.style.color="crimson";
        feilEtternav.style.color="crimson";
        feilTel.style.color="crimson";
        feilPost.style.color="crimson";
        feilAntal.style.color="crimson";

    }
    // IF All input boxes are filled in do:
    else
    {

        const objekt = {
            film : $("#film").val(),
            navn :$("#navn").val(),
            etternavn : $("#etteravn").val(),
            telefon : $("#tel").val(),
            epost : $("#epost").val(),
            antal: $("#nr").val()
        }
        $.post("/innData", objekt, function (){
            hentKunder();
            // vi utvider denne med feil funksjonen(gjør en liten endring på Html
            // for å vise feilen
        }).fail(function (jqXHR){
            const json = $.parseJSON(jqXHR.responseText);
            // Show the error to user
            $("#feil").html(json.message);
        });
        //resets input fields
        $("#film").val("");
        $("#navn").val("");
        $("#etteravn").val("");
        $("#tel").val("");
        $("#epost").val("");
        $("#nr").val("");

        //Resetting error messages
        $("#feilFilm").html("");
        $("#feilNavn").html("");
        $("#feilEtternav").html("");
        $("#feilTel").html("");
        $("#feilPost").html("");
        $("#feilAntal").html("");
    }


}
// vi utvider denne med feil funksjonen
function hentKunder(){
    $.get("/hentData", function ( data ){
        innKunder(data);

    }).fail(function (jqXHR){
        const json = $.parseJSON(jqXHR.responseText);
        // Show the error to user
        $("#feil").html(json.message);
    });
}

function  innKunder(myArray){
    let ut = "<table class=\"table table-striped table-bordered\">" +
        "<tr><th>Film</th><th>Navn</th><th>Etternavn</th><th>Telefon</th><th>Epost</th><th>Antall</th></tr>";
    for (const reg of myArray){
        ut+="<tr><td>"+reg.film+"</td><td>"+reg.navn+"</td><td>"+reg.etternavn+"</td><td>"+reg.telefon+"</td><td>"
            +reg.epost +"</td><td>"+reg.antal+"</td></tr>";
    }
    ut+="</table>";
    $("#bilettKjop").html(ut);
}
function slett(){
    $.get("/slettArray", function (){
        hentKunder();
    });
}