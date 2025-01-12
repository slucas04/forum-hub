CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    mensagem MEDIUMTEXT NOT NULL,
    data_criacao DATETIME NOT NULL,
    usuario_id BIGINT NOT NULL,
    curso VARCHAR(50) NOT NULL,
    ativo TINYINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_topico_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE respostas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensagem MEDIUMTEXT NOT NULL,
    data DATETIME NOT NULL,
    usuario_id BIGINT NOT NULL,
    topico_id BIGINT NOT NULL,
    ativo TINYINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_resposta_topico FOREIGN KEY (topico_id) REFERENCES topicos(id) ON DELETE CASCADE,
    CONSTRAINT fk_resposta_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
