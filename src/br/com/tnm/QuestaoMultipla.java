package br.com.tnm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.tnm.R.color;
import br.com.tnm.R.id;
import br.com.tnm.bean.Questao;
import br.com.tnm.dao.ComentarioQuestaoDaoImpl;
import br.com.tnm.dao.QuestaoDaoImpl;
import br.com.tnm.dao.RespostaDaoImpl;
import br.com.tnm.dao.SimuladoDaoImpl;
import br.com.tnm.util.MontaResposta;
import br.com.tnm.util.MudaFonte;
import br.com.tnm.util.Resposta;


public class QuestaoMultipla extends Activity {

	private static final int DIALOG_YES_NO_MESSAGE = 1;
	private static final int DIALOG_GRAFICO = 2;

	private char respostaCerta;
	private RadioButton r1;
	private RadioButton r2;
	private RadioButton r3;
	private RadioButton r4;
	private RadioButton r5;
	private Button btResponder;
	private Button btProxima;
	private Button btPular;
	private int indiceQuestao = 0;
	private QuestaoDaoImpl qDao;
	private SimuladoDaoImpl sDao;
	private Long idSimulado;
	private String tipoQuestao;
	private String enunciadoAtual;
	private float tamanhoFonte = 15;
	private int acerto = 0;
	private Chronometer conometro;
	private Questao questao;
	private boolean finalizado = false;
	private long totalQuestoes;
	private ImageButton ibComentario;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.questao_multipla);

		idSimulado = (Long) getIntent().getExtras().getSerializable(
				"idSimulado");

		if (getIntent().getExtras().getSerializable("indiceResposta") != null) {
			indiceQuestao = (Integer) getIntent().getExtras().getSerializable(
					"indiceResposta");
			if (indiceQuestao > 0)
				indiceQuestao++;

		}

		ibComentario = (ImageButton) findViewById(R.id.ibComentario);

		ibComentario.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				criarComentario(questao.getCodigo());

			}
		});

		qDao = new QuestaoDaoImpl();
		sDao = new SimuladoDaoImpl();

		qDao.listarQuestoes(this, idSimulado);

		totalQuestoes = qDao.totalQuestoes();

		if (totalQuestoes > 0) {
			questao = qDao.buscarQuestao(indiceQuestao);

			conometro = (Chronometer) findViewById(R.id.conometro);
			conometro.setText(sDao.getTempoSimulado(getApplicationContext(),
					idSimulado));
			continuaConometro();

			MostrarQuestao(questao);

		}

	}

	public void MostrarQuestao(Questao q) {
		ScrollView svItens = (ScrollView) findViewById(R.id.scrollViewItens);
		svItens.fullScroll(0);
		svItens.focusSearch(33);

		if (questao.getComentario() != null)
			ibComentario.setVisibility(View.VISIBLE);
		
		if (questao.getRotulo() != null)
		findViewById(R.id.TableLayout01).setBackgroundColor(
				getResources().getColor(R.color.duvida));
		
		respostaCerta = q.getRepostaCerta().charAt(0);
		enunciadoAtual = q.getEnunciado();
		tipoQuestao = q.getTipoQuestao();

		final TextView tvTitulo = (TextView) findViewById(R.id.TotalQuestoes);
		
		tvTitulo.setText("TáNaMão -> Questão " + (indiceQuestao + 1) + " de "
				+ totalQuestoes);

		final TextView tvOrigem = (TextView) findViewById(R.id.OrigemQuestao);
		tvOrigem.setText(q.getOrigemQuestao());

		final TextView tvEnunciado = (TextView) findViewById(R.id.TextoEnunciado);
		tvEnunciado.setText(q.getEnunciado());

		tvEnunciado.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mostrarPopUpEnunciado();
			}
		});

		final RadioGroup radioGroup = (RadioGroup) findViewById(id.radioQuestoes);

		final TextView tvTextoItemCE = (TextView) findViewById(R.id.TextoItemCE);

		tvTextoItemCE.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mostrarPopUpEnunciado();
			}
		});
		
		
		r1 = (RadioButton) findViewById(R.id.radio_item_a);

		r2 = (RadioButton) findViewById(R.id.radio_item_b);
		r3 = (RadioButton) findViewById(R.id.radio_item_c);
		r4 = (RadioButton) findViewById(R.id.radio_item_d);
		r5 = (RadioButton) findViewById(R.id.radio_item_e);
		
		
		tipoQuestao = questao.getTipoQuestao();
		if ("ME".equals(tipoQuestao)) {

			tvEnunciado.setText(questao.getEnunciado());
			tvTextoItemCE.setVisibility(View.GONE);

			r1.setText(questao.getItemA());
			r2.setText(questao.getItemB());
			r3.setText(questao.getItemC());
			r4.setText(questao.getItemD());
			r5.setText(questao.getItemE());
			r5.setVisibility(View.VISIBLE);
			if (questao.getItemE() == null || questao.getItemE().equals("")) {
				r5.setVisibility(View.GONE);
			}
		} else {
			tvEnunciado.setText(questao.getEnunciado());

			tvTextoItemCE.setText(questao.getItemA());
			tvTextoItemCE.setVisibility(View.VISIBLE);

			r1.setText("Certo");
			r2.setText("Errado");
			r3.setVisibility(View.GONE);
			r4.setVisibility(View.GONE);
			r5.setVisibility(View.GONE);
		}

		r1.setChecked(false);
		r2.setChecked(false);
		r3.setChecked(false);
		r4.setChecked(false);
		r5.setChecked(false);

		btResponder = (Button) findViewById(R.id.btResponder);
		btProxima = (Button) findViewById(R.id.btProxima);
		btPular = (Button) findViewById(R.id.btPular);

		if (indiceQuestao == 0 && totalQuestoes == 1) {

			btPular.setVisibility(View.INVISIBLE);
		}

		btPular.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				showMenu();
			}

		});

		
		btResponder.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
				if (radioGroup.getCheckedRadioButtonId() != -1) {

					if ("ME".equals(tipoQuestao)) {
						switch (radioGroup.getCheckedRadioButtonId()) {
						case R.id.radio_item_a:
							acerto = verificaRespostaMultiplaEscolha('A', r1);
							break;
						case R.id.radio_item_b:
							acerto = verificaRespostaMultiplaEscolha('B', r2);
							break;
						case R.id.radio_item_c:
							acerto = verificaRespostaMultiplaEscolha('C', r3);
							break;
						case R.id.radio_item_d:
							acerto = verificaRespostaMultiplaEscolha('D', r4);
							break;
						case R.id.radio_item_e:
							acerto = verificaRespostaMultiplaEscolha('E', r5);
							break;
						}
					} else {
						switch (radioGroup.getCheckedRadioButtonId()) {
						case R.id.radio_item_a:
							acerto = verificaRespostaCertoErrado('C', r1);
							break;
						case R.id.radio_item_b:
							acerto = verificaRespostaCertoErrado('E', r2);
							break;
						}
					}

					r1.setClickable(false);
					r2.setClickable(false);
					r3.setClickable(false);
					r4.setClickable(false);
					r5.setClickable(false);

					btResponder.setClickable(false);
					btResponder.setTextColor(color.cinza_claro);
					
					btPular.setClickable(false);
					btPular.setTextColor(color.cinza_claro);

					if (indiceQuestao + 1 == totalQuestoes) {
						showDialog(DIALOG_GRAFICO);
						// mandar direto para a estatistica.... fos

						btPular.setVisibility(View.INVISIBLE);
						btProxima.setClickable(false);

					}

					btProxima.setVisibility(View.VISIBLE);
					btProxima.setOnClickListener(new View.OnClickListener() {

						public void onClick(View v) {
							proximaQuestao();
						}

					});

				} else {
					new MontaResposta(getApplicationContext(),
							getLayoutInflater(),
							"Você deve escolher\npelo menos uma opção.");
				}
			}
		});

	}


	protected void showMenu() {
		this.getWindow().openPanel(Window.FEATURE_OPTIONS_PANEL,
				new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MENU));

	}

	private void proximaQuestao() {

		if (indiceQuestao + 1 != totalQuestoes) {
			questao = qDao.buscarQuestao(++indiceQuestao);
			limparQuestao();
			MostrarQuestao(questao);
		} else {
			finalizaSimulado();
		}

	}

	// 0 - para questão errada
	// 1 - para questão certa
	private int verificaRespostaCertoErrado(char item, RadioButton r) {
		int acertado = 0;
		if (respostaCerta == item) {
			acertado = 1;
			
			r.setTextColor(getApplicationContext().getResources().getColor(
					color.branco));
			r.setBackgroundColor(getApplicationContext().getResources()
					.getColor(color.verde_escuro_mesmo));
			
			new RespostaDaoImpl().inserirResposta(getApplicationContext(),
					qDao.buscarQuestao(indiceQuestao), Resposta.CERTO, item,
					idSimulado, indiceQuestao);
			new SimuladoDaoImpl().atualizaTempoSimulado(
					getApplicationContext(), conometro.getText().toString(),
					idSimulado);

		} else {
			switch (respostaCerta) {
			case 'C':
				
				r1.setBackgroundColor(getApplicationContext().getResources()
						.getColor(color.verde_escuro_mesmo));
				r1.setTextAppearance(getApplicationContext(),
						R.style.radio_resposta_certa);
				
				// marcando a errada
				r2.setBackgroundColor(getApplicationContext().getResources()
						.getColor(color.rosa));
				r2.setTextAppearance(getApplicationContext(),
						R.style.radio_resposta_errada);
				break;
			case 'E':
				
				r2.setTextAppearance(getApplicationContext(),
						R.style.radio_resposta_certa);
				r2.setBackgroundColor(getApplicationContext().getResources()
						.getColor(color.verde_escuro_mesmo));
				
				r1.setBackgroundColor(getApplicationContext().getResources()
						.getColor(color.rosa));
				r1.setTextAppearance(getApplicationContext(),
						R.style.radio_resposta_errada);

				break;
			}
			
			new RespostaDaoImpl().inserirResposta(getApplicationContext(),
					qDao.buscarQuestao(indiceQuestao), Resposta.ERRADO, item,
					idSimulado, indiceQuestao);
			new SimuladoDaoImpl().atualizaTempoSimulado(
					getApplicationContext(), conometro.getText().toString(),
					idSimulado);

		}
		return acertado;
	}

	private int verificaRespostaMultiplaEscolha(char item, RadioButton r) {
		int acertado = 0;
		if (respostaCerta == item) {
			acertado = 1;
			r.setBackgroundColor(getApplicationContext().getResources()
					.getColor(color.verde_escuro_mesmo));
			
			r.setTextAppearance(getApplicationContext(),
					R.style.radio_resposta_certa);

			new RespostaDaoImpl().inserirResposta(getApplicationContext(),
					qDao.buscarQuestao(indiceQuestao), Resposta.CERTO, item,
					idSimulado, indiceQuestao);
			new SimuladoDaoImpl().atualizaTempoSimulado(
					getApplicationContext(), conometro.getText().toString(),
					idSimulado);


		} else {
			r.setBackgroundColor(getApplicationContext().getResources()
					.getColor(color.rosa));
			

			switch (respostaCerta) {
			case 'A':
				r1.setTextAppearance(getApplicationContext(),
						R.style.radio_resposta_certa);
				r1.setBackgroundColor(getApplicationContext().getResources()
						.getColor(color.verde_escuro_mesmo));
				
				break;
			case 'B':
				r2.setTextAppearance(getApplicationContext(),
						R.style.radio_resposta_certa);
				r2.setBackgroundColor(getApplicationContext().getResources()
						.getColor(color.verde_escuro_mesmo));
				
				break;
			case 'C':
				r3.setTextAppearance(getApplicationContext(),
						R.style.radio_resposta_certa);
				r3.setBackgroundColor(getApplicationContext().getResources()
						.getColor(color.verde_escuro_mesmo));
				
				break;
			case 'D':
				r4.setTextAppearance(getApplicationContext(),
						R.style.radio_resposta_certa);
				r4.setBackgroundColor(getApplicationContext().getResources()
						.getColor(color.verde_escuro_mesmo));
				
				break;

			case 'E':
				r5.setTextAppearance(getApplicationContext(),
						R.style.radio_resposta_certa);
				r5.setBackgroundColor(getApplicationContext().getResources()
						.getColor(color.verde_escuro_mesmo));
				
				break;
			}
			
			new RespostaDaoImpl().inserirResposta(getApplicationContext(),
					qDao.buscarQuestao(indiceQuestao), Resposta.ERRADO, item,
					idSimulado, indiceQuestao);
			new SimuladoDaoImpl().atualizaTempoSimulado(
					getApplicationContext(), conometro.getText().toString(),
					idSimulado);

			if (indiceQuestao + 1 == totalQuestoes) {
				finalizaSimulado();
			}
		}
		return acertado;
	}
	
	private void limparQuestao() {

		findViewById(R.id.TableLayout01).setBackgroundColor(
				getResources().getColor(R.color.title_background));
		
		btResponder.setClickable(true);
		btResponder.setTextColor(Color.WHITE);
		
		btProxima.setVisibility(View.VISIBLE);
		
		ibComentario.setVisibility(View.GONE);

		r1.setClickable(true);
		r2.setClickable(true);
		r3.setClickable(true);
		r4.setClickable(true);
		r5.setClickable(true);
		
		r1.setVisibility(View.VISIBLE);
		r2.setVisibility(View.VISIBLE);
		r3.setVisibility(View.VISIBLE);
		r4.setVisibility(View.VISIBLE);
		r5.setVisibility(View.VISIBLE);
		
		r1.setBackgroundColor(Color.TRANSPARENT);
		r1.setTextAppearance(getApplicationContext(), R.style.radio_resposta_limpa);
		
		r2.setBackgroundColor(Color.TRANSPARENT);
		r2.setTextAppearance(getApplicationContext(), R.style.radio_resposta_limpa);
		
		r3.setBackgroundColor(Color.TRANSPARENT);
		r3.setTextAppearance(getApplicationContext(), R.style.radio_resposta_limpa);
		
		r4.setBackgroundColor(Color.TRANSPARENT);
		r4.setTextAppearance(getApplicationContext(), R.style.radio_resposta_limpa);
		
		r5.setBackgroundColor(Color.TRANSPARENT);
		r5.setTextAppearance(getApplicationContext(), R.style.radio_resposta_limpa);
		
		RadioGroup rg = (RadioGroup) findViewById(R.id.radioQuestoes);
		rg.clearCheck();
		


		if (!(indiceQuestao + 1 == totalQuestoes)) {
			btPular.setClickable(true);
			btPular.setTextColor(Color.BLACK);
			btProxima.setVisibility(View.INVISIBLE);
		} else {
			btPular.setVisibility(View.INVISIBLE);
			btProxima.setVisibility(View.INVISIBLE);
			btProxima.setText("Sair");
		}

	}

	public void finalizaSimulado() {

		if (!finalizado) {
			new SimuladoDaoImpl().finalizaSimulado(getApplicationContext(),
					idSimulado);
			new SimuladoDaoImpl().atualizaTempoSimulado(
					getApplicationContext(), conometro.getText().toString(),
					idSimulado);
			finalizado = true;
		} else {
			finish();
			overridePendingTransition(0, 0);
		}
	}

	@Override
	public Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_YES_NO_MESSAGE:
			return new AlertDialog.Builder(QuestaoMultipla.this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("Tem certeza que deseja sair do simulado")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									sDao.atualizaTempoSimulado(
											getApplicationContext(), conometro
													.getText().toString(),
											idSimulado);
									finish();
								}
							})
					.setNegativeButton("Cancelar",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

								}
							}).create();
		case DIALOG_GRAFICO:
			return new AlertDialog.Builder(QuestaoMultipla.this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle(
							"O Simulado foi finalizado, verifique os resultados.")
					.setPositiveButton("Resultados",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									Intent it = new Intent(
											getApplicationContext(),
											Grafico.class);
									it.putExtra("idSimulado", idSimulado);
									startActivity(it);
									finish();
								}
							})
					.setNegativeButton("Fechar",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									finish();
								}
							}).create();
		}
		return null;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuinflater = getMenuInflater();
		menuinflater.inflate(R.menu.menu_opcoes_questoes, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.pular:
			if (btPular.isClickable()) {
				Questao questaoAtual = new Questao();
				int proximo = indiceQuestao + 1;
				
				questaoAtual = qDao.buscarQuestao(indiceQuestao);
				// Se esse atributo for igual a 1, o cara pulou a questão.
				questaoAtual.setQuestaoPulada(1);
				qDao.reposicionaItemLista(indiceQuestao, questaoAtual);
				questao = new Questao();
				questao = qDao.buscarQuestao(indiceQuestao);
				limparQuestao();
				MostrarQuestao(questao);
			}
			break;
		case R.id.duvida:
			qDao.alterarRotulo(getApplicationContext(), "duvida",
					questao.getCodigo());
			findViewById(R.id.TableLayout01).setBackgroundColor(
					getResources().getColor(R.color.duvida));

			break;
		case R.id.comentario:
			criarComentario(questao.getCodigo());
			// Toast.makeText(getApplicationContext(), "Comentario",
			// Toast.LENGTH_SHORT).show();
			break;

		}

		// new
		// MontaResposta(getApplicationContext(),getLayoutInflater(),"Selecionado item "+item.toString());
		return super.onOptionsItemSelected(item);
	}

	public void continuaConometro() {

		int stoppedMilliseconds = 0;

		String chronoText = conometro.getText().toString();
		String array[] = chronoText.split(":");
		if (array.length == 2) {
			stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000
					+ Integer.parseInt(array[1]) * 1000;
		} else if (array.length == 3) {
			stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
					+ Integer.parseInt(array[1]) * 60 * 1000
					+ Integer.parseInt(array[2]) * 1000;
		}

		conometro.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
		conometro.start();

	}

	private void mostrarPopUpEnunciado() {

		final Dialog dialog = new Dialog(this);

		dialog.setContentView(R.layout.dialog_enunciado);// carregando o layout
															// do
															// dialog do xml
		dialog.setTitle("::Enunciado Questão");

		final TextView enunciado = (TextView) dialog
				.findViewById(R.id.tvEnunciadoDialog);
		enunciado.setText(enunciadoAtual);

		final TextView item = (TextView) dialog
				.findViewById(R.id.TextoItemCEDialog);

		if ("ME".equals(tipoQuestao)) {
			item.setVisibility(View.GONE);
		} else {
			item.setText(questao.getItemA());
			item.setVisibility(View.VISIBLE);
		}

		final ImageButton continuar = (ImageButton) dialog
				.findViewById(R.id.bt_continuar);
		continuar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				dialog.dismiss();

			}
		});

		final ImageButton btA1 = (ImageButton) dialog.findViewById(R.id.btA1);
		btA1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setTamanhoFonte(tamanhoFonte - 1);
				MudaFonte.MudaFonteTextView(enunciado, getTamanhoFonte());
				MudaFonte.MudaFonteTextView(item, getTamanhoFonte());
			}
		});

		final ImageButton btA3 = (ImageButton) dialog.findViewById(R.id.btA3);
		btA3.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setTamanhoFonte(tamanhoFonte + 1);
				MudaFonte.MudaFonteTextView(enunciado, getTamanhoFonte());
				MudaFonte.MudaFonteTextView(item, getTamanhoFonte());
			}
		});

		dialog.show();// mostra o dialog

	}

	private void criarComentario(final Long codigoQuestao) {

		final Dialog dialog = new Dialog(this);

		dialog.setContentView(R.layout.dialog_criar_comentario);// carregando o
																// layout
		// do
		// dialog do xml
		dialog.setTitle("::Digite seu comentário - " + codigoQuestao);

		final EditText txComentario = (EditText) dialog
				.findViewById(R.id.txComentario);

		txComentario.setText(questao.getComentario());

		final ImageButton continuar = (ImageButton) dialog
				.findViewById(R.id.bt_comentario_retorno);
		continuar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				dialog.dismiss();

			}
		});

		final ImageButton btSalvarComentario = (ImageButton) dialog
				.findViewById(R.id.bt_salvar_comentario);

		btSalvarComentario.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (txComentario.getText().toString() != null) {
					ComentarioQuestaoDaoImpl comentarioDao = new ComentarioQuestaoDaoImpl();
					if (questao.getComentario() == null) {
						comentarioDao.salvarComentario(getApplicationContext(),
								codigoQuestao, txComentario.getText()
										.toString());
						questao.setComentario(txComentario.getText().toString());
					} else {
						comentarioDao.atualizarComentario(
								getApplicationContext(), codigoQuestao,
								txComentario.getText().toString());
						questao.setComentario(txComentario.getText().toString());
					}

					Toast.makeText(getApplicationContext(),
							"Comentário Salvo.", Toast.LENGTH_SHORT).show();
					ibComentario.setVisibility(View.VISIBLE);
					dialog.dismiss();
				}else{
					Toast.makeText(getApplicationContext(),
							"Nenhum texto foi digitado.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		dialog.show();// mostra o dialog

	}

	

	public void onBackPressed() {

		showDialog(DIALOG_YES_NO_MESSAGE);
		return;
	}
	
}