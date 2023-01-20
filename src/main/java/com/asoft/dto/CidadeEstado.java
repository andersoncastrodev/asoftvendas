package com.asoft.dto;


public class CidadeEstado {

	private String cidade;
	
	private String estado;

	public CidadeEstado() {}
	
	public CidadeEstado(String cidade, String estado) {
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "CidadeEstado [cidade=" + cidade + ", estado=" + estado + "]";
	}
	
	
	
}
