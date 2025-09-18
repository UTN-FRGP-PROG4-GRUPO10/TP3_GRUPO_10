package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
			String query = "insert into categorias(nombre) values ('" + categoria.getNombre() + "' )";
			Connection cn = null;
			int filas = 0;
			try
			{
				cn = DriverManager.getConnection(host + dbName,user,pass);
					Statement st = cn.createStatement();
					filas = st.executeUpdate(query);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return filas;
		}
		
		public int eliminarCategoria(String idCategoria) {
			
			try {
				String query = "SELECT COUNT(*) FROM productos WHERE idCategoria = ?";
				Connection cn = null;
				cn = DriverManager.getConnection(host + dbName, user, pass);
				PreparedStatement pst = cn.prepareStatement(query);
				pst.setString(1, idCategoria);
				
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
		            int cantidad = rs.getInt(1); 
		            if (cantidad > 0) {
		                return 0;
		            }
		        }

			}catch(Exception e) {
				e.printStackTrace();
			}
			
			String query = "DELETE FROM categorias WHERE IdCategoria = ?";
			Connection cn = null;
			int filas = 0;
			
			try {
				
				cn = DriverManager.getConnection(host + dbName, user, pass);
				PreparedStatement pst = cn.prepareStatement(query);
				pst.setString(1, idCategoria);
				filas = pst.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return filas;
		}
		
}
