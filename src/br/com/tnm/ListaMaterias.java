package br.com.tnm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.tnm.R;
import br.com.tnm.dao.MateriaDaoImpl;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListaMaterias extends ListActivity implements Runnable {

	private List<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
	private ProgressDialog dialog;
	private Intent it;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		MateriaDaoImpl mDao = new MateriaDaoImpl();
		lista = mDao.listarMapaMaterias(this);

		String[] from = new String[] { "nome_materia", "total" };
		int[] to = new int[] { R.id.text1 };
		int LayoutNativo = R.layout.modelo_lista;
		
		setListAdapter(new SimpleAdapter(this, lista, LayoutNativo, from, to));
		
		
		
		setContentView(R.layout.materias);
				
		TextView botaoVoltar = (TextView) findViewById(R.id.botaoVoltar,true);

		botaoVoltar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				overridePendingTransition(0, 0);
				finish();
			}
		});
		
		
	}

	public void onClickMenuVoltar(View arg0) {
		overridePendingTransition(0, 0);
		finish();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		@SuppressWarnings("unchecked")
		HashMap<String, String> lista = (HashMap<String, String>) this
				.getListAdapter().getItem(position);

		Handler h = new Handler();
		h.postDelayed(this, 100);

		dialog = ProgressDialog.show(this, "Aguarde...", "Carregando Assuntos",
				true, false);
		it = new Intent(getApplicationContext(),ListaAssuntos.class);
		it.putExtra("idMateria", lista.get("id").toString());

	}

	public void run() {

		startActivity(it);
		dialog.dismiss();
		overridePendingTransition(0, 0);
		finish();
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