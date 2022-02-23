




function anadirComentario() {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	let comentario_nuevo = new ComentarioNuevoDTO();

	comentario_nuevo.texto = document.getElementById("textoComentario").value;
	comentario_nuevo.idUsuario = document.getElementById("idUsuario").value;
	comentario_nuevo.idProducto = document.getElementById("idProd").value;

	$.ajax({
		url: '/comentario/add',
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(comentario_nuevo),
		dataType: "json",
		type: "POST",
		success: function(response) {
			let comentario;

			let res = response;

				comentario = res;
				console.log(res);
				nuevoComentario(comentario);

		},
		error: function() {

			console.log("Algo mal")
		}

	});

}

function nuevoComentario(comentario){
	
	let comentarios = document.getElementById("preguntas");
	//let idUsuario = document.getElementById("idUsuario").value;
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
				
				/*if(email==comentario.email){
				var boton = document.createElement("button");		
				boton.setAttribute("class","btn btn-danger");
				boton.setAttribute("type","button");
				boton.setAttribute("class","btn btn-danger");
				boton.textContent = "borrar";
				body.appendChild(boton);
				}*/
				div.appendChild(body);
				comentarios.appendChild(div);
}

function obtenerComentario() {
	let comentarios = document.getElementById("preguntas");
	var id = document.getElementById("idProd").value;
	fetch('/comentario/todos?producto=' + id, { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json())
		.then(response => {
			for (let comentario of response) {
				
				nuevoComentario(comentario);

			}
		})
}

document.addEventListener("DOMContentLoaded", obtenerComentario);
