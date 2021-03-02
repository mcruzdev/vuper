package br.com.vuper.orcamento.domain.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "pecas")
public class Peca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String unidadeMedida;

	@ManyToOne
	private Categoria categoria;

	@Deprecated
	public Peca() {
	}

	public Peca(String nome, String unidadeMedida, Categoria categoria) {
		this.nome = nome;
		this.unidadeMedida = unidadeMedida;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void addCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public boolean temCategoria() {
		return !Objects.isNull(categoria);
	}

}
