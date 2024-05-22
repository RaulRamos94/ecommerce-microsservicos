package com.ecommerce.produtos.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.produtos.api.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByCodigo(String codigo);

    @Query("SELECT p FROM tb_produtos p WHERE p.categoria.nome = :nome")
    Optional<List<Produto>> findByCategoria(@Param("nome") String nomeCategoria);

    @Query("SELECT p FROM tb_produtos p WHERE p.categoria.nome = :nome AND p.preco <= :preco")
    Optional<List<Produto>> findByCategoriaAndPrecoSmallerThan(@Param("nome") String nomeCategoria,
            @Param("preco") double preco);

    Optional<List<Produto>> findByPrecoBetween(double limiteInferior, double limiteSuperior);

}
