-- Database: Loja
drop schema public cascade;
create schema public;
CREATE DATABASE "Loja"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'pt_BR.UTF-8'
    LC_CTYPE = 'pt_BR.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	

CREATE TABLE IF NOT EXISTS recados
(
	mensagem VARCHAR(55),
	cod_item integer

);
-- ALTER TABLE public.recados
--     ADD COLUMN cod_item integer;

CREATE TABLE IF NOT EXISTS funcionario
(
	cpf VARCHAR(11) NOT NULL,
	nome VARCHAR (50) NOT NULL,
	endereco VARCHAR (50),
	data_demissao DATE,
	motivo_demissao VARCHAR DEFAULT NULL,
	funcao VARCHAR (50),
	salario double precision,
	classificacao varchar(50),
	PRIMARY KEY (cpf)
);

CREATE TABLE IF NOT EXISTS vendas
(
	cod_venda INTEGER,
	cpf_vendedor VARCHAR (11),
	comissao double precision,	
	valor_venda double precision,
	data_venda date,
	PRIMARY KEY(cod_venda)
);
ALTER TABLE vendas ADD FOREIGN KEY(cpf_vendedor) REFERENCES funcionario(cpf);

CREATE TABLE IF NOT EXISTS cx
(
	cpf_vendedor varchar (11),
	cod_venda integer NOT NULL,
	numero integer,
	
	PRIMARY KEY (cod_venda)
);


CREATE TABLE IF NOT EXISTS itens_vendidos
(
	numero_nf INTEGER,
	cod_item INTEGER,
	quantidade integer,
);
ALTER TABLE itens_vendidos ADD FOREIGN KEY (cod_item) REFERENCES itens(cod_item);


CREATE TABLE IF NOT EXISTS itens
(
	cod_item SERIAL NOT NULL,
	descricao VARCHAR (50),
	setor VARCHAR (2),
	estoque integer,
	valor double precision,
	garantia VARCHAR (30) DEFAULT ('90 DIAS'),
	PRIMARY KEY (cod_item)
);


--REINICIAR TABELAS----------------------------------------
truncate vendas cascade;
truncate cx cascade;
truncate itens_vendidos cascade;
truncate recados cascade;
truncate itens cascade;
truncate funcionario cascade;
ALTER SEQUENCE cx_cod_venda_seq RESTART WITH 1;
ALTER SEQUENCE itens_cod_item_seq RESTART WITH 1;
-----------------------------------------------------------




