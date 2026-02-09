DELETE FROM usuarios WHERE email = 'admin@email.com';
INSERT INTO usuarios (login, email, senha)
VALUES ('admin',
        'admin@email.com',
        '$2a$10$p0yogVt8Sz0r8s8/gavZ9uGSDmuQda28eKCPr79VALcx7DBj/NJjC');