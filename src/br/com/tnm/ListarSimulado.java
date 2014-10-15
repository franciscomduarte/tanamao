package br.com.tnm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.tnm.R;
import br.com.tnm.dao.SimuladoDaoImpl;
import br.com.tnm.util.MontaResposta;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListarSimulado extends ListActivity {

	private static final int DIALOG_YES_NO_MESSAGE = 1;
	private ImageButton btExcluir;
	private TextView tvTituloSimulados;

	private List<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		final SimuladoDaoImpl sDao = new SimuladoDaoImpl();
		lista = sDao.listarMapaSimulado(this);

		String[] from = new String[] { "nome_simulado", "questoes", "acertos" };
		int[] to = new int[] { R.id.text1, R.id.text2, R.id.text3 };
		int LayoutNativo = R.layout.simulado_lista;

		setListAdapter(new SimpleAdapter(this, lista, LayoutNativo, from, to));

		setContentView(R.layout.simulados);

		tvTituloSimulados = (TextView) findViewById(R.id.tvTituloSimulado);
		btExcluir = (ImageButton) findViewById(R.id.action_bar_menu_delete);

		tvTituloSimulados.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				onBackPressed();
			}
		});

		if (lista.isEmpty()) {
			new MontaResposta(getApplicationContext(), getLayoutInflater(),
					" Não existem simulados.");
			btExcluir.setVisibility(View.GONE);
		} else {
			btExcluir.setVisibility(View.VISIBLE);
		}
	}

	public void onClickMenuExcluir(View v) {
		showDialog(DIALOG_YES_NO_MESSAGE);
	}

	public void onClickMenuVoltar(View v) {
		onBackPressed();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		@SuppressWarnings("unchecked")
		HashMap<String, String> lista = (HashMap<String, String>) this
				.getListAdapter().getItem(position);

		Intent acao = new Intent(getApplicationContext(), QuestaoMultipla.class);

		long idSimulado = Long.parseLong(lista.get("codigo"));
		int indiceResposta = Integer.parseInt(lista.get("indice_resposta"));

		acao.putExtra("idSimulado", idSimulado);
		acao.putExtra("indiceResposta", indiceResposta);

		startActivity(acao);

		finish();

	}

	@Override
	public Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_YES_NO_MESSAGE:
			return new AlertDialog.Builder(ListarSimulado.this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("Tem certeza que deseja excluir os simulados")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									SimuladoDaoImpl sDao = new SimuladoDaoImpl();
									sDao.excluirSimulados(getApplicationContext());
									new MontaResposta(getApplicationContext(),
											getLayoutInflater(),
											"Simulados excluídos\ncom sucesso.");
									finish();
								}
							})
					.setNegativeButton("Cancelar",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

								}
							}).create();

		}
		return null;
	}

}