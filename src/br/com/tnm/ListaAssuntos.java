package br.com.tnm;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import br.com.tnm.R;
import br.com.tnm.bean.Assunto;
import br.com.tnm.dao.AssuntoDaoImpl;
import br.com.tnm.util.MontaResposta;

public class ListaAssuntos extends ListActivity {

	private ListView listView;
	private ArrayList<Assunto> lista = new ArrayList<Assunto>();
	private String assuntosSelecionados;
	private Intent it;
	private long idMateria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.assuntos);

		idMateria = Long.parseLong(getIntent().getExtras()
				.getSerializable("idMateria").toString());

		findViewById(R.id.action_bar_menu_proseguir)
				.setVisibility(View.VISIBLE);

		final AssuntoDaoImpl aDao = new AssuntoDaoImpl();
		lista = aDao.listarAssuntosPorMateria(this, idMateria);

		this.setListAdapter(new ArrayAdapter<Assunto>(this,
				R.layout.modelo_lista_assuntos, lista));

		TextView tv = (TextView) findViewById(R.id.botaoVoltar,true);
		tv.setText(":: Escolha o(s) assuntos");

		ImageButton botaoConfirmar = (ImageButton) findViewById(R.id.btConfirmar);
		botaoConfirmar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				prosseguirSimulado();
			}
		});

	}

	public void onClickMenuProsseguir(View v) {
		prosseguirSimulado();
	}

	public void prosseguirSimulado() {
		if (listView != null) {

			long[] items = listView.getCheckItemIds();

			if (listView.getCheckItemIds().length > 0) {

				assuntosSelecionados = "";

				for (int i = 0; i < items.length; i++) {
					// pega os itens selecionlados

					if (items.length == i + 1) {

						assuntosSelecionados += lista.get((int) items[i])
								.getCodigo();

					} else {

						assuntosSelecionados += lista.get((int) items[i])
								.getCodigo() + ",";

					}

				}

				Intent acao = new Intent(getApplicationContext(),
						Pesquisa.class);

				acao.putExtra("idMateria", idMateria);
				acao.putExtra("idAssuntos", assuntosSelecionados);

				startActivity(acao);

				finish();

			}
		} else {

			new MontaResposta(getApplicationContext(), getLayoutInflater(),
					"Você deve escolher\npelo menos uma opção.");

		}
	}

	@Override
	public void onBackPressed() {
		overridePendingTransition(0, 0);
		finish();
		it = new Intent(this, ListaMaterias.class);
		startActivity(it);
	}

	public void onClickMenuVoltar(View arg0) {
		onBackPressed();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		listView = new ListView(this);

		listView = l;

		long[] items = listView.getCheckItemIds();

		if (items.length != 0) {

			if (items[0] == 0 && id == 0) {

				for (int i = 0; i < listView.getCount(); i++) {

					listView.setItemChecked(i, true);

				}
			}

			else if (items[0] == 1 && id == 0) {

				for (int i = 0; i < listView.getCount(); i++) {

					listView.setItemChecked(i, false);

				}

			}

			if (id > 0) {

				listView.setItemChecked(0, false);

			}

		}

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
