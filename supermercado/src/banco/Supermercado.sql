use supermercado;

create table usuario (
      id_usuario int primary key auto_increment,
      nome_usuario varchar(45) not null unique,
      cpf varchar(45) not null unique
);