-- Crear la tabla agente
CREATE TABLE IF NOT EXISTS agente (
    id_agente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255) NOT NULL
);

-- Crear la tabla sensor
CREATE TABLE IF NOT EXISTS sensor (
    id_sensor BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255) NOT NULL
);

-- Crear la tabla habilidad
CREATE TABLE IF NOT EXISTS habilidad (
    id_habilidad BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255) NOT NULL
);

-- Crear la tabla de relación muchos a muchos entre agente y habilidad
CREATE TABLE IF NOT EXISTS agente_habilidad (
    id_agente BIGINT,
    id_habilidad BIGINT,
    PRIMARY KEY (id_agente, id_habilidad),
    FOREIGN KEY (id_agente) REFERENCES agente(id_agente) ON DELETE CASCADE,
    FOREIGN KEY (id_habilidad) REFERENCES habilidad(id_habilidad) ON DELETE CASCADE
);

-- Crear la tabla de relación muchos a muchos entre agente y sensor
CREATE TABLE IF NOT EXISTS agente_sensor (
     id_agente BIGINT,
     id_sensor BIGINT,
     PRIMARY KEY (id_agente, id_sensor),
     FOREIGN KEY (id_agente) REFERENCES agente(id_agente) ON DELETE CASCADE,
     FOREIGN KEY (id_sensor) REFERENCES sensor(id_sensor) ON DELETE CASCADE
);
