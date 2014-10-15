package br.com.tnm.bean;

import br.com.tnm.util.ENivelQuestao;
import br.com.tnm.util.ETipoQuestao;

public class QuestaoFiltro {
	private long concursoCodigo;
	private long bancaCodigo;
	private long orgaoCodigo;
	private long ano;
	private long cargoCodigo;
	private long totalQuestoes;
	private ETipoQuestao tipoQuestao;
	private ENivelQuestao nivelQuestao;
	private long materiaCodigo;
	private String assuntos;
	private String tempo;

	
	public ENivelQuestao getNivelQuestao() {
		return nivelQuestao;
	}


	public void setNivelQuestao(ENivelQuestao nivelQuestao) {
		this.nivelQuestao = nivelQuestao;
	}


	public ETipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}


	public void setTipoQuestao(ETipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}


	public QuestaoFiltro() {
		// TODO Auto-generated constructor stub
	}


	@SuppressWarnings("unused")
	private long getConcursoCodigo() {
		return concursoCodigo;
	}


	@SuppressWarnings("unused")
	private void setConcursoCodigo(long concursoCodigo) {
		this.concursoCodigo = concursoCodigo;
	}


	public long getBancaCodigo() {
		return bancaCodigo;
	}


	public void setBancaCodigo(long bancaCodigo) {
		this.bancaCodigo = bancaCodigo;
	}


	public long getOrgaoCodigo() {
		return orgaoCodigo;
	}


	public void setOrgaoCodigo(long orgaoCodigo) {
		this.orgaoCodigo = orgaoCodigo;
	}


	public long getAno() {
		return ano;
	}


	public void setAno(long ano) {
		this.ano = ano;
	}


	public long getCargoCodigo() {
		return cargoCodigo;
	}


	public void setCargoCodigo(long cargoCodigo) {
		this.cargoCodigo = cargoCodigo;
	}


	public long getTotalQuestoes() {
		return totalQuestoes;
	}


	public void setTotalQuestoes(long totalQuestoes) {
		this.totalQuestoes = totalQuestoes;
	}


	public long getMateriaCodigo() {
		return materiaCodigo;
	}
	
	public void setMateriaCodigo(long materiaCodigo) {
		this.materiaCodigo = materiaCodigo;
	}
	

	public String getAssuntos() {
		return assuntos;
	}


	public void setAssuntos(String assuntos) {
		this.assuntos = assuntos;
	}


	public String getTempo() {
		return tempo;
	}


	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
}
