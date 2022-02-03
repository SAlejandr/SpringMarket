DROP TABLE IF EXISTS usuario_rol;

DROP TABLE IF EXISTS lista_compra;

DROP TABLE IF EXISTS listaCompra;

DROP TABLE IF EXISTS compra;

DROP TABLE IF EXISTS producto;

DROP TABLE IF EXISTS rol;

DROP TABLE IF EXISTS usuario;

DROP TABLE IF EXISTS tarjeta;






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
);

INSERT INTO rol VALUES (1,'Admin'),(2,'Cliente');
INSERT INTO usuario VALUES (1,'Marco','Polo','viajes','marco.polo@mail.com','1989-12-01',123456789),(2,'Atila','Elhuno','apocalipsis','caballo.negro@hunos.com','1963-10-28',987654321),(3,'alex','a','a','a@gmail.es','2021-11-03',987654321),(4,'alex','a','pl','alejandro.guerra@dfs.es','2021-11-17',987654321),(5,'Yo','guerra','1','ad@gmail.es','2021-11-10',123456789),(6,'Arnaket','DeMuial','$2a$10$BfVrTqN3NuusBu1nmjfRs.hqpONiVWXiMWiTWSSGi4opXq8.6UW56','nosealgo@mail.err','2021-06-11',NULL),(7,'b','b','$2a$10$JDCaJjxn0K4iVbaT1J2nHe/jMmvlkUrNWB2Bvy.8KdyqljYxRuYGy','b@gmail.com','2021-11-15',NULL),(8,'hoola','hol','$2a$10$s6V/xvtWg/kaTCjAML8jHugpvzIukPyR9V4JC8PXORZsRjSiuStpq','hola@gmail.com','2021-11-23',123456789),(9,'prueba','prueba','$2a$10$ZKtOVdWo.xTZCjYF63lEGeebAss1hqO2RfaEXLWLyKk/Rv3YOYRUq','prueba@gmail.com','2021-11-23',NULL),(10,'alex','guerra','$2a$10$p30XCDXgBvEUCwj8ksBLVer81b4qAtYEwy7FYLiLuWQMcTsPLd3Um','aaaaa@gmail.com','2022-01-05',NULL),(11,'Marcos','Puente','$2a$10$fXBcqzGnwIhGl.0sf1uHNuVBzD7Lf3iWnE/9TvZQyTYJf15TBkiE2','marcos@marcos.com','2022-01-05',987654321);
INSERT INTO usuario_rol VALUES (2,6),(1,7),(2,7),(2,8),(2,9),(2,10),(2,11);