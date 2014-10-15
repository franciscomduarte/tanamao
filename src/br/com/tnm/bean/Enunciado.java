package br.com.tnm.bean;

public class Enunciado {

	public static final String NOME_TABELA = "enunciado_questao";

	public static final String CODIGO = "codigo";
	public static final String ENUNCIADO = "enunciado";

	public static String[] colunas = new String[] { Enunciado.CODIGO,
			Enunciado.ENUNCIADO };

	private long codigo;
	private String enunciado;

	public Enunciado() {

	}

	public Enunciado(long codigo, String enunciado) {
		this.codigo = codigo;
		this.enunciado = enunciado;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

}
