const cardsGrid = document.getElementById("grid-cards-semester");
const eyebrow = document.querySelector(".eyebrow");

const params = new URLSearchParams(window.location.search);
const gradeManagerId = params.get("id");

const gradeManagerName = params.get("name");

eyebrow.textContent = gradeManagerName;


// Modal

const newSemesterButton = document.getElementById("modal-open");
const semesterModal = document.getElementById("modal");
const createSemesterModalButton = document.getElementById("modal-create");
const cancelModalButton = document.getElementById("modal-cancel");
const closeModalButton = document.getElementById("modal-close");

function hiddenModal(modal) {
    modal.classList.add("hidden");
}

function showModal(modal) {
    modal.classList.remove("hidden");
}

closeModalButton.addEventListener("click", () => {
    hiddenModal(semesterModal);
})

newSemesterButton.addEventListener("click", () => {
    showModal(semesterModal);
});

createSemesterModalButton.addEventListener("click", () => {
    showModal(semesterModal);
});

cancelModalButton.addEventListener("click", () => {
    hiddenModal(semesterModal);
});

// POST Semester

const semesterForm = document.getElementById("modal-form");
const semesterNameInput = document.getElementById("modal-input");

semesterForm.addEventListener("submit", async (e) => {

    e.preventDefault();

    const response = await fetch(`http://localhost:8080/grade-manager/${gradeManagerId}/semester`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            name: semesterNameInput.value.trim()
        })
    });

    if (response.ok) window.location.reload();

});

// Delete Modal

const deleteSemesterModal = document.getElementById("delete-modal");
const deleteModalTitle = document.getElementById("delete-modal-title");
const deleteModalText = document.getElementById("delete-modal-text");
const closeDeleteModalButton = document.getElementById("delete-modal-close");
const cancelDeleteModalButton = document.getElementById("delete-modal-cancel");
const deleteModalButton = document.getElementById("delete-modal-confirm");
let targetSemesterId;


function setupDeleteSemesterButton() {

    cardsGrid.addEventListener("click", (e) => {

        if (e.target.classList.contains("delete-semester")) {

            const semesterName = e.target.dataset.semesterName;
            targetSemesterId = e.target.dataset.semesterId;

            deleteModalTitle.textContent = "Excluir " + semesterName + "?";
            deleteModalText.textContent = "Tem certeza que deseja excluir " + semesterName + "? Essa ação não poderá ser desfeita."
            showModal(deleteSemesterModal);
        }
    });
}

closeDeleteModalButton.addEventListener("click", () => {
    hiddenModal(deleteSemesterModal);
});

cancelDeleteModalButton.addEventListener("click", () => {
    hiddenModal(deleteSemesterModal);
});

deleteModalButton.addEventListener("click", async () => {
    const response = await fetch(`http://localhost:8080/grade-manager/${gradeManagerId}/semester/${targetSemesterId}`, {method: "DELETE"});
    if (response.ok) document.location.reload();
})

// Semester Cards

async function generateCards() {

    const response = await fetch(`http://localhost:8080/grade-manager/${gradeManagerId}/semester`)
    const semesters = await response.json();

    let semesterCount = 1;

    for (const semester of semesters) {

        const semesterCardElement = document.createElement("article");
        semesterCardElement.classList.add("card", "item-card");

        semesterCardElement.innerHTML = `
                <div class="item-head">
                    <div class="icon">${semesterCount}</div>
                    <div class="item-main"><h2>${semester.name}</h2>
                        <p>Terceiro período</p>
                    </div>
                </div>
                <div class="meta">
                    <span class="badge cyan">${semester.courseQuantity} matéria${semester.courseQuantity > 1 ? "s" : ""}</span>
                </div>
                <div class="card-footer">
                    <a class="btn primary" href="courses.html?semester-id=${semester.id}&semester-name=${semester.name}">Abrir semestre</a>
                    <button class="btn danger delete-semester" data-semester-id="${semester.id}" data-semester-name="${semester.name}">Excluir</button>
                </div>
        `
        cardsGrid.appendChild(semesterCardElement);
        semesterCount++;
    }
}

generateCards();
setupDeleteSemesterButton();