package com.ecommerce.produtos.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.compras.client.produto.ProdutoDTO;
import com.ecommerce.produtos.api.model.Produto;
import com.ecommerce.produtos.api.service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastroProduto(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvarProduto(produto));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> listarProdutos(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutos(pageable));
    }

    @GetMapping(value = "/codigo")
    public ResponseEntity<ProdutoDTO> obterProdutoPeloCodigo(@RequestParam("codigo") String codigo) {
        ProdutoDTO produto = produtoService.obterProdutoPeloCodigo(codigo);

        if (Objects.isNull(produto)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @Autowired
    private ProdutoService produtoService;

}
