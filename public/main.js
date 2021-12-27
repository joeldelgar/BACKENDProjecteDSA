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
            $("#health").text(result.health);
        },
        error : function(error) {
        alert("Unable to get your data.");
        }
    });
}

//GetInventory: problemes amb el DAO
function getInventory() {
    let userlocal = localStorage.getItem("loggedUser");
    $.ajax({
        type: 'GET',
        url: "/dsaApp/store/getUserInventory/"+userlocal,
        dataType: 'json',
        success: function (result) {
            $("#username").text(result.userName);
            $('#table').append(
                '<tr><td> Magic Berry </td><td>'+ result.magicBerry +'</td></tr>' +
                '<tr><td> Level 1 Item </td><td>'+ result.level1Item +'</td></tr>' +
                '<tr><td> Level 1 Key </td><td>'+ result.level1Key +'</td></tr>' +
                '<tr><td> Level 2 Item </td><td>'+ result.level2Item +'</td></tr>' +
                '<tr><td> Level 2 Key </td><td>'+ result.level2Key +'</td></tr>' +
                '<tr><td> Level 3 Item</td><td>'+ result.level3Item +'</td></tr>' +
                '<tr><td> Level 3 Key </td><td>'+ result.level3Key +'</td></tr>' +
                '<tr><td> Level 4 Item </td><td>'+ result.level4Item +'</td></tr>' +
                '<tr><td> Level 4 Key </td><td>'+ result.level4Key +'</td></tr>' +
                '<tr><td> Level 5 Item </td><td>'+ result.level5Item +'</td></tr>' +
                '<tr><td> Level 5 Key </td><td>'+ result.level5Key +'</td></tr>'
            );
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
                    '<tr><td><p>' +e.name + '</p><p>' + e.description +
                        '</p><p>' + 'Price: ' + e.cost +
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

//UpdateUser no implementat al DAO
function updateUser(){
    alert('Function unavailable');

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

//BuyItem OK
function buyItem(it){
    let userlocal = localStorage.getItem("loggedUser");
    $.ajax({
        contentType: "application/json",
        type: 'POST',
        url: "/dsaApp/store/buyItem",
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

function logOut(){
    localStorage.setItem("loggedUser", null);
    alert('See you soon!');
    window.location.href = "index.html";
}
function setUpShop(){
    getShop();
    getUser();
}