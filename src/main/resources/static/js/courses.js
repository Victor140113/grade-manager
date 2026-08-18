const gridPai = document.getElementById("grid-cards");
console.log(gridPai);

const parameter = new URLSearchParams(window.location.search);

const semesterName = parameter.get("semester-name");
const semesterId = parameter.get("semester-id");

const eyebrow = document.querySelector(".eyebrow");
eyebrow.textContent = semesterName;

    // Gera updates com elementos do banco para seus determinados bimestres
function gerarUpdates(gradeUpdates, pai) {

    for (const gu of gradeUpdates) {

        const guHtml = document.createElement("div");
        guHtml.classList.add("update");

        guHtml.innerHTML = `<div class="update-main">
                <strong>${gu.description}</strong>
            </div>
            <span class="update-value">+${String(gu.value).replace(".", ",")}</span>
            <button class="btn danger" data-grade-id="${gu.gradeId}">Excluir</button>`;
        pai.appendChild(guHtml);
    }
}

// Gera cards com dados vindos do banco
async function gerarCards() {

    const response = await fetch(`http://localhost:8080/grade-manager/semester/${semesterId}/course`);
    console.log(response);

    const json = await response.json();
    console.log(json);

    for (const obj of json) {

        const card = document.createElement("article");
        // console.log(card);
        card.classList.add("card", "item-card");
        console.log(card);

        card.innerHTML = `
                <div class="item-head">
                    <div class="icon">${String(obj.name).charAt(0).toUpperCase()}</div>
                    <div class="item-main"><h2>${obj.name}</h2>
                        <p>Course #11</p></div>
                    <button class="btn">•••</button>
                </div>
                <div class="grade-grid">
                    <div class="grade"><small>1º Bimestre</small><strong>${obj.gradeResponses[0].value == null ? "—" : String(obj.gradeResponses[0].value).replace(".", ",")}</strong><span class="badge green">${obj.gradeResponses[0].gradeUpdates.length} updates</span></div>
                    <div class="grade"><small>2º Bimestre</small><strong>${obj.gradeResponses[1].value == null ? "—" : String(obj.gradeResponses[1].value).replace(".", ",")}</strong><span class="badge green">${obj.gradeResponses[1].gradeUpdates.length} updates</span></div>
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
                            <button class="btn primary">+ Atualizar nota</button>
                        </div>
                    </div>
                
                    <!-- 2º Bimestre -->
                    <div class="section">
                        <div class="section-head">
                            <h3>2º Bimestre</h3>
                        </div>
                
                        <div class="grade-updates-2b"></div>
                
                        <div class="card-footer">
                            <button class="btn primary">+ Atualizar nota</button>
                        </div>
                    </div>
                </details>
            `

        gerarUpdates(obj.gradeResponses[0].gradeUpdates, card.querySelector(".grade-updates-1b"));
        gerarUpdates(obj.gradeResponses[1].gradeUpdates, card.querySelector(".grade-updates-2b"));

        gridPai.appendChild(card);
    }
}

gerarCards();