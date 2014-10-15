package br.com.tnm.util;

public enum Resposta {
	CERTO,ERRADO;

	
	public static int converterResposta(Resposta r){
		if (r == Resposta.CERTO)
			return 1;
		else
			return 0;
	}
}



