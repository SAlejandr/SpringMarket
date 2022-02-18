function ComentarioNuevoDTO(usuario,producto,comentarioPadre,texto){
	this.usuario=usuario;
	this.producto=producto;
	this.comentarioPadre=comentarioPadre;
	this.texto=texto;
}
function ComentarioNuevoDTO(usuario,producto,comentarioPadre,texto,){
	this.usuario=usuario;
	this.producto=producto;
	this.comentarioPadre=comentarioPadre;
	this.texto=texto;
	this.borrado=borrado;
}
function ComentarioNuevoDTO(){
	this.usuario=0;
	this.producto=0;
	this.comentarioPadre=0;
	this.texto="";
}

