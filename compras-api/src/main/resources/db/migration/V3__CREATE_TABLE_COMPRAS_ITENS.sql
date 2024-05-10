CREATE TABLE IF NOT EXISTS `tb_compras_itens` (
    `idCompra` BIGINT,
    `idItem` BIGINT,
    PRIMARY KEY (`idCompra`, `idItem`),
    FOREIGN KEY (`idCompra`) REFERENCES `tb_compras` (`id`),
    FOREIGN KEY (`idItem`) REFERENCES `tb_itens` (`id`)
);