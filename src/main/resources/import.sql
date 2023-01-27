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

