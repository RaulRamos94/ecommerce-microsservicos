package com.ecommerce.compras.client.compra;

import java.time.LocalDate;
import java.util.List;

import com.ecommerce.compras.client.usuario.ClienteDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraDTO {

    private Long id;
    private ClienteDTO cliente;
    private double total;
    private LocalDate data;
    private List<ItemDTO> itens;

}
