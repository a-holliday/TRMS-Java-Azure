
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
    //AJAX - Asynchronous JavaScript and XML
    //Initialize xhr object
    let xhr = new XMLHttpRequest();
    let xhr2 = new XMLHttpRequest();
    console.log("LETS SEE IF WE GET ANYTHING");
    //console.log(getCookie("employee_id"))
    const url = "/cookie";
    let url2;
    //sets up ready state handler
    xhr.onreadystatechange = function () {
        console.log(xhr.readyState);
        switch (xhr.readyState) {

            case 0:
                //console.log("nothing, initalized not sent");
                break;
            case 1:
                //console.log("connection established")
                break;
            case 2:
                //console.log("request sent");
                break;
            case 3:
                //console.log("waiting response");
                break;
            case 4:
                //console.log("FINISHED!!!!!!!!!!!");
                //logic to add guest to table
                if (xhr.status === 200) {
                    console.log(xhr.responseText);
                    let employee_id = JSON.parse(xhr.responseText);
                    console.log(employee_id);
                    document.cookie = "employee_id=" + employee_id.value;
                    if (getCookie("employee_id") == "") {

                    }
                    else {
                        url2 = "/employee/" + getCookie("employee_id");
                        xhr2.open("GET", url2, true);
                        xhr2.send();
                    }


                }
                break;

        }
    }

    xhr2.onreadystatechange = function () {
        if (xhr2.readyState == 4 && xhr2.status === 200) {
            let employee = JSON.parse(xhr2.responseText);
            console.log(xhr2.responseText);
            if (employee.employee_role == "EMPLOYEE")
                populateHome(employee);

            else {
                populateApproverHome(employee);
            }
        }
    }

    //opens up the request
    xhr.open("GET", url, true);
    //sends request
    xhr.send();



}

let populateHome = function (employee) {
    document.querySelector("#welcome-banner").innerHTML = "Welcome " + employee.first_name;
    document.querySelector("#funds-banner").innerHTML = employee.fundsAllocated.toFixed(2) + "$ /" + employee.fundsMax.toFixed(2) + "$";
    let linksList = document.querySelector(".links");
    let listItemRequest = document.createElement("li");
    let linkItemRequest = document.createElement("a");
    let listItemPending = document.createElement("li");
    let linkItemPending = document.createElement("a");
    let listItemApproved = document.createElement("li");
    let linkItemApproved = document.createElement("a");
    let listItemLogout = document.createElement("li");
    let linkItemLogout = document.createElement("a");

    listItemRequest.appendChild(linkItemRequest);
    listItemPending.appendChild(linkItemPending);
    listItemApproved.appendChild(linkItemApproved);
    listItemLogout.appendChild(linkItemLogout);

    linkItemRequest.appendChild(document.createTextNode("Request Reimbursement"));
    linkItemRequest.href = "/request";

    linkItemPending.appendChild(document.createTextNode("Check Your Pending Reimbursements"));
    linkItemPending.href = "/pending";

    linkItemApproved.appendChild(document.createTextNode("Check Your Resolved Reimbursements"));
    linkItemApproved.href = "/completed";

    linkItemLogout.appendChild(document.createTextNode("Logout"));
    linkItemLogout.href = "/logout";


    linksList.appendChild(listItemRequest);
    linksList.appendChild(listItemPending);
    linksList.appendChild(listItemApproved);
    linksList.appendChild(listItemLogout);





}

let populateApproverHome = function (employee) {
    document.querySelector("#welcome-banner").innerHTML = "Welcome " + employee.first_name;
    document.querySelector("#funds-banner").innerHTML = employee.fundsAllocated.toFixed(2) + "$ /" + employee.fundsMax.toFixed(2) + "$";

    let linksList = document.querySelector(".links");
    let listItemRequest = document.createElement("li");
    let linkItemRequest = document.createElement("a");
    let listItemPending = document.createElement("li");
    let linkItemPending = document.createElement("a");
    let listItemApproved = document.createElement("li");
    let linkItemApproved = document.createElement("a");

    let listItemApproveOthers = document.createElement("li");
    let linkItemApproveOthers = document.createElement("a");

    let listItemLogout = document.createElement("li");
    let linkItemLogout = document.createElement("a");

    listItemRequest.appendChild(linkItemRequest);
    listItemPending.appendChild(linkItemPending);
    listItemApproved.appendChild(linkItemApproved);
    listItemApproveOthers.appendChild(linkItemApproveOthers);
    listItemLogout.appendChild(linkItemLogout);

    linkItemApproveOthers.appendChild(document.createTextNode("Approve Reimbursements"));
    linkItemApproveOthers.href = "/approve/";

    linkItemRequest.appendChild(document.createTextNode("Request Reimbursement"));
    linkItemRequest.href = "/request";

    linkItemPending.appendChild(document.createTextNode("Check Your Pending Reimbursements"));
    linkItemPending.href = "/pending";

    linkItemApproved.appendChild(document.createTextNode("Check Your Resolved Reimbursements"));
    linkItemApproved.href = "/completed";

    linkItemLogout.appendChild(document.createTextNode("Logout"));
    linkItemLogout.href = "/logout";

    linkItemLogout.addEventListener("click", function () {
        document.cookie = "employee_id=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        document.cookie = "case_id=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    });


    linksList.appendChild(listItemRequest);
    linksList.appendChild(listItemPending);
    linksList.appendChild(listItemApproved);
    linksList.appendChild(listItemApproveOthers);
    linksList.appendChild(listItemLogout);





}

let noAccess = function () {
    document.querySelector("#welcome-banner").innerHTML = "No Access";

}