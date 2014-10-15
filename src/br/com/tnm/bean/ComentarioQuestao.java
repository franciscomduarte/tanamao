package br.com.tnm.bean;

public class ComentarioQuestao {
	
	public static final String NOME_TABELA = "comentario_questao";
	
	public static final String QUESTAO_CODIGO = "questao_codigo";
	public static final String COMENTARIO = "comentario";
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public static String[] colunas = new String[] { 
		ComentarioQuestao.QUESTAO_CODIGO, 
		ComentarioQuestao.COMENTARIO};
	
	private long questaoCodigo;
	private String comentario;
	
	public ComentarioQuestao() {
		// TODO Auto-generated constructor stub
	}
	
	public ComentarioQuestao(long questaoCodigo, String comentario) {
		this.questaoCodigo = questaoCodigo;
		this.comentario = comentario;
	}
	
	public long getQuestaoCodigo() {
		return questaoCodigo;
	}
	
	public void setQuestaoCodigo(long questaoCodigo) {
		this.questaoCodigo = questaoCodigo;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	
}
