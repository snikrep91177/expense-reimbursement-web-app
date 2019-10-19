window.onload = function() {
	disableLinks();
	
	getUser();
	
	document.getElementById('logout-link').addEventListener('click',
			logout);

	document.getElementById('submitrequest').addEventListener('click',
			submitRequest);

	document.getElementById('viewreimbbtn')
			.addEventListener('click', viewReimb);
	
	$('#submitreimbmodal').on('hidden.bs.modal', clearForm);
	
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
	var managerLink = document.getElementById("manager-link");
	managerLink.classList.add("disabled");
}

function clearForm(e){
	document.getElementById("reimb-form").reset();
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

function submitRequest() {

	$('#submitreimbmodal').modal({
		  keyboard: false
		})

	let reimbtype = document.getElementById('reimbtype').value;
	let amount = document.getElementById('amount').value;
	let description = document.getElementById('description').value;

	var reimb = {
		reimbType : reimbtype,
		amount : amount,
		description : description
	};
	console.log(reimb);

	$('#submitreimbmodal').modal('hide');

	// STEP 1: create the XMLHttpRequest object
	let xhttp = new XMLHttpRequest();

	// STEP 2: create the callback function for readystate changes
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			
			// this parse function turns a JSON into a javascript
			// object
			let success = JSON.parse(xhttp.responseText);

			console.log(success);

			if (success) {
				document.getElementById('headertext').innerHTML = "Success!!!";
				document.getElementById('toastbody').innerHTML = "Reimbursement was submitted";
				$('#mytoast').toast('show');
				// let t = document.getElementById('mytoast');
				// t.className = "show";
			}
		}
	}

	// STEP 3: create and open a connection
	// xhttp.open(http method, url)
	xhttp.open("POST", '/Project1/submitReimb.json');

	// STEP 4: send the request
	xhttp.send(JSON.stringify(reimb));

}

function viewReimb() {
	
	lj("viewReimb() called");
	let xhttp = new XMLHttpRequest();

	// create the callback function for readystate changes
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {

			let reimbs = [];
			reimbs = JSON.parse(xhttp.responseText);
			
			if (reimbs != null) {
				// format date field within reimb object
				for(let obj of reimbs){
					obj.submitted=new Date(obj.submitted).toDateString();
					if(obj.resolved != null){
						obj.resolved=new Date(obj.resolved).toDateString();
					}
					obj.amount=(obj.amount).toFixed(2);
					if(obj.resolver == 0){
						obj.resolver = "not assigned"
					}
				}

				// Build an array containing Customer records.
				let headers = new Array();
				headers=[ "Amount", "Submit Date", "Resolve Date",
						"Description", "Manager", "Status", "Type" ];
				
				// Create a HTML Table element.
				let table = document.createElement("TABLE");
				table.className="table";

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

				let tablediv = document.getElementById("tablediv");
				tablediv.innerHTML = "";
				tablediv.appendChild(table);

				$('#viewreimbmodal').modal('handleUpdate');
				$('#viewreimbmodal').modal('show');

			}
		}
	}

	// create and open a connection
	xhttp.open("POST", '/Project1/retrieveReimb.json');

	// STEP 4: send the request
	xhttp.send();

}

function lj(message) {
	d = new Date();
	return (d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + "-" + message);

}
