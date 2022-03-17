




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
	//let correo = document.getElementById("correoUsuario").value;
	
				let li = document.createElement('li');
				let div1=document.createElement("div");
				
				let spanImagen=document.createElement("span");
				let imagen=document.createElement("img");
				
				let nombre=document.createElement("h4");
				
				let div2=document.createElement("div");
				let div3=document.createElement("div");
				let div4=document.createElement("div");
				
				let texto=document.createElement("p");
				
				let fecha=document.createElement("span");
				
				div1.setAttribute("class","d-flex");
				
				div4.setAttribute("class","left");
				
				imagen.setAttribute("src","/img/cocheDefault.png");
				imagen.setAttribute("class","profile-pict-img img-fluid");
				 
				div2.setAttribute("class","right");
				
				nombre.textContent=comentario.username;
				
				div3.setAttribute("class","review-description");
				texto.textContent = comentario.texto;
				
				fecha.setAttribute("class","publish py-3 d-inline-block w-100");
				fecha.textContent=comentario.fecha;
				
				li.appendChild(div1);
				div1.appendChild(div4);
				div4.appendChild(spanImagen);
				spanImagen.appendChild(imagen);
				div1.appendChild(div2);
				div2.appendChild(nombre);
				div2.appendChild(div3);
				div3.appendChild(texto);
				div2.appendChild(fecha);
				
				
				/*if(email==comentario.email){
				var boton = document.createElement("button");		
				boton.setAttribute("class","btn btn-danger");
				boton.setAttribute("type","button");
				boton.setAttribute("class","btn btn-danger");
				boton.textContent = "borrar";
				body.appendChild(boton);
				}*/
				comentarios.appendChild(li);
}

function obtenerComentario() {
	var id = document.getElementById("idProd").value;
	fetch('/comentario/todos?producto=' + id, { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json())
		.then(response => {
			for (let comentario of response) {
				
				nuevoComentario(comentario);

			}
		})
}

document.addEventListener("DOMContentLoaded", () =>{
	obtenerComentario();
	
} );
