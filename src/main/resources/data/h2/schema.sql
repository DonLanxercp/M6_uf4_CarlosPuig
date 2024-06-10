CREATE TABLE IF NOT EXISTS COCHES_F1 (
    id BIGINT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    temporada INT NOT NULL,
    escuderia VARCHAR(255) NOT NULL,
    fecha_presentacion DATE NOT NULL,
    activo BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);