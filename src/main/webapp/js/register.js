//send the user data to the 
function register() {
    let formData = new FormData(document.querySelector("#regform"));

    let username = formData.get("username").toString();
    let password = formData.get("password").toString();



                let object = {};
                formData.forEach(function (value, key) {
                    object[key] = value;
                });
                let json = JSON.stringify(object);
                let fetchoptions = {
                    method: 'POST',
                    body: json,
                    headers: {
                        "Content-Type": "application/json",
                    }
                };
                fetch("restservices/authentication/register", fetchoptions)
                    .then(function (response) {
                        if (response.ok) {
                            alert("New user registered!");
                            document.location.href = "index.html";
                        } else if (response.status === 401) {
                            alert("Username is already in use!");
                            throw "Username is already in use!";
                        } else if (response.status === 422) {
                            alert("Missing a required field!");
                            throw "Missing a required field!";
                        } else {
                            alert("Error with registration");
                            throw "Error with registration";
                        }
                    })
                    .catch(error => console.error(error));
            

}

// execute the register function on click
document.querySelector("#registerBtn").addEventListener("click", register);

