package br.com.tnm.bean;



public class Banca {
	
	public static final String NOME_TABELA = "banca";
	
	public static final String CODIGO = "codigo";
	public static final String NOME_BANCA = "nome_banca";
	
	public static String[] colunas = new String[] { Banca.CODIGO, 
		Banca.NOME_BANCA};
	
	public Banca() {
		// TODO Auto-generated constructor stub
	}
	
	public Banca(long codigo, String nome) {
		this.codigo = codigo;
		this.nome   = nome;
	}
	
	private long codigo;
	private String nome;
	
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
	
	public String toString() {
		return this.getNome();
	}
}
