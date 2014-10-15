package br.com.tnm;

import br.com.tnm.R;
import br.com.tnm.dao.RepositorioScript;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TaNaMaoActivity extends Activity {
	
	private static final int MAXIMO = 100;
	private ProgressBar barraProgressiva;
	private TextView tvBarraProgressiva;
	private int progresso = 0;
	private boolean questoesInstaladas = false;
	private Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
	
		findViewById(R.id.imageView3, true);
		findViewById(R.id.tvLogomarca, true);
		findViewById(R.id.tvDescFerramenta, true);
		findViewById(R.id.tvParceiros, true);
		
		// barra de progresso
		barraProgressiva 	= (ProgressBar) findViewById(R.id.barraProgresso);
		tvBarraProgressiva 	= (TextView) findViewById(R.id.tvBarraProgresso,true);

		barraProgressiva.setProgress(progresso);
		
		while (progresso <= 55) {
			progresso = completaBarra();
			barraProgressiva.setProgress(progresso);
			tvBarraProgressiva.setText("Inicializando... "+ progresso + "%");
		}

		new Thread(new Runnable() {
			public void run() {

				tvBarraProgressiva.setText("Aguarde carregando as questões "
						+ progresso + "%");

				while (progresso < MAXIMO) {
					progresso = chamarInclusao();
					
					handler.post(new Runnable() {

						public void run() {
							barraProgressiva.setProgress(progresso);
							tvBarraProgressiva
									.setText("Finalizando... "
											+ progresso + "%");
						}
					});
				}

				finish();
				Intent menu = new Intent(getApplicationContext(), Home.class);
				startActivity(menu);

			}
		}).start();

	}

	public int completaBarra() {
		progresso++;
		barraProgressiva.setProgress(progresso);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return progresso;
	}

	public int chamarInclusao() {
		progresso++;

		if (!questoesInstaladas) {
				new RepositorioScript(this);
				questoesInstaladas = true;
		}
		
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return progresso;

	}

	public View findViewById(int id, boolean mudaFonte) {

		if (mudaFonte) {
			TextView bt = (TextView) super.findViewById(id);
			Typeface font = Typeface.createFromAsset(getAssets(),
					"harabara.ttf");

			bt.setTypeface(font);

			return bt;
		} else {
			return super.findViewById(id);
		}
	}

	
}