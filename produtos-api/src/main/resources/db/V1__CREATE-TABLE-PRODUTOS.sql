CREATE TABLE tb_produtos (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    preco FLOAT(255) NOT NULL,
    
    FOREIGN KEY (idCategoria) REFERENCES tb_categorias(id)   
);