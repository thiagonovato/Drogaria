package br.pro.delfino.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Banco extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String sigla;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private Boolean ativo;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = "Não";

		if (ativo) {
			ativoFormatado = "Sim";
		}

		return ativoFormatado;
	}
	
	
}
