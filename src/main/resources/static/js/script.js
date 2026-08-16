const form = document.querySelector(".form-gm");
const input = document.querySelector("#input-name");
const mainDiv = document.querySelector(".main-div");

async function postGM(data) {

    const response = await fetch("http://localhost:8080/grade-manager", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });


    const json = await response.json();
    const obj = document.createElement("div");

    obj.textContent = json.name;

    mainDiv.appendChild(obj);
}

form.addEventListener("submit", function (e){

    e.preventDefault();
    const data = {nome: input.value} ;
    postGM(data);

})

