package com.ecommerce.compras.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.compras.api.model.Compra;
import com.ecommerce.compras.api.repository.CompraRepository;
import com.ecommerce.compras.client.compra.CompraDTO;
import com.ecommerce.compras.client.compra.ItemDTO;
import com.ecommerce.compras.client.usuario.ClienteDTO;

@Service
public class CompraService {

    public ClienteDTO obterDetalhesDoCliente(String emailCliente, String token) {
        RestTemplate template = new RestTemplate();

        String url = String.format("%s/clientes/email?email=%s", usuarioWsURL, emailCliente);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ClienteDTO> response = template.exchange(url, HttpMethod.GET, entity, ClienteDTO.class);

        return response.getBody();
    }

    public CompraDTO salvarCompra(Compra compra, ClienteDTO cliente, List<ItemDTO> itens) {
        return compraRepository.save(compra).converterParaDTO(cliente, itens);
    }

    @Value(value = "${usuarios.ws.url}")
    private String usuarioWsURL;

    @Autowired
    private CompraRepository compraRepository;

}
