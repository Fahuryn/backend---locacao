CREATE TABLE tipos_locacao (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    valor_diaria NUMERIC(10,2) NOT NULL
);