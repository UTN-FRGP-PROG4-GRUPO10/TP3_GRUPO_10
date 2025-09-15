package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import entidad.Producto;

public class DaoProducto {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "bdinventario";
	
	public DaoProducto()
	{
		
	}
	
	public int agregarProducto(Producto producto) {
		
		String query = "INSERT INTO productos(Codigo, Nombre, Precio, Stock, IdCategoria)"
				+ " VALUES('"+producto.getCodigo()+"','"+producto.getNombre()+"','"+producto.getPrecio()+"','"+producto.getStock()+"','"+producto.getIdCategoria()+"')";
		Connection cn = null;
		int filas=0;
		
		try {
				
			cn = DriverManager.getConnection(host+dbName,user,pass);
			Statement st = cn.createStatement();
			filas = st.executeUpdate(query);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}
}
