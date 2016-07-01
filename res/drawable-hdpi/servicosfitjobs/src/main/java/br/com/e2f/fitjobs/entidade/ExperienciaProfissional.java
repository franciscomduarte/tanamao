package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the experiencia_profissional database table.
 * 
 */
@Entity
@Table(name="experiencia_profissional")
public class ExperienciaProfissional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="data_fim")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inicio")
	private Date dataInicio;

	private String local;

	private String referencia;

	private String telefone;

	@Column(name="tipo_funcao")
	private String tipoFuncao;

	private String funcao;

	public ExperienciaProfissional() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getLocal() {
		return this.local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipoFuncao() {
		return this.tipoFuncao;
	}

	public void setTipoFuncao(String tipoFuncao) {
		this.tipoFuncao = tipoFuncao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

}