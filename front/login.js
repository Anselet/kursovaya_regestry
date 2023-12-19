// document.addEventListener('DOMContentLoaded', ()=>{
//    // window.localStorage.removeItem("user")
// }, false);

async function onSubmit(){
    const log = document.getElementById("username").value;
    const pass = document.getElementById("pass").value;
    console.log(log+" "+pass);

    let user = {
        password: pass,
        login: log
    };

    let url = 'http://localhost:8080/employees/login';
    let response = await fetch(url, {
        method: 'POST',
        headers: {
                  'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify(user)
    }).then((response) => {
        return response.json();
      })
      .then((data) => {
        console.log(data);
        if(data){
            fetch('http://localhost:8080/employees/getLogin?login='+log)
            .then((response) => {
                return response.json();
            })
            .then((user) => {   
                console.log(user);
                window.localStorage.setItem("user", JSON.stringify(user))
                window.open("./main.html","_self")
            })
        }
        else alert("Неверный логин или пароль!")
      });

     ; 
    
}


