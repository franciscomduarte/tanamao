package br.com.tnm;

import java.util.ArrayList;
import java.util.List;

import br.com.tnm.R;
import br.com.tnm.bean.Feed;
import br.com.tnm.util.FeedListAdapter;
import br.com.tnm.util.MontaResposta;
import br.com.tnm.util.XmlHandler;


import android.R.id;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class FeedActivity extends ListActivity implements Runnable {

	List<Feed> rssStr;
	ListView feedList;
	FeedListAdapter adapter;
	ProgressDialog dialog;
	Intent it;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.feed);

		feedList = (ListView) findViewById(id.list);

		if (!isOnline(getApplicationContext())) {
			new MontaResposta(getApplicationContext(), getLayoutInflater(),
					"Nao foi encontrado nenhuma conexão.");
			finish();
		} else {

			RssFeedTask rssTask = new RssFeedTask();
			rssTask.execute();
		}

		//Botao voltar 
		
		
		TextView btVoltar = (TextView) findViewById(R.id.botaoVoltar,true);
		btVoltar.setText(":: Notícias");
		
		// BOTAO CATEGORIA

		final TextView tvDF = (TextView) findViewById(R.id.df);
		final TextView tvAC = (TextView) findViewById(R.id.ac);
		final TextView tvAL = (TextView) findViewById(R.id.al);
		final TextView tvSP = (TextView) findViewById(R.id.sp);
		
		final TextView tvMG = (TextView) findViewById(R.id.mg);
		final TextView tvES = (TextView) findViewById(R.id.es);
		final TextView tvGO = (TextView) findViewById(R.id.go);
		final TextView tvMS = (TextView) findViewById(R.id.ms);
		final TextView tvPA = (TextView) findViewById(R.id.pa);
		final TextView tvPR = (TextView) findViewById(R.id.pr);
		final TextView tvAM = (TextView) findViewById(R.id.am);
		
		final TextView tvMA = (TextView) findViewById(R.id.ma);
		final TextView tvPB = (TextView) findViewById(R.id.pb);
		final TextView tvPE = (TextView) findViewById(R.id.pe);
		final TextView tvPI = (TextView) findViewById(R.id.pi);
		final TextView tvRN = (TextView) findViewById(R.id.rn);
		final TextView tvSE = (TextView) findViewById(R.id.se);
		final TextView tvRJ = (TextView) findViewById(R.id.rj);
		final TextView tvRS = (TextView) findViewById(R.id.rs);
		final TextView tvSC = (TextView) findViewById(R.id.sc);
		final TextView tvMT = (TextView) findViewById(R.id.mt);
		final TextView tvAP = (TextView) findViewById(R.id.ap);
		final TextView tvRO = (TextView) findViewById(R.id.ro);
		final TextView tvRR = (TextView) findViewById(R.id.rr);
		final TextView tvTO = (TextView) findViewById(R.id.to);
		final TextView tvBA = (TextView) findViewById(R.id.ba);
		final TextView tvCE = (TextView) findViewById(R.id.ce);
		
		tvMA.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/ma");

				
			}
		});
		
		tvPB.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/pb");

				
			}
		});
		
		tvPE.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/pe");

				
			}
		});
		
		tvPI.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/pi");

				
			}
		});
		
		
		tvRN.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/rn");

				
			}
		});
		
		tvSE.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/se");

				
			}
		});
		
		tvRJ.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/rj");

				
			}
		});
		
		
		tvRS.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/rs");

				
			}
		});
		
		tvSC.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/sc");

				
			}
		});
		
		tvMT.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/mt");

				
			}
		});
		
		tvAP.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/ap");

				
			}
		});
		
		tvRO.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/ro");

				
			}
		});
		
		tvRR.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/rr");

				
			}
		});
		
		tvTO.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/to");

				
			}
		});
		
		tvBA.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/ba");

				
			}
		});
		
		tvCE.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/ce");

				
			}
		});
		
		
		
		tvDF.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/df");

				
			}
		});
		
		tvSP.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/sp");

				
			}
		});

		
		
		tvMG.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("MG -> http://feeds.pciconcursos.com.br/feed/mg");

				
			}
		});
		
		
		tvES.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/es");

				
			}
		});
		
		
		tvPR.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/pr");

				
			}
		});
		

		
		tvGO.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/go");

				
			}
		});
		
		
		tvMS.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/ms");

				
			}
		});
		
		
		tvAM.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/am");

				
			}
		});

		tvPA.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/pa");

			}
		});

		tvAL.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/al");

			}
		});

		tvAC.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				filtrarCategoria("http://feeds.pciconcursos.com.br/feed/al");

			}
		});

		
		}
	
	
	
	public void filtrarCategoria(final String urlFeed){

		

		final Handler    handler = new Handler();

		dialog = ProgressDialog.show(FeedActivity.this, "Aguarde...", "Filtrando Notícias",
				true, false);
		


		Thread thread = new Thread() {
			public void run () {

				
				handler.post(new Runnable() {
					
					//@Override
					public void run() {

						ArrayList<Feed> feedsFiltrado = new ArrayList<Feed>();

						XmlHandler rh = new XmlHandler();
						rssStr = rh.getLatestArticles(urlFeed);
						if (rssStr != null) {
							adapter = new FeedListAdapter(FeedActivity.this, rssStr);
							feedList.setAdapter(adapter);

						dialog.dismiss();
						}

					 }

                });
				

            }

        };

        thread.start();
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		Feed feed = (Feed) this.adapter.getItem(position);

		Handler h = new Handler();
		h.postDelayed(this, 100);

		dialog = ProgressDialog.show(this, "Aguarde...", "Carregando Notícias",
				true, false);
		it = new Intent(getApplicationContext(), Estudo.class);
		it.putExtra("titulo", feed.getTitulo());
		it.putExtra("descricao", feed.getDescricao());
	//	it.putExtra("imgLink", feed.getImgLink());
		it.putExtra("categoria", feed.getCategoria());
		it.putExtra("url", feed.getUrl());

	}

	private class RssFeedTask extends AsyncTask<String, Void, String> {
		// private String Content;
		private ProgressDialog Dialog;
		String response = "";

		@Override
		protected void onPreExecute() {
			Dialog = new ProgressDialog(FeedActivity.this);
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
				adapter = new FeedListAdapter(FeedActivity.this, rssStr);
				feedList.setAdapter(adapter);
			}
			Dialog.dismiss();
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

	public void run() {

		startActivity(it);
		dialog.dismiss();
		// finish();
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
