package com.ecommerce.usuarios.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.obterListaClientes());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> obterDadosCliente(@PathVariable("id") Long id) {
        ClienteDTO cliente = clienteService.obterCliente(id);

        if (Objects.isNull(cliente)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping(value = "/paginacao")
    public ResponseEntity<Page<Cliente>> listarClientesPaginacao(
        @PageableDefault(page = 1, size = 5, sort = "nome", direction = Direction.DESC) Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.obterPaginaClientes(paginacao));
    }

    @Autowired
    private ClienteService clienteService;

}
