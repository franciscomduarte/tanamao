package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the assinatura database table.
 * 
 */
@Entity
public class Assinatura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_assinatura")
	private Date dataAssinatura;

	private String situacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="validade_assinatura")
	private Date validadeAssinatura;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	private Empresa empresa;

	//bi-directional many-to-one association to HistoricoAssinatura
	@OneToMany(mappedBy="assinatura")
	private List<HistoricoAssinatura> historicoAssinaturas;

	public Assinatura() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAssinatura() {
		return this.dataAssinatura;
	}

	public void setDataAssinatura(Date dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getValidadeAssinatura() {
		return this.validadeAssinatura;
	}

	public void setValidadeAssinatura(Date validadeAssinatura) {
		this.validadeAssinatura = validadeAssinatura;
	}


	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<HistoricoAssinatura> getHistoricoAssinaturas() {
		return this.historicoAssinaturas;
	}

	public void setHistoricoAssinaturas(List<HistoricoAssinatura> historicoAssinaturas) {
		this.historicoAssinaturas = historicoAssinaturas;
	}

}