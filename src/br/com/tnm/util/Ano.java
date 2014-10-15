package br.com.tnm.util;


public class Ano {
		
	private String ano;
	

	public Ano(String ano) {
		this.ano = ano;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	
	@Override
	public String toString() {
		return this.ano;
	}

}
