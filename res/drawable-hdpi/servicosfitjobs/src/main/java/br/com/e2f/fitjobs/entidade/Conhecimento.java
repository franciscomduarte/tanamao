package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the estado database table.
 * 
 */
//@Entity
public class Conhecimento implements Serializable {
	private static final long serialVersionUID = 1L;

	//	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}