package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexaobd.Conexao;

public class VendasDAO 
{
	private Connection con; //atributo de conexão

	public VendasDAO() throws SQLException, ClassNotFoundException 
	{
		con = Conexao.criarConexao();
	}
	
	public void inserirItemCarrinho(int numeroCx, String cpfVendedor, int codItem, int qtd)
	{
		try 
		{			
			//(numero_cx integer, cpf_v varchar, cod_item integer, qtd integer)
			PreparedStatement st = con.prepareStatement("SELECT * FROM inserir_item_carrinho(?,?,?,?)");
			st.setInt(1, numeroCx);
			st.setString(2, cpfVendedor);
			st.setInt(3, codItem);
			st.setInt(4, qtd);
			
			ResultSet res = st.executeQuery();
			while (res.next())
			{
				//cod_item_ integer, descricao_item varchar, setor_item varchar, estoque_item smallint, 
				//preco_item double precision, garantia_item varchar, numero_nf integer, qtd_item integer)
				
			   System.out.print("Cod item: " + res.getInt("cod_item_") 
			   				+ " Descrição: " + res.getString("descricao_item") 
			   				+ " Setor: " + res.getString("setor_item") 
			   				+ " Estoque: " + res.getInt("estoque_item") 
			   				+ " Preço: " + res.getDouble("preco_item") 
			   				+ " Prazo de garantia: " + res.getString("garantia_item") 
			   				+ " Numero NF: " + res.getInt("numero_nf") 
			   				+ " Quantidade: " + res.getInt("qtd_item"));
		
			}//Fim do while
			res.close();
			st.close();
		}//fim do try
		catch (SQLException e)
		{
			System.out.println("Erro - " + e.getMessage());
		}
	}/*FIM DO MÉTODO INSERIR ITEM CARRINHO*/
	
	public void finalizarVenda() 
	{
		try 
		{	
			//(numero_cx integer, cpf_v varchar, cod_item integer, qtd integer)
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("SELECT finalizar_venda()");
			while (res.next())
			{
				System.out.print("Resposta do BD: ");
				System.out.println(res.getString(1));
			}//Fim do while
			res.close();
			st.close();
		}//fim do try
		catch (SQLException e)
		{
			System.out.println("Erro - " + e.getMessage());
		}	
	}/*FIM DO METODO FINALIZAR VENDA*/
	
	public void classificacarVendedores() 
	{
		/*cpf_ varchar, nome_ varchar, endereco_ varchar, funcao_ varchar, salario_ double precision, classificacao_ varchar*/
		try 
		{	
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("select classificacao_vendas()");
			while (res.next())
			{
				System.out.print("CPF: " + res.getString("cpf_") + " Nome: " + res.getString("nome_") + " Endereco: " + 
						   res.getString("endereco_") + " Data de demissão: " + " Funcao: " + res.getString("funcao_") + " Salario: " + 
						   res.getDouble("salario_") + " Classificacao: " + res.getString("classificacao_"));
			}//Fim do while
			res.close();
			st.close();
		}//fim do try
		catch (SQLException e)
		{
			System.out.println("Erro - " + e.getMessage());
		}
		
	}/*FIM DO MÉTODO CLASSIFICACAO VENDAS*/
	
}
