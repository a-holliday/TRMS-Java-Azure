
console.log("JS Connected");
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

window.onload = function () {
    console.log("Cookie is " + getCookie("employee_id"))
    if (getCookie("employee_id") == "") {
        document.body.style.display = "none";
    }
    else {
        let submitform = document.querySelector("#requests-form");
        submitform.action = "/request/" + getCookie("employee_id")
        console.log(submitform.action);
    }


    let now = new Date();
    now.setDate(now.getDate() + 7);
    let myDate = now;
    const offset = myDate.getTimezoneOffset()
    myDate = new Date(myDate.getTime() - (offset * 60 * 1000))
    myDate = myDate.toISOString().split('T')[0]

    document.getElementById("date").setAttribute("min", myDate);
}