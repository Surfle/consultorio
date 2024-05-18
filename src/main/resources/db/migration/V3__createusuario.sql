CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    estado VARCHAR(50),
    rua VARCHAR(50),
    numero BIGINT,
    cep VARCHAR(9)
);

CREATE TABLE paciente (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20),
    endereco_id BIGINT,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE consulta (
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    paciente_id BIGINT,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id)
);

CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);
