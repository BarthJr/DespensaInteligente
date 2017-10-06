insert into categoria(id, nome) values(1, 'Congelado');
insert into categoria(id, nome) values(2, 'Fruta');
insert into cliente(id, nome, login, senha) values(1,'Lacuna Na Mata', 'abc', '123');
insert into cliente(id, nome, login, senha) values(2,'Guido van Rossum', 'abcd', '1234');
insert into produto(id, marca, nome, peso, tipo, categoria_id, cliente_id) values(1, 'Renata', 'Macarrão', 1, 'Semola com ovos', 1, 2);
insert into produto(id, marca, nome, peso, tipo, categoria_id, cliente_id) values(2, 'Nestle', 'Chocolate', 2, 'Ao leite', 2, 1);
insert into despensa(id, localizacao, nome) values(1, 'Curitiba', 'Casa');
insert into despensa(id, localizacao, nome) values(2, 'Floripa', 'Casa praia');

