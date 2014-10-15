package br.com.tnm.bean;

public class Cargo {
	
	public static final String NOME_TABELA = "cargo";
	
	public static final String CODIGO = "codigo";
	public static final String NOME_CARGO = "nome_cargo";
	
	public static String[] colunas = new String[] { Cargo.CODIGO, 
		Cargo.NOME_CARGO};
	
	private long codigo;
	private String nome;
	
	public Cargo() {
		// TODO Auto-generated constructor stub
	}
	
	public Cargo(long codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;	
	}
	
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
	
	@Override
	public String toString() {
		return this.getNome();
	}
	
}
