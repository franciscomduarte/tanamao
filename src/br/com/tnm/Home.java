package br.com.tnm;

import java.util.List;

import br.com.tnm.R;
import br.com.tnm.R.color;
import br.com.tnm.bean.Feed;
import br.com.tnm.util.FeedListAdapter;
import br.com.tnm.util.MontaResposta;
import br.com.tnm.util.XmlHandler;
import android.R.id;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Home extends ActivityComActionBarPrincipal implements
		OnClickListener {
	
	List<Feed> rssStr;
	ListView feedList;
	FeedListAdapter adapter;
	ProgressDialog dialog;
	TextView ultNoticiais;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_inicial);

		feedList = (ListView) findViewById(id.list);
	 	ultNoticiais = (TextView) findViewById(R.id.txUltimasNoticias);
	 	
	 	ultNoticiais.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent acao = new Intent(getApplicationContext(),
						FeedActivity.class);
				startActivity(acao);

			}
		});

		
		if (!isOnline(getApplicationContext())) {
			new MontaResposta(getApplicationContext(), getLayoutInflater(),
					"Nao foi encontrado nenhuma conexão.");
			//finish();
		} else {

			RssFeedTask rssTask = new RssFeedTask();
			rssTask.execute();
		}
		
		feedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
	        public void onItemClick(AdapterView<?> parent, View view, int position,   long id) {  

	        	adapter = new FeedListAdapter(Home.this, rssStr);
	        	Feed feed = (Feed) adapter.getItem(position);

	    		Handler h = new Handler();
//	    		h.postDelayed(this, 100);
//
//	    		dialog = ProgressDialog.show(this, "Aguarde...", "Carregando Notícias",
//	    				true, false);
	    		Intent it = new Intent(getApplicationContext(), Estudo.class);
	    		it.putExtra("titulo", feed.getTitulo());
	    		it.putExtra("descricao", feed.getDescricao());
	    	//	it.putExtra("imgLink", feed.getImgLink());
	    		it.putExtra("categoria", feed.getCategoria());
	    		it.putExtra("url", feed.getUrl());
				startActivity(it);

	        	
	        }
	        
		}); 
		
		findViewById(R.id.action_bar_menu_principal).setVisibility(View.GONE);

		Button botaoPesquisa = (Button) findViewById(R.id.menu_buscar, true);
		botaoPesquisa.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent acao = new Intent(getApplicationContext(),
						ListaMaterias.class);
				startActivity(acao);

			}
		});

		Button botaoContinuar = (Button) findViewById(R.id.menu_continuar, true);
		botaoContinuar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent acao = new Intent(getApplicationContext(),ListarSimulado.class);
				startActivity(acao);
			}
		});

		Button botaoRelatorio = (Button) findViewById(R.id.menu_grafico, true);
		botaoRelatorio.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent acao = new Intent(getApplicationContext(),ListarGrafico.class);
				startActivity(acao);
			}
		});

		Button botaoComprar = (Button) findViewById(R.id.menu_comprar, true);
		botaoComprar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				// String url =
				// "play.google.com/store/apps/details?id=br.com.tnmjuridico";
				String url = "market://details?id=br.com.tnmjuridico";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);

			}
		});

	}
	
	
	
	private class RssFeedTask extends AsyncTask<String, Void, String> {
		// private String Content;
		private ProgressDialog Dialog;
		String response = "";

		@Override
		protected void onPreExecute() {
			Dialog = new ProgressDialog(Home.this);
			Dialog.setMessage("Buscando Notícias...");
			Dialog.show();

		}

		@Override
		protected String doInBackground(String... urls) {
			try {
				String feed = "http://feeds.pciconcursos.com.br/feed/concursos";
				XmlHandler rh = new XmlHandler();
				rssStr = rh.getLatestArticles(feed);

			} catch (Exception e) {
			}
			return response;

		}

		@Override
		protected void onPostExecute(String result) {

			if (rssStr != null) {
				adapter = new FeedListAdapter(Home.this, rssStr);
				feedList.setAdapter(adapter);
			}
			Dialog.dismiss();
		}

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent acao = new Intent("ACAO_MATERIAS");
		startActivity(acao);
	}

	public View findViewById(int id, boolean mudaFonte) {

		if (mudaFonte) {
			Button bt = (Button) super.findViewById(id);
			Typeface font = Typeface.createFromAsset(getAssets(),
					"harabara.ttf");

			bt.setTypeface(font);

			return bt;
		} else {
			return super.findViewById(id);
		}
	}
	
	public boolean isOnline(Context c) {
		ConnectivityManager cm = (ConnectivityManager) c
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();

		if (ni != null && ni.isConnected())
			return true;
		else
			return false;
	}
	
	
}
