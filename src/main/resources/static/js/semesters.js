const grid = document.getElementById("grid-cards-semester");
const gradeNameTitle = document.querySelector(".eyebrow");

const params = new URLSearchParams(window.location.search);
const gmId = params.get("id");
const gradeName = params.get("name");

gradeNameTitle.textContent = gradeName;


async function gerarCards() {


    console.log(gmId);

    const response = await fetch(`http://localhost:8080/grade-manager/${gmId}/semester`)
    const json = await response.json();

    // console.log(json);

    for (const obj of json) {

        const card = document.createElement("article");
        card.classList.add("card", "item-card");

        card.innerHTML = `
                <div class="item-head">
                    <div class="icon">03</div>
                    <div class="item-main"><h2>${obj.name}</h2>
                        <p>Terceiro período</p>
                    </div>
                </div>
                <div class="meta">
                    <span class="badge cyan">5 matérias</span>
                </div>
                <div class="card-footer">
                    <a class="btn primary" href="courses.html?semester-id=${obj.id}&semester-name=${obj.name}">Abrir semestre</a>
                    <button class="btn danger">Excluir</button>
                </div>
        `
        grid.appendChild(card);
    }
}

gerarCards();