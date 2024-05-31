package com.ecommerce.usuarios.api.dto;

import com.ecommerce.compras.client.usuario.ClienteDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private ClienteDTO cliente;
    private String token;

}
