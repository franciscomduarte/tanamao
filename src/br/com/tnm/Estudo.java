package br.com.tnm;



import br.com.tnm.R;
import br.com.tnm.util.MudaFonte;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;

public class Estudo extends Activity {

	private float tamanhoFonte = 15;
	private TextView tvNoticia;
	private TextView tvTituloNoticia;
	private TextView tvFonte;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.estudo);

		tvNoticia = (TextView) findViewById(R.id.tvNoticia);
		tvTituloNoticia = (TextView) findViewById(R.id.tvTituloNoticia);
		tvFonte = (TextView) findViewById(R.id.tvFonte);
	//tvCategoria = (TextView) findViewById(R.id.categoria);
	//	imagemNoticia = (ImageView) findViewById(R.id.imageNoticia);
	//	btVoltar = (ImageButton) findViewById(R.id.botaoVoltar);

		tvNoticia.setText(Html.fromHtml(getIntent().getExtras()
				.getSerializable("descricao").toString()));
		tvNoticia.setMovementMethod(LinkMovementMethod.getInstance());

		tvTituloNoticia.setText(getIntent().getExtras()
				.getSerializable("titulo").toString());
//		tvCategoria.setText(getIntent().getExtras()
//				.getSerializable("categoria").toString());

//		try {
			final String urlNoticia = getIntent().getExtras()
					.getSerializable("url").toString();

			tvNoticia.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					Intent in = new Intent(Intent.ACTION_VIEW, Uri
							.parse(urlNoticia));
					startActivity(in);

				}
			});
			
			
			TextView tv = (TextView) findViewById(R.id.botaoVoltar,true); 
			tv.setText("Detalhe notícia");
//
//			URL feedImage = new URL(getIntent().getExtras()
//					.getSerializable("imgLink").toString());
//			if (!feedImage.toString().equalsIgnoreCase("null")) {
//				HttpURLConnection conn = (HttpURLConnection) feedImage
//						.openConnection();
//				InputStream is = conn.getInputStream();
//				Bitmap img = BitmapFactory.decodeStream(is);
//				imagemNoticia.setImageBitmap(img);
//			}
//
//		} catch (MalformedURLException e) {
//
//		} catch (IOException e) {
//
//		}

		// TextView tvMenor = (TextView) findViewById(R.id.tvMenor);
		// tvMenor.setText("<");
		//
		// TextView tvMaior = (TextView) findViewById(R.id.tvMaior);
		// tvMaior.setText(">");

//		btVoltar.setVisibility(1);
//
//		btVoltar.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				finish();
//			}
//		});

		// MUNDANDO FONTE PADRAO
		// TextView txt = (TextView) findViewById(R.id.tvNoticia);
		// TextView txt2 = (TextView) findViewById(R.id.tvTituloNoticia);
		// Typeface font = Typeface.createFromAsset(getAssets(),
		// "CreteRound-Regular.ttf");
		// txt.setTypeface(font);
		// txt2.setTypeface(font);

		overridePendingTransition(0, 0);
		// ACTION BAR
//		ImageButton abaOpcoes = (ImageButton) findViewById(R.id.btOpcoes);
//
//		abaOpcoes.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View v) {
//
//				finish();
//				overridePendingTransition(0, 0);
//			}
//		});
//
//		ImageButton abaBalao = (ImageButton) findViewById(R.id.btBalao);
//
//		abaBalao.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View v) {
//
//				// Intent acao = new Intent(getApplicationContext(),
//				// Ajuda.class);
//				// startActivity(acao);
//				// finish();
//
//			}
//		});
//
//		ImageButton abaAjuda = (ImageButton) findViewById(R.id.btAjuda);
//
//		abaAjuda.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View v) {
//
//				Intent acao = new Intent(getApplicationContext(), Ajuda.class);
//				startActivity(acao);
//
//			}
//		});
//
//		ImageButton abaInfo = (ImageButton) findViewById(R.id.btInformacao);
//
//		abaInfo.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View v) {
//
//				Intent acao = new Intent(getApplicationContext(), Sobre.class);
//				startActivity(acao);
//				finish();
//			}
//		});

		ImageButton btA1 = (ImageButton) findViewById(R.id.btA1);
		btA1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setTamanhoFonte(tamanhoFonte - 1);
				MudaFonte.MudaFonteTextView(tvNoticia, tvFonte,
						getTamanhoFonte());
				MudaFonte.MudaFonteTextView(tvTituloNoticia, tvFonte,
						getTamanhoFonte());

			}
		});

		ImageButton btA3 = (ImageButton) findViewById(R.id.btA3);
		btA3.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setTamanhoFonte(tamanhoFonte + 1);
				MudaFonte.MudaFonteTextView(tvNoticia, tvFonte,
						getTamanhoFonte());
				MudaFonte.MudaFonteTextView(tvTituloNoticia, tvFonte,
						getTamanhoFonte());

			}
		});
	}

	public void onClick(View arg0) {
		tvNoticia.setText("My text on click");
	}

	public void setTamanhoFonte(float tamanho) {
		if ((tamanho < 15) || (tamanho > 20)) {
			if (tamanho < 15)
				tamanhoFonte = 15;
			if (tamanho > 20)
				tamanhoFonte = 20;
		} else {
			tamanhoFonte = tamanho;
		}
	}

	public float getTamanhoFonte() {
		return this.tamanhoFonte;
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
	
	public void onClickMenuVoltar(View v){
		onBackPressed();
	}
	
}
