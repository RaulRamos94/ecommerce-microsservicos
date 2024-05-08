CREATE TABLE IF NOT EXISTS `tb_clientes` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `nome` VARCHAR(255) NOT NULL,
    `cpf` VARCHAR(255) NOT NULL UNIQUE,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `telefone` VARCHAR(255) NOT NULL,
    `data_nascimento` DATE NOT NULL,
    `cep` VARCHAR(255),
    `logradouro` VARCHAR(255),
    `cidade` VARCHAR(255),
    `uf` VARCHAR(255),
    `bairro` VARCHAR(255),
    `numero` VARCHAR(255),
    `complemento` VARCHAR(255)
);
