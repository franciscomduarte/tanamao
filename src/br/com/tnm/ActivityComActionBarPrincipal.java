package br.com.tnm;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public abstract class ActivityComActionBarPrincipal extends Activity {

	public void onClickMenuPrincipal(View arg0) {

		finish();
		overridePendingTransition(0, 0);
	}

	public void onCliconClickMenuInfo(View arg0) {

		Intent it = new Intent(getApplicationContext(),Sobre.class);
		startActivity(it);
		overridePendingTransition(0, 0);
	}

	public void onClickMenuCompartilhar(View arg0) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(android.content.Intent.EXTRA_TEXT, "Estou utilizando o tanamão e me preparando para os concursos, baixe você também no https://play.google.com/store/apps/details?id=br.com.tnmbankti");
		startActivity(intent); 
	}

	public void onClickMenuNoticias(View arg0) {

		Intent acao = new Intent(getApplicationContext(),
				FeedActivity.class);
		startActivity(acao);		//overridePendingTransition(0, 0);
		
	}

	public void onClickMenuEstrela(View arg0) {
		String url = "market://details?id=br.com.tnmbankti";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

	public void onClickMenuEmail(View arg0) {
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "[CONTATO APP-TáNaMão]");
		intent.setData(Uri.parse("mailto:sac@tanamaoconcursos.com.br"));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(0, 0);
		super.onBackPressed();
	}
}
