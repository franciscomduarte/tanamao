package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the curso_complementar database table.
 * 
 */
@Entity
@Table(name="curso_pessoa")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String nome;

	private String local;

	private Integer ano;
	
	@Column(name="carga_horaria")
	private Integer cargaHoraria;
	
	@Column(name="semestre_fim")
	private Integer semestreFim;

	@Column(name="ano_curso_fim")
	private Integer anoCursoFim;
	
	@OneToOne(optional=true)
	@JoinColumn(name="curso_superior_id")
	private CursoSuperior cursoSuperior;
	
	public Curso() {
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

	public Integer getSemestreFim() {
		return semestreFim;
	}

	public void setSemestreFim(Integer semestreFim) {
		this.semestreFim = semestreFim;
	}

	public Integer getAnoCursoFim() {
		return anoCursoFim;
	}

	public void setAnoCursoFim(Integer anoCursoFim) {
		this.anoCursoFim = anoCursoFim;
	}

	public CursoSuperior getCursoSuperior() {
		return cursoSuperior;
	}

	public void setCursoSuperior(CursoSuperior cursoSuperior) {
		this.cursoSuperior = cursoSuperior;
	}


}