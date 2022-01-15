//Login OK
function login(){
    var n = $('#username').val();
    var p = $('#password').val();

    localStorage.setItem("loggedUser", n);

    $.ajax({
        contentType: "application/json",
        type: 'POST',
        url: "/dsaApp/user/logIn",
        data: JSON.stringify({name: n, password: p}),
        dataType: 'json',
        success: function (result) {
            window.location.href = "profile.html";
        },
        error : function(error) {
            if (n == "" || p == "")
                alert("Username or Password are blank, please try again!");
            else
                alert("Wrong Username or Password, please try again!");
        }
    });
}

//Register OK
function register(){
    var n = $('#username').val();
    var p = $('#password').val();
    var m = $('#mail').val();

    localStorage.setItem("loggedUser", n);

    $.ajax({
        contentType: "application/json",
        type: 'POST',
        url: "/dsaApp/user/register",
        data: JSON.stringify({name: n, password: p, mail: m}),
        dataType: 'json',
        success: function (result) {
            window.location.href = "profile.html";
        },
        error : function(error) {
        if (n == "" || p == "" || m == "")
            alert("Make sure none of the camps are blank and try again!");
         else
            alert("This username is already in use, please try another one!");
        }
    });
}

//GetUser OK
function getUser() {
    let userlocal = localStorage.getItem("loggedUser");
    $.ajax({
        type: 'GET',
        url: "/dsaApp/user/"+userlocal,
        dataType: 'json',
        success: function (result) {
            $("#username").text(result.name);
            $("#mail").text(result.mail);
            $("#coins").text(result.coins);
        },
        error : function(error) {
        alert("Unable to get your data.");
        }
    });
}

//GetInventory OK
function getInventory() {
    let userlocal = localStorage.getItem("loggedUser");
    $.ajax({
        type: 'GET',
        url: "/dsaApp/store/userInventoryList/"+userlocal,
        dataType: 'json',
        success: function (result) {
            $.each(result, function(i, e){
                $('#table').append(
                    '<tr><td><p>' +e.itemName + '</p></td><td><p>' + e.itemDescription +
                    '</p></td><td><p>' + e.itemQuantity +
                    '</p></td><td><p>' + '<img src="' + e.itemAvatar + '" alt="Avatar" style="width:30%">'+
                    '</p></td></tr>'
                );
            });
        },
        error : function(error) {
            alert("Unable to get your inventory.");
        }
    });
}

//GetShop OK
function getShop(){
    $.ajax({
        type: 'GET',
        url: "/dsaApp/store/itemList",
        dataType: 'json',
        success: function (result) {
            $.each(result, function(i, e){
                $('#table').append(
                    '<tr><td><p><b> ' +e.name + '</b></p><p>' + e.description +
                        '</p><p>' + '<b>Price:</b> ' + e.cost +
                        '</p></td> <td><p>' + '<img src="' + e.avatar + '" alt="Avatar" style="width:35%">'+
                        '</p></td> <td> <div class="container"> <input type="submit" value="Buy" ' +
                        ' class="button" onclick="buyItem(this.id)" id="' + e.name +'"></div> </td> </tr>'
                );
            });
        },
        error : function(error) {
            alert("Unable to get Shop data.");
        }
    });
}

//UpdateUser OK
function updateUser(){
    let userlocal = localStorage.getItem("loggedUser");
    var n = $('#username').val();
    var p = $('#password').val();
    var m = $('#mail').val();
    $.ajax({
        contentType: "application/json",
        type: 'PUT',
        url: "/dsaApp/user/update/"+userlocal,
        data: JSON.stringify({name: n, password: p, mail: m}),
        dataType: 'json',
        success: function (result) {
            alert("Updated successfully!");
            localStorage.setItem("loggedUser", n);
            window.location.href = "profile.html";
        },
        error : function(error) {
            alert("Something went wrong, try again!");
        }
    });
}

//DeleteUser OK
function deleteUser(){
    let userlocal = localStorage.getItem("loggedUser");
    $.ajax({
        contentType: "application/json",
        type: 'DELETE',
        url: "/dsaApp/user/delete/"+userlocal,
        dataType: 'json',
        success: function (result) {
            alert("User successfully deleted.")
            window.location.href = "index.html";
        },
        error : function(error) {
            alert("We were unable to delete your account, please try again.");
        }
    });
}

//BuyItem pilla el item i el username però no compra, revisar després
//mirar què passa si es treu el getUser (actualitza monedes)
function buyItem(it){
    let userlocal = localStorage.getItem("loggedUser");
    $.ajax({
        contentType: "application/json",
        type: 'PUT',
        url: "/dsaApp/store/buyItem/"+it,
        data: JSON.stringify({itemName: it, userName: userlocal}),
        dataType: 'json',
        success: function (result) {
            alert(it + ' bought successfully!');
            getUser();
        },
        error : function(error) {
             alert('Purchase failed! Check if you have enough coins or if you already own this item.');

        }
    });
}

//GetRankings  OK
function getRankings(){
    $.ajax({
        type: 'GET',
        url: "/dsaApp/game/byPoints",
        dataType: 'json',
        success: function (result) {
            $.each(result, function(i, e){
                $('#table').append(
                    '<tr><td><p>' +e.userName + '</p></td><td><p>' + e.points +'</td></p></tr>'
                );
            });
        },
        error : function(error) {
            alert("Unable to get rankings.");
        }
    });
}

// Funcions extres

function logOut(){
    localStorage.setItem("loggedUser", null);
    alert('See you soon!');
    window.location.href = "index.html";
}

function setUpShop(){
    getShop();
    getUser();
}

function setUpInventory(){
    getInventory();
    getUser();
}
