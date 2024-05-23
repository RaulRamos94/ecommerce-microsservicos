package com.ecommerce.compras.client.compra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;
    private String codigoProduto;
    private Integer quantidade;
    private Double subtotal;

}
