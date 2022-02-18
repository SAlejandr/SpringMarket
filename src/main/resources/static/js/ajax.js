




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

	$.ajax({url:'/comentario/add',
		contentType: "application/json; charset=utf-8",
		body: JSON.stringify(comentario),
		type: "POST",
		success : function(response) {
			
			if (response.ok) {
				let res = response.json();
				res => {
					comentario = res;
			//anadirfila(comentario);
					console.log(res);
					
					}
			} else {
				throw "No va";

			}
		}, 
		error: function(){
			
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
