package br.com.tnm.bean;

public class Materia {
	
	public static final String NOME_TABELA = "materia";
	
	public static final String CODIGO = "codigo";
	public static final String NOME_MATERIA = "nome_materia";
	
	public static String[] colunas = new String[] { Materia.CODIGO, 
		Materia.NOME_MATERIA};
	
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
	
}
