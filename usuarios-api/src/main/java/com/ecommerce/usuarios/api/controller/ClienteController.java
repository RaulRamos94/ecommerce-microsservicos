package com.ecommerce.usuarios.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.usuarios.api.dto.ClienteDTO;
import com.ecommerce.usuarios.api.model.Cliente;
import com.ecommerce.usuarios.api.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvarCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> obterDadosCliente(@PathVariable("id") Long id) {
        return null;
    }

    @Autowired
    private ClienteService clienteService;

}
