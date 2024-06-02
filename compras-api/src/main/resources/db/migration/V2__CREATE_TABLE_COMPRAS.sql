CREATE TABLE IF NOT EXISTS `tb_compras` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `email_cliente` VARCHAR(255) NOT NULL,
    `total` DECIMAL(10, 2) NOT NULL,
    `data` DATE NOT NULL
)