
// If name and password are correct login
function validerOgLogin(){
    const navnOK = validerNavn($("#navn").val());
    const passordOK = validerPassord($("#passord").val());
    if (navnOK && passordOK){
        login();
    }
}

// logout
function logout(){
    const url = "/logout";
    $.get( url, function() {
        window.location.href = 'login.html';
    })
}

// login
function login(){
    const kunde = {
        navn : $("#navn").val(),
        passord : $("#passord").val()
    }
    const url = "/login";
    $.get( url, kunde, function(innlogget) {
        if(innlogget){
            window.location.href = 'index.html';
        }
        else{
            $("#feil").html("Feil brukernavn eller passord");
        }
    })
        .fail(function() {
                $("#feil").html("Serverfeil- prøv igjen senere");
            }
        );
}