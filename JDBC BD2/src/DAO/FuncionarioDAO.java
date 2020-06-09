package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entidades.Funcionario;
import conexaobd.Conexao;

public class FuncionarioDAO 
{
	private Connection con; //atributo de conexão

	public FuncionarioDAO() throws SQLException, ClassNotFoundException 
	{
		con = Conexao.criarConexao();
	}
	
	public void exibirFuncionariosCPF() 
	{
		try 
		{
			PreparedStatement st = con.prepareStatement("SELECT * FROM funcionario where cpf = ?");
			st.setString(1, "12345678900");
			ResultSet res = st.executeQuery();
			while (res.next())
			{
			   System.out.print("CPF: " + res.getString("cpf") + " Nome: " + res.getString("cpf") + " Endereco: " + 
					   res.getString("endereco") + " Data de demissão: " + res.getDate("data_demissao") + " Motivo da demissão: " + 
					   res.getString("motivo_demissao") + " Funcao: " + res.getString("funcao") + " Salario: " + 
					   res.getDouble("salario") + " Classificacao: " + res.getString("classificacao"));
		
			}
			res.close();
			st.close();
		}catch (SQLException e)
		{
			System.out.println("Erro - " + e.getMessage());
		}
	}
	
	public void cadastrarFuncionario(Funcionario fun) 
	{

		String sql = "INSERT INTO funcionario(cpf, nome, endereco, data_demissao, funcao, salario, classificacao) VALUES(?,?,?,?,?,?,?)"; 

		try 
		{ //fazer a atribuição dos valores

			PreparedStatement preparador = con.prepareStatement(sql);
	
			preparador.setString(1, fun.getCpf());
	
			preparador.setString(2, fun.getNome());
			
			preparador.setString(3, fun.getEndereco());

			preparador.setDate(4, fun.getData_demissao());

			preparador.setString(5, fun.getFuncao());

			preparador.setDouble(6, fun.getSalario());

			preparador.setString(7, fun.getClassificacao());

			preparador.execute(); 
			
			preparador.close();

			System.out.println("Inserção realizada!");
		}
		

		catch (SQLException e)
		{

			System.out.println("Erro - " + e.getMessage());

		}
		
	}

}
