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

closeModalButton.addEventListener("click", () => {
    semesterModal.classList.add("hidden");
})

newSemesterButton.addEventListener("click", () => {
    semesterModal.classList.remove("hidden");
});

createSemesterModalButton.addEventListener("click", () => {
    semesterModal.classList.remove("hidden");
});

cancelModalButton.addEventListener("click", () => {
    semesterModal.classList.add("hidden");
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

// DELETE Semester

function setupDeleteSemesterButton() {

    cardsGrid.addEventListener("click", async (e) => {

        if (e.target.classList.contains("delete-semester")) {

            const semesterId = e.target.dataset.semesterId;
            const response = await fetch(`http://localhost:8080/grade-manager/${gradeManagerId}/semester/${semesterId}`, {method: "DELETE"});

            if (response.ok) document.location.reload();
        }
    });
}

// Cards

async function generateCards() {

    const response = await fetch(`http://localhost:8080/grade-manager/${gradeManagerId}/semester`)
    const semesters = await response.json();

    let semesterCount = 1;

    for (const semester of semesters) {

        const card = document.createElement("article");
        card.classList.add("card", "item-card");

        card.innerHTML = `
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
                    <button class="btn danger delete-semester" data-semester-id="${semester.id}">Excluir</button>
                </div>
        `
        cardsGrid.appendChild(card);
        semesterCount++;
    }
}

generateCards();
setupDeleteSemesterButton();