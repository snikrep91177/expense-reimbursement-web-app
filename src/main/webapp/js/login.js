window.onload = function() {
	disableLinks();
}

function disableLinks(){
	var employeeLink = document.getElementById("employee-link");
	employeeLink.classList.add("disabled");
	var managerLink = document.getElementById("manager-link");
	managerLink.classList.add("disabled");
}