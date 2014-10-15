package br.com.tnm;

import br.com.tnm.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Sobre extends ActivityComActionBarPrincipal {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sobre);
				
		findViewById(R.id.action_bar_menu_info).setVisibility(View.GONE);
		
		TextView tv = (TextView) findViewById(R.id.e2f);
		tv.setClickable(true);
		tv.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				String url = "http://www.e2f.com.br";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);

			}
		});

		ImageView iv = (ImageView) findViewById(R.id.ta_na_mao_logo);
		iv.setClickable(true);
		iv.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				String url = "http://www.tanamaoconcursos.com.br";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);

			}
		});

	}

	
	@Override
	public void onCliconClickMenuInfo(View arg0) {	}
}