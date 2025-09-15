package main;

import dao.DaoProducto;
import entidad.Producto;

public class Principal {

	
		public static void main(String[] args) {
			DaoProducto productoDao = new DaoProducto();
			
			Producto producto1 = new Producto();
			producto1.setCodigo("1");
			producto1.setNombre("leche");
			producto1.setPrecio(3000);
			producto1.setStock(10);
			producto1.setIdCategoria(1);
			
			int filas = productoDao.agregarProducto(producto1);
			
			if(filas==1)
					System.out.println("Producto agregado");
			else
				System.out.println("Producto no agregado");
		}
}
