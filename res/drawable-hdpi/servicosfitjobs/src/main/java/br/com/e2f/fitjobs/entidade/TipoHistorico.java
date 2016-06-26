package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_historico database table.
 * 
 */
@Entity
@Table(name="tipo_historico")
@NamedQuery(name="TipoHistorico.findAll", query="SELECT t FROM TipoHistorico t")
public class TipoHistorico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nome;

	//bi-directional many-to-one association to HistoricoAssinatura
	@OneToMany(mappedBy="tipoHistorico")
	private List<HistoricoAssinatura> historicoAssinaturas;

	public TipoHistorico() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<HistoricoAssinatura> getHistoricoAssinaturas() {
		return this.historicoAssinaturas;
	}

	public void setHistoricoAssinaturas(List<HistoricoAssinatura> historicoAssinaturas) {
		this.historicoAssinaturas = historicoAssinaturas;
	}

	public HistoricoAssinatura addHistoricoAssinatura(HistoricoAssinatura historicoAssinatura) {
		getHistoricoAssinaturas().add(historicoAssinatura);
		historicoAssinatura.setTipoHistorico(this);

		return historicoAssinatura;
	}

	public HistoricoAssinatura removeHistoricoAssinatura(HistoricoAssinatura historicoAssinatura) {
		getHistoricoAssinaturas().remove(historicoAssinatura);
		historicoAssinatura.setTipoHistorico(null);

		return historicoAssinatura;
	}

}