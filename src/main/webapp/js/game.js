let playingList;
let playingWord;
let playingWordId;
let playingWordLength;
let wordToGuess;
let guessedWord;
let turns;


function getWordsFromList(wordListId){
	let url = "restservices/words/" + wordListId;
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
                        let length = data.length;
                        if(length > 1){
                        	let randomWordId = getRandomInt(length);
                        	playingWord = data[randomWordId].word;
                        	playingWordId = data[randomWordId].id;
                        	playingWordLength = playingWord.length;
                        	wordToGuess = playingWord;
                        	createGamePage();
                        }
                    });
                    return;               	
                }
                }).catch(error => console.error(error));
	
}

function startGame(){
	playingList  = sessionStorage.getItem('playingList');
	document.getElementById("title").append(" " + sessionStorage.getItem('playingListName'));
	turns = 0;
	getWordsFromList(playingList);
}

function getRandomInt(max) {
	  return Math.floor(Math.random() * Math.floor(max));
}

function createGamePage(){
	turns++;
	document.getElementById("count").innerHTML = turns;
	for (var i = 0; i < playingWordLength; i++) {
		var input = document.createElement('input');
		input.setAttribute('id', 'character-' + i + '-turn'+ turns);
		input.setAttribute('class', "inputcharacter");
		input.setAttribute('maxlength', "1");
		
		if(turns != 1){
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

document.getElementById("submitGuess").onclick = function submitGuess(){
	if(checkEmptyInput()){ // Validate if all fields are not empty
		if(checkInput()){
			getScoreAndReset();
		}else{
			createGamePage();
		}		
	}
}

function getScores(){
	
	let url = "restservices/scores/" + playingList;
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
                }
                }).catch(error => console.error(error));
	
}

function getScoreAndReset(){
	var name = prompt("Please enter your name:", "username");
	if(name || name !== ""){
		insertScore(name);
	}
}

function insertScore(playerName){

	let json = '{'
		+'"name": "' + playerName + '",'
		+'"wordlist": ' + playingList + ','
		+'"turns": ' + turns + '}'
	
	if(playerName && playingList && turns > 0){
		let url = "restservices/scores";
		let fetchoptions = {
	            method: 'POST',
	            headers: {
	                "Content-Type": "application/json",
	            },
	            body: json
	        };
	        fetch(url, fetchoptions)
	            .then(function (response) {
	                if (response.ok) {
	                	alert("Succesfully added score");
	                	}
	                })
	              }else{
	            	  alert("Something went wrong, please try again");
	            	  }
	
	
	}

function checkEmptyInput(){
	let result = true;
	let word = ""
	for (var i = 0; i < playingWordLength; i++) {
		if(result && !document.getElementById('character-' + i + '-turn' + turns).value){ //todo regex for bullshit
			result = false;
		}else{
			word += document.getElementById('character-' + i + '-turn' + turns).value;
		}
		
	}
	guessedWord = word;
	return result;
}

function checkInput(){
	let result = false;
	let guess = "";
	let inputField;
	for (var i = 0; i < playingWordLength; i++) {
		
		guess = document.getElementById("character-" + i + '-turn'+ turns).value;
		inputField = document.getElementById("character-" + i +'-turn'+ turns);
		
		if(guess == playingWord[i]){
			inputField.style.backgroundColor = "green";
			inputField.disabled = true;
			const regEx = new RegExp(guess, "g") //Variable so we can use our own variable in the regex. otherwise it would take variable name as string input
			wordToGuess = wordToGuess.replace(regEx , '');
			inputField.setAttribute('correct', true);

		}else if((wordToGuess.includes(guess)) && (occurrences(wordToGuess, guess) >= occurrences(guessedWord, guess))){
			inputField.style.backgroundColor = "orange";
			inputField.disabled = true;
			
		}else{
			inputField.style.backgroundColor = "red";
			inputField.disabled = true;
		}
		
		if(!wordToGuess){
			result = true;
		} 
	}
	return result;
}

function occurrences(string, subString) {

    string += "";
    subString += "";
    if (subString.length <= 0) return (string.length + 1);

    var n = 0,
        pos = 0,
        step = 1;

    while (true) {
        pos = string.indexOf(subString, pos);
        if (pos >= 0) {
            ++n;
            pos += step;
        } else break;
    }
    return n;
}

startGame();
getScores();






