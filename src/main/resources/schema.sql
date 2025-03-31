CREATE TABLE IF NOT EXISTS Cursos (
    codigo INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(4000) NOT NULL,
    duracao INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Funcionarios (
    codigo INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    nascimento DATE NOT NULL,
    cargo VARCHAR(200) NOT NULL,
    admissao DATE NOT NULL,
    status BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS Turmas (
    codigo INT PRIMARY KEY AUTO_INCREMENT,
    inicio DATE NOT NULL,
    fim DATE NOT NULL,
    local VARCHAR(200) NOT NULL,
    curso INT NOT NULL,
    FOREIGN KEY (curso) REFERENCES Cursos(codigo)
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Turma_participantes (
    codigo INT PRIMARY KEY AUTO_INCREMENT,
    turma INT NOT NULL,
    funcionario INT NOT NULL,
    FOREIGN KEY (turma) REFERENCES Turmas(codigo),
    FOREIGN KEY (Funcionario) REFERENCES Funcionarios(codigo)
    ON DELETE CASCADE
);