package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private SolicitacaoEntregaService solicitacaoEntregaService;

	@Autowired
	private EntregaRepository entregaRepository;

	@Autowired
	private EntregaAssembler entregaAssembler;

	@Autowired
	private FinalizacaoEntregaService finalizacaoEntregaService;
	
	@Operation(summary = "Solicitar uma Entrega")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Sucesso na requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EntregaModel.class))}),
			  @ApiResponse(responseCode = "400", description = "Parametro inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "403", description = "Não Autorizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "504", description = "Timeout", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);

		return entregaAssembler.toModelEntrega(entregaSolicitada);
	}

	@Operation(summary = "Obter Listagem das Entregas Cadastradas")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Sucesso na requisição", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EntregaModel.class)))}),
			  @ApiResponse(responseCode = "400", description = "Parametro inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "403", description = "Não Autorizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "504", description = "Timeout", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),})
	@GetMapping
	public List<EntregaModel> listar() {
		return entregaAssembler.toCollectModel(entregaRepository.findAll());
	}

	@Operation(summary = "Buscar Uma Entrega Pelo Id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Sucesso na requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EntregaModel.class))}),
			  @ApiResponse(responseCode = "400", description = "Parametro inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "403", description = "Não Autorizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "504", description = "Timeout", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),})
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId).map(entrega -> {
			return ResponseEntity.ok(entregaAssembler.toModelEntrega(entrega));
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@Operation(summary = "Finalizar uma Entrega")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Sucesso na requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = void.class))}),
			  @ApiResponse(responseCode = "400", description = "Parametro inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "403", description = "Não Autorizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "504", description = "Timeout", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),})
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}

}
