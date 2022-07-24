package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;

@Component
public class EntregaAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public EntregaModel toModelEntrega(Entrega entrega) {
		return modelMapper.map(entrega, EntregaModel.class);
	}

	public List<EntregaModel> toCollectModel(List<Entrega> entregas) {
		return entregas.stream().map(this::toModelEntrega).collect(Collectors.toList());
	}

	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
}
