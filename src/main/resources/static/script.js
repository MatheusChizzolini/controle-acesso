function login() {
    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const senha = document.getElementById("senha").value;
    fetch(`http://localhost:8080/auth/${nome}/${senha}`)
        .then(response => {
            if (!response.ok)
                throw new Error("Dados inválidos");
            return response.text();
        })
        .then(token => {
            localStorage.setItem("token", token);
            alert("Login feito com sucesso!");
            window.location.href = "users.html";
        })
        .catch(error => {
            alert(error.message);
            console.error(error);
        });
}

function carregaUsuarios() {
    const token = localStorage.getItem("token");
    if (!token) {
        alert("Acesso negado.");
        window.location.href = "index.html";
    } else {
        fetch("http://localhost:8080/apis/user", {
            headers: {
                "Authorization": token
            }
        })
            .then(response => {
                if (!response.ok)
                    throw new Error("Erro ao buscar usuários");
                return response.json();
            })
            .then(usuarios => {
                const tabela = document.getElementById("tabela-usuarios");
                tabela.innerHTML = "";
                usuarios.forEach(usuario => {
                    const linha = document.createElement("tr");
                    linha.innerHTML = `
                        <td>${usuario.id}</td>
                        <td>${usuario.nome}</td>
                        <td>${usuario.nivel}</td>
                    `;
                    tabela.appendChild(linha);
                });
            })
            .catch(error => {
                alert("Erro ao carregar usuários: " + error.message);
                console.error(error);
            });
    }
}
