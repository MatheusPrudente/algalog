package com.algaworks.algalog.api.model.input;

import javax.validation.constraints.NotNull;

public class ClienteIdInput {

	@NotNull
	private Long Id;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

}
