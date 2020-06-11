let dropdown = document.getElementById("wordlists");

document.getElementById("selectWordList").onclick = function () {
	let playingList = dropdown.options[dropdown.selectedIndex].value;
	startGame(playingList);

    };
    
    function getWordLists(){
    	let fetchoptions = {
                method: 'GET',
                headers: {
                	'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken"),
                    "Content-Type": "application/json",
                }
            };
            fetch("restservices/words", fetchoptions)
                .then(function (response) {
                    if (response.ok) {
                    	response.json().then(function (data) {
                            let option;
                            for (let i = 0; i < data.length; i++) {
                                option = document.createElement('option');
                                option.text = data[i].name + " | " + data[i].language.code;
                                option.value = data[i].id;
                                dropdown.add(option);
                            }
                        });
                        return;               	
                    }
                    }).catch(error => console.error(error));
    	
    }
    
    function startGame(playingList){
    	let fetchoptions = {
                method: 'POST',
                headers: {
                	'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken"),
                    "Content-Type": "application/json",
                }
            };
            fetch("restservices/games/"+playingList, fetchoptions)
                .then(function (response) {
                    if (response.ok) {
                    		location.href = "game.html";
                    }else{
                    	location.href = "index.html";
                    }
                    }).catch(error => console.error(error));
    	
    }
    
function parseJwt(token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
    };

function isValid() {
	let result = false;
        var jwt = window.sessionStorage.getItem("sessionToken");
        if (jwt != null) {
            var decodedJWT = parseJwt(jwt);
            result = checkExpirationDate(decodedJWT.exp);      
        }
        return result;
    }
  
function init(){
	let result = isValid();
	if(result){
		getWordLists();
		}else{
			location.href = "index.html";
		}
}

function checkExpirationDate(exp){
	let result = false;
	var date = new Date(exp * 1000);
	var now = new Date(Date.now());
    if(now.getTime() < date.getTime()){
    	console.log("Token is time valid");
    	result = true;
    }else{
        console.log("Token is NOT time valid");
        console.log(now.getTime());
        console.log(date.getTime());
    	
    }
    return result;
}

init();
