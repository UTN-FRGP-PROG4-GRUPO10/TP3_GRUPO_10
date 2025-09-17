package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public int eliminarProducto(String codigo) {
		
		String query = "DELETE FROM productos WHERE Codigo = ?";
		Connection cn = null;
		int filas = 0;
		
		try {
			
			cn = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement pst = cn.prepareStatement(query);
			pst.setString(1, codigo);
			filas = pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}
	
	public int modificarProducto(Producto producto) {
		
		String query = "UPDATE productos SET Nombre = ?, Precio = ?, Stock = ?, IdCategoria = ? WHERE Codigo = ?";
		Connection cn = null;
		int filas = 0;
		
		try {
			
			cn = DriverManager.getConnection(host + dbName, user, pass);
			PreparedStatement pst = cn.prepareStatement(query);
			pst.setString(1, producto.getNombre());
			pst.setDouble(2, producto.getPrecio());
			pst.setInt(3, producto.getStock());
			pst.setInt(4, producto.getIdCategoria());
			pst.setString(5, producto.getCodigo());
			
			filas = pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}
	
	public Producto obtenerProducto(String codigo) {
		
		Producto producto = new Producto();
		Connection cn = null;
		
		try {
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM productos WHERE Codigo = '" + codigo + "'";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				producto.setCodigo(codigo);
				producto.setNombre(rs.getString("Nombre"));	
				producto.setPrecio(rs.getDouble("Precio"));	
				producto.setStock(rs.getInt("Stock"));
				producto.setIdCategoria(rs.getInt("IdCategoria"));				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return producto;
	}
	
	public ArrayList<Producto> obtenerTodosLosProductos() {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		Connection cn = null;
		try {
			cn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = cn.createStatement();
			String query = "SELECT * FROM productos";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				Producto producto = new Producto();
				producto.setCodigo(rs.getString("Codigo"));
				producto.setNombre(rs.getString("Nombre"));	
				producto.setPrecio(rs.getDouble("Precio"));	
				producto.setStock(rs.getInt("Stock"));
				producto.setIdCategoria(rs.getInt("IdCategoria"));
				
				productos.add(producto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productos;
	}
}
