package br.com.tnm.bean;

import java.io.Serializable;

public class Questao implements Serializable{

	private static final long serialVersionUID = -7693861446266622353L;

	public static final String NOME_TABELA = "questao";

	public static final String CODIGO = "codigo";
	public static final String ENUNCIADO = "enunciado_codigo";
	public static final String ITEM_A = "item_a";
	public static final String ITEM_B = "item_b";
	public static final String ITEM_C = "item_c";
	public static final String ITEM_D = "item_d";
	public static final String ITEM_E = "item_e";
	public static final String RESPOSTA_CERTA = "resposta_certa";
	public static final String ASSUNTO_CODIGO = "assunto_codigo";
	public static final String CONCURSO_CODIGO = "concurso_codigo";
	public static final String TIPO_QUESTAO = "tipo_questao";
	public static final String ROTULO = "rotulo";
	
	
	public static String[] colunas = new String[] { Questao.CODIGO, 
													Questao.ENUNCIADO, 
													Questao.ITEM_A,
													Questao.ITEM_B,
													Questao.ITEM_C,
													Questao.ITEM_D,
													Questao.ITEM_E,
													Questao.RESPOSTA_CERTA,
													Questao.ASSUNTO_CODIGO,
													Questao.CONCURSO_CODIGO,
													Questao.TIPO_QUESTAO,
													Questao.ROTULO};
	
	private long codigo;
	private String enunciado;
	private String itemA;
	private String itemB;
	private String itemC;
	private String itemD;
	private String itemE;
	private String repostaCerta;
	private Assunto assunto;
	private Concurso concurso;
	private String tipoQuestao;
	private String origemQuestao;
	private String rotulo;
	
	//transiente
	private int questaoPulada;
	
	//transiente
	private String comentario;
	
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

	public String getItemA() {
		return itemA;
	}

	public void setItemA(String itemA) {
		this.itemA = itemA;
	}

	public String getItemB() {
		return itemB;
	}

	public void setItemB(String itemB) {
		this.itemB = itemB;
	}

	public String getItemC() {
		return itemC;
	}

	public void setItemC(String itemC) {
		this.itemC = itemC;
	}

	public String getItemD() {
		return itemD;
	}

	public void setItemD(String itemD) {
		this.itemD = itemD;
	}

	public String getItemE() {
		return itemE;
	}

	public void setItemE(String itemE) {
		this.itemE = itemE;
	}

	public String getRepostaCerta() {
		return repostaCerta;
	}

	public void setRepostaCerta(String repostaCerta) {
		this.repostaCerta = repostaCerta;
	}
	public Assunto getAssunto() {
		return assunto;
	}
	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}
	public Concurso getConcurso() {
		return concurso;
	}
	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}
	public String getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(String tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	public String getOrigemQuestao() {
		return origemQuestao;
	}

	public void setOrigemQuestao(String origemQuestao) {
		this.origemQuestao = origemQuestao;
	}
	public String getRotulo() {
		return rotulo;
	}
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}
	public int getQuestaoPulada() {
		return questaoPulada;
	}
	public void setQuestaoPulada(int questaoPulada) {
		this.questaoPulada = questaoPulada;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getComentario() {
		return comentario;
	}
	
	
}
