package br.com.tnm.bean;

public class Orgao {
	
	public static final String NOME_TABELA = "orgao";

	public static final String CODIGO = "codigo";
	public static final String NOME_ORGAO = "nome_orgao";
	
	public static String[] colunas = new String[] { Orgao.CODIGO, 
		Orgao.NOME_ORGAO};
	
	private long codigo;
	private String nome;
	
	public Orgao() {
		// TODO Auto-generated constructor stub
	}
	
	public Orgao(long codigo, String nome) {
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
		// TODO Auto-generated method stub
		return this.getNome();
	}

}
