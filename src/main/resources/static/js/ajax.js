
function anadirComentario() {

	var comentario = document.getElementById("").value;

	let comentario = new Comentario(id,usuario,producto,comentarioPadre,texto);
	
	fetch('/anadir', {
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
				
				var a = document.createElement('a');
				a.textContent="pruebaaaa";
				comentarios.appendChild(a);
			}
		})
}

document.addEventListener("DOMContentLoaded", obtenerComentario);
