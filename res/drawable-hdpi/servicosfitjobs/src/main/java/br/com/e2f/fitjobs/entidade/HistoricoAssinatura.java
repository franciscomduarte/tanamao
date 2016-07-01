package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the historico_assinatura database table.
 * 
 */
@Entity
@Table(name="historico_assinatura")
public class HistoricoAssinatura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String descricao;

	//bi-directional many-to-one association to Assinatura
	@ManyToOne
	private Assinatura assinatura;

	//bi-directional many-to-one association to TipoHistorico
	@ManyToOne
	@JoinColumn(name="tipo_historico_id")
	private TipoHistorico tipoHistorico;

	public HistoricoAssinatura() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Assinatura getAssinatura() {
		return this.assinatura;
	}

	public void setAssinatura(Assinatura assinatura) {
		this.assinatura = assinatura;
	}

	public TipoHistorico getTipoHistorico() {
		return this.tipoHistorico;
	}

	public void setTipoHistorico(TipoHistorico tipoHistorico) {
		this.tipoHistorico = tipoHistorico;
	}

}