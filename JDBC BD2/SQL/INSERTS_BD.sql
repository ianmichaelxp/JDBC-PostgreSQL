
INSERT INTO funcionario (cpf, nome, endereco, funcao, salario)
VALUES 
('98765432100', 'Joao Pedro', 'Joao H, 44', 'vendedor', 1200),
('12345678900', 'Pedro Henrique', 'L M H, 585', 'vendedor', 1200),
('12345678909', 'Maria Helena', 'T N S, 123', 'vendedor', 1200),
('55566677700', 'Henrique Pedro', 'j k H, 404', 'supervisor', 1800),
('54334554311', 'Luis Gerald', 'n L Z, 391', 'supervisor', 1800)
;

INSERT INTO itens (descricao, tipo, estoque, valor, garantia)
VALUES ('liquidificador arno', 'ed', 20, 150, '12 meses')

--ITENS COM GARANTIA
INSERT INTO itens (descricao, setor, estoque, valor, garantia)
VALUES ('maquina de lavar consul', 'ed', 30, 1200.00, '50 meses'),
('smartv', 'ee', 30, 1500.00, '12 meses'),
('mesa jantar', 'ig', 30, 600.00, '6 meses'),
('microondas brastemp', 'ed', 30, 400.00, '12 meses');

-- ITEM COM GARANTIA DEFAULT 90 DIAS
INSERT INTO itens (descricao, setor, estoque, valor)
VALUES 
('carregador usb', 'ee', 5, 29.90);
