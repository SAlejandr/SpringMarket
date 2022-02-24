function ComentarioDTO(id, email,usuario,producto,comentarioPadre,texto,borrado){
	this.id= id;
	this.email = email;
	this.usuario=usuario;
	this.producto=producto;
	this.comentarioPadre=comentarioPadre;
	this.texto=texto;
	this.borrado=borrado;
}
function ComentarioDTO(){
	this.id = 0;
	this.email= '';
	this.usuario=0;
	this.producto=0;
	this.comentarioPadre=0;
	this.texto="";
	this.borrado=false;
}

