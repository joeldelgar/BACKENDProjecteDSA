function login() {
    alert("LOGIN");

    var n = $('#username');
    var p = $('#password');

    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify({'name': n.val(), 'password': p.val()}),
        url: "http://147.83.7.206:8080/dsaApp/user/login",
        success: function (result) {
            alert('USUARI I CONTRASENYA CORRECTES');
            window.location.replace("profile.html");

        }, error: function (result){alert('USUARI I/O CONTRASENYA INCORRECTES');
    }
    })
}

function register(){
    alert("REGISTER");

    var n = $('#usuari');
    var p = $('#contrasenya');
    var m = $('#mail');

    $.ajax({
        contentType: 'application/json',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify({'name': n.val(), 'password': p.val(), 'mail':m.val()}),
        url: "http://localhost:8080/dsaApp/user/add",
        success: function (result) {
            alert('USUARI REGISTRAT CORRECTAMENT');
            window.location.replace("profile.html");

        }, error: function (result){alert("EL NOM D'USUARI INTRODUIT JA EXISTEIX, PORVA AMB UN ALTRE DIFERENT");
    }
    })
}

function back(){ window.location.replace("index.html"); }

function updateUser(){
  alert("UPDATE");

  var n = $('#usuari');
  var p = $('#contrasenya');
  var m = $('#mail');

  $.ajax({
      contentType: 'application/json',
      type: 'POST',
      dataType: 'json',
      data: JSON.stringify({'name': n.val(), 'password': p.val(), 'mail':m.val()}),
      url: "http://localhost:8080/dsaApp/user/add",
      success: function (result) {
          alert('USUARI UPDATEJAT CORRECTAMENT');
          window.location.replace("edituser.html");

      }, error: function (result){alert("NO S'HA POGUT ACTUALITZAR CORRECTAMENT");
  }
  })

}

function deleteUser(){

}




function getInventary(){

}

function getUser(){}
