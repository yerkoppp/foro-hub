-- 1. Tabla de Usuarios (Autores)
CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(300) NOT NULL,
    PRIMARY KEY (id)
);

-- 2. Tabla de Cursos
CREATE TABLE cursos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

-- 3. Tabla de Tópicos (Corregida)
CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Genera la fecha sola
    status VARCHAR(50) NOT NULL,
    id_autor BIGINT NOT NULL,
    id_curso BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_topicos_autor FOREIGN KEY (id_autor) REFERENCES usuarios(id),
    CONSTRAINT fk_topicos_curso FOREIGN KEY (id_curso) REFERENCES cursos(id)
);