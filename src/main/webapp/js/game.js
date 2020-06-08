let playingList;
let playingListName;


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
                        	let word = data[randomWordId].name;
                            let wordId = data[randomWordId].id;
                            
                            
                            
                            console.log(word);
                        	
                        	
                        }
                        
                       
                       
                    });
                    return;               	
                }
                }).catch(error => console.error(error));
	
}

function startGame(){
	playingList  = sessionStorage.getItem('playingList');
	playingListName = sessionStorage.getItem('playingListName');
	document.getElementById("title").append(" " + playingListName);
	
	getWordsFromList(playingList);
	
}

startGame();







