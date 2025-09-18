CREATE DATABASE `bdInventario`;
USE bdInventario;
CREATE TABLE `Categorias` (
`IdCategoria` int NOT NULL AUTO_INCREMENT,
`Nombre` varchar(45) NOT NULL,
PRIMARY KEY (`IdCategoria`)
);
CREATE TABLE `Productos` (
`Codigo` varchar(20) NOT NULL,
`Nombre` varchar(45) NOT NULL,
`Precio` decimal(10,2) NOT NULL,
`Stock` int NOT NULL,
`IdCategoria` int NOT NULL,
PRIMARY KEY (`Codigo`),
FOREIGN KEY (`IdCategoria`) REFERENCES Categorias(`IdCategoria`)
);
DELIMITER $$
CREATE PROCEDURE sp_AgregarProducto (
 IN pCodigo VARCHAR(20),
 IN pNombre VARCHAR(45),
 IN pPrecio DECIMAL(10,2),
 IN pStock INT,
 IN pIdCategoria INT
)
BEGIN
 INSERT INTO Productos (Codigo, Nombre, Precio, Stock, IdCategoria)
 VALUES (pCodigo, pNombre, pPrecio, pStock, pIdCategoria);
END$$
DELIMITER ;