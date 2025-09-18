package main;

import java.util.ArrayList;

import dao.DaoCategoria;
import dao.DaoProducto;
import entidad.Categoria;
import entidad.Producto;

public class Principal {

	
		public static void main(String[] args) {
			DaoProducto productoDao = new DaoProducto();
			DaoCategoria categoriaDao = new DaoCategoria();
			
			//AGREGAR CATEGOR�A
			Categoria categoria1 = new Categoria();
			categoria1.setNombre("lacteos");
			int filasCategorias = categoriaDao.agregarCategoria(categoria1);
			
			if(filasCategorias == 1)
				System.out.println("Categoria agregada");
			else
				System.out.println("Categoria no agregada");
			
			
			//AGREGAR PRODUCTO
			Producto producto1 = new Producto();
			producto1.setCodigo("1");
			producto1.setNombre("leche");
			producto1.setPrecio(3000);
			producto1.setStock(10);
			producto1.setIdCategoria(categoria1.getId());
			
			int filasAgregadas = productoDao.sp_agregarProducto(producto1);
			if(filasAgregadas == 1)
				System.out.println("Producto agregado");
			else
				System.out.println("Producto no agregado");
			
			//OBTENER PRODUCTO
			Producto producto = productoDao.obtenerProducto("1");
			System.out.println(producto.toString());
			
			//MODIFICAR PRODUCTO
			producto.setNombre("Leche");
			producto.setPrecio(12999.99);
			producto.setStock(15);
			
			int filasModificadas = productoDao.modificarProducto(producto);
			if(filasModificadas == 1)
				System.out.println("Producto " + producto.getCodigo() + " modificado.");
			else
				System.out.println("No se pudo modificar el producto.");
			
			//OBTENER TODOS LOS PRODUCTOS
			ArrayList<Producto> productos = productoDao.obtenerTodosLosProductos();
			System.out.println("LISTADO DE PRODUCTOS:");
			for (Producto p : productos) {
				System.out.println(p.toString());
			}
			
			//ELIMINAR PRODUCTO
			int filasEliminadas = productoDao.eliminarProducto("1");
			if(filasEliminadas == 1)
				System.out.println("Producto eliminado.");
			else
				System.out.println("No se pudo eliminar el producto.");
			
			//ELIMINAR CATEGORIA
			int filasEliminadasCat = categoriaDao.eliminarCategoria("1");
			if(filasEliminadasCat == 1)
				System.out.println("Categoria eliminada.");
			else
				System.out.println("No se pudo eliminar la categoria.");
			
		}
}
