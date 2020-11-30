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
    console.log("Cookie is" + getCookie("employee_id"))
    const url = "/approve/" + getCookie("employee_id");
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
    td_case_id.innerHTML = "Case ID: " + reimbursement.case_id;
    tr_case_id.appendChild(td_case_id);
    table.appendChild(tr_case_id);

    let tr_employee_name = document.createElement("div");
    let td_employee_name = document.createElement("div");
    td_employee_name.innerHTML = "Name: " + reimbursement.full_name;
    tr_employee_name.appendChild(td_employee_name);
    table.appendChild(tr_employee_name);

    let tr_employee_id = document.createElement("div");
    let td_employee_id = document.createElement("div");
    td_employee_id.innerHTML = "Employee ID: " + reimbursement.employee_id;
    tr_employee_id.appendChild(td_employee_id);
    table.appendChild(tr_employee_id);



    let tr_date = document.createElement("div");
    let td_date = document.createElement("div");
    td_date.innerHTML = "Training Date: " + reimbursement.training_date;
    tr_date.appendChild(td_date);
    table.appendChild(tr_date);





    let tr_time = document.createElement("div");
    let td_time = document.createElement("div");
    td_time.innerHTML = "Training Time: " + reimbursement.training_time;
    tr_time.appendChild(td_time);
    table.appendChild(tr_time);




    let tr_zipcode = document.createElement("div");
    let td_zipcode = document.createElement("div");
    td_zipcode.innerHTML = "Training Zipcode: " + reimbursement.training_zipcode;
    tr_zipcode.appendChild(td_zipcode);
    table.appendChild(tr_zipcode);



    let tr_address = document.createElement("div");
    let td_address = document.createElement("div");
    td_address.innerHTML = "Training Address : " + reimbursement.training_address;
    tr_address.appendChild(td_address);
    table.appendChild(tr_address);



    let tr_state = document.createElement("div");
    let td_state = document.createElement("div");
    td_state.innerHTML = "Training State: " + reimbursement.training_state;
    tr_state.appendChild(td_state);
    table.appendChild(tr_state);



    let tr_cost = document.createElement("div");
    let td_cost = document.createElement("div");
    td_cost.innerHTML = "Training Cost: " + reimbursement.training_cost;
    tr_cost.appendChild(td_cost);
    table.appendChild(tr_cost);




    let tr_priority = document.createElement("div");
    let td_priority = document.createElement("div");
    td_priority.innerHTML = "Priority: " + reimbursement.priority;
    tr_priority.appendChild(td_priority);
    table.appendChild(tr_priority);



    let tr_sup_approval = document.createElement("div");
    let td_sup_approval = document.createElement("div");
    td_sup_approval.innerHTML = "Direct Supervisor Decision: " + reimbursement.directSupervisorApproval;
    tr_sup_approval.appendChild(td_sup_approval);
    table.appendChild(tr_sup_approval);


    let tr_head_approval = document.createElement("div");
    let td_head_approval = document.createElement("div");
    td_head_approval.innerHTML = "Department Head Approval " + reimbursement.deptHeadApproval;
    tr_head_approval.appendChild(td_head_approval);
    table.appendChild(tr_head_approval);




    let tr_benco_approval = document.createElement("div");
    let td_benco_approval = document.createElement("div");
    td_benco_approval.innerHTML = "Benefit Coordinator Decision: " + reimbursement.benCoApproval;
    tr_benco_approval.appendChild(td_benco_approval);
    table.appendChild(tr_benco_approval);



    let tr_coverage = document.createElement("div");
    let td_coverage = document.createElement("div");
    td_coverage.innerHTML = "Coverage: " + reimbursement.coverage;
    tr_coverage.appendChild(td_coverage);
    table.appendChild(tr_coverage);


    let tr_type = document.createElement("div");
    let td_type = document.createElement("div");
    td_type.innerHTML = "Training Type: " + reimbursement.training_type;
    tr_type.appendChild(td_type);
    table.appendChild(tr_address);





    let tr_grade = document.createElement("div");
    let td_grade = document.createElement("div");
    td_grade.innerHTML = "Training Grade: " + reimbursement.training_grade;
    tr_grade.appendChild(td_grade);
    table.appendChild(tr_grade);



    let tr_justification = document.createElement("div");
    let td_justification = document.createElement("div");
    td_justification.innerHTML = "Justification: " + reimbursement.justification;
    tr_justification.appendChild(td_justification);
    table.appendChild(tr_justification);





    let tr_hours_missed = document.createElement("div");
    let td_hours_missed = document.createElement("div");
    td_hours_missed.innerHTML = "Hours Missed: " + reimbursement.hours_missed;
    tr_hours_missed.appendChild(td_hours_missed);
    table.appendChild(tr_hours_missed);






    let tr_employee_approval = document.createElement("div");
    let td_employee_approval = document.createElement("div");
    td_employee_approval.innerHTML = "Employee Decision: " + reimbursement.employee_approval;
    tr_employee_approval.appendChild(td_employee_approval);
    table.appendChild(tr_employee_approval);




    let tr_final_approval = document.createElement("div");
    let td_final_approval = document.createElement("div");
    td_final_approval.innerHTML = "Final Decision: " + reimbursement.final_approval;
    tr_final_approval.appendChild(td_final_approval);
    table.appendChild(tr_final_approval);



    let tr_date_of_request = document.createElement("div");
    let td_date_of_request = document.createElement("div");
    td_date_of_request.innerHTML = "Date of Request" + reimbursement.date_of_request;
    tr_date_of_request.appendChild(td_date_of_request);
    table.appendChild(tr_date_of_request);


    let tr_description = document.createElement("div");
    let td_description = document.createElement("div");
    td_description.innerHTML = "Description: " + reimbursement.description;
    tr_description.appendChild(td_description);
    table.appendChild(tr_description);

    let editDiv = document.createElement("div");
    let editLink = document.createElement("a");
    editLink.href = "/case-approver"
    editLink.addEventListener("click", function () {
        document.cookie = "case_id=" + reimbursement.case_id;
    })
    editLink.innerHTML = "Details";
    editDiv.appendChild(editLink);
    table.appendChild(editDiv);




    document.querySelector("body").appendChild(table);
    let brk = document.createElement("br");
    document.querySelector("body").appendChild(brk);
    document.querySelector("body").appendChild(brk);










}