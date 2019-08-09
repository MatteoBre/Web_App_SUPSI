

function checkEveryThing() {
    var firstNameTest = (/^[a-zA-Z]+$/.test(firstName.value));
    var lastNameTest = (/^[a-zA-Z]+$/.test(lastName.value));
    var usernameTest = (/^[a-zA-Z0-9_]+$/.test(username.value));
    var passwordTest = (/^[a-zA-Z0-9_]+$/.test(password.value) && password.value.length >= 8 && password.value.length <= 15);
    var passwordCheckTest =(/^[a-zA-Z0-9_]+$/.test(passwordCheck.value) && passwordCheck.value.length >= 8 && passwordCheck.value.length <= 15 && password.value == passwordCheck.value);

    //summary test
    if(firstNameTest && lastNameTest && usernameTest && passwordTest && passwordCheckTest)
    {
        if(document.getElementById("register_button") == null)
            document.getElementById("form").insertAdjacentHTML('beforeend', submit);
    }
    else
    {
        var registerButton = document.getElementById("register_button");
        registerButton.parentNode.removeChild(registerButton);
    }
};

function passVerificationCheck() {
    var passLengthCheck = passwordCheck.value.length >= 8 && passwordCheck.value.length <= 15;
    if(/^[a-zA-Z0-9_]+$/.test(passwordCheck.value) && passLengthCheck && password.value == passwordCheck.value)
        passwordCheck.className = passwordCheck.className.replace(" redBorder", "")
    else if(!passwordCheck.classList.contains("redBorder"))
        passwordCheck.className = passwordCheck.className + " redBorder";
    checkEveryThing();
}

//loading document elements
var firstName = document.getElementById("firstName");
var lastName = document.getElementById("lastName");
var username = document.getElementById("username");
var password = document.getElementById("password");
var passwordCheck = document.getElementById("passwordCheck");

//register Button
var submit = '<button id="register_button" class="btn btn-lg btn-primary btn-block">Register</button>';

firstName.addEventListener('input', function firstNameCheck() {
    if(/^[a-zA-Z]+$/.test(firstName.value))
        firstName.className = firstName.className.replace(" redBorder", "");
    else if(!firstName.classList.contains("redBorder"))
            firstName.className = firstName.className + " redBorder";
    checkEveryThing();
});

lastName.addEventListener('input', function lastNameCheck() {
    if(/^[a-zA-Z]+$/.test(lastName.value))
        lastName.className = lastName.className.replace(" redBorder", "");
    else if(!lastName.classList.contains("redBorder"))
            lastName.className = lastName.className + " redBorder";
    checkEveryThing();
});

username.addEventListener('input', function usernameCheck() {
    if(/^[a-zA-Z0-9_]+$/.test(username.value))
        username.className = username.className.replace(" redBorder", "")
    else if(!username.classList.contains("redBorder"))
        username.className = username.className + " redBorder";
    checkEveryThing();
});

password.addEventListener('input', function passCheck() {
    var passLengthCheck = password.value.length >= 8 && password.value.length <= 15;
    if(/^[a-zA-Z0-9_]+$/.test(password.value) && passLengthCheck)
        password.className = password.className.replace(" redBorder", "")
    else if(!password.classList.contains("redBorder"))
        password.className = password.className + " redBorder";
    passVerificationCheck();
    checkEveryThing();
});

passwordCheck.addEventListener('input', function passVerification() {
    passVerificationCheck();
    checkEveryThing();
});