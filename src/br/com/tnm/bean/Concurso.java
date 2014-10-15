package br.com.tnm.bean;

import java.util.Date;

public class Concurso {
	
	public static final String NOME_TABELA = "concurso";
	public static final String NOME_CONCURSO = "nome_concurso";
	public static final String NIVEL = "nivel";
	public static final String DATA_PROVA = "data_prova";
	public static final String NOME_CARGO = "nome_cargo";
	public static final String NOME_BANCA = "nome_banca";
	public static final String NOME_ORGAO = "nome_orgao";
	public static final String TOTAL_QUESTOES = "total_questoes";
	public static final String DATA_INCLUSAO = "data_inclusao";
	public static final String TIPO_QUESTAO = "tipo_questao";
	
	public static final String CODIGO = "codigo";
	
	private long codigo;
	private String nome;
	private String nivel;
	private Date dataProva;
	private Cargo cargo;
	private Orgao orgao;
	private long totalQuestoes;
	private Date dataInclusao;
	private String tipoQuestao;
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public Date getDataProva() {
		return dataProva;
	}
	public void setDataProva(Date dataProva) {
		this.dataProva = dataProva;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public Orgao getOrgao() {
		return orgao;
	}
	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}
	public long getTotalQuestoes() {
		return totalQuestoes;
	}
	public void setTotalQuestoes(long totalQuestoes) {
		this.totalQuestoes = totalQuestoes;
	}
	public Date getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	public String getTipoQuestao() {
		return tipoQuestao;
	}
	public void setTipoQuestao(String tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}
	

}
