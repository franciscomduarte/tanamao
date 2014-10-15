package br.com.tnm.bean;

import br.com.tnm.util.ENivelQuestao;
import br.com.tnm.util.ETipoQuestao;

public class Simulado {

	public static final String NOME_TABELA = "simulado";

	public static final String CODIGO = "codigo";
	public static final String DATA_CRIACAO = "data_criacao";
	public static final String BANCA_CODIGO = "banca_codigo";
	public static final String ORGAO_CODIGO = "orgao_codigo";
	public static final String ANO = "ano";
	public static final String TOTAL_QUESTAO = "total_questoes";
	public static final String TIPO_QUESTAO = "tipo_questao";
	public static final String NIVEL_QUESTAO = "nivel_questao";
	public static final String FINALIZADO = "finalizado";
	public static final String MATERIA_CODIGO = "materia_codigo";
	public static final String CARGO_CODIGO = "cargo_codigo";
	public static final String ASSUNTOS = "assuntos";
	public static final String TEMPO = "tempo";

	public static String[] colunas = new String[] { Simulado.CODIGO,
			Simulado.DATA_CRIACAO, Simulado.BANCA_CODIGO,
			Simulado.ORGAO_CODIGO, Simulado.ANO, Simulado.TOTAL_QUESTAO,
			Simulado.NIVEL_QUESTAO, Simulado.FINALIZADO,
			Simulado.MATERIA_CODIGO, Simulado.CARGO_CODIGO, Simulado.ASSUNTOS, Simulado.TEMPO };

	private long codigo;
	private String dataCriacao;
	private long bancaCodigo;
	private long orgaoCodigo;
	private long ano;
	private long totalQuestao;
	private ETipoQuestao tipoQuestao;
	private ENivelQuestao nivelQuestao;
	private boolean finalizado;
	private long materiaCodigo;
	private long cargoCodigo;
	private String assuntos;
	private String tempo;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
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

	public long getTotalQuestao() {
		return totalQuestao;
	}

	public void setTotalQuestao(long totalQuestao) {
		this.totalQuestao = totalQuestao;
	}

	public ETipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(ETipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	public ENivelQuestao getNivelQuestao() {
		return nivelQuestao;
	}

	public void setNivelQuestao(ENivelQuestao nivelQuestao) {
		this.nivelQuestao = nivelQuestao;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public long getMateriaCodigo() {
		return materiaCodigo;
	}

	public void setMateriaCodigo(long materiaCodigo) {
		this.materiaCodigo = materiaCodigo;
	}

	public long getCargoCodigo() {
		return cargoCodigo;
	}

	public void setCargoCodigo(long cargoCodigo) {
		this.cargoCodigo = cargoCodigo;
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
