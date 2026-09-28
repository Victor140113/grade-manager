const gridPai = document.getElementById("grid-cards");

const parameter = new URLSearchParams(window.location.search);

const semesterName = parameter.get("semester-name");
const semesterId = parameter.get("semester-id");

const eyebrow = document.querySelector(".eyebrow");
eyebrow.textContent = semesterName;

// --------------------- POST Form --------------------

const form = document.getElementById("modal-form");
const inputData = document.getElementById("modal-input");
const inputValue = document.getElementById("modal-value");
const valueGroup = document.getElementById("modal-value-group");

form.addEventListener("submit", async (e) => {

    e.preventDefault();

    const type = form.dataset.type;
    const id = form.dataset.id;

    let url;
    let body;

    if (type === "course") {

        url = `http://localhost:8080/grade-manager/semester/${id}/course`;

        body = {
            name: inputData.value.trim()
        };

    } else if (type === "grade-update") {

        url = `http://localhost:8080/grade-manager/semester/course/grade/${id}/grade-update`;

        body = {
            description: inputData.value.trim(),
            value: Number(inputValue.value)
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

// -------------------------------------------------------------

// ------------------------ Botão Voltar Modal -------------------------

const backBtn = document.getElementById("btn-back");

backBtn.addEventListener("click", () => {
    history.back();
});

// ---------------------------------------------------------------------

// --------------------- Modal Config --------------------

const btnOpenModalCourse = document.getElementById("modal-open");
const modal = document.getElementById("modal");
const btnCreate = document.getElementById("modal-create");
const btnCancel = document.getElementById("modal-cancel");
const btnCloseModal = document.getElementById("modal-close");

btnCloseModal.addEventListener("click", () => {
    modal.classList.add("hidden");
});

btnOpenModalCourse.addEventListener("click", () => {

    form.dataset.type = "course";
    form.dataset.id = semesterId;

    modal.querySelector("#modal-title").textContent = "Nova Matéria";
    modal.querySelector("#modal-label").textContent = "Nome";

    inputData.placeholder = "Digite o nome";

    valueGroup.classList.add("hidden");
    inputValue.required = false;

    modal.classList.remove("hidden");
});

btnCreate.addEventListener("click", () => {
    modal.classList.remove("hidden");
});

btnCancel.addEventListener("click", () => {
    modal.classList.add("hidden");
});

// --------------------------------------------------------------------------

// --------------------------- Gera Card Updates ----------------------------

function gerarUpdates(gradeUpdates, pai) {

    for (const gu of gradeUpdates) {

        const guHtml = document.createElement("div");
        guHtml.classList.add("update");

        guHtml.innerHTML = `<div class="update-main">
                <strong>${gu.description}</strong>
            </div>
            <span class="update-value">+${String(gu.value).replace(".", ",")}</span>
            <button class="btn danger delete-grade-update" data-grade-id="${gu.gradeId}" data-grade-update-id="${gu.id}">Excluir</button>`;

        pai.appendChild(guHtml);
    }
}

// --------------------------------------------------------------------------


// ------------------------------ Post Updates ------------------------------

function postUpdate(btns) {

    for (const btn of btns) {

        btn.addEventListener("click", e => {

            form.dataset.type = "grade-update";
            form.dataset.id = btn.dataset.gradeId;

            modal.querySelector("#modal-title").textContent = "Nova Nota";
            modal.querySelector("#modal-label").textContent = "Descrição";

            inputData.placeholder = "Digite a descrição";

            valueGroup.classList.remove("hidden");
            inputValue.required = true;

            modal.classList.remove("hidden");

        });
    }
}

// ----------------------------------------------------------------------------



// --------------------------- Delete Grade Update ----------------------------

function deleteGradeUpdate(){


    gridPai.addEventListener("click", async (e) =>{


       if(e.target.classList.contains("delete-grade-update")){

           const guId = e.target.dataset.gradeUpdateId;
           const gradeId = e.target.dataset.gradeId;

           const response = await fetch(`http://localhost:8080/grade-manager/semester/course/grade/${gradeId}/grade-update/${guId}`, {method: "DELETE"});
           console.log(response);

           if(response.ok) document.location.reload();

       }

    });


}

// ----------------------------------------------------------------------------



// Gera cards com dados vindos do banco
async function gerarCards() {

    const response = await fetch(`http://localhost:8080/grade-manager/semester/${semesterId}/course`);
    const json = await response.json();

    //console.log(json);

    for (const obj of json) {

        const card = document.createElement("article");
        card.classList.add("card", "item-card");

        card.innerHTML = `
                <div class="item-head">
                    <div class="icon">${String(obj.name).charAt(0).toUpperCase()}</div>
                    <div class="item-main"><h2>${obj.name}</h2>
                        <p>Course #11</p></div>
                    <button class="btn">•••</button>
                </div>

                <div class="grade-grid">
                    <div class="grade">
                        <small>1º Bimestre</small>
                        <strong>${obj.gradeResponses[0].value == null || obj.gradeResponses[0].value <=0 ? "—" : String(obj.gradeResponses[0].value.toFixed(2)).replace(".", ",")}</strong>
                        <span class="badge green">${obj.gradeResponses[0].gradeUpdates.length} updates</span>
                    </div>

                    <div class="grade">
                        <small>2º Bimestre</small>
                        <strong>${obj.gradeResponses[1].value == null || obj.gradeResponses[1].value <= 0? "—" : String(obj.gradeResponses[1].value.toFixed(2)).replace(".", ",")}</strong>
                        <span class="badge green">${obj.gradeResponses[1].gradeUpdates.length} updates</span>
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
                                    data-grade-id="${obj.gradeResponses[0].id}">
                                + Atualizar nota
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
                                    data-grade-id="${obj.gradeResponses[1].id}">
                                + Atualizar nota
                            </button>
                        </div>
                    </div>
                </details>
            `;

        gerarUpdates(
            obj.gradeResponses[0].gradeUpdates,
            card.querySelector(".grade-updates-1b"),
        );

        gerarUpdates(
            obj.gradeResponses[1].gradeUpdates,
            card.querySelector(".grade-updates-2b"),
        );

        postUpdate(card.querySelectorAll(".open-gu-modal"));

        gridPai.appendChild(card);
    }
}

gerarCards();
deleteGradeUpdate();