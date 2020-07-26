package com.arafael.cobranca.model;

public enum StatusTitulo {
	PENDENTE("pendente"),
	RECEBIDO("recebido"),
	CANCELADO("cancelado");
	
	private String descricao;

	private StatusTitulo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
	
}
