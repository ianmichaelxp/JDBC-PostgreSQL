package Entidades;

import java.sql.Date;

public class Funcionario {
	
	private String cpf;
	private String nome;
	private String endereco;
	private Date data_demissao;
	private String motivo_demissao;
	private String funcao;
	private double salario;
	private String classificacao;
	

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Date getData_demissao() {
		return data_demissao;
	}
	public void setData_demissao(Date i) {
		this.data_demissao = i;
	}
	public String getMotivo_demissao() {
		return motivo_demissao;
	}
	public void setMotivo_demissao(String motivo_demissao) {
		this.motivo_demissao = motivo_demissao;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	
	

}
