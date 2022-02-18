
function anadirComentario() {

	var texto = document.getElementById("textoComentario").value;
	var idUsuario=document.getElementById("idUsuario").value;
	var idProucto=document.getElementById("id").value;
	let comentario = new Comentario();
	
	fetch('/add', {
		headers: { "Content-Type": "application/json; charset=utf-8" },
		method: 'POST',
		body: JSON.stringify(comentario)
	})
		.then(function(response){
			if(response.ok){
				return response.json();
			
			}else{
				throw "No va";
				
			}
		}).then(res => {
			comentario = res;
			anadirfila(comentario);
			console.log(res);
		});
	
}

function obtenerComentario() {
	let comentarios = document.getElementById("preguntas");
	var id= document.getElementById("idProd").value;
	fetch('/comentario/todos?producto='+id, { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json())
		.then(response => {
			for (let comentario of response) {
				let tr = document.createElement('tr');
				var cel1 = document.createElement("td");
				var cel2 = document.createElement("td");
				var cel3 = document.createElement("td");
				cel1.textContent = comentario.username;
				cel2.textContent = comentario.texto;
				cel3.textContent = comentario.fecha;
				tr.appendChild(cel1);
				tr.appendChild(cel2);
				tr.appendChild(cel3);

				comentarios.appendChild(tr);
			
			}
		})
}

document.addEventListener("DOMContentLoaded", obtenerComentario);
