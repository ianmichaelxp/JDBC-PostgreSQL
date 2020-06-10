package Main;

import java.sql.SQLException;

import DAO.VendasDAO;

public class TestaVendas {

	public static void main(String[] args) {
		
		VendasDAO venDAO = null;
		try 
		{
			System.out.println("Abrindo conexão...");
			venDAO = new VendasDAO();

		} catch (SQLException e) 
		{
			e.printStackTrace();

		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		/*FUNÇÕES PARA IMPLEMENTAR NO MAIN*/
//		venDAO.inserirItemCarrinho(numeroCx, cpfVendedor, codItem, qtd);
//		venDAO.finalizarVenda();
//		venDAO.consultarVendasData("2020-06-04", "2020-06-06", "12345678909"); 
//		venDAO.alterarVenda(codItem, qtdItem);
//		venDAO.exibirVendas();
//		venDAO.classificacarVendedores();
	}

}
