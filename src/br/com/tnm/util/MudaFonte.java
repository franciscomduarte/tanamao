package br.com.tnm.util;

import android.widget.TextView;

public class MudaFonte {

	public static void MudaFonteTextView(TextView tv, TextView tvFonte, float tamanho) {

		if (tamanho < 15)
			tamanho = 15;
		else if (tamanho > 20) 
			tamanho=20;
		
		tv.setTextSize(tamanho);
		
		tvFonte.setText("Tamanho da fonte: "+(int)tamanho+"px");
		
		
	}
	
	
	public static void MudaFonteTextView(TextView tv, float tamanho) {

		if (tamanho < 15)
			tamanho = 15;
		else if (tamanho > 20) 
			tamanho=20;
		
		tv.setTextSize(tamanho);		
		
	}
	
	
	
	
}
