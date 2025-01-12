create table usuarios (
    id bigint not null auto_increment,
    username varchar(50) not null,
    email varchar(100) not null unique,
    senha varchar(255) not null,
    ativo tinyint,

    primary key(id)
);