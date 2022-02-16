function ComentarioDTO(usuario,producto,comentarioPadre,texto){
	this.usuario=usuario;
	this.producto=producto;
	this.comentarioPadre=comentarioPadre;
	this.texto=texto;
	this.borrado=false;
}
function ComentarioDTO(usuario,producto,comentarioPadre,texto,borrado){
	this.usuario=usuario;
	this.producto=producto;
	this.comentarioPadre=comentarioPadre;
	this.texto=texto;
	this.borrado=borrado;
}
function ComentarioDTO(){
	this.usuario=0;
	this.producto=0;
	this.comentarioPadre="";
	this.texto="";
	this.borrado=false;
}

