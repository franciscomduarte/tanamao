package br.com.tnm;

import java.util.HashMap;

import br.com.tnm.R;
import br.com.tnm.dao.SimuladoDaoImpl;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Typeface;

public class Grafico extends Activity {

	private ProgressBar progressAcertos;
	private ProgressBar progressErros;
	private TextView tvTotalQuestoes;
	private TextView tempoTotal;
	private TextView tempoMedio;
	private TextView porcentagemAcertos;
	private TextView porcentagemErros;
	private long idSimulado;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graficos);

		TextView btVoltar = (TextView) findViewById(R.id.botaoVoltar, true);
		btVoltar.setText("::Desempenho");

		progressAcertos = (ProgressBar) findViewById(R.id.ProgressBarAcertos);
		progressErros = (ProgressBar) findViewById(R.id.ProgressBarErros);
		tempoTotal = (TextView) findViewById(R.id.tvTempoTotal, true);
		tempoMedio = (TextView) findViewById(R.id.tvTempoMedio,true);
		tvTotalQuestoes = (TextView) findViewById(R.id.tvTotalDeQuestoes, true);
		porcentagemAcertos = (TextView) findViewById(R.id.porcentagemAcertos,
				true);
		porcentagemErros = (TextView) findViewById(R.id.porcentagemErros, true);

		findViewById(R.id.tvAcertos, true);
		findViewById(R.id.tvErros, true);
		findViewById(R.id.tvTempoMedioLabel, true);
		findViewById(R.id.TvTempoTotalLabel, true);

		idSimulado = getIntent().getExtras().getLong("idSimulado");

		HashMap<String, String> mapaSimulado = new SimuladoDaoImpl()
				.mapaSimulado(getApplicationContext(), idSimulado);

		String nomeSimulado = mapaSimulado.get("nome_simulado");
		String tempo = mapaSimulado.get("tempo");
		int acertos = Integer.parseInt(mapaSimulado.get("acertos"));
		int erros = Integer.parseInt(mapaSimulado.get("erros"));
		int questoeRespondidadas = Integer.parseInt(mapaSimulado
				.get("questoes_respondidas"));
		int total = Integer.parseInt(mapaSimulado.get("total_questoes"));

		int porcAcertos = (100 * acertos) / questoeRespondidadas;
		int porcErros = (100 * erros) / questoeRespondidadas;

		porcentagemAcertos.setText(porcAcertos + "%");
		porcentagemErros.setText(porcErros + "%");

		progressAcertos.setProgress(porcAcertos);
		progressErros.setProgress(porcErros);
		tempoTotal.setText(tempo);

		tvTotalQuestoes.setText(nomeSimulado + "Questões respondidas ("
				+ questoeRespondidadas + " de " + total + ")");

		int tempoPorQuestao = calcularTempo(tempo)/questoeRespondidadas;
		tempoMedio.setText(converterSegundosEmMinutos(tempoPorQuestao));
		
		
	}

	public int calcularTempo(String tempo) {
		String array[] = tempo.split(":");
		int hora = 0;
		int minutos = 0;
		int segundos = 0;
		if (array.length > 2) {
			hora = Integer.parseInt(array[0]) * 3600;
			minutos = Integer.parseInt(array[1]) * 60;
			segundos = Integer.parseInt(array[2]);
		} else {
			minutos = Integer.parseInt(array[0]) * 60;
			segundos = Integer.parseInt(array[1]);
		}

		return hora + minutos + segundos;
	}

	public String converterSegundosEmMinutos(int segundos){
		String tempoFinal;
		
		int tempoMinutos = (segundos / 60);
		
		tempoFinal = tempoMinutos+":";
		
		int tempoSegundos = segundos - (tempoMinutos*60);
		if ((tempoSegundos>0) && (tempoSegundos < 10))
			tempoFinal+= "0"+tempoSegundos;
		else if (tempoSegundos>0) {
			tempoFinal+= tempoSegundos;
		}else{
			tempoFinal+="00";
		}
		
		return tempoFinal;
	}
	
	
	public void onClickMenuVoltar(View v) {
		onBackPressed();
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
