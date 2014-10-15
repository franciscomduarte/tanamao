package br.com.tnm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import br.com.tnm.R;
import br.com.tnm.dao.SimuladoDaoImpl;
import br.com.tnm.util.MontaResposta;

public class ListarGrafico extends ListActivity {

	private List<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();

	private static final int DIALOG_YES_NO_MESSAGE = 1;
	private ImageButton btExcluir;
	private TextView tvTituloSimulados;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		SimuladoDaoImpl sDao = new SimuladoDaoImpl();
		lista = sDao.listarMapaSimuladoGrafico(this);

		String[] from = new String[] { "nome_simulado", "questoes", "acertos" };
		int[] to = new int[] { R.id.text1, R.id.text2, R.id.text3 };
		int LayoutNativo = R.layout.simulado_lista;

		setListAdapter(new SimpleAdapter(this, lista, LayoutNativo, from, to));

		setContentView(R.layout.simulados_grafico);

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

		long idSimulado = Long.parseLong(lista.get("codigo"));

		// Intent acao = new BudgetPieChart().execute(this, idSimulado);

		Intent acao = new Intent(getApplicationContext(), Grafico.class);

		acao.putExtra("idSimulado", idSimulado);

		finish();

		startActivity(acao);

	}

	@Override
	public Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_YES_NO_MESSAGE:
			return new AlertDialog.Builder(ListarGrafico.this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle(
							"Tem certeza que deseja excluir os simulados reallizados")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									SimuladoDaoImpl sDao = new SimuladoDaoImpl();
									sDao.excluirSimuladosGrafico(getApplicationContext());
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