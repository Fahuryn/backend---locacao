CREATE TABLE reservas (
    id BIGSERIAL PRIMARY KEY,

    cliente_id BIGINT NOT NULL,
    tipo_locacao_id BIGINT NOT NULL,

    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,

    CONSTRAINT fk_reserva_cliente
        FOREIGN KEY (cliente_id)
            REFERENCES clientes (id),

    CONSTRAINT fk_reserva_tipo_locacao
        FOREIGN KEY (tipo_locacao_id)
            REFERENCES tipos_locacao (id)
);