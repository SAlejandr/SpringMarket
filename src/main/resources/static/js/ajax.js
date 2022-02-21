




function anadirComentario() {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	var texto = document.getElementById("textoComentario").value;
	var idUsuario = document.getElementById("idUsuario").value;
	var idProducto = document.getElementById("idProd").value;

	let comentario = new ComentarioNuevoDTO(idUsuario, idProducto, null, texto);

	$.ajax({
		url: '/comentario/add',
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(comentario),
		type: "POST",
		success: function(response) {


			let res = response.json();
			res => {
				comentario = res;
				console.log(res);

			}

		},
		error: function() {

			console.log("Algo mal")
		}

	});

}

function obtenerComentario() {
	let comentarios = document.getElementById("preguntas");
	var id = document.getElementById("idProd").value;
	fetch('/comentario/todos?producto=' + id, { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json())
		.then(response => {
			for (let comentario of response) {
				
				let div = document.createElement('div');
				let body=document.createElement("div");
				let texto=document.createElement("h5");
				let nombre=document.createElement("h6");
				div.setAttribute("class","card");
				body.setAttribute("class","card-body");
				texto.setAttribute("class","card-title");
				nombre.setAttribute("class","card-subtitle mb-2 text-muted");
				texto.textContent = comentario.texto;
				nombre.textContent=comentario.username+"-"+comentario.fecha;
				body.appendChild(texto);
				body.appendChild(nombre);
				div.appendChild(body);
				comentarios.appendChild(div);

			}
		})
}

document.addEventListener("DOMContentLoaded", obtenerComentario);
