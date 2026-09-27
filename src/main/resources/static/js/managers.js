const grid = document.getElementById("grid-cards");

// --------------------- Modal Config --------------------

const btnOpenModal = document.getElementById("modal-open")
const modal = document.getElementById("modal");
const btnCreate = document.getElementById("btn-create");
const btnCancel = document.getElementById("modal-cancel");
const btnCloseModal = document.getElementById("modal-close");

btnCloseModal.addEventListener("click", () => {
    modal.classList.add("hidden");
})

btnOpenModal.addEventListener("click", () => {
    modal.classList.remove("hidden");
});

btnCreate.addEventListener("click", () => {
    modal.classList.remove("hidden");
});

btnCancel.addEventListener("click", () => {
    modal.classList.add("hidden");
});

// -----------------------------------------------------

// --------------------- POST Grade Manager --------------------

const formGM = document.getElementById("modal-form");
const inputNameGM = document.getElementById("modal-input");

formGM.addEventListener("submit", async (e) =>{

    e.preventDefault();

    const response = await fetch(`http://localhost:8080/grade-manager`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            name: inputNameGM.value.trim()
        })});

    if(response.ok) window.location.reload();

});

// -------------------------------------------------------------

async function gerarCards(){

    const response = await fetch("http://localhost:8080/grade-manager");
    const json = await response.json();

    for (const obj of json){

        const card = document.createElement("article");
        card.classList.add("card", "item-card")
        card.setAttribute("data-grade-id", obj.id)

        card.innerHTML = `
            <div class="item-head">
                <div class="icon">W</div>
                <div class="item-main">
                    <h2>${obj.name}</h2>
                </div>
            </div>
            <div class="meta">
                <span class="badge">${obj.semesterQuantity} semestre${obj.semesterQuantity > 1 ? "s":""}</span>
            </div>
            <div class="card-footer">
                <a class="btn" href="semesters.html?id=${obj.id}&name=${obj.name}">Ver semestres</a>
                <button class="btn">Editar</button>
                <button class="btn danger">Excluir</button>
            </div>
        `
        grid.appendChild(card);
    }
}



gerarCards();