package br.com.tnm.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import br.com.tnm.R;

public class MontaResposta {
	
	View toastRoot;
	int LayoutPadrao;
	
	public MontaResposta(Context ctx, LayoutInflater inflater, String mensagem) {
		
		LayoutPadrao = R.layout.my_toast_alerta;
				
		toastRoot = inflater.inflate(LayoutPadrao, null);
		
		Toast toast = new Toast(ctx);
		 
		toast.setView(toastRoot);
		
		TextView tv = (TextView) toastRoot.findViewById(R.id.TextoToast);
		tv.setText(mensagem);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,
		        0, 0);
		toast.show();
	}
	
}
