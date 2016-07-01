package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the curso_complementar database table.
 * 
 */
@Entity
@Table(name="curso_complementar")
public class CursoComplementar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String nome;

	private String local;

	private Integer ano;
	
	@Column(name="carga_horaria")
	private Integer cargaHoraria;

	
	public CursoComplementar() {
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}


}