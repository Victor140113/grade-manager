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

const deleteGradeManagerModal = document.getElementById("delete-modal");
const deleteModalTitle = document.getElementById("delete-modal-title");
const deleteModalText = document.getElementById("delete-modal-text");
const closeDeleteModalButton = document.getElementById("delete-modal-close");
const cancelDeleteModalButton = document.getElementById("delete-modal-cancel");
const deleteModalButton = document.getElementById("delete-modal-confirm");
let targetGradeManagerId;

function setupDeleteGradeManagerButton() {

    cardsGrid.addEventListener("click", async (e) => {

        if (e.target.classList.contains("delete-grade-manager")) {

            const gradeManagerName = e.target.dataset.gradeManagerName;
            targetGradeManagerId = e.target.dataset.gradeManagerId;

            deleteModalTitle.textContent = "Excluir " + gradeManagerName + "?";
            deleteModalText.textContent = "Tem certeza que deseja excluir " + gradeManagerName + "? Essa ação não poderá ser desfeita."

            showModal(deleteGradeManagerModal);

        }
    });
}

closeDeleteModalButton.addEventListener("click", () => {
    hiddenModal(deleteGradeManagerModal);
});

cancelDeleteModalButton.addEventListener("click", () => {
    hiddenModal(deleteGradeManagerModal);
});

deleteModalButton.addEventListener("click", async () => {
    const response = await fetch(`http://localhost:8080/grade-manager/${targetGradeManagerId}`, {method: "DELETE"});
    if (response.ok) document.location.reload();
})

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
                <button class="btn danger delete-grade-manager" data-grade-manager-id="${gradeManager.id}" data-grade-manager-name="${gradeManager.name}">Excluir</button>
            </div>
        `;

        cardsGrid.appendChild(gradeManagerCardElement);
    }
}

generateCards();
setupDeleteGradeManagerButton();