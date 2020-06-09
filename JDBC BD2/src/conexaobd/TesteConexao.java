package conexaobd;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao 
{
	public static void main(String[] args) 
	{
		
		try {
			System.out.println("ok");
			Connection con = Conexao.criarConexao();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
