
document.querySelector("#loginBtn").addEventListener("click", login);

function login() {
    let formData = new FormData(document.querySelector("#loginForm"));
 
        let object = {};
        formData.forEach(function (value, key) {
            object[key] = value;
        });
        let json = JSON.stringify(object);
        let fetchoptions = {
            method: 'POST',
            body: json,
            headers: {
                "Content-Type": "application/json"
            }
        };

        fetch("restservices/authentication", fetchoptions)
            .then(function (response) {
                if (response.ok) {
                    return response.json();
                }
                else {
                    document.getElementById("warning").innerHTML = "Wrong username or password";
                    throw ("Wrong username/password");
                }
            })
            .then(myJson => window.sessionStorage.setItem("sessionToken", myJson.JWT))
            .then(function () {
                window.sessionStorage.setItem("username", formData.get("username").toString());
                document.location.href = "startGame.html"
                
            }).catch(error => console.error(error));
    }
   
document.querySelector('#register').addEventListener("click", function () {
    window.location.href = "registerpage.html";
});


