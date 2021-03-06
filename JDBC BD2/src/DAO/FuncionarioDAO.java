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
			   System.out.print("CPF: " + res.getString("cpf") + " Nome: " + res.getString("nome") + " Endereco: " + 
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
		
	}/*FIM DO MÉTODO CADASTRO FUNCIONARIO*/
	

	public void alterarSalario(String cpf_ou_nome, double novo_salario) /*filtro varchar, novo_sal double precision*/
	{
		try 
		{			
			//(numero_cx integer, cpf_v varchar, cod_item integer, qtd integer)
			PreparedStatement st = con.prepareStatement("SELECT * from alterar_salario(?,?)");
			st.setString(1, cpf_ou_nome);
			st.setDouble(2, novo_salario);
			
			ResultSet res = st.executeQuery();
			while (res.next())
			{
				//cpf_ varchar, nome_ varchar, endereco_ varchar, funcao_ varchar, salario_ double precision
				System.out.println("\n| CPF: " + res.getString("cpf_") + " | Nome: " + res.getString("nome_") + " | Endereco: " + 
						   res.getString("endereco_") + " | Funcao: " + res.getString("funcao_") + " | Salario: " + 
						   res.getDouble("salario_") + " |");
			}//Fim do while
			res.close();
			st.close();
		}//fim do try
		catch (SQLException e)
		{
			System.out.println("Erro - " + e.getMessage());
		}
	}/*FIM DO MÉTODO ALTERAR SALARIO*/

}
