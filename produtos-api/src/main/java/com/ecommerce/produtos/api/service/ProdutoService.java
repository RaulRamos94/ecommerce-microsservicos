package com.ecommerce.produtos.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.compras.client.produto.ProdutoDTO;
import com.ecommerce.produtos.api.model.Produto;
import com.ecommerce.produtos.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

    public ProdutoDTO salvarProduto(Produto produto) {
        return produtoRepository.save(produto).converterParaDTO();
    }

    public ProdutoDTO obterProdutoPeloCodigo(String codigo) {
        Optional<Produto> produto = produtoRepository.findByCodigo(codigo);

        if (produto.isPresent()) {
            return produto.get().converterParaDTO();
        }

        return null;
    }

    public Page<ProdutoDTO> listarProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable).map(Produto::converterParaDTO);
    }

    @Autowired
    private ProdutoRepository produtoRepository;

}
