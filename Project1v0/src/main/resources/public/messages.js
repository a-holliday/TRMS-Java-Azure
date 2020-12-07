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

    if (getCookie("employee_id") == "") {

    }
    else {

        //AJAX - Asynchronous JavaScript and XML
        //Initialize xhr object
        let xhr = new XMLHttpRequest();
        //console.log(getCookie("employee_id"))
        const url = "/messages/" + getCookie("employee_id");
        //sets up ready state handler
        xhr.onreadystatechange = function () {
            console.log(xhr.readyState);
            switch (xhr.readyState) {

                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:

                    if (xhr.status === 200) {
                        let messages = JSON.parse(xhr.responseText);
                        console.log(xhr.responseText);

                        for (message of messages) {
                            populateHome(message);
                        }



                    }
                    break;

            }
        }


        //opens up the request
        xhr.open("GET", url, true);
        //sends request
        xhr.send();


    }
}


let populateHome = function () {

    let table = document.createElement("table");
    let td_message = document.createElement("div");
    let messageHeader = document.createElement("h3");
    messageHeader.innerHTML = message.subject;
    let messageDescription = document.createElement("p");
    messageDescription.innerHTML = "Case ID: " + message.caseId + "- " + message.description;
    let messageReciever = document.createElement("p");
    messageReciever.innerHTML = "To: " + message.recieverName;

    let messageSender = document.createElement("p");
    messageSender.innerHTML = "From: " + message.name;

    let messageBody = document.createElement("div");
    messageBody.innerHTML = message.body;
    messageBody.style.width = "400px";
    messageBody.style.margin = "auto";
    let messageForm = document.createElement("form");
    let messageReplyHeader = document.createElement("p");
    messageForm.method = "POST";
    if (getCookie("employee_id") == message.sender) {
        messageForm.action = "/message/" + message.caseId + "/" + getCookie("employee_id") + "/" + message.reciever;

    } else {
        messageForm.action = "/message/" + message.caseId + "/" + getCookie("employee_id") + "/" + message.sender;
    }
    let messageSubjectInput = document.createElement("input");
    messageSubjectInput.placeholder = "Subject";
    messageSubjectInput.type = "text";
    messageSubjectInput.name = "subject";
    messageSubjectInput.required = "true";
    let messageBodyInput = document.createElement("textarea");
    messageBodyInput.name = "body";
    messageBodyInput.placeholder = "Enter Message Here"
    messageBodyInput.style.height = "200px";
    messageBodyInput.style.display = "block";
    let messageButton = document.createElement("button");
    messageButton.style.display = "block";
    messageButton.innerHTML = "Reply";
    messageButton.className = "submit";
    messageButton.type = "submit";
    let deleteForm = document.createElement("form");
    deleteForm.action = "/message/" + message.messageId;
    deleteForm.method = "POST";
    let deleteButton = document.createElement("button");
    deleteForm.className = "deleteButton";
    deleteButton.type = "submit";
    deleteButton.innerHTML = "Delete";
    deleteForm.appendChild(deleteButton);


    messageForm.appendChild(messageSubjectInput);
    messageForm.appendChild(messageBodyInput);
    messageForm.appendChild(messageButton);
    td_message.appendChild(messageHeader);
    td_message.appendChild(messageReciever);
    td_message.appendChild(messageSender);
    td_message.appendChild(messageDescription);
    td_message.appendChild(messageBody);
    td_message.appendChild(messageForm);
    td_message.appendChild(deleteForm);
    table.appendChild(td_message);
    table.className = "main";

    document.querySelector("body").appendChild(table);

}