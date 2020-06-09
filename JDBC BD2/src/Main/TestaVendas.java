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

		venDAO.inserirItemCarrinho(12,"12345678900", 1, 1);
		venDAO.finalizarVenda();

	}

}
