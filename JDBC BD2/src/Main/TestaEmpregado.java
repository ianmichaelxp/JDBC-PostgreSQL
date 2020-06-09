package Main;
import java.sql.SQLException;

import DAO.FuncionarioDAO;
import Entidades.Funcionario;

public class TestaEmpregado {

	public static void main(String[] args) 
	{
		FuncionarioDAO funDAO = null;
		try 
		{
			System.out.println("entrou no try");
			funDAO = new FuncionarioDAO();

		} catch (SQLException e) 
		{
			e.printStackTrace();

		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		System.out.println("In fun");
		funDAO.exibirFuncionariosCPF();
	}
	
	
	
	public static void testaInsert() 
	{

		Funcionario fun = new Funcionario();

		//atribuindo valores aos atributos do objeto criado

		fun.setCpf("12345556789");

		fun.setNome("Spheal Kuros");

		fun.setEndereco("S T J, 173");

//		String sValue = "2009-06-16";
//		java.sql.Date dtValue = java.sql.Date.valueOf(sValue);
		
		fun.setData_demissao(java.sql.Date.valueOf("2020-06-10"));
		

		fun.setMotivo_demissao("Faltas injustificas");

		fun.setFuncao("Vendedor");

		fun.setSalario(1500);
		
		fun.setClassificacao("Bom");
		
		FuncionarioDAO funDAO = null;

		//criação do objeto DAO

		try {
			System.out.println("entrou no try");

			funDAO = new FuncionarioDAO();

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

		//chamado do método que insere um novo empregado
		System.out.println("entrou no fun");
		funDAO.cadastrarFuncionario(fun);
	}
}
