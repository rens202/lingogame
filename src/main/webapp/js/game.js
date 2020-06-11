let turns;
let sessionTurns;
let wordlength;
let disabled;

function getTurn(){
	let url = "restservices/games";
	let fetchoptions = {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken"),
            }
        };
        fetch(url, fetchoptions)
            .then(function (response) {
                if (response.ok) {
                	response.json().then(function (data) {
                			turns = data.turn;
                			wordlength = data.wordlength;
                        	createGamePage();
                        
                    });
                    return;               	
                }else{
                	location.href = "index.html";
                }
                }).catch(error => console.error(error)
                		);
	
}

function startGame(){
	disabled = false;
	document.getElementById("submitGuess").disabled = false;
	console.log("startgame");
	sessionTurns = 0;
	document.getElementById("title").append(" " + "");
	getTurn();
		
}

function createGamePage(){
	document.getElementById("count").innerHTML = "Turn: " + turns;
	for (var i = 0; i < wordlength; i++) {
		var input = document.createElement('input');
		input.setAttribute('id', 'character-' + i + '-turn'+ turns);
		input.setAttribute('class', "inputcharacter");
		input.setAttribute('maxlength', "1");
		
		if(sessionTurns != 0){
			if(document.getElementById('character-' + i + '-turn'+ (turns - 1)).getAttribute('correct')){
				input.value = document.getElementById('character-' + i + '-turn'+ (turns - 1)).value;
				input.setAttribute('correct', true);
				input.style.backgroundColor = "green";
				input.disabled = true;
			}
		}
		document.getElementById("word").appendChild(input);
	}
	var br = document.createElement('br');
	document.getElementById("word").appendChild(br);
}

document.getElementById("newGame").onclick = function newGame(){
	location.href = "startGame.html";
}

document.getElementById("submitGuess").onclick = function submitGuess(){
	if(!disabled){
		if(checkEmptyInput()){ // Validate if all fields are not empty
			getInput();
	}}
}

function getScores(){
	
	let url = "restservices/scores" ;
	let fetchoptions = {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
            }
        };
        fetch(url, fetchoptions)
            .then(function (response) {
                if (response.ok) {
                	response.json().then(function (data) {
                        let res = "<table><tr><th>Name</th><th>Turns</th></tr>";
                		for (let i = 0; i < data.length; i++) {
                			res += '<tr><td>' + data[i].username + '</td><td>' + data[i].turns + '</td></tr>';
                            
                		}
                		res += "</table>"
                		document.getElementById('scores').innerHTML = res;
                        
                    });
                    return;               	
                }else{
                	location.href = "index.html";
                }
                }).catch(error => console.error(error));
	
}

function checkEmptyInput(){
	let result = true;
	for (var i = 0; i < wordlength; i++) {
		if(result && !document.getElementById('character-' + i + '-turn' + turns).value){ // todo
			result = false;
		}
		
	}
	return result;
}

function getInput(){
	let result = false
	var jsonobj = '{"input": ['
	for (var i = 0; i < wordlength; i++) {
		guess = document.getElementById("character-" + i + '-turn'+ turns).value;
		if(i != 0){
			jsonobj += ','
		}
		jsonobj += '{"index": ' + i + ', "input": "' + guess + '"}'
	}
	jsonobj += ']}'
	let fetchoptions = {
            method: 'POST',
            headers: {
            	'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken"),
                "Content-Type": "application/json",
            },
            body: jsonobj,
        };
        fetch("restservices/games", fetchoptions)
            .then(function (response) {
                if (response.ok) {
                	response.json().then(function (data) {       
                		if(data.status != 2){
                			wordlength = data.wordlength;
                			for (var i = 0; i < wordlength; i++) {
                				inputField = document.getElementById("character-" + i +'-turn'+ turns);
                				if(data.results[i].status == 2){
                					inputField.style.backgroundColor = "green";
                					inputField.disabled = true;  
                					inputField.setAttribute('correct', true);                				
                					}
                				else if (data.results[i].status == 1){
                					inputField.style.backgroundColor = "orange";
                					inputField.disabled = true;
                					}
                				else{
                					inputField.style.backgroundColor = "red";
                					inputField.disabled = true;
                					}               			
                			}
                		turns = data.turn;
                		sessionTurns++;
                		createGamePage();
                }else{
                	result = true;
                	for (var i = 0; i < wordlength; i++) {
        				inputField = document.getElementById("character-" + i +'-turn'+ turns);
        				inputField.style.backgroundColor = "green";
    					inputField.disabled = true;  }
                	document.getElementById("submitGuess").disabled = true;
                	disabled = true;
                	alert("winner winner chicken dinner");
                }}
                ).catch(error => console.error(error));
}})

return result;
        }




startGame();
getScores();






