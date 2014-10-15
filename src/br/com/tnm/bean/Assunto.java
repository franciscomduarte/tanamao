package br.com.tnm.bean;

public class Assunto {
	
	public static final String NOME_TABELA = "assunto";
	
	public static final String CODIGO = "codigo";
	public static final String NOME_ASSUNTO = "nome_assunto";
	public static final String MATERIA_CODIGO = "materia_codigo";
	public static final String PALAVRA_CHAVE = "palavra_chave";
	public static final String ASSUNTO_CODIGO = "assunto_codigo";
	
	public static String[] colunas = new String[] { Assunto.CODIGO, 
		Assunto.NOME_ASSUNTO,
		Assunto.MATERIA_CODIGO,
		Assunto.PALAVRA_CHAVE,
		Assunto.ASSUNTO_CODIGO};
	
	private long codigo;
	private String nome;
	private Materia materia;
	private String palavraChave;
	private long assuntoCodigo;
	private boolean checked;
	
	public Assunto() {
		// TODO Auto-generated constructor stub
	}
	
	public Assunto(long codigo, String nome) {
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
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public String getPalavraChave() {
		return palavraChave;
	}
	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}
	public long getAssuntoCodigo() {
		return assuntoCodigo;
	}
	public void setAssuntoCodigo(long assuntoCodigo) {
		this.assuntoCodigo = assuntoCodigo;
	}
	
    public boolean isChecked() {
    	return checked;
    }
    
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}

	private long total;

    
	  
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	
    	if (getTotal()!=0){
    	
    		return this.getNome()+ "("+ getTotal()+")";

    	}else

    		return this.getNome();

		}
    }

