select sum(valor_venda) from vendas where cpf_vendedor = '12345678909' -- registro de vendas

select * from vendas ORDER BY cod_venda
select * from cx 
select * from itens order by cod_item
select * from itens_vendidos
select * from funcionario
select * from recados

--VENDAS - CAIXA
select v.cod_venda, cx.numero as numero_caixa, v.cpf_vendedor, v.comissao, v.valor_venda, v.data_venda
from vendas v inner join cx on cx.cod_venda = v.cod_venda 

--TOTAL POR VENDEDOR
select cpf_vendedor, sum(valor_venda) from vendas inner join funcionario on cpf = cpf_vendedor group by cpf_vendedor


