package Main;

import java.sql.SQLException;

import DAO.VendasDAO;

public class TestaVendas {

	public static void main(String[] args) {
		
		VendasDAO venDAO = null;
		try 
		{
			System.out.println("entrou no try");
			venDAO = new VendasDAO();

		} catch (SQLException e) 
		{
			e.printStackTrace();

		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		/*FUNÇÕES PARA IMPLEMENTAR NO MAIN*/
//		venDAO.alterarSalario("98765432100", 1500);
//		venDAO.inserirItemCarrinho(numeroCx, cpfVendedor, codItem, qtd);
//		venDAO.finalizarVenda();
		

	}

}
