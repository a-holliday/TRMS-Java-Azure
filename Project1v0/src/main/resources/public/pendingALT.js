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

    //AJAX - Asynchronous JavaScript and XML
    //Initialize xhr object
    let xhr = new XMLHttpRequest();
    //console.log(getCookie("employee_id"))
    const url = "/pending/" + getCookie("employee_id");
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
                    console.log(xhr.responseText);
                    let pendingReimbursements = JSON.parse(xhr.responseText);

                    for (reimbursement of pendingReimbursements) {
                        populateHome(reimbursement);
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

let populateHome = function (reimbursement) {
    let table = document.createElement("table");

    let tr_case_id = document.createElement("div")
    let td_case_id = document.createElement("div")
    td_case_id.innerHTML = reimbursement.case_id;
    tr_case_id.appendChild(td_case_id);
    table.appendChild(tr_case_id);

    let tr_employee_id = document.createElement("div");
    let td_employee_id = document.createElement("div");
    td_employee_id.innerHTML = reimbursement.employee_id;
    tr_employee_id.appendChild(td_employee_id);
    table.appendChild(tr_employee_id);



    let tr_date = document.createElement("div");
    let td_date = document.createElement("div");
    td_date.innerHTML = reimbursement.training_date;
    tr_date.appendChild(td_date);
    table.appendChild(tr_date);
    //should be able to change date
    let dateForm = document.createElement("form");
    dateForm.action = "/date/" + reimbursement.case_id;
    dateForm.method = "POST";
    let dateInput = document.createElement("input");
    dateInput.name = "training_date";
    dateInput.type = "date";
    let dateButton = document.createElement("button");
    dateButton.type = "submit";
    dateButton.innerHTML = "Update Training Date";
    dateForm.appendChild(dateInput);
    tr_date.appendChild(dateForm);
    dateForm.appendChild(dateButton);




    let tr_time = document.createElement("div");
    let td_time = document.createElement("div");
    td_time.innerHTML = reimbursement.training_time;
    tr_time.appendChild(td_time);
    table.appendChild(tr_time);
    //should be able to edit time
    let timeForm = document.createElement("form");
    timeForm.action = "/time/" + reimbursement.case_id;
    timeForm.method = "POST";
    let timeInput = document.createElement("input");
    timeInput.type = "time";
    timeInput.name = "training_time";
    let timeButton = document.createElement("button");
    timeButton.type = "submit";
    timeButton.innerHTML = "Update Training Time";
    timeForm.appendChild(timeInput);
    tr_time.appendChild(timeForm);
    tr_time.appendChild(timeButton);



    let tr_zipcode = document.createElement("div");
    let td_zipcode = document.createElement("div");
    td_zipcode.innerHTML = reimbursement.training_zipcode;
    tr_zipcode.appendChild(td_zipcode);
    table.appendChild(tr_zipcode);
    //should be able to edit zipcode
    let zipcodeForm = document.createElement("form");
    zipcodeForm.action = "/zipcode/" + reimbursement.case_id;
    zipcodeForm.method = "POST";
    let zipcodeInput = document.createElement("input");
    zipcodeInput.type = "text";
    zipcodeInput.name = "training_zipcode";
    let zipcodeButton = document.createElement("button");
    zipcodeButton.type = "submit";
    zipcodeButton.innerHTML = "Update Traning  Zipcode";
    zipcodeForm.appendChild(zipcodeInput);
    tr_zipcode.appendChild(zipcodeForm);
    tr_zipcode.appendChild(zipcodeButton);


    let tr_address = document.createElement("div");
    let td_address = document.createElement("div");
    td_address.innerHTML = reimbursement.training_address;
    tr_address.appendChild(td_address);
    table.appendChild(tr_address);

    //should be ablet to edit address
    let addressForm = document.createElement("form");
    addressForm.action = "/address/" + reimbursement.case_id;
    addressForm.method = "POST";
    let addressInput = document.createElement("input");
    addressInput.type = "text";
    addressInput.name = "training_address";
    let addressButton = document.createElement("button");
    addressButton.type = "submit";
    addressButton.innerHTML = "Update  Training Address";
    addressForm.appendChild(addressInput);
    td_address.appendChild(addressForm);
    addressForm.appendChild(addressButton);

    let tr_state = document.createElement("div");
    let td_state = document.createElement("div");
    td_state.innerHTML = reimbursement.training_state;
    tr_state.appendChild(td_state);
    table.appendChild(tr_state);

    //should be able to edit state
    let stateForm = document.createElement("form");
    stateForm.action = "/state/" + reimbursement.case_id;
    stateForm.method = "POST";
    let stateInput = document.createElement("input");
    stateInput.type = "text";
    stateInput.name = "training_state";
    let stateButton = document.createElement("button");
    stateButton.type = "submit";
    stateButton.innerHTML = "Update Training State";
    stateForm.appendChild(stateInput);
    td_state.appendChild(stateForm);
    stateForm.appendChild(stateButton);



    let tr_cost = document.createElement("div");
    let td_cost = document.createElement("div");
    td_cost.innerHTML = reimbursement.training_cost;
    tr_cost.appendChild(td_cost);
    table.appendChild(tr_cost);
    //should be able to edit cost

    let costForm = document.createElement("form");
    costForm.action = "/cost/" + reimbursement.case_id;
    costForm.method = "POST";
    let costInput = document.createElement("input");
    costInput.type = "number";
    costInput.min = "1";
    costInput.max = "1000";
    costInput.step = "0.01";
    costInput.name = "training_cost";
    let costButton = document.createElement("button");
    costButton.type = "submit";
    costButton.innerHTML = "Update Training Cost";
    costForm.appendChild(costInput);
    td_cost.appendChild(costForm);
    costForm.appendChild(costButton);



    let tr_priority = document.createElement("div");
    let td_priority = document.createElement("div");
    td_priority.innerHTML = reimbursement.priority;
    tr_priority.appendChild(td_priority);
    table.appendChild(tr_priority);



    let tr_sup_approval = document.createElement("div");
    let td_sup_approval = document.createElement("div");
    td_sup_approval.innerHTML = reimbursement.directSupervisorApproval;
    tr_sup_approval.appendChild(td_sup_approval);
    table.appendChild(tr_sup_approval);


    let tr_head_approval = document.createElement("div");
    let td_head_approval = document.createElement("div");
    td_head_approval.innerHTML = reimbursement.deptHeadApproval;
    tr_head_approval.appendChild(td_head_approval);
    table.appendChild(tr_head_approval);




    let tr_benco_approval = document.createElement("div");
    let td_benco_approval = document.createElement("div");
    td_benco_approval.innerHTML = reimbursement.benCoApproval;
    tr_benco_approval.appendChild(td_benco_approval);
    table.appendChild(tr_benco_approval);



    let tr_coverage = document.createElement("div");
    let td_coverage = document.createElement("div");
    td_coverage.innerHTML = reimbursement.coverage;
    tr_coverage.appendChild(td_coverage);
    table.appendChild(tr_coverage);


    let tr_type = document.createElement("div");
    let td_type = document.createElement("div");
    td_type.innerHTML = reimbursement.training_type;
    tr_type.appendChild(td_type);
    table.appendChild(tr_address);
    //Should be able to edit training type
    //Needs select type

    let typeForm = document.createElement("form");
    typeForm.action = "/type/" + reimbursement.case_id;
    typeForm.method = "POST";
    let typeSelect = document.createElement("select");
    typeSelect.name = "training_type";
    let seminarOption = document.createElement("option");
    seminarOption.value = "SEMINAR";
    seminarOption.innerHTML = "Seminar";
    let certificationOption = document.createElement("option");
    certificationOption.value = "CERTIFICATION";
    certificationOption.innerHTML = "Certification";
    let trainingOption = document.createElement("option");
    trainingOption.value = "TRAINING";
    trainingOption.innerHTML = "Training";
    let courseOption = document.createElement("option");
    courseOption.value = "COURSE";
    courseOption.innerHTML = "University Course";
    let certPrepOption = document.createElement("option");
    certPrepOption.value = "CERT_PREP";
    certPrepOption.innerHTML = "Certification Preparation";
    let otherOption = document.createElement("option");
    otherOption.value = "SEMINAR";
    otherOption.innerHTML = "SEMINAR";
    typeSelect.appendChild(seminarOption);
    typeSelect.appendChild(certificationOption);
    typeSelect.appendChild(trainingOption);
    typeSelect.appendChild(courseOption);
    typeSelect.appendChild(certPrepOption);
    typeSelect.appendChild(seminarOption);
    typeSelect.appendChild(otherOption);
    typeForm.appendChild(typeSelect);
    td_type.appendChild(typeForm);
    let typeButton = document.createElement("button");
    typeButton.type = "submit";
    td_type.appendChild(typeForm);
    typeForm.appendChild(typeButton);





    let tr_grade = document.createElement("div");
    let td_grade = document.createElement("div");
    td_grade.innerHTML = reimbursement.training_grade;
    tr_grade.appendChild(td_grade);
    table.appendChild(tr_grade);
    //Should be able to edit grade
    //Needs to be select type
    let gradeForm = document.createElement("form");
    gradeForm.method = "POST";
    gradeForm.action = "/grade/" + reimbursement.case_id;
    let gradeSelect = document.createElement("select");
    gradeSelect.name = "training_grade";
    let aOption = document.createElement("option");
    aOption.value = "A";
    aOption.innerHTML = "A";
    let bOption = document.createElement("option");
    bOption.value = "B";
    bOption.innerHTML = "B";
    let cOption = document.createElement("option");
    cOption.value = "C";
    cOption.innerHTML = "C";
    let dOption = document.createElement("option");
    dOption.value = "D";
    dOption.innerHTML = "D";
    let fOption = document.createElement("option");
    fOption.value = "F";
    fOption.innerHTML = "F";
    let passingOption = document.createElement("option");
    passingOption.value = "PASSING";
    passingOption.innerHTML = "PASSING";
    let failedOption = document.createElement("option");
    failedOption.value = "FAILED";
    failedOption.innerHTML = "FAILED";
    let pendingOption = document.createElement("option");
    pendingOption.value = "PENDING";
    pendingOption.innerHTML = "PENDING";
    gradeSelect.appendChild(aOption);
    gradeSelect.appendChild(bOption);
    gradeSelect.appendChild(cOption);
    gradeSelect.appendChild(dOption);
    gradeSelect.appendChild(fOption);
    gradeSelect.appendChild(passingOption);
    gradeSelect.appendChild(failedOption);
    gradeSelect.appendChild(pendingOption);
    gradeForm.appendChild(gradeSelect);
    td_grade.appendChild(gradeForm);
    let gradeButton = document.createElement("button");
    gradeButton.type = "submit";
    gradeButton.innerHTML = "Update Grade";
    td_grade.appendChild(gradeForm);
    gradeForm.appendChild(gradeButton);



    let tr_justification = document.createElement("div");
    let td_justification = document.createElement("div");
    td_justification.innerHTML = reimbursement.justification;
    tr_justification.appendChild(td_justification);
    table.appendChild(tr_justification);

    //Should be able to edit justification
    let justificationForm = document.createElement("form");
    justificationForm.action = "/justification/" + reimbursement.case_id;
    justificationForm.method = "POST";
    let justificationInput = document.createElement("input");
    justificationInput.type = "text";
    justificationInput.name = "justification";
    let justificationButton = document.createElement("button");
    justificationButton.type = "submit";
    justificationButton.innerHTML = "Update Justification";
    justificationForm.appendChild(justificationInput);
    td_justification.appendChild(justificationForm);
    justificationForm.appendChild(justificationButton);



    let tr_hours_missed = document.createElement("div");
    let td_hours_missed = document.createElement("div");
    td_hours_missed.innerHTML = reimbursement.hours_missed;
    tr_hours_missed.appendChild(td_hours_missed);
    table.appendChild(tr_hours_missed);

    //Should be able to edit hours missed
    let hoursForm = document.createElement("form");
    hoursForm.action = "/hours/" + reimbursement.case_id;
    hoursForm.method = "POST";
    let hoursInput = document.createElement("input");
    hoursInput.type = "number";
    hoursInput.name = "hours_missed";
    let hoursButton = document.createElement("button");
    hoursButton.type = "submit";
    hoursButton.innerHTML = "Update Hours Missed";
    hoursForm.appendChild(hoursInput);
    td_hours_missed.appendChild(hoursForm);
    hoursForm.appendChild(hoursButton);




    let tr_employee_approval = document.createElement("div");
    let td_employee_approval = document.createElement("div");
    td_employee_approval.innerHTML = reimbursement.employee_approval;
    tr_employee_approval.appendChild(td_employee_approval);
    table.appendChild(tr_employee_approval);

    //Should be able to edit employee approval, //need select
    let approvalForm = document.createElement("form");
    approvalForm.method = "POST";
    approvalForm.action = "/empApproval/" + reimbursement.case_id;
    let approvalSelect = document.createElement("select");
    approvalSelect.name = "employee_approval";
    let approvedOption = document.createElement("option");
    approvedOption.value = "APPROVED";
    approvedOption.innerHTML = "APPROVED";
    let declinedOption = document.createElement("option");
    declinedOption.value = "DECLINED";
    declinedOption.innerHTML = "DECLINED";
    approvalSelect.appendChild(approvedOption);
    approvalSelect.appendChild(declinedOption);
    approvalButton = document.createElement("button");
    approvalButton.type = "submit";
    approvalButton.innerHTML = "Update Employee Approval";
    approvalForm.appendChild(approvalSelect);
    approvalForm.appendChild(approvalButton);
    td_employee_approval.appendChild(approvalForm);



    let tr_final_approval = document.createElement("div");
    let td_final_approval = document.createElement("div");
    td_final_approval.innerHTML = reimbursement.final_approval;
    tr_final_approval.appendChild(td_final_approval);
    table.appendChild(tr_final_approval);



    let tr_date_of_request = document.createElement("div");
    let td_date_of_request = document.createElement("div");
    td_date_of_request.innerHTML = reimbursement.date_of_request;
    tr_date_of_request.appendChild(td_date_of_request);
    table.appendChild(tr_date_of_request);


    let tr_description = document.createElement("div");
    let td_description = document.createElement("div");
    td_description.innerHTML = reimbursement.description;
    tr_description.appendChild(td_description);
    table.appendChild(tr_description);
    //should be able to edit description

    let descriptionForm = document.createElement("form");
    descriptionForm.action = "/description/" + reimbursement.case_id;
    descriptionForm.method = "POST";
    let descriptionInput = document.createElement("input");
    descriptionInput.type = "text";
    descriptionInput.name = "description";
    let descriptionButton = document.createElement("button");
    descriptionButton.type = "submit";
    descriptionButton.innerHTML = "Update Description";
    descriptionForm.appendChild(descriptionInput);
    td_description.appendChild(descriptionForm);
    descriptionForm.appendChild(descriptionButton);




    document.querySelector("body").appendChild(table);
    let brk = document.createElement("br");
    document.querySelector("body").appendChild(brk);
    document.querySelector("body").appendChild(brk);










}