package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import entidad.Categoria;

public class DaoCategoria {
		private String host = "jdbc:mysql://localhost:3306/";
		private String user = "root";
		private String pass = "root";
		private String dbName = "bdinventario";
		
		public DaoCategoria()
		{
			
		}
		
		public int agregarCategoria(Categoria categoria)
		{
			String query = "INSERT INTO categorias (nombre) VALUES (?)";
			Connection cn = null;
			int filas = 0;
			int idGenerado = -1;
			
			try
			{
				cn = DriverManager.getConnection(host + dbName,user,pass);
				PreparedStatement pst = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				
				pst.setString(1, categoria.getNombre());
				filas = pst.executeUpdate();
					
				if(filas > 0) {
					ResultSet rs = pst.getGeneratedKeys();
					if(rs.next()) {
						idGenerado = rs.getInt(1);
						categoria.setId(idGenerado);
					}
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return idGenerado;
		}
		
		public int eliminarCategoria(int idCategoria) {
			
			String query = "DELETE FROM categorias WHERE IdCategoria = ?";
			Connection cn = null;
			int filas = 0;
			
			try {
				
				cn = DriverManager.getConnection(host + dbName, user, pass);
				PreparedStatement pst = cn.prepareStatement(query);
				pst.setInt(1, idCategoria);
				filas = pst.executeUpdate();
				
			} catch (MySQLIntegrityConstraintViolationException e) {
				System.out.println("ERROR: La categoría tiene productos asociados.");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return filas;
		}
		
		public int modificarCategoria(Categoria categoria) {
			
			String query = "UPDATE categorias SET Nombre = ? WHERE IdCategoria = ?";
			Connection cn = null;
			
			try {
				
				cn = DriverManager.getConnection(host + dbName, user, pass);
				PreparedStatement pst = cn.prepareStatement(query);
				pst.setString(1, categoria.getNombre());
				pst.setInt(2, categoria.getId());
				
				return pst.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		
		public Categoria obtenerCategoria(int id) {
			
			String query = "SELECT * FROM categorias WHERE IdCategoria = ?";
			Categoria categoria = new Categoria();
			Connection cn = null;
			
			try {
				cn = DriverManager.getConnection(host + dbName, user, pass);
				PreparedStatement pst = cn.prepareStatement(query);
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()) {
					categoria.setId(id);
					categoria.setNombre(rs.getString("Nombre"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return categoria;
			
		}
		
		public ArrayList<Categoria> obtenerTodasLasCategorias() {
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			Connection cn = null;
			
			try {
				cn = DriverManager.getConnection(host + dbName, user, pass);
				Statement st = cn.createStatement();
				String query = "SELECT * FROM categorias";
				ResultSet rs = st.executeQuery(query);
				
				while(rs.next()) {
					Categoria categoria = new Categoria();
					categoria.setId(rs.getInt("IdCategoria"));
					categoria.setNombre(rs.getString("Nombre"));
					
					categorias.add(categoria);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return categorias;
		}
		
}
