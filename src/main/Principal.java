package main;

import dao.DaoCategoria;
import dao.DaoProducto;
import entidad.Categoria;
import entidad.Producto;

public class Principal {

	
		public static void main(String[] args) {
			DaoProducto productoDao = new DaoProducto();
			DaoCategoria categoriaDao = new DaoCategoria();
			
			Categoria categoria1 = new Categoria();
			categoria1.setNombre("lacteos");
			int filas = categoriaDao.agregarCategoria(categoria1);
			
			if(filas==1)
					System.out.println("Categoria agregada");
			else
				System.out.println("Categoria no agregada");
			
			
			Producto producto1 = new Producto();
			producto1.setCodigo("1");
			producto1.setNombre("leche");
			producto1.setPrecio(3000);
			producto1.setStock(10);
			producto1.setIdCategoria(1);
			
			filas = productoDao.agregarProducto(producto1);
			
			if(filas==1)
					System.out.println("Producto agregado");
			else
				System.out.println("Producto no agregado");
		}
}
