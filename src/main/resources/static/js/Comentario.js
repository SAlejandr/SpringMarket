function Comentario(id,usuario,producto,comentarioPadre,texto){
	this.id=id;
	this.usuario=usuario;
	this.producto=producto;
	this.comentarioPadre=comentarioPadre;
	this.texto=texto;
}
function Comentario(){
	this.id=0;
	this.usuario=0;
	this.producto=0;
	this.comentarioPadre="";
	this.texto="";
}