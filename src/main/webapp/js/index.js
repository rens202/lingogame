let dropdown = document.getElementById("wordlists");
let playingList;
let playingListName;



document.getElementById("selectWordList").onclick = function () {
	playingList = dropdown.options[dropdown.selectedIndex].value;
	playingListName = dropdown.options[dropdown.selectedIndex].text;
	
	sessionStorage.setItem('playingList', playingList);
	sessionStorage.setItem('playingListName', playingListName);
	
	location.href = "game.html";
    };
    
    function getWordLists(){
    	let fetchoptions = {
                method: 'GET',
                headers: {
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
    
getWordLists();