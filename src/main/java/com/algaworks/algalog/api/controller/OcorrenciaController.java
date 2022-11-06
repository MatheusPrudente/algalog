package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.OcorrenciaAssembler;
import com.algaworks.algalog.api.model.OcorrenciaModel;
import com.algaworks.algalog.api.model.input.OcorrenciaInput;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	@Autowired
	private RegistroOcorrenciaService registroOcorrenciaService;

	@Autowired
	private OcorrenciaAssembler ocorrenciaAssembler;

	@Autowired
	private BuscaEntregaService buscaEntregaService;

	@Operation(summary = "Registrar uma Ocorrencia")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Sucesso na requisição", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OcorrenciaModel.class))}),
			  @ApiResponse(responseCode = "400", description = "Parametro inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "403", description = "Não Autorizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "504", description = "Timeout", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private OcorrenciaModel registrar(@PathVariable Long entregaId,
			@Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId,
				ocorrenciaInput.getDescricao());

		return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
	}

	@Operation(summary = "Obter Listagem das Ocorrencia Cadastradas Pelo Id da Entrega")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Sucesso na requisição", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OcorrenciaModel.class)))}),
			  @ApiResponse(responseCode = "400", description = "Parametro inválido", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "403", description = "Não Autorizado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),
			  @ApiResponse(responseCode = "504", description = "Timeout", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))}),})
	@GetMapping
	public List<OcorrenciaModel> listar(@PathVariable Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);

		return ocorrenciaAssembler.toCollectModel(entrega.getOcorrencias());
	}
}
