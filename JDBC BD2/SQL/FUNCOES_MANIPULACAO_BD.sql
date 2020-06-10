
create or replace function abrir_venda(numero_cx integer, cpf_v varchar)
returns TABLE (cod_vend integer, num_cx integer, cpf_vnd varchar(11)) as $$
declare codigo_venda integer;
begin
	insert into cx(numero, cpf_vendedor) values (numero_cx, cpf_v);
	return query select cod_venda, numero, cpf_vendedor
	from cx;
end;
$$ language plpgsql;

create or replace function inserir_item_carrinho(numero_cx integer, cpf_v varchar, codigo_item integer, qtd integer)
RETURNS table (cod_item_ integer, descricao_item varchar, setor_item varchar, estoque_item integer, 
			   preco_item double precision, garantia_item varchar, numero_nf integer, qtd_item integer)AS $$
declare venda RECORD;
BEGIN
	create temporary table if not exists temp_cx as 
	select *
	from abrir_venda(numero_cx, cpf_v);
	
	select tmp.cod_vend into venda 
	from temp_cx tmp
	order by tmp.cod_vend desc;
	
	update itens set estoque = (estoque-qtd)
	where cod_item = codigo_item;
	
	INSERT INTO itens_vendidos values (venda.cod_vend, codigo_item, qtd);
	return query select i.cod_item, i.descricao, i.setor, i.estoque, i.valor, i.garantia, iv.numero_nf, iv.quantidade
		from itens i inner join itens_vendidos iv on iv.cod_item = i.cod_item where iv.numero_nf = 8;--venda.cod_vend;
END;
$$ language plpgsql;

create or replace function finalizar_venda()
returns text as $$
declare 
    valor_total double precision;
	codigo_rec RECORD;
-- 	comissao_venda double precision;
begin

	select cod_vend, cpf_vnd  INTO codigo_rec from temp_cx order by cod_vend desc;
	create temporary table tmp_total as
	SELECT it.setor, sum(it.valor * iv.quantidade) as soma_valor
	from itens_vendidos iv, itens it
	where iv.numero_nf = codigo_rec.cod_vend and
	iv.cod_item = it.cod_item
	group by it.setor;
	
 	select sum(soma_valor) into valor_total
 	from tmp_total;
	
	insert into vendas(cod_venda, cpf_vendedor, valor_venda, data_venda) 
		values (codigo_rec.cod_vend, codigo_rec.cpf_vnd, valor_total, current_date);
 	drop table if exists tmp_total;
 	drop table if exists temp_cx;
	return 'Venda finalizada';
end;
$$ language plpgsql;

create or replace function trg_estoque_vazio() 
RETURNS trigger AS $$
    BEGIN
	if new.estoque = 0 then
		insert into recados values ('Estoque do produto acabou', new.cod_item);
	elsif new.estoque < 0 then 
		RAISE exception 'Estoque do produto: % acabou', new.descricao
		USING HINT = 'Inserir próximo produto ou finalizar venda';
    END IF;
	RETURN new;
END;
$$ language plpgsql;

create or replace function trg_item_comissao() 
RETURNS trigger AS $$
declare comissao_ee double precision;
declare comissao_ed double precision;
    BEGIN
	comissao_ed = 0;
	comissao_ee = 0;
	--classificacao_vendas, consultar_vendas_datas, finalizar_vendas, inserir_funcionario, inserir_item_carrinho
	SELECT * FROM funcionario
 	 select sum((i.valor) * 0.03) into comissao_ed
 	 from itens_vendidos iv inner join itens i on iv.cod_item = i.cod_item 
 	 where iv.numero_nf = new.cod_venda and i.setor = 'ed' group by i.setor;
	 
	 select sum((i.valor) * 0.05) into comissao_ee
 	 from itens_vendidos iv inner join itens i on iv.cod_item = i.cod_item 
 	 where iv.numero_nf = new.cod_venda and i.setor = 'ee' group by i.setor;
	 
--   	 select case when itens.setor = 'ee' then (sum((itens.valor) * 0.05) into comissao_ee) end
--   	 from itens inner join itens_vendidos on itens.cod_item = itens_vendidos.cod_item 
--   	 where itens_vendidos.numero_nf = new.cod_venda group by itens.setor;
	 
	 if(comissao_ee is not null and comissao_ed is not null) then new.comissao := comissao_ee + comissao_ed;
	 elsif(comissao_ee is null and comissao_ed is not null) then new.comissao := comissao_ed;
	 elsif(comissao_ee is not null and comissao_ed is null) then new.comissao := comissao_ee;
	 end if;
	 
 	insert into recados values ('trigger comissao');
	return new;
END;
$$ language plpgsql;

CREATE TRIGGER venda_comissao
  before INSERT OR UPDATE ON vendas
    FOR EACH ROW EXECUTE PROCEDURE trg_item_comissao();

CREATE TRIGGER estoque_vazio
  before UPDATE ON itens
    FOR EACH ROW EXECUTE PROCEDURE trg_estoque_vazio();

    




------------------------------------------TESTES------------------------------------------
------------------------------------------------------------------------------------------

select inserir_item_carrinho(1, '12345678909', 1, 2);
select inserir_item_carrinho(1, '12345678909', 2, 2);
select inserir_item_carrinho(1, '12345678909', 3, 1);
select finalizar_venda();

select inserir_item_carrinho(2, '12345678900', 1, 3);
select inserir_item_carrinho(2, '12345678900', 2, 1);
select finalizar_venda();

select inserir_item_carrinho(3, '98765432100', 1, 1);
select inserir_item_carrinho(3, '98765432100', 2, 5);
select finalizar_venda();

select * from inserir_item_carrinho(2, '12345678900', 4, 1);
select finalizar_venda();

select inserir_item_carrinho(3, '54334554311', 4, 2);
select inserir_item_carrinho(3, '54334554311', 2, 1);
select finalizar_venda();


---------------------------------------------------------------------------------------------------