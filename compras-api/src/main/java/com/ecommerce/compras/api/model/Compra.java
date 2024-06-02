package com.ecommerce.compras.api.model;

import java.time.LocalDate;
import java.util.List;

import com.ecommerce.compras.client.compra.CompraDTO;
import com.ecommerce.compras.client.compra.ItemDTO;
import com.ecommerce.compras.client.usuario.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "email_cliente")
    private String emailCliente;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @OneToMany
    @JoinTable(
        name = "tb_compras_itens",
        joinColumns = @JoinColumn(name = "compra_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> itens;

    public CompraDTO converterParaDTO(ClienteDTO cliente, List<ItemDTO> itens) {
        CompraDTO dto = new CompraDTO();

        dto.setId(id);
        dto.setCliente(cliente);
        dto.setData(data);
        dto.setTotal(total);
        dto.setItens(itens);

        return dto;
    }

}
