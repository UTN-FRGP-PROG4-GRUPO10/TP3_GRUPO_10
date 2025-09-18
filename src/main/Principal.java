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
			
			//AGREGAR CATEGORIA
			Categoria categoria1 = new Categoria("Alimento");
			Categoria categoria2 = new Categoria("limpieza (modificar)");
			Categoria categoria3 = new Categoria("Eliminar");
			
			int idCategoriaCreada1 = categoriaDao.agregarCategoria(categoria1);
			if(idCategoriaCreada1 != -1)
				System.out.println("Categoria '" + categoria1.getNombre() + "' agregada");
			else
				System.out.println("Categoria no agregada");
			
			int idCategoriaCreada2 = categoriaDao.agregarCategoria(categoria2);
			if(idCategoriaCreada2 != -1)
				System.out.println("Categoria '" + categoria2.getNombre() + "' agregada");
			else
				System.out.println("Categoria no agregada");
			
			int idCategoriaCreada3 = categoriaDao.agregarCategoria(categoria3);
			if(idCategoriaCreada3 != -1)
				System.out.println("Categoria '" + categoria3.getNombre() + "' agregada");
			else
				System.out.println("Categoria no agregada");
			
			//OBTENER CATEGORIA
			Categoria categoriaObtenida = categoriaDao.obtenerCategoria(idCategoriaCreada2);
			System.out.println("OBTENIENDO CATEGORIA DE BASE DE DATOS: ");
			System.out.println(categoriaObtenida.toString());
			
			//MODIFICAR CATEGORIA
			categoriaObtenida.setNombre("Limpieza (modificado)");
			if(categoriaDao.modificarCategoria(categoriaObtenida) != 0){
				System.out.println("CATEGORIA MODIFICADA");
			} else {
				System.out.println("NO SE PUDO MODIFICAR LA CATEGORIA");
			}
			
			//ELIMINAR CATEGORIA
			int catFilasEliminadas = categoriaDao.eliminarCategoria(idCategoriaCreada3);
			if(catFilasEliminadas > 0) {
				System.out.println("CATEGORIA ELIMINADA");
			} else {
				System.out.println("NO SE PUDO ELIMINAR LA CATEGORIA");
			}
			
			//OBTENER TODAS LAS CATEGORIAS
			ArrayList<Categoria> categorias = categoriaDao.obtenerTodasLasCategorias();
			System.out.println("LISTADO DE CATEGORIAS:");
			for (Categoria c : categorias) {
				System.out.println(c.toString());
			}
			
			
			
			//AGREGAR PRODUCTOS
			ArrayList<Producto> nuevosProductos = new ArrayList<Producto>();
			//ALIMENTO
			nuevosProductos.add(new Producto("A123", "Cereales", 4999.99, 25, idCategoriaCreada1));
			nuevosProductos.add(new Producto("A124", "Arroz", 2999.99, 40, idCategoriaCreada1));
			nuevosProductos.add(new Producto("A125", "Fideos (eliminar)", 1999.99, 35, idCategoriaCreada1));
			nuevosProductos.add(new Producto("A126", "Leche", 5999.99, 20, idCategoriaCreada1));
			nuevosProductos.add(new Producto("A127", "Huevos", 2499.99, 50, idCategoriaCreada1));
			//LIMPIEZA
			nuevosProductos.add(new Producto("B123", "Detergente", 3999.99, 13, idCategoriaCreada2));
			nuevosProductos.add(new Producto("B124", "Lavandina", 3499.99, 15, idCategoriaCreada2));
			nuevosProductos.add(new Producto("B125", "Jabón en barra", 999.99, 30, idCategoriaCreada2));
			nuevosProductos.add(new Producto("B126", "Limpiador multiuso", 2799.99, 25, idCategoriaCreada2));
			nuevosProductos.add(new Producto("B127", "Esponjas", 499.99, 60, idCategoriaCreada2));
			
			int contadorAgregados = 0;
			for (Producto p : nuevosProductos) {
				int resultado = productoDao.sp_agregarProducto(p);
				if(resultado != 0)
					contadorAgregados ++;
			}
			
			System.out.println("PRODUCTOS AGREGADOS: " + contadorAgregados + "/" + nuevosProductos.size());
			
			//OBTENER PRODUCTO
			Producto productoObtenido = productoDao.obtenerProducto(nuevosProductos.get(0).getCodigo());
			System.out.println("OBTENIENDO PRODUCTO DE BASE DE DATOS: ");
			System.out.println(productoObtenido.toString());
			
			//MODIFICAR PRODUCTO
			productoObtenido.setNombre("cereales (modificado)");
			if(productoDao.modificarProducto(productoObtenido) != 0){
				System.out.println("PRODUCTO MODIFICADO");
			} else {
				System.out.println("NO SE PUDO MODIFICAR EL PRODUCTO");
			}
			
			//ELIMINAR PRODUCTO
			int prodFilasEliminadas = productoDao.eliminarProducto(nuevosProductos.get(2).getCodigo());
			if(prodFilasEliminadas > 0) {
				System.out.println("PRODUCTO ELIMINADO");
			} else {
				System.out.println("NO SE PUDO ELIMINAR EL PRODUCTO");
			}
			
			//OBTENER TODOS LOS PRODUCTOS
			ArrayList<Producto> productos = productoDao.obtenerTodosLosProductos();
			System.out.println("LISTADO DE PRODUCTOS:");
			for (Producto p : productos) {
				System.out.println(p.toString());
			}
			
		}
}
