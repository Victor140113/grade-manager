const cardsGrid = document.getElementById("grid-cards");

const urlSearchParams = new URLSearchParams(window.location.search);

const semesterName = urlSearchParams.get("semester-name");
const semesterId = urlSearchParams.get("semester-id");

const eyebrow = document.querySelector(".eyebrow");
eyebrow.textContent = semesterName;


// Delete Modal

const deleteModal = document.getElementById("delete-modal");
const deleteModalTitle = document.getElementById("delete-modal-title");
const deleteModalText = document.getElementById("delete-modal-text");
const btnCloseDeleteModal = document.getElementById("delete-modal-close");
const btnCancelDeleteModal = document.getElementById("delete-modal-cancel");
const btnDeleteModal = document.getElementById("delete-modal-confirm");
let courseId;

async function confirmDeleteCourse(courseId, semesterId){
    const response = await fetch(`http://localhost:8080/grade-manager/semester/${semesterId}/course/${courseId}`, {method: "DELETE"});
    if (response.ok) document.location.reload();
}

btnCloseDeleteModal.addEventListener("click", () =>{
    hiddenModal(deleteModal);
});

btnCancelDeleteModal.addEventListener("click", () =>{
    hiddenModal(deleteModal);
});

btnDeleteModal.addEventListener("click", () =>{
    confirmDeleteCourse(courseId, semesterId);
});


// Post Form

const form = document.getElementById("modal-form");
const dataInput = document.getElementById("modal-input");
const valueInput = document.getElementById("modal-value");
const inputValueGroupElement = document.getElementById("modal-value-group");

form.addEventListener("submit", async (e) => {

    e.preventDefault();

    const targetType = form.dataset.type;
    const targetId = form.dataset.id;

    let url;
    let body;

    if (targetType === "course") {

        url = `http://localhost:8080/grade-manager/semester/${targetId}/course`;

        body = {
            name: dataInput.value.trim()
        };

    } else if (targetType === "grade-update") {

        url = `http://localhost:8080/grade-manager/semester/course/grade/${targetId}/grade-update`;

        body = {
            description: dataInput.value.trim(),
            value: Number(valueInput.value)
        };
    }

    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    });

    if (response.ok) {
        window.location.reload();
    }
});


// Navegação

const backButton = document.getElementById("btn-back");

backButton.addEventListener("click", () => {
    history.back();
});


// Modal Config

function hiddenModal(modal){
    modal.classList.add("hidden");
}

function showModal(modal){
    modal.classList.remove("hidden");
}

const newCourseButton = document.getElementById("modal-open");
const newCourseModal = document.getElementById("modal");
const createCourseModalButton = document.getElementById("modal-create");
const cancelCourseModalButton = document.getElementById("modal-cancel");
const closeCourseModalButton = document.getElementById("modal-close");

closeCourseModalButton.addEventListener("click", () => {
    hiddenModal(newCourseModal);
});

newCourseButton.addEventListener("click", () => {

    form.dataset.type = "course";
    form.dataset.id = semesterId;

    newCourseModal.querySelector("#modal-title").textContent = "Nova Matéria";
    newCourseModal.querySelector("#modal-label").textContent = "Nome";

    dataInput.placeholder = "Digite o nome";

    inputValueGroupElement.classList.add("hidden");
    valueInput.required = false;

    showModal(newCourseModal);
});

createCourseModalButton.addEventListener("click", () => {
    showModal(newCourseModal);
});

cancelCourseModalButton.addEventListener("click", () => {
    hiddenModal(newCourseModal);
});


// Gera os Grade Updates dentro de updatesContainer

function generateUpdates(gradeUpdates, updatesContainer) {

    for (const gradeUpdate of gradeUpdates) {

        const gradeUpdateElement = document.createElement("div");
        gradeUpdateElement.classList.add("update");

        gradeUpdateElement.innerHTML = `<div class="update-main">
                <strong>${gradeUpdate.description}</strong>
            </div>
            <span class="update-value">+${String(gradeUpdate.value).replace(".", ",")}</span>
            <button class="btn danger delete-grade-update" data-grade-id="${gradeUpdate.gradeId}" data-grade-update-id="${gradeUpdate.id}">Excluir</button>`;

        updatesContainer.appendChild(gradeUpdateElement);
    }
}


// Configuração do modal de criação de gradeUpdates

function setupGradeUpdateButtons(buttonsCollection) {

    for (const button of buttonsCollection) {

        button.addEventListener("click", () => {

            form.dataset.type = "grade-update";
            form.dataset.id = button.dataset.gradeId;

            newCourseModal.querySelector("#modal-title").textContent = "Nova Nota";
            newCourseModal.querySelector("#modal-label").textContent = "Descrição";

            dataInput.placeholder = "Digite a descrição";
            valueInput.required = true;

            showModal(inputValueGroupElement);
            
            showModal(newCourseModal);

        });
    }
}


// Gerenciador de exclusões

function handleDelete() {

    cardsGrid.addEventListener("click", async (e) => {

        if (e.target.classList.contains("delete-grade-update")) {

            const gradeUpdateId = e.target.dataset.gradeUpdateId;
            const gradeId = e.target.dataset.gradeId;

            const response = await fetch(`http://localhost:8080/grade-manager/semester/course/grade/${gradeId}/grade-update/${gradeUpdateId}`, {method: "DELETE"});

            if (response.ok) document.location.reload();

        }

        if (e.target.classList.contains("delete-course")) {

            const courseName = e.target.dataset.courseName;
            courseId = e.target.dataset.courseId;

            deleteModalTitle.textContent = "Excluir " + courseName + "?";
            deleteModalText.textContent = "Tem certeza que deseja excluir " + courseName + "? Essa ação não poderá ser desfeita."

            showModal(deleteModal);

        }

    });
}


// Gera Cards

// Busca os cursos no banco e gera os cards na tela
async function generateCourseCards() {

    const response = await fetch(`http://localhost:8080/grade-manager/semester/${semesterId}/course`);
    const courses = await response.json();

    for (const course of courses) {

        const courseCardElement = document.createElement("article");
        courseCardElement.classList.add("card", "item-card");

        courseCardElement.innerHTML = `
                <div class="item-head">
                    <div class="icon">${String(course.name).charAt(0).toUpperCase()}</div>
                    <div class="item-main"><h2>${course.name}</h2>
                        <p>Course #11</p>
                    </div>
                    <button class="btn danger delete-course" data-course-id="${course.id}" data-course-name="${course.name}">Excluir</button>
                </div>

                <div class="grade-grid">
                    <div class="grade">
                        <small>1º Bimestre</small>
                        <strong>${course.gradeResponses[0].value == null || course.gradeResponses[0].value <= 0 ? "—" : String(course.gradeResponses[0].value.toFixed(2)).replace(".", ",")}</strong>
                        <span class="badge green">${course.gradeResponses[0].gradeUpdates.length} updates</span>
                    </div>

                    <div class="grade">
                        <small>2º Bimestre</small>
                        <strong>${course.gradeResponses[1].value == null || course.gradeResponses[1].value <= 0 ? "—" : String(course.gradeResponses[1].value.toFixed(2)).replace(".", ",")}</strong>
                        <span class="badge green">${course.gradeResponses[1].gradeUpdates.length} updates</span>
                    </div>
                    
                    <div class="grade">
                        <small>Média Semestral</small>
                        <strong>${course.avg == null || course.avg <= 0 ? "—" : String(course.avg.toFixed(2)).replace(".", ",")}</strong>
                    </div>
                    
                </div>

                <details class="section">
                    <summary>Ver atualizações</summary>

                    <!-- 1º Bimestre -->
                    <div class="section">
                        <div class="section-head">
                            <h3>1º Bimestre</h3>
                        </div>

                        <div class="grade-updates-1b"></div>

                        <div class="card-footer">
                            <button class="btn primary open-gu-modal"
                                    data-grade-id="${course.gradeResponses[0].id}">
                                + Atualizar Nota 1º Bim
                            </button>
                        </div>
                    </div>

                    <!-- 2º Bimestre -->
                    <div class="section">
                        <div class="section-head">
                            <h3>2º Bimestre</h3>
                        </div>

                        <div class="grade-updates-2b"></div>

                        <div class="card-footer">
                            <button class="btn primary open-gu-modal"
                                    data-grade-id="${course.gradeResponses[1].id}">
                                + Atualizar Nota 2º Bim
                            </button>
                        </div>
                    </div>
                </details>
            `;

        generateUpdates(
            course.gradeResponses[0].gradeUpdates,
            courseCardElement.querySelector(".grade-updates-1b"),
        );

        generateUpdates(
            course.gradeResponses[1].gradeUpdates,
            courseCardElement.querySelector(".grade-updates-2b"),
        );

        setupGradeUpdateButtons(courseCardElement.querySelectorAll(".open-gu-modal"));

        cardsGrid.appendChild(courseCardElement);
    }
}

generateCourseCards();
handleDelete();