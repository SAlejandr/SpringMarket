DROP TABLE IF EXISTS usuario_rol;

DROP TABLE IF EXISTS lista_compra;

DROP TABLE IF EXISTS listaCompra;

DROP TABLE IF EXISTS compra;

DROP TABLE IF EXISTS producto;

DROP TABLE IF EXISTS rol;

DROP TABLE IF EXISTS tarjeta;

DROP TABLE IF EXISTS usuario;




CREATE TABLE tarjeta (
  numero int NOT NULL,
  titular text NOT NULL,
  codigo_seguridad int NOT NULL,
  facturacion text NOT NULL,
  PRIMARY KEY (numero)
);

CREATE TABLE usuario (
  id serial NOT NULL primary key,
  nombre text DEFAULT NULL,
  apellido text DEFAULT NULL,
  contrasenna text DEFAULT NULL,
  email text DEFAULT NULL,
  nacimiento date DEFAULT NULL,
  numero_tarjeta int DEFAULT NULL,
  CONSTRAINT usuario_ibfk_1 FOREIGN KEY (numero_tarjeta) REFERENCES tarjeta (numero)
);

CREATE TABLE compra (
  id serial primary key NOT NULL,
  usuario int DEFAULT NULL,
  fecha date DEFAULT NULL,
  borrado int DEFAULT NULL,
  CONSTRAINT compra_ibfk_1 FOREIGN KEY (usuario) REFERENCES usuario (id) ON DELETE RESTRICT ON UPDATE CASCADE
) ;
CREATE TABLE producto (
  id serial NOT NULL ,
  titulo text DEFAULT NULL,
  descripcion text DEFAULT NULL,
  precio double precision DEFAULT NULL,
  descuento int DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE listaCompra (
  id int NOT NULL  ,
  articulo int NOT NULL  ,
     PRIMARY KEY
   (
      id,
      articulo
   ),
  
  cantidad int DEFAULT NULL,
  CONSTRAINT listaCompra_ibfk_1 FOREIGN KEY (articulo) REFERENCES producto (id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT listaCompra_ibfk_2 FOREIGN KEY (id) REFERENCES compra (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE lista_compra (
  cantidad int DEFAULT NULL,
  articulo int NOT NULL,
  id int NOT NULL,
  PRIMARY KEY (
  id,
  articulo
  ),
  CONSTRAINT FK2qgcksk30xvhg54elfc7iig7i FOREIGN KEY (articulo) REFERENCES producto (id),
  CONSTRAINT FK3uv6tijxmynbiipw3x9wsg1i9 FOREIGN KEY (id) REFERENCES compra (id)
);



CREATE TABLE rol (
  id int NOT NULL,
  nombre text DEFAULT NULL,
  PRIMARY KEY (id)
);





CREATE TABLE usuario_rol (
  rol int NOT NULL,
  usuario int NOT NULL,
  PRIMARY KEY (
  rol,
  usuario
  ),
  CONSTRAINT usuario_rol_ibfk_1 FOREIGN KEY (rol) REFERENCES rol (id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT usuario_rol_ibfk_2 FOREIGN KEY (usuario) REFERENCES usuario (id) ON DELETE RESTRICT ON UPDATE CASCADE
)