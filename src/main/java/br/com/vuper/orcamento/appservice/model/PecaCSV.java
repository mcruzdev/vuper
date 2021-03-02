package br.com.vuper.orcamento.appservice.model;

public class PecaCSV {

	private String nome;
	private String unidadeMedida;
	private String categoria;

	public PecaCSV() {
	}

	public PecaCSV(String nome, String categoria, String unidadeMedida) {
		this.nome = nome;
		this.categoria = categoria;
		this.unidadeMedida = unidadeMedida;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "PecaCSV [nome=" + nome + ", unidadeMedida=" + unidadeMedida + ", categoria=" + categoria + "]";
	}
	
}
