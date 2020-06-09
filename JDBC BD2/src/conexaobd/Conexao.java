package conexaobd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao 
{
	static final String url = "jdbc:postgresql://localhost/Teste_loja";
	static final String user = "postgres";
	static final String password = "admin";
	
	public static Connection criarConexao() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.postgresql.Driver"); 
		Connection conecta = DriverManager.getConnection (url, user, password);
		if (conecta != null)
		{
			System.out.print ("Conexão efetuada com sucesso...");
	
			return conecta; 
		}
		return null; 	
	}
}

