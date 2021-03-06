package DAO;

import Util.Datas;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexaobd.Conexao;

public class VendasDAO 
{
	Datas data = new Datas();
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
				
			   System.out.print("\n|Cod item:  " + res.getInt("cod_item_") 
			   				+ "| Descrição: " + res.getString("descricao_item") 
			   				+ "| Setor:  " + res.getString("setor_item") 
			   				+ "| Estoque: " + res.getInt("estoque_item") 
			   				+ "| Preço:  " + res.getDouble("preco_item") 
			   				+ "| Prazo de garantia:  " + res.getString("garantia_item") 
			   				+ "| Numero NF:  " + res.getInt("numero_nf") 
			   				+ "| Quantidade:  " + res.getInt("qtd_item") +
			   				"|");
		
			}//Fim do while
			res.close();
			st.close();
		}//fim do try
		catch (SQLException e)
		{
			System.out.println("\nErro - " + e.getMessage());
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
				System.out.print("\nResposta do BD: ");
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
			ResultSet res = st.executeQuery("select * from classificacao_vendas()");
			while (res.next())
			{
				System.out.println(
							"\n|CPF:       " + res.getString("cpf_") 
							+ "| Nome:     " + res.getString("nome_") 
							+ "| Endereco: " + res.getString("endereco_") 
							+ "| Funcao:   " + res.getString("funcao_") 
							+ "| Salario:  " + res.getDouble("salario_") 
							+ "| Classif:  " + res.getString("classificacao_"));
			}//Fim do while
			res.close();
			st.close();
		}//fim do try
		catch (SQLException e)
		{
			System.out.println("Erro - " + e.getMessage());
		}
		
	}/*FIM DO MÉTODO CLASSIFICACAO VENDAS*/
	
	
	public void consultarVendasData(String dataInicial, String dataFinal, int codigoItem) 
	{
		try 
		{	//data_inicial date, data_final date, codigoitem integer	
			PreparedStatement st = con.prepareStatement("SELECT * from consultar_vendas_datas(?,?,?)");
			st.setDate(1, data.converteData(dataInicial));
			st.setDate(2, data.converteData(dataFinal));
			st.setInt(3, codigoItem);
			
			
			ResultSet res = st.executeQuery();
			while (res.next())
			{
				//codigo_venda integer, numero_caixa integer, cpf_vendedor varchar(11), comissao_venda double precision, valor_venda double precision, data_venda date, codigo_item integer, quantidade_item integer
				System.out.println(
							"\n | Código venda:   " + res.getInt("codigo_venda") 
							+ " | Número caixa:   " + res.getInt("numero_caixa") 
							+ " | CPF vendedor:   " + res.getString("cpf_vendedor") 
							+ " | Comissão venda: " + res.getString("comissao_venda") 
							+ " | Valor venda:    " + res.getDouble("valor_venda") 
							+ " | Data venda:     " + res.getDate("data_venda") 
							+ " | Código item:    " + res.getInt("codigo_item") 
							+ " | Quantidade:     " + res.getInt("quantidade_item") 
							+ " |");
			}//Fim do while
			res.close();
			st.close();
		}//fim do try
		catch (SQLException e)
		{
			System.out.println("Erro - " + e.getMessage());
		}
		
	}/*FIM DO METODO CONSULTA DATA*/
	
	public void consultarVendasData(String dataInicial, String dataFinal, String cpfVendedor) 
	{
		try 
		{	//data_inicial date, data_final date, codigoitem integer	
			PreparedStatement st = con.prepareStatement("SELECT * from consultar_vendas_datas(?,?,?)");
			st.setDate(1, data.converteData(dataInicial));
			st.setDate(2, data.converteData(dataFinal));
			st.setString(3, cpfVendedor);
			
			
			ResultSet res = st.executeQuery();
			while (res.next())
			{
				//codigo_venda integer, numero_caixa integer, cpf_vendedor varchar(11), comissao_venda double precision, valor_venda double precision, data_venda date, codigo_item integer, quantidade_item integer
				System.out.println(
							"\n | Código venda:   " + res.getInt("codigo_venda") 
							+ " | Número caixa:   " + res.getInt("numero_caixa") 
							+ " | CPF vendedor:   " + res.getString("cpf_vendedor") 
							+ " | Comissão venda: " + res.getString("comissao_venda") 
							+ " | Valor venda:    " + res.getDouble("valor_venda") 
							+ " | Data venda:     " + res.getDate("data_venda") 
							+ " | Código item:    " + res.getInt("codigo_item") 
							+ " | Quantidade:     " + res.getInt("quantidade_item") 
							+ " |");
			}//Fim do while
			res.close();
			st.close();
		}//fim do try
		catch (SQLException e)
		{
			System.out.println("Erro - " + e.getMessage());
		}
		
	}/*FIM DO METODO CONSULTA DATA*/
	
	public void alterarVenda(int codItem, int qtdItem) 
	{
		try 
		{	
			PreparedStatement st = con.prepareStatement("SELECT * from alterar_venda(?,?)");
			st.setInt(1, codItem);
			st.setInt(2, qtdItem);
			
			ResultSet res = st.executeQuery();
			while (res.next())
			{
				//num_nf integer, codigo_item integer, qtd integer
				System.out.println(
							"\n | Número nota:    " + res.getInt("num_nf") 
							+ " | Código item:    " + res.getInt("codigo_item") 
							+ " | Quantidade:     " + res.getInt("qtd") 
							+ " |");
			}//Fim do while
			res.close();
			st.close();
		}//fim do try
		catch (SQLException e)
		{
			System.out.println("Erro - " + e.getMessage());
		}
		
	}/*FIM DO METODO CONSULTA DATA*/
	
	public void exibirVendas() 
	{
		try 
		{	
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("select * from exibir_vendas()");
			while (res.next())
			{
				//codigo_venda integer, numero_caixa integer, cpf_vendedor varchar(11), comissao_venda double precision, valor_venda double precision, data_venda date
				System.out.print("\n | Código venda:   " + res.getInt("codigo_venda") 
				+ " | Número caixa:   " + res.getInt("numero_caixa") 
				+ " | CPF vendedor:   " + res.getString("cpf_vendedor") 
				+ " | Comissão venda: " + res.getString("comissao_venda") 
				+ " | Valor venda:    " + res.getDouble("valor_venda") 
				+ " | Data venda:     " + res.getDate("data_venda")  
				+ " |");
			}//Fim do while
			res.close();
			st.close();
		}//fim do try
		catch (SQLException e)
		{
			System.out.println("Erro - " + e.getMessage());
		}
		
	}/*FIM DO METODO EXIBIR VENDAS*/
}
