package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the formacao_curso database table.
 * 
 */
@Entity
@Table(name="formacao_curso")
public class FormacaoCurso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Integer ano;

	@Column(name="nome_local")
	private String nomeLocal;

	@ManyToOne
	@JoinColumn(name="curso_complementar_id")
	private CursoComplementar cursoComplementar;

	public FormacaoCurso() {
	}



	public Integer getAno() {
		return this.ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getNomeLocal() {
		return this.nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}


	public CursoComplementar getCursoComplementar() {
		return this.cursoComplementar;
	}

	public void setCursoComplementar(CursoComplementar cursoComplementar) {
		this.cursoComplementar = cursoComplementar;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

}