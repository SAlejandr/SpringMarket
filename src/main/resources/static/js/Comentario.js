function Comentario(id,usuario,producto,comentarioPadre,texto){
	this.id=id;
	this.usuario=usuario;
	this.producto=producto;
	this.comentarioPadre=comentarioPadre;
	this.texto=texto;
	this.borrado=false;
}
function Comentario(id,usuario,producto,comentarioPadre,texto,borrado){
	this.id=id;
	this.usuario=usuario;
	this.producto=producto;
	this.comentarioPadre=comentarioPadre;
	this.texto=texto;
	this.borrado=borrado;
}
function Comentario(){
	this.id=0;
	this.usuario=0;
	this.producto=0;
	this.comentarioPadre="";
	this.texto="";
	this.borrado=false;
}