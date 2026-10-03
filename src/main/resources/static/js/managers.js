const cardsGrid = document.getElementById("grid-cards");


// Modal

const newGradeManagerButton = document.getElementById("modal-open");
const gradeManagerModal = document.getElementById("modal");
const createGradeManagerModalButton = document.getElementById("btn-create");
const cancelModalButton = document.getElementById("modal-cancel");
const closeModalButton = document.getElementById("modal-close");

function hiddenModal(modal) {
    modal.classList.add("hidden");
}

function showModal(modal) {
    modal.classList.remove("hidden");
}

closeModalButton.addEventListener("click", () => {
    hiddenModal(gradeManagerModal);
});

newGradeManagerButton.addEventListener("click", () => {
    showModal(gradeManagerModal);
});

createGradeManagerModalButton.addEventListener("click", () => {
    showModal(gradeManagerModal);
});

cancelModalButton.addEventListener("click", () => {
    hiddenModal(gradeManagerModal);
});


// POST Grade Manager

const gradeManagerForm = document.getElementById("modal-form");
const gradeManagerNameInput = document.getElementById("modal-input");

gradeManagerForm.addEventListener("submit", async (e) => {

    e.preventDefault();

    const response = await fetch(`http://localhost:8080/grade-manager`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            name: gradeManagerNameInput.value.trim()
        })
    });

    if (response.ok) window.location.reload();

});


// DELETE Grade Manager

function setupDeleteGradeManagerButton() {

    cardsGrid.addEventListener("click", async (e) => {

        if (e.target.classList.contains("delete-grade-manager")) {

            const gradeManagerId = e.target.dataset.gradeManagerId;

            const response = await fetch(
                `http://localhost:8080/grade-manager/${gradeManagerId}`,
                {method: "DELETE"}
            );

            if (response.ok) window.location.reload();

        }
    });
}


// Grade Manager Cards

async function generateCards() {

    const response = await fetch("http://localhost:8080/grade-manager");
    const gradeManagers = await response.json();

    for (const gradeManager of gradeManagers) {

        const gradeManagerCardElement = document.createElement("article");
        gradeManagerCardElement.classList.add("card", "item-card");
        gradeManagerCardElement.setAttribute("data-grade-id", gradeManager.id);

        gradeManagerCardElement.innerHTML = `
            <div class="item-head">
                <div class="icon">W</div>
                <div class="item-main">
                    <h2>${gradeManager.name}</h2>
                </div>
            </div>
            <div class="meta">
                <span class="badge">${gradeManager.semesterQuantity} semestre${gradeManager.semesterQuantity > 1 ? "s" : ""}</span>
            </div>
            <div class="card-footer">
                <a class="btn" href="semesters.html?id=${gradeManager.id}&name=${gradeManager.name}">Ver semestres</a>
                <button class="btn">Editar</button>
                <button class="btn danger delete-grade-manager" data-grade-manager-id="${gradeManager.id}">Excluir</button>
            </div>
        `;

        cardsGrid.appendChild(gradeManagerCardElement);
    }
}

generateCards();
setupDeleteGradeManagerButton();