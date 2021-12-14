function login() {
    var n = $('#usuari');
    var p = $('#contrasenya');

    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify({'name': n.val(), 'password': p.val()}),
        url: "http://147.83.7.206:8080/dsaApp/user/login",
        success: function (result) {
            alert('USUARI I CONTRASENYA CORRECTES');
            window.location.replace("principal.html");

        }, error: function (result){alert('USUARI I/O CONTRASENYA INCORRECTES');
    }
    })
}

function register(){
    var n = $('#usuari');
    var p = $('#contrasenya');

    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify({'name': n.val(), 'password': p.val()}),
        url: "http://localhost:8080/dsaApp/user/add",
        success: function (result) {
            alert('USUARI REGISTRAT CORRECTAMENT');
            window.location.replace("principal.html");

        }, error: function (result){alert("EL NOM D'USUARI INTRODUIT JA EXISTEIX, PORVA AMB UN ALTRE DIFERENT");
    }
    })
}

function back(){
    window.location.replace("index.html");
}
