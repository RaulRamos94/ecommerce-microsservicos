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
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.compras.client.usuario.ClienteDTO;
import com.ecommerce.usuarios.api.model.Cliente;
import com.ecommerce.usuarios.api.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
    
    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvarCliente(cliente));
    }

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.obterListaClientes());
    }

    @GetMapping(value = "/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<ClienteDTO> obterDadosCliente(@PathVariable("id") Long id) {
        ClienteDTO cliente = clienteService.obterCliente(id);

        if (Objects.isNull(cliente)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping(value = "/paginacao")
    public ResponseEntity<Page<ClienteDTO>> listarClientesPaginacao(
            @PageableDefault(page = 1, size = 5, sort = "nome", direction = Direction.DESC) Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.obterPaginaClientes(paginacao));
    }

    @GetMapping(value = "/nome")
    public ResponseEntity<List<ClienteDTO>> filtrarClientesPeloNome(
            @RequestParam(name = "nome", required = false) String nome) {
        List<ClienteDTO> clientes = clienteService.obterClientesPeloNome(nome);

        if (Objects.nonNull(clientes)) {
            return ResponseEntity.status(HttpStatus.OK).body(clientes);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/email")
    public ResponseEntity<ClienteDTO> obterClientePeloEmail(@RequestParam("email") String email) {
        ClienteDTO cliente = clienteService.obterClientePeloEmail(email);

        if (Objects.isNull(cliente)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @Autowired
    private ClienteService clienteService;

}
