const grid = document.getElementById("grid-cards");

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
                <span class="badge">${obj.semesterQuantity} semestres</span>
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