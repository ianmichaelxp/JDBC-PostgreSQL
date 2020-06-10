package Main;

import java.sql.SQLException;

import javax.swing.JOptionPane;

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
		
		int escolha = 0;

	    while (escolha != 9)
	    {
	       escolha = Integer.parseInt(JOptionPane.showInputDialog("Menu:"
	           + "\n1- Abrir venda"
	           + "\n2- Exibir todas as vendas"
	           + "\n3- Consultar vendas por data"
	           + "\n4- Alterar nota fiscal"
	           + "\n5- Classificação funcionários"
	           + "\n9- Sair"));
	       switch (escolha)
	       {
	           case 1:
	        	   int numeroCx = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do seu caixa:"));
	        	   String cpfVendedor = JOptionPane.showInputDialog("Digite o seu cpf/matricula:");
	        	   int codItem = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do item:"));
	        	   int qtd = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade:"));
	        	   int continua = Integer.parseInt(JOptionPane.showInputDialog("Deseja inserir mais itens? \nDigite 1 - sim ou 0 - não"));
	        	   while(continua > 0) 
	        	   {
	        		   codItem = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do item:"));
		        	   qtd = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade:"));
		        	   continua = Integer.parseInt(JOptionPane.showInputDialog("Deseja inserir mais itens? \nDigite 1 - sim ou 0 - não"));
	        		   venDAO.inserirItemCarrinho(numeroCx, cpfVendedor, codItem, qtd);
	        	   }
	        	   venDAO.inserirItemCarrinho(numeroCx, cpfVendedor, codItem, qtd);
	        	   venDAO.finalizarVenda();
	               break;
	           case 2:
	        	   
	        	   venDAO.exibirVendas();
	               break;
	           case 3:
	        	   String dataInicial = JOptionPane.showInputDialog("Digite a data inicial: aaaa-mm-dd");
	        	   String dataFinal = JOptionPane.showInputDialog("Digite a data final: aaaa-mm-dd");
	        	   int codItem2 = Integer.parseInt(JOptionPane.showInputDialog("Digite 1 - codigo ou 2 - CPF"));
	        	   if(codItem2 == 1) 
	        	   {
	        		   codItem2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do item: "));
	        		   venDAO.consultarVendasData(dataInicial, dataFinal, codItem2);
	        	   }
	        	   else if(codItem2 == 2) 
	        	   {
	        		   String cpfFiltro = JOptionPane.showInputDialog("Digite o seu cpf/matricula:");
	        		   venDAO.consultarVendasData(dataInicial, dataFinal, cpfFiltro);
	        	   } 
	               break;
	           case 4:
	        	   int codItem3 = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do item que quer alterar: "));
	        	   int qtdItem = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova quantidade: "));
	        	   venDAO.alterarVenda(codItem3, qtdItem);
	        	   break;
	           case 5:
	        	   venDAO.classificacarVendedores();
	        	   break;
	        }
	    }
	}

}
