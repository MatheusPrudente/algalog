package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setEmail("joa@joa.com");
		cliente1.setId(1L);
		cliente1.setNome("Joa");
		cliente1.setTelefone("343 34 34 3 43");

		return Arrays.asList(cliente1);
	}
}
