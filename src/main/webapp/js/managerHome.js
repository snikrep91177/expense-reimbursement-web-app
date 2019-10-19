window.onload = function() {
	disableLinks();
	
	getUser();
	
	document.getElementById('logout-link').addEventListener('click',
			logout);
	
	document.getElementById('pending')
	.addEventListener('click', showPending);
	
	document.getElementById('approved')
	.addEventListener('click', showApproved);
	
	document.getElementById('denied')
	.addEventListener('click', showDenied);

	document.getElementById('viewallreimbbtn')
			.addEventListener('click', viewReimb);
	
	var user;
	
}

function logout(){
	
	// STEP 1: create the XMLHttpRequest object
	let xhttp = new XMLHttpRequest();

	// STEP 2: create the callback function for readystate changes
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			// console.log("state 4!");

			// this parse function turns a JSON into a javascript object
			let sessionDestroyed = JSON.parse(xhttp.responseText);

			if(sessionDestroyed){
				user = null;
				console.log(user);
				window.location.replace("/Project1/html/index.html");
			}
		}
	}

	// STEP 3: create and open a connection
	// xhttp.open(http method, url)
	xhttp.open("POST", '/Project1/logout.json');

	// STEP 4: send the request
	xhttp.send();

}

function disableLinks(){
	var employeeLink = document.getElementById("employee-link");
	employeeLink.classList.add("disabled");
}

function showApproved(){
	
let xhttp = new XMLHttpRequest();
	
	// create the callback function for readystate changes
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {

			
			let reimbs = JSON.parse(xhttp.responseText);
			if (reimbs != null) {
				// format date field within reimb object
				for(let obj of reimbs){
					if(obj.resolved != null){
						obj.resolved=new Date(obj.resolved).toDateString();
					}
					obj.submitted=new Date(obj.submitted).toDateString();
					obj.amount=(obj.amount).toFixed(2);
					if(obj.resolver == 0){
						obj.resolver = "not assigned"
					}
				}

				// Build an array containing Customer records.
				let headers = new Array();
				headers=[ "Employee", "Amount", "Submit Date", "Resolve Date",
						"Description", "Manager", "Status", "Type" ];
				
				// Create a HTML Table element.
				let table = document.createElement("TABLE");
				table.className = "table";
				
				// Get the count of columns.
				let columnCount = headers.length;

				// Add the header row.
				let row = table.insertRow(-1);
				for (let i = 0; i < columnCount; i++) {
					let headerCell = document.createElement("TH");
					headerCell.innerHTML = headers[i];
					row.appendChild(headerCell);
				}

				// Add the data rows.
				for (let i = 0; i < reimbs.length; i++) {
					row = table.insertRow(-1);
					for (let j = 1; j <= columnCount; j++) {
						let cell = row.insertCell(-1);
						cell.innerHTML = Object.values(reimbs[i])[j];
						console.log()
					}
				}
				var tablediv = document.getElementById("tablediv");
				tablediv.innerHTML = "";
				tablediv.appendChild(table);

			}
		}
	}

	// create and open a connection
	xhttp.open("POST", '/Project1/getApprovedReimb.json');

	// STEP 4: send the request
	xhttp.send();
}

function showDenied(){
	
let xhttp = new XMLHttpRequest();
	
	// create the callback function for readystate changes
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {

			
			let reimbs = JSON.parse(xhttp.responseText);
			if (reimbs != null) {
				// format date field within reimb object
				for(let obj of reimbs){
					if(obj.resolved != null){
						obj.resolved=new Date(obj.resolved).toDateString();
					}
					obj.submitted=new Date(obj.submitted).toDateString();
					obj.amount=(obj.amount).toFixed(2);
					if(obj.resolver == 0){
						obj.resolver = "not assigned"
					}
				}

				// Build an array containing Customer records.
				let headers = new Array();
				headers=[ "Employee", "Amount", "Submit Date", "Resolve Date",
						"Description", "Manager", "Status", "Type" ];
				
				// Create a HTML Table element.
				let table = document.createElement("TABLE");
				table.className = "table";
				
				// Get the count of columns.
				let columnCount = headers.length;

				// Add the header row.
				let row = table.insertRow(-1);
				for (let i = 0; i < columnCount; i++) {
					let headerCell = document.createElement("TH");
					headerCell.innerHTML = headers[i];
					row.appendChild(headerCell);
				}

				// Add the data rows.
				for (let i = 0; i < reimbs.length; i++) {
					row = table.insertRow(-1);
					for (let j = 1; j <= columnCount; j++) {
						let cell = row.insertCell(-1);
						cell.innerHTML = Object.values(reimbs[i])[j];
						console.log()
					}
				}
				var tablediv = document.getElementById("tablediv");
				tablediv.innerHTML = "";
				tablediv.appendChild(table);

			}
		}
	}

	// create and open a connection
	xhttp.open("POST", '/Project1/getDeniedReimb.json');

	// STEP 4: send the request
	xhttp.send();
}

function showPending(){
	
let xhttp = new XMLHttpRequest();
	
	// create the callback function for readystate changes
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {

			
			let reimbs = JSON.parse(xhttp.responseText);
			if (reimbs != null) {
				// format date field within reimb object
				for(let obj of reimbs){
					if(obj.resolved != null){
						obj.resolved=new Date(obj.resolved).toDateString();
					}
					obj.submitted=new Date(obj.submitted).toDateString();
					obj.amount=(obj.amount).toFixed(2);
					if(obj.resolver == 0){
						obj.resolver = "not assigned"
					}
				}

				// Build an array containing Customer records.
				let headers = new Array();
				headers=[ "Employee", "Amount", "Submit Date", "Resolve Date",
						"Description", "Manager", "Status", "Type" ];
				
				// Create a HTML Table element.
				let table = document.createElement("TABLE");
				table.className = "table";
				
				// Get the count of columns.
				let columnCount = headers.length;

				// Add the header row.
				let row = table.insertRow(-1);
				for (let i = 0; i < columnCount; i++) {
					let headerCell = document.createElement("TH");
					headerCell.innerHTML = headers[i];
					row.appendChild(headerCell);
				}

				// Add the data rows.
				for (let i = 0; i < reimbs.length; i++) {
					row = table.insertRow(-1);
					for (let j = 1; j <= columnCount; j++) {
						let cell = row.insertCell(-1);
						cell.innerHTML = Object.values(reimbs[i])[j];
						console.log()
					}
				}
				var tablediv = document.getElementById("tablediv");
				tablediv.innerHTML = "";
				tablediv.appendChild(table);

			}
		}
	}

	// create and open a connection
	xhttp.open("POST", '/Project1/getPendingReimb.json');

	// STEP 4: send the request
	xhttp.send();
}
			
			
			




	


function getUser() {

	// STEP 1: create the XMLHttpRequest object
	let xhttp = new XMLHttpRequest();

	// STEP 2: create the callback function for readystate changes
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			// console.log("state 4!");

			// this parse function turns a JSON into a javascript object
			user = JSON.parse(xhttp.responseText);

			setWelcome(user);
		}
	}

	// STEP 3: create and open a connection
	// xhttp.open(http method, url)
	xhttp.open("POST", '/Project1/user.json');

	// STEP 4: send the request
	xhttp.send();

}

function setWelcome(user) {
	document.getElementById('welcomeHeader').innerHTML = "Welcome "
			+ user.first_name + " " + user.last_name;
}

function viewReimb() {
	
	lj("viewReimb() called");
	let xhttp = new XMLHttpRequest();
	
	// create the callback function for readystate changes
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {

			
			let reimbs = JSON.parse(xhttp.responseText);
			if (reimbs != null) {
				// format date field within reimb object
				for(let obj of reimbs){
					if(obj.resolved != null){
						obj.resolved=new Date(obj.resolved).toDateString();
					}
					obj.submitted=new Date(obj.submitted).toDateString();
					obj.amount=(obj.amount).toFixed(2);
					if(obj.resolver == 0){
						obj.resolver = "not assigned"
					}
				}

				// Build an array containing Customer records.
				let headers = new Array();
				headers=[ "Employee", "Amount", "Submit Date", "Resolve Date",
						"Description", "Manager", "Status", "Type" ];
				
				// Create a HTML Table element.
				let table = document.createElement("TABLE");
				table.className = "table";
				table.id = "reimbTable";
				
				// Get the count of columns.
				let columnCount = headers.length;

				// Add the header row.
				let row = table.insertRow(-1);
				for (let i = 0; i < columnCount; i++) {
					let headerCell = document.createElement("TH");
					headerCell.innerHTML = headers[i];
					row.appendChild(headerCell);
				}

				// Add the data rows.
				for (let i = 0; i < reimbs.length; i++) {
					row = table.insertRow(-1);
					for (let j = 1; j <= columnCount; j++) {
						let cell = row.insertCell(-1);
						cell.innerHTML = Object.values(reimbs[i])[j];
						console.log()
					}
					if (reimbs[i].reimb_status == "pending"){
						let cellApprove = row.insertCell(-1);
						cellApprove.innerHTML = "<button type='button' class='btn btn-success btn-md' aria-label='Left Align'" +
								" onClick='updateStatus(2," + reimbs[i].reimb_id + ")'>Approve</button>";
						
						let cellDeny = row.insertCell(-1);
						cellDeny.innerHTML = "<button type='button' class='btn btn-danger btn-md' aria-label='Left Align'" +
								" onClick='updateStatus(3," + reimbs[i].reimb_id + ")'>Deny</button>";
					}
				}
				var tablediv = document.getElementById("tablediv");
				tablediv.innerHTML = "";
				tablediv.appendChild(table);
			}
		}
	}

	// create and open a connection
	xhttp.open("POST", '/Project1/retrieveAllReimb.json');

	// STEP 4: send the request
	xhttp.send();
	
}

function updateStatus(status, id){
	
	var status = {			
			reimbStatus : status,
			reimbId : id
	};
	
	// STEP 1: create the XMLHttpRequest object
	let xhttp = new XMLHttpRequest();

	// STEP 2: create the callback function for readystate changes
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			
				viewReimb();
					
		}
	}


	// STEP 3: create and open a connection
	// xhttp.open(http method, url)
	xhttp.open("POST", '/Project1/updateReimbStatus.json');

	// STEP 4: send the request
	xhttp.send(JSON.stringify(status));
}

function lj(message) {
	d = new Date();
	return (d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + "-" + message);

}
