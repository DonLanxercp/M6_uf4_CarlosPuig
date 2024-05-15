CREATE TABLE IF NOT EXISTS COCHES_F1 (
    id INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    temporada INT NOT NULL,
    escuderia VARCHAR(255) NOT NULL,
    fechaPresentacion DATE NOT NULL,
    activo BOOLEAN NOT NULL
);