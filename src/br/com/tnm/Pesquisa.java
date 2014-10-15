package br.com.tnm;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.tnm.R;
import br.com.tnm.bean.Banca;
import br.com.tnm.bean.Cargo;
import br.com.tnm.bean.Materia;
import br.com.tnm.bean.Orgao;
import br.com.tnm.bean.QuestaoFiltro;
import br.com.tnm.dao.BancaDaoImpl;
import br.com.tnm.dao.CargoDaoImpl;
import br.com.tnm.dao.MateriaDaoImpl;
import br.com.tnm.dao.OrgaoDaoImpl;
import br.com.tnm.dao.QuestaoDaoImpl;
import br.com.tnm.dao.SimuladoDaoImpl;
import br.com.tnm.util.Ano;
import br.com.tnm.util.ENivelQuestao;
import br.com.tnm.util.MontaResposta;

public class Pesquisa extends Activity implements OnClickListener{
	
	private Spinner comboBanca;
	private Spinner comboOrgao;
	private Spinner comboAno;
	private Spinner comboCargo;
	private CheckBox checkBoxNivelMedio;
	private CheckBox checkBoxNivelSuperior;
	
	private QuestaoFiltro qf = new QuestaoFiltro();

	private Banca banca = new Banca();
	private Orgao orgao = new Orgao();
	private Cargo cargo = new Cargo();
	
	private BancaDaoImpl aDao = new BancaDaoImpl();
	private OrgaoDaoImpl orgaoDaoImpl = new OrgaoDaoImpl();
	private SimuladoDaoImpl simuladoDaoImpl = new SimuladoDaoImpl();
	private CargoDaoImpl cargoDaoImpl = new CargoDaoImpl();
	private MateriaDaoImpl mDao = new MateriaDaoImpl();
	
	private List<Banca> bancas = new ArrayList<Banca>();
	private List<Orgao> orgaos = new ArrayList<Orgao>();
	private List<Ano> anos = new ArrayList<Ano>();
	private List<Cargo> cargos = new ArrayList<Cargo>();
	
	private ArrayAdapter<Banca> adpBanca = null;
	private ArrayAdapter<Orgao> adpOrgao = null;
	private ArrayAdapter<Ano> adpAno = null;
	private ArrayAdapter<Cargo> adpCargo = null;
	
	private Materia m = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pesquisa);
		
		
		TextView tvTitulo = (TextView) findViewById(R.id.botaoVoltar,true);
		ImageButton botaoContinuar = (ImageButton) findViewById(R.id.btContinuar);
		botaoContinuar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				comboBanca = (Spinner) findViewById(R.id.spBanca);
				comboOrgao = (Spinner) findViewById(R.id.spInstituicao);
				comboAno   = (Spinner) findViewById(R.id.spAno);
				comboCargo = (Spinner) findViewById(R.id.spCargo);
				
				checkBoxNivelMedio = (CheckBox)findViewById(R.id.ckNivelMedio);
				checkBoxNivelSuperior = (CheckBox)findViewById(R.id.ckNivelSuperior);
				
				if (checkBoxNivelMedio.isChecked())
				   qf.setNivelQuestao(ENivelQuestao.M);
						
				if (checkBoxNivelSuperior.isChecked())
					qf.setNivelQuestao(ENivelQuestao.S);
					
				Intent acao = new Intent(getApplicationContext(),QuestaoMultipla.class);
				
				banca = (Banca) comboBanca.getSelectedItem();
				qf.setBancaCodigo(banca.getCodigo());
				qf.setMateriaCodigo(Long.parseLong(getIntent().getExtras().getSerializable("idMateria").toString()));
				
				qf.setAssuntos(getIntent().getExtras().getSerializable("idAssuntos").toString());
				
				cargo = (Cargo) comboCargo.getSelectedItem();
				qf.setCargoCodigo(cargo.getCodigo());
				
				orgao = (Orgao) comboOrgao.getSelectedItem();
				qf.setOrgaoCodigo(orgao.getCodigo());

				SimuladoDaoImpl daoImpl = new SimuladoDaoImpl();
				QuestaoDaoImpl qDao = new QuestaoDaoImpl();
				
				Integer total = qDao.listarQuestoesFiltro(getApplicationContext(), qf);
				qf.setTotalQuestoes(total);
				
				if (total>0) {
					long codigoSimualdo = daoImpl.inserirSimulado(getApplicationContext(), qf);
				
					acao.putExtra("idSimulado", codigoSimualdo);
					acao.putExtra("idMateria", qf.getMateriaCodigo());
				
					finish();
				
					startActivity(acao);
				} else {
					new MontaResposta(getApplicationContext(), getLayoutInflater(), " Nenhuma questão encontrada\n com os filtros selecionados");
				}
				
			}
		});			
		
		m = mDao.getMateria(this, getIntent().getExtras().getSerializable("idMateria").toString());
		
		qf.setMateriaCodigo(m.getCodigo());
		qf.setAssuntos(getIntent().getExtras().getSerializable("idAssuntos").toString());
		
        comboBanca = (Spinner) findViewById(R.id.spBanca);
        comboOrgao = (Spinner) findViewById(R.id.spInstituicao);
    	comboAno   = (Spinner) findViewById(R.id.spAno);
		comboCargo = (Spinner) findViewById(R.id.spCargo);
		
        bancas = aDao.listarBancasFiltro(getApplicationContext(), qf);
        adpBanca = new ArrayAdapter<Banca>(getApplicationContext(), android.R.layout.simple_spinner_item, bancas);
        adpBanca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		comboBanca.setAdapter(adpBanca);
		
		anos = simuladoDaoImpl.getAnos(getApplicationContext(), qf);
        adpAno = new ArrayAdapter<Ano>(getApplicationContext(), android.R.layout.simple_spinner_item, anos);
        adpAno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		comboAno.setAdapter(adpAno);
        
        orgaos = orgaoDaoImpl.listarOrgaos(getApplicationContext(), qf);
        adpOrgao = new ArrayAdapter<Orgao>(getApplicationContext(), android.R.layout.simple_spinner_item, orgaos);
        adpOrgao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboOrgao = (Spinner) findViewById(R.id.spInstituicao);
        comboOrgao.setAdapter(adpOrgao);
        
        cargos = cargoDaoImpl.listarCargos(getApplicationContext(), qf);
        adpCargo = new ArrayAdapter<Cargo>(getApplicationContext(), android.R.layout.simple_spinner_item, cargos);
        adpCargo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		comboCargo.setAdapter(adpCargo);

		comboBanca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			@SuppressWarnings("rawtypes")
			public void onItemSelected(AdapterView parent, View arg1,
					int posicao, long id) {
				
				banca = (Banca) parent.getSelectedItem();
		        qf.setBancaCodigo(banca.getCodigo());
		        //Se retirar essa parte os filtros da combo ficam vazia ao alterar a banca....
		        qf.setAno(0);
		        qf.setOrgaoCodigo(0);
		        qf.setCargoCodigo(0);
		        
		        if(qf.getBancaCodigo() != 0) {
		        	if(qf.getOrgaoCodigo() == 0) {
				        orgaos = orgaoDaoImpl.listarOrgaos(getApplicationContext(), qf);
				        adpOrgao = new ArrayAdapter<Orgao>(getApplicationContext(), android.R.layout.simple_spinner_item, orgaos);
				        adpOrgao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				        comboOrgao.setAdapter(adpOrgao);
		        	}
			        
			        if(qf.getAno() == 0) {
						anos = simuladoDaoImpl.getAnos(getApplicationContext(), qf);
				        adpAno = new ArrayAdapter<Ano>(getApplicationContext(), android.R.layout.simple_spinner_item, anos);
				        adpAno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						comboAno.setAdapter(adpAno);
			        }
			        
					if(qf.getCargoCodigo() == 0) {
				        cargos = cargoDaoImpl.listarCargos(getApplicationContext(), qf);
				        adpCargo = new ArrayAdapter<Cargo>(getApplicationContext(), android.R.layout.simple_spinner_item, cargos);
				        adpCargo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						comboCargo.setAdapter(adpCargo);
					}
//					Thread thread = new Inner();
//			        thread.start();
		        }
				
		    }

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		}); 
		
		comboOrgao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				 
				orgao = (Orgao) arg0.getSelectedItem();
				qf.setOrgaoCodigo(orgao.getCodigo());
				
				if(qf.getOrgaoCodigo() != 0) {
					
					if(qf.getAno() == 0) {
						anos = simuladoDaoImpl.getAnos(getApplicationContext(), qf);
				        adpAno = new ArrayAdapter<Ano>(getApplicationContext(), android.R.layout.simple_spinner_item, anos);
				        adpAno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						comboAno.setAdapter(adpAno);
					}
			        
					if(qf.getCargoCodigo() == 0) {
				        cargos = cargoDaoImpl.listarCargos(getApplicationContext(), qf);
				        adpCargo = new ArrayAdapter<Cargo>(getApplicationContext(), android.R.layout.simple_spinner_item, cargos);
				        adpCargo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						comboCargo.setAdapter(adpCargo);
					}
					
					//Se a banca não estiver selecionada
					if(qf.getBancaCodigo() == 0) {
				        bancas = aDao.listarBancasFiltro(getApplicationContext(), qf);
				        adpBanca = new ArrayAdapter<Banca>(getApplicationContext(), android.R.layout.simple_spinner_item, bancas);
				        adpBanca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						comboBanca.setAdapter(adpBanca);
					}
				}

				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		comboAno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				if (arg0.getItemAtPosition(arg2).toString()=="-- Todos --"){
					qf.setAno(0);
				} else {
					qf.setAno(Long.parseLong(arg0.getItemAtPosition(arg2).toString()));
				}
				
				if(qf.getAno() != 0) {
					
					if(qf.getCargoCodigo() == 0) {
				        cargos = cargoDaoImpl.listarCargos(getApplicationContext(), qf);
				        adpCargo = new ArrayAdapter<Cargo>(getApplicationContext(), android.R.layout.simple_spinner_item, cargos);
				        adpCargo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						comboCargo.setAdapter(adpCargo);
					}
					//Se a banca não estiver selecionada
					if(qf.getBancaCodigo() == 0) {
				        bancas = aDao.listarBancasFiltro(getApplicationContext(), qf);
				        adpBanca = new ArrayAdapter<Banca>(getApplicationContext(), android.R.layout.simple_spinner_item, bancas);
				        adpBanca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						comboBanca.setAdapter(adpBanca);
					}
					//Se o orgão não estiver selecionado
					if(qf.getOrgaoCodigo() == 0) {
				        orgaos = orgaoDaoImpl.listarOrgaos(getApplicationContext(), qf);
				        adpOrgao = new ArrayAdapter<Orgao>(getApplicationContext(), android.R.layout.simple_spinner_item, orgaos);
				        adpOrgao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				        comboOrgao.setAdapter(adpOrgao);
					}
				}

				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		comboCargo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Cargo c = new Cargo();
				c = (Cargo) arg0.getSelectedItem();
				qf.setCargoCodigo(c.getCodigo());
				
				//Se o ano não estiver selecionada
				if(qf.getCargoCodigo() != 0) {
					
					//Se a banca não estiver selecionada
					if(qf.getBancaCodigo() == 0) {
				        bancas = aDao.listarBancasFiltro(getApplicationContext(), qf);
				        adpBanca = new ArrayAdapter<Banca>(getApplicationContext(), android.R.layout.simple_spinner_item, bancas);
				        adpBanca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						comboBanca.setAdapter(adpBanca);
					}
					//Se o orgão não estiver selecionado
					if(qf.getOrgaoCodigo() == 0) {
				        orgaos = orgaoDaoImpl.listarOrgaos(getApplicationContext(), qf);
				        adpOrgao = new ArrayAdapter<Orgao>(getApplicationContext(), android.R.layout.simple_spinner_item, orgaos);
				        adpOrgao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				        comboOrgao.setAdapter(adpOrgao);
					}
					//Se o orgão não estiver selecionado
					if(qf.getAno() == 0) {
						anos = simuladoDaoImpl.getAnos(getApplicationContext(), qf);
				        adpAno = new ArrayAdapter<Ano>(getApplicationContext(), android.R.layout.simple_spinner_item, anos);
				        adpAno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						comboAno.setAdapter(adpAno);
					}
				}

		        
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}

		});
		
		tvTitulo.setText(m.getNome());		
	
	}

	public void onClick(View v) {
		
	}
	
	@Override
	public void onBackPressed() {
		overridePendingTransition(0, 0);
		finish();
		Intent it = new Intent(this, ListaAssuntos.class);
		it.putExtra("idMateria", getIntent().getExtras().getSerializable("idMateria").toString());
		startActivity(it);
	}

	public void onClickMenuVoltar(View arg0) {
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
	
//	private class Inner extends Thread {
//		public void run() {
//			handler.post(new Runnable() {
//				public void run() {
//					progressDialog = ProgressDialog.show(Pesquisa.this, "",
//							"Aguarde...");
//				}
//			});
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//
//			}
//			progressDialog.cancel();
//		}
//	}
	
}