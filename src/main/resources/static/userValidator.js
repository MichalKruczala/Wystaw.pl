function validateLoginForm() {
    var login = document.getElementById("login");
    var password = document.getElementById("password");

    var regex = /^[a-zA-Z0-9]{5,}$/;

    var result = true;

    if(!regex.test(login.value)) {
        login.style.background = "#E72627";
        result = false;
    } else {
        login.style.background = "#769B4B";
    }

    if(!regex.test(password.value)) {
        password.style.background = "#E72627";
        result = false;
    } else {
        password.style.background = "#769B4B";
    }

    return result;
}