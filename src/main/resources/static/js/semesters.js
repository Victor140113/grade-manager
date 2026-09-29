const grid = document.getElementById("grid-cards-semester");
const gradeNameTitle = document.querySelector(".eyebrow");

const params = new URLSearchParams(window.location.search);
const gmId = params.get("id");
const gradeName = params.get("name");

gradeNameTitle.textContent = gradeName;


// --------------------- Modal Config --------------------

const btnOpenModal = document.getElementById("modal-open");
const modal = document.getElementById("modal");
const btnCreate = document.getElementById("model-create");
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

// --------------------- POST Semester --------------------

const formSemester = document.getElementById("modal-form");
const inputNameSemester = document.getElementById("modal-input");

formSemester.addEventListener("submit", async (e) =>{

    e.preventDefault();

    const response = await fetch(`http://localhost:8080/grade-manager/${gmId}/semester`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            name: inputNameSemester.value.trim()
        })});

    if(response.ok) window.location.reload();

});

// -------------------------------------------------------------

// ----------------------- DELETE Semester ---------------------

function deleteSemester(){

    grid.addEventListener("click", async (e) =>{

        if(e.target.classList.contains("delete-semester")){

            const semesterId = e.target.dataset.semesterId;
            const response = await fetch(`http://localhost:8080/grade-manager/${gmId}/semester/${semesterId}`, {method: "DELETE"});

            if(response.ok) document.location.reload();
        }
    });
}

// -------------------------------------------------------------


// --------------------------- Cards ---------------------------
async function gerarCards() {

    const response = await fetch(`http://localhost:8080/grade-manager/${gmId}/semester`)
    const json = await response.json();

    let contagem = 1;

    for (const obj of json) {

        const card = document.createElement("article");
        card.classList.add("card", "item-card");

        card.innerHTML = `
                <div class="item-head">
                    <div class="icon">${contagem}</div>
                    <div class="item-main"><h2>${obj.name}</h2>
                        <p>Terceiro período</p>
                    </div>
                </div>
                <div class="meta">
                    <span class="badge cyan">${obj.courseQuantity} matéria${obj.courseQuantity > 1 ? "s":""}</span>
                </div>
                <div class="card-footer">
                    <a class="btn primary" href="courses.html?semester-id=${obj.id}&semester-name=${obj.name}">Abrir semestre</a>
                    <button class="btn danger delete-semester" data-semester-id="${obj.id}">Excluir</button>
                </div>
        `
        grid.appendChild(card);
        contagem++;
    }
}

gerarCards();
deleteSemester();
// -------------------------------------------------------------