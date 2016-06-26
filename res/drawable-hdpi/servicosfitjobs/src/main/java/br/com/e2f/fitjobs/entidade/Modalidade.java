package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the modalidade database table.
 * 
 */
@Entity
public class Modalidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String nome;

	
	public Modalidade() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}