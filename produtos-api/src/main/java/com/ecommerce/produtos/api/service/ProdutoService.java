package com.ecommerce.produtos.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.produtos.api.repository.ProdutoRepository;

@Service
public class ProdutoService {


    @Autowired
    private ProdutoRepository produtoRepository;

}
