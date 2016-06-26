package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * The persistent class for the cidade database table.
 * 
 */
@Entity
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String nome;

	@OneToOne
	@JoinColumn(name="estado_id")
	private Estado estado;

	@OneToMany
	@JoinColumn(name="cidade_id")
	private Set<Bairro> bairros;
	
	public Cidade() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Set<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(Set<Bairro> bairros) {
		this.bairros = bairros;
	}

}