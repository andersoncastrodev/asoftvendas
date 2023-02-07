insert into estado (nome) values ('CE');
insert into estado (nome) values ('SP');
insert into estado (nome) values ('RJ');
insert into estado (nome) values ('BA');
insert into estado (nome) values ('MA');

insert into cidade (nome, estado_id) values('Maracanau', 1);
insert into cidade (nome, estado_id) values('Sao Paulo', 2);
insert into cidade (nome, estado_id) values('Rio de Janeiro', 3);
insert into cidade (nome, estado_id) values('Salvador', 4);
insert into cidade (nome, estado_id) values('São Luiz', 5);

insert into endereco ( bairro, complemento, lograduro,numero,cidade_id ) values ('Centro','Casa','Rua 15','1500',1);
insert into endereco ( bairro, complemento, lograduro,numero,cidade_id ) values ('Garanba','Casa','Rua 175','2400',2);
insert into endereco ( bairro, complemento, lograduro,numero,cidade_id ) values ('Copabana','Casa','Av Principal','123',3);
insert into endereco ( bairro, complemento, lograduro,numero,cidade_id ) values ('Lapa','Casa','Rua de Patria','1400',4);
insert into endereco ( bairro, complemento, lograduro,numero,cidade_id ) values ('Milha','Casa','Rua 147','1452',5);


insert into cliente (ativo, data_nascimento, nome, telefone, endereco_id) values (1, '2015-01-20', 'Antonio Alves', '85 8574-8574', 1);
insert into cliente (ativo, data_nascimento, nome, telefone, endereco_id) values (0, '1989-01-01', 'Francisco Santos', '85 8596-7412', 2);
insert into cliente (ativo, data_nascimento, nome, telefone, endereco_id) values (1, '1991-03-15', 'Joao Teves', '85 2536-8596', 3);
insert into cliente (ativo, data_nascimento, nome, telefone, endereco_id) values (0, '1999-02-14', 'Maria Fernandes', '85 8574-8574', 4);
insert into cliente (ativo, data_nascimento, nome, telefone, endereco_id) values (1, '2015-01-20', 'Helena Sousa', '85 9999-8574', 5);

insert into venda (ativo, data_cancelamento, data_venda, sub_total, total, cliente_id) values (1,'2022-12-12','2022-12-01', 150.00, 300.00, 1);
insert into venda (ativo, data_cancelamento, data_venda, sub_total, total, cliente_id) values (1,'2022-11-10','2022-11-02', 75.00,  150.00, 2);
insert into venda (ativo, data_cancelamento, data_venda, sub_total, total, cliente_id) values (1,'2022-10-10','2022-10-03', 250.00, 500.00, 3);
insert into venda (ativo, data_cancelamento, data_venda, sub_total, total, cliente_id) values (1,'2022-09-09','2022-09-02', 125.00, 250.00, 4);
insert into venda (ativo, data_cancelamento, data_venda, sub_total, total, cliente_id) values (1,'2022-08-12','2022-08-01', 25.00, 50.00, 5);

insert into produto (ativo, descricao, nome, preco_custo, preco_venda) values (1,'Teclado de Computador','Teclado',20.50,50.00);
insert into produto (ativo, descricao, nome, preco_custo, preco_venda) values (1,'Mouse de Computador','Mouse',15.00,25.00);
insert into produto (ativo, descricao, nome, preco_custo, preco_venda) values (1,'Monitor de Computador','Monitor',150.00,350.00);
insert into produto (ativo, descricao, nome, preco_custo, preco_venda) values (1,'Camera de Fotografia','Camera',400.0,800.00);
insert into produto (ativo, descricao, nome, preco_custo, preco_venda) values (1,'Fone de HeadSet','Fone',14.50,45.00);

insert into carrinho (status, produto_id, venda_id) values (1,1,2);
insert into carrinho (status, produto_id, venda_id) values (1,2,1);
insert into carrinho (status, produto_id, venda_id) values (1,3,3);
insert into carrinho (status, produto_id, venda_id) values (1,4,4);
insert into carrinho (status, produto_id, venda_id) values (1,5,1);
