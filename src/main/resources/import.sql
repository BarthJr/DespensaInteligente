insert into categoria(id, nome) values(1, 'Congelado');
insert into categoria(id, nome) values(2, 'Fruta');
insert into cliente(id, nome, login, senha) values(1,'Lacuna Na Mata', 'abc', '123');
insert into cliente(id, nome, login, senha) values(2,'Guido van Rossum', 'abcd', '1234');
insert into produto(id, marca, nome, peso, tipo, categoria_id, cliente_id) values(1, 'Renata', 'Macarrão', 1, 'Semola com ovos', 1, 2);
insert into produto(id, marca, nome, peso, tipo, categoria_id, cliente_id) values(2, 'Nestle', 'Chocolate', 2, 'Ao leite', 2, 1);
insert into despensa(id, localizacao, nome, cliente_id) values(1, 'Curitiba', 'Casa', 2);
insert into despensa(id, localizacao, nome, cliente_id) values(2, 'Floripa', 'Casa praia', 1);
insert into produto_despensa(id, quantidade, despensa_id, produto_id) values(1, 5.0, 1, 1);
INSERT INTO receita(id, modo_preparo, quantidade, titulo, cliente_id) VALUES (1, 'Mexer', 2.0, 'Algo Legal', 1);
INSERT INTO medida(id, nome, valor) VALUES (1, 'gramas', 2.0);
INSERT INTO produto_receita(id, quantidade, medida_id, produto_id, receita_id) VALUES (1, 55.0, 1, 1, 1);

INSERT INTO receita(id, modo_preparo, quantidade, titulo, cliente_id) VALUES (2, 'Colocar em agua fervente por 3 minutos', 1.0, 'Miojo', 2);

INSERT INTO favorita(id, cliente_id, receita_id) VALUES (1, 2, 1);


