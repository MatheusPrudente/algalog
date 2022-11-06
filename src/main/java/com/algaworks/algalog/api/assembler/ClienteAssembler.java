package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.ClienteModel;
import com.algaworks.algalog.api.model.input.ClienteInput;
import com.algaworks.algalog.domain.model.Cliente;

@Component
public class ClienteAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ClienteModel toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteModel.class);
	}

	public List<ClienteModel> toCollectionModel(List<Cliente> clientes) {
		return clientes.stream().map(this::toModel).collect(Collectors.toList());
	}
	
	public Cliente toEntity (ClienteInput cliente) {
		return modelMapper.map(cliente, Cliente.class);
	}

}
