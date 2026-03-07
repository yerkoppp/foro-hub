CREATE TABLE respuestas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    solucion TINYINT NOT NULL DEFAULT 0,
    topico_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_respuestas_topico_id FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_respuestas_autor_id FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);