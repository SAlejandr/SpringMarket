function ComentarioNuevoDTO(idUsuario,idProducto,idComentarioPadre,texto){
	this.idUsuario=idUsuario;
	this.idProducto=idProducto;
	this.idComentarioPadre=idComentarioPadre;
	this.texto=texto;
}

function ComentarioNuevoDTO(){
	this.idUsuario=0;
	this.idProducto=0;
	this.idComentarioPadre=null;
	this.texto="";
}

