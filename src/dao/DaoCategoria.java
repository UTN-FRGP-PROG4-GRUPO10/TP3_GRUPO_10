package dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
		
}
