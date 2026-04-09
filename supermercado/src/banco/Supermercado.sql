create database if not exists db_supermercado;
use db_supermercado;

create table usuario (
      id_usuario int primary key auto_increment,
      nome_usuario varchar(45) not null,
      cpf varchar(45) not null unique,
      is_admin boolean not null -- NOVA COLUNA AQUI
);

create table produto (
      id_produto int primary key auto_increment,
      nome_produto varchar(45) not null unique,
      qtd int not null, -- Faltava essa vírgula no seu código
      preco double not null
);
