package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.ClienteAssembler;
import com.algaworks.algalog.api.model.ClienteModel;
import com.algaworks.algalog.api.model.input.ClienteInput;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CatalogoClienteService catalogoClienteService;
	
	@Autowired
	private ClienteAssembler clienteAssembler;
	
	@Operation(summary = "Obter Listagem dos Clientes Cadastrados")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Sucesso na requisição", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClienteModel.class)))}),
			  @ApiResponse(responseCode = "400", description = "Parametro inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "403", description = "Não Autorizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "504", description = "Timeout", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),})
	@GetMapping()
	public List<ClienteModel> listar() {
		return clienteAssembler.toCollectionModel(clienteRepository.findAll());
	}

	@Operation(summary = "Buscar Cliente Cadastrado Pelo Id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Sucesso na requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteModel.class))}),
			  @ApiResponse(responseCode = "400", description = "Parametro inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "403", description = "Não Autorizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "504", description = "Timeout", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),})
	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteModel> listarPorID(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId).map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
				.orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Adicionar um Cliente")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Sucesso na requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteModel.class))}),
			  @ApiResponse(responseCode = "400", description = "Parametro inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "403", description = "Não Autorizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "504", description = "Timeout", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionar(@Valid @RequestBody ClienteInput clienteInput) {
		return clienteAssembler.toModel(catalogoClienteService.salvarCliente(clienteAssembler.toEntity(clienteInput)));
	}

	@Operation(summary = "Atualizar um Cliente")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Sucesso na requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteModel.class))}),
			  @ApiResponse(responseCode = "400", description = "Parametro inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "403", description = "Não Autorizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "504", description = "Timeout", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),})
	@PutMapping("/{clienteId}")
	public ResponseEntity<ClienteModel> atualizar(@Valid @PathVariable Long clienteId, @RequestBody ClienteInput clienteInput) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente cliente = clienteAssembler.toEntity(clienteInput);
		cliente.setId(clienteId);
		catalogoClienteService.salvarCliente(cliente);

		return ResponseEntity.ok(clienteAssembler.toModel(cliente));
	}
	
	@Operation(summary = "Deletar um Cliente")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Sucesso na requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteModel.class))}),
			  @ApiResponse(responseCode = "400", description = "Parametro inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "403", description = "Não Autorizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "504", description = "Timeout", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),})
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		catalogoClienteService.excluir(clienteId);

		return ResponseEntity.noContent().build();
	}
}
