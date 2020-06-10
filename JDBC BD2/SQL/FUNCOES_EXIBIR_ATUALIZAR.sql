create or replace function inserir_funcionario(cpf_func varchar, nome_func varchar, endereco_func varchar, funcao_func varchar, salario_func double precision)
returns TABLE (cpf_ varchar, nome_ varchar, endereco_ varchar, funcao_ varchar, salario_ double precision) as $$
declare codigo_venda integer;
begin
	insert into funcionario(cpf, nome, endereco, funcao, salario) values (cpf_func, nome_func, endereco_func, funcao_func, salario_func);
	return query select cpf, nome, endereco, funcao, salario
	from funcionario;
end;
$$ language plpgsql;

select * from exibir_vendas()
create or replace function exibir_vendas ()
returns table (codigo_venda integer, numero_caixa integer, cpf_vendedor varchar(11), comissao_venda double precision, valor_venda double precision, data_venda date) as $exibir_vendas$
begin
	return query select v.cod_venda, cx.numero as numero_caixa, v.cpf_vendedor, v.comissao, v.valor_venda, v.data_venda
	from vendas v inner join cx on cx.cod_venda = v.cod_venda;
end;
$exibir_vendas$ language plpgsql;

select * from classificacao_vendas()
create or replace function classificacao_vendas ()
returns table (cpf_ varchar, nome_ varchar, endereco_ varchar, funcao_ varchar, salario_ double precision, classificacao_ varchar) as $$
declare rec_class record;
begin
	create temporary table tabela_soma_vendas as 
	select cpf_vendedor, sum(valor_venda) as soma_vendas
	from vendas inner join funcionario on cpf = cpf_vendedor 
	group by cpf_vendedor;
	
	FOR rec_class IN SELECT * FROM tabela_soma_vendas LOOP
		if rec_class.soma_vendas <= 50000 then
			update funcionario set classificacao = 'Vendedor regular'
			where cpf = rec_class.cpf_vendedor;
		elsif rec_class.soma_vendas <= 200000 then
			update funcionario set classificacao = 'Bom Vendedor'
			where cpf = rec_class.cpf_vendedor;
		elsif rec_class.soma_vendas > 200000 then
			update funcionario set classificacao = 'Otimo Vendedor'
			where cpf = rec_class.cpf_vendedor;
		end if;
	END LOOP;

	drop table if exists tabela_soma_vendas;
	return query select cpf, nome, endereco, funcao, salario, classificacao
	from funcionario; 
end;
$$ language plpgsql;

select * from alterar_salario(filtro, novo_sal)
create or replace function alterar_salario(filtro varchar, novo_sal double precision)
returns TABLE (cpf_ varchar, nome_ varchar, endereco_ varchar, funcao_ varchar, salario_ double precision) as $$
begin
	update funcionario set salario = novo_sal
	where cpf = filtro or nome = filtro;
	
	return query select cpf, nome, endereco, funcao, salario
	from funcionario;
end;
$$ language plpgsql;

create or replace function apagar_recado(codigo integer)
returns table (msg varchar, cod integer) as $$
begin
	delete from recados
	where cod_item = codigo;
	
	return query select mensagem, cod_item from recados;
end;
$$ language plpgsql;

create or replace function alterar_recado(codigo integer, texto varchar)
returns table (msg varchar, cod integer) as $$
begin
	update mensagem set mensagem = texto
	where cod_item = codigo;
	
	return query select mensagem, cod_item from recados;
end;
$$ language plpgsql;

create or replace function consultar_vendas_datas(data_inicial date, data_final date, codigoitem integer)
returns table (codigo_venda integer, numero_caixa integer, cpf_vendedor varchar(11), comissao_venda double precision, valor_venda double precision, data_venda date, codigo_item integer, quantidade_item integer)
as $$
begin
	
	return query select v.cod_venda, cx.numero, v.cpf_vendedor, v.comissao, v.valor_venda, v.data_venda, i.cod_item, i.quantidade
					from vendas v inner join cx on cx.cod_venda = v.cod_venda 
					inner join itens_vendidos i on i.numero_nf = v.cod_venda
					where (v.data_venda between data_inicial and data_final) and i.cod_item = codigoitem;
end;
$$ language plpgsql;

create or replace function consultar_vendas_datas(data_inicial date, data_final date, cpfvend varchar)
returns table (codigo_venda integer, numero_caixa integer, cpf_vendedor varchar(11), comissao_venda double precision, valor_venda double precision, data_venda date, codigo_item integer, quantidade_item integer)
as $$
begin
	return query select v.cod_venda, cx.numero, v.cpf_vendedor, v.comissao, v.valor_venda, v.data_venda, i.cod_item, i.quantidade
					from vendas v inner join cx on cx.cod_venda = v.cod_venda 
					inner join itens_vendidos i on i.numero_nf = v.cod_venda
					where (v.data_venda between data_inicial and data_final) and v.cpf_vendedor = cpfvend;
end;
$$ language plpgsql;

create or replace function alterar_venda(codigoitem integer, qtditem integer)
returns table (num_nf integer, codigo_item integer, qtd integer) as $$
declare 
	codigo_rec RECORD;
begin
	select cod_venda INTO codigo_rec from cx order by cod_venda desc;
	update itens_vendidos set cod_item = codigoitem
	where numero_nf = codigo_rec.cod_venda;
	update itens_vendidos set quantidade = qtditem
	where numero_nf = codigo_rec.cod_venda;
	
	return query select * from itens_vendidos;
end;
$$ language plpgsql;

select * from alterar_venda(4,2)

create or replace function alterar_recado(codigo integer, texto varchar)
returns table (msg varchar, cod integer) as $$
begin
	update mensagem set mensagem = texto
	where cod_item = codigo;
	
	return query select mensagem, cod_item from recados;
end;
$$ language plpgsql;


------------------------------------------TESTES------------------------------------------
------------------------------------------------------------------------------------------

select * from alterar_salario('Joao Pedro', 1045.00)
select * from funcionario
select * from recados
select * from alterar_recado(codigo, texto)
select inserir_funcionario('10020030000', 'Thiago Nunes', 'M A S, 55', 'vendedor', 1100.00)