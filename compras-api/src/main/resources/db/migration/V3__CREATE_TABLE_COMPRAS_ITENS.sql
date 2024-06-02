CREATE TABLE IF NOT EXISTS `tb_compras_itens` (
    `compra_id` BIGINT,
    `item_id` BIGINT,
    PRIMARY KEY (`compra_id`, `item_id`),
    FOREIGN KEY (`compra_id`) REFERENCES `tb_compras` (`id`),
    FOREIGN KEY (`item_id`) REFERENCES `tb_itens` (`id`)
);