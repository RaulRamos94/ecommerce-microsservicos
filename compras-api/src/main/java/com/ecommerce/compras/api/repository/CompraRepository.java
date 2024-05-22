package com.ecommerce.compras.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.compras.api.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query("SELECT c FROM tb_compras c JOIN tb_compra_item ci ON c.id = ci.idCompra " +
            "JOIN tb_itens i ON ci.idItem = i.id WHERE i.id = :idItem")
    Optional<List<Compra>> findByItem(@Param("idItem") long idItem);

}
