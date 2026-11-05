-- Tabla de usuarios independiente para el auth-service
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL, -- Contraseña hasheada (BCrypt)
                       role VARCHAR(50) NOT NULL       -- Ej: USER, ADMIN
);

-- Datos iniciales de prueba (opcional para desarrollo)
INSERT INTO users (id, username, password, role)
VALUES (1, 'luis@taskhub.com', '$2a$10$X...', 'USER');