package br.com.tnm.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//import util.ETipoQuestao;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils.InsertHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.tnm.Conexao;
import br.com.tnm.bean.QuestaoFiltro;
import br.com.tnm.bean.Simulado;
import br.com.tnm.util.Ano;
import br.com.tnm.util.ENivelQuestao;

public class SimuladoDaoImpl extends Conexao{

	Long codigo; 
	
	public Long inserirSimulado(Context ctx, QuestaoFiltro filtro){
		
		SQLiteDatabase db = abreConexao(ctx);
		//Fazer consulta codigo
		
		Cursor c = db.rawQuery("SELECT ifnull(max(codigo),0)+1 as codigo FROM simulado", null);
		
	try {
		
		if (c.moveToFirst()) {
				// Recupera os indices das colunas
		int idxCodigo = c.getColumnIndex("codigo");
		codigo =c.getLong(idxCodigo);
				
		
		InsertHelper ih = new InsertHelper(db, "simulado");
		
		final int col1 = ih.getColumnIndex("codigo");
		final int col2 = ih.getColumnIndex("banca_codigo");
		final int col3 = ih.getColumnIndex("orgao_codigo");
		final int col4 = ih.getColumnIndex("ano");
		final int col5 = ih.getColumnIndex("total_questoes");
		final int col6 = ih.getColumnIndex("tipo_questao");
		final int col7 = ih.getColumnIndex("nivel_questao");
		final int col8 = ih.getColumnIndex("data_criacao");
		final int col9 = ih.getColumnIndex("materia_codigo");
		final int col10 = ih.getColumnIndex("cargo_codigo");
		final int col11 = ih.getColumnIndex("assuntos");
		final int col12 = ih.getColumnIndex("tempo");
		
		ih.prepareForInsert();
		ih.bind(col1, codigo);
		ih.bind(col2, filtro.getBancaCodigo());
		ih.bind(col3, filtro.getOrgaoCodigo());
		ih.bind(col4, filtro.getAno());
		ih.bind(col5, filtro.getTotalQuestoes());
		ih.bind(col6, new String());
		
		if (filtro.getNivelQuestao()!=null) {
			ih.bind(col7, filtro.getNivelQuestao().name());
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ih.bind(col8, dateFormat.format(new Date()));
		
		ih.bind(col9, filtro.getMateriaCodigo());
		ih.bind(col10, filtro.getCargoCodigo());
		ih.bind(col11, filtro.getAssuntos());
		ih.bind(col12, filtro.getTempo());
		
		ih.execute();
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao inserir simulados: " + e.toString());
			
		} finally {
			c.close();		
			fechaConexao();
		}
	return codigo;
	
	}
	
	public void atualizaTotalQuestoes(Context ctx, Long totalQuestoes){
		SQLiteDatabase db = abreConexao(ctx);
		
		String sqlUpdate = "UPDATE simulado set total_questoes = "+ totalQuestoes +
						   " where codigo = "+codigo;
		
		db.execSQL(sqlUpdate);
		fechaConexao();
	}
	
	public void excluirSimulados(Context ctx){
		SQLiteDatabase db = abreConexao(ctx);
		Cursor c = db.rawQuery("SELECT codigo FROM simulado where finalizado = 0 ", null);
		try {
			if (c.moveToFirst()) {
				int idxCodigo = c.getColumnIndex("codigo");
				do {
					long codigo = c.getLong(idxCodigo);
					String sqlResposta = " delete from resposta where simulado_codigo = " + codigo;
					db.execSQL(sqlResposta);
				}while(c.moveToNext());
			}
			String sql = " delete from simulado where finalizado is null ";
			db.execSQL(sql);
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao excluir os Simulados: " + e.toString());
		} finally {
			c.close();
			fechaConexao();
		}
	}
	
	public void excluirSimuladosGrafico(Context ctx){
		SQLiteDatabase db = abreConexao(ctx);
		Cursor c = db.rawQuery("SELECT codigo FROM simulado where finalizado = 1 ", null);
		try {
			if (c.moveToFirst()) {
				int idxCodigo = c.getColumnIndex("codigo");
				do {
					long codigo = c.getLong(idxCodigo);
					String sqlResposta = " delete from resposta where simulado_codigo = " + codigo;
					db.execSQL(sqlResposta);
				}while(c.moveToNext());
			}
			String sql = " delete from simulado where finalizado = 1 ";
			db.execSQL(sql);
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao excluir os Simulados: " + e.toString());
		} finally {
			c.close();
			fechaConexao();
		}
	}
	
	public void finalizaSimulado(Context ctx, long codigo){
		SQLiteDatabase db = abreConexao(ctx);
		String sqlUpdate = "UPDATE simulado set finalizado = 1 where codigo = "+codigo;
		db.execSQL(sqlUpdate);
		fechaConexao();
	}
		
	public Simulado getSimulado(Context ctx, Long id) {
		SQLiteDatabase db = abreConexao(ctx);
		Cursor c = db.rawQuery("SELECT * FROM simulado where codigo = "+id, null);
		Simulado simulado = new Simulado();

		try {
			if (c.moveToNext()) {
			
				//indice das colunas no banco de dados, desta forma fica mais organizado.
				
				int idxCodigo 		= c.getColumnIndex(Simulado.CODIGO);
				int idxDataCriacao  = c.getColumnIndex(Simulado.DATA_CRIACAO);
				int idxBancaCodigo 	= c.getColumnIndex(Simulado.BANCA_CODIGO);
				int idxOrgaoCodigo  = c.getColumnIndex(Simulado.ORGAO_CODIGO);
				int idxAno			= c.getColumnIndex(Simulado.ANO);
				int idxTotalQuestao = c.getColumnIndex(Simulado.TOTAL_QUESTAO);
				//int idxTipoQuestao  = c.getColumnIndex(Simulado.TIPO_QUESTAO);
				int idxNivelQuestao = c.getColumnIndex(Simulado.NIVEL_QUESTAO);
				int idxFinalizado	= c.getColumnIndex(Simulado.FINALIZADO);
				int idxCargoCodigo  = c.getColumnIndex(Simulado.CARGO_CODIGO);
				int idxAssuntos     = c.getColumnIndex(Simulado.ASSUNTOS);
				int idxTempo    = c.getColumnIndex(Simulado.TEMPO);
				
				//Colocando os valores no objeto simulado
				
				simulado.setCodigo(c.getLong(idxCodigo));
				simulado.setDataCriacao(c.getString(idxDataCriacao));
				simulado.setBancaCodigo(c.getLong(idxBancaCodigo));
				simulado.setOrgaoCodigo(c.getLong(idxOrgaoCodigo));
				simulado.setAno(c.getLong(idxAno));
				simulado.setTotalQuestao(c.getInt(idxTotalQuestao));
				//simulado.setTipoQuestao(ETipoQuestao.valueOf(c.getString(idxTipoQuestao)));
				
				if (c.getString(idxNivelQuestao)!=null)
				simulado.setNivelQuestao(ENivelQuestao.valueOf(c.getString(idxNivelQuestao)));
				
				simulado.setFinalizado(Boolean.getBoolean(c.getString(idxFinalizado)));
				simulado.setCargoCodigo(c.getLong(idxCargoCodigo));
				simulado.setAssuntos(c.getString(idxAssuntos));
				simulado.setTempo(c.getString(idxTempo));
				
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao busca os Simulado: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return simulado;
	}	
	
	public List<Ano> getAnos(Context ctx, QuestaoFiltro questaoFiltro) {
		SQLiteDatabase db = abreConexao(ctx);
		String sql = "SELECT DISTINCT strftime('%Y',c.data_prova) as ano"+
		" FROM questao q, assunto a, concurso c " +
		" WHERE a.materia_codigo = "+ questaoFiltro.getMateriaCodigo() +
		" AND c.codigo = q.concurso_codigo " +
		" AND q.assunto_codigo = a.codigo ";
		
		if(questaoFiltro.getBancaCodigo() != 0) {
			sql += " AND c.nome_banca = " + questaoFiltro.getBancaCodigo();
		}
		if(questaoFiltro.getAno() != 0) {
			sql += " AND strftime('%Y',c.data_prova) = '" + questaoFiltro.getAno() + "'";
		}
		if(questaoFiltro.getOrgaoCodigo() != 0) {
			sql += " AND c.nome_orgao = " + questaoFiltro.getOrgaoCodigo();
		}
		if(questaoFiltro.getCargoCodigo() != 0) {
			sql += " AND c.nome_cargo = " + questaoFiltro.getCargoCodigo();
		}
		if(!questaoFiltro.getAssuntos().equals(null)){
			sql += " AND a.codigo in ("+questaoFiltro.getAssuntos()+")";
		}
		
		sql += " order by ano desc ";
		
		Cursor c = db.rawQuery(sql , null);
		
		List<Ano> anos = new ArrayList<Ano>();
		
		try {
			if (c.moveToFirst()) {
			
				//indice das colunas no banco de dados, desta forma fica mais organizado.
				int idxAno 		= c.getColumnIndex("ano");
				anos.add(new Ano("-- Todos --"));
				
				do {
					anos.add(new Ano(c.getString(idxAno)));	
					
				}while(c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao busca os Materia: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return anos;
	}	
	
	public List<HashMap<String, String>> listarMapaSimulado(Context ctx) {
		SQLiteDatabase db = abreConexao(ctx);
		
		String sql = "SELECT s.codigo as codigo, s.total_questoes as total_questoes, m.nome_materia as materia, strftime('%d/%m/%Y', s.data_criacao) as data, s.tempo as tempo, "+
					 "(select count(*) from resposta where simulado_codigo = s.codigo) as total_respondida, "+
					 "(select count(*) from resposta where simulado_codigo = s.codigo and correta = 1 ) as total_correta, "+
					 "(select count(*) from resposta where simulado_codigo = s.codigo and correta = 0) as total_errada, "+
					 "(select indice_resposta from resposta where simulado_codigo = s.codigo order by indice_resposta desc LIMIT 0, 1) as indice_resposta "+
					 "FROM simulado s, materia m " +
					 "where s.materia_codigo=m.codigo and s.finalizado is null " +
					 "ORDER BY CODIGO DESC";
		
		Cursor c = db.rawQuery(sql, null);
		List<HashMap<String, String>> simulados = new ArrayList<HashMap<String, String>>();
		
		try {
			if (c.moveToFirst()) {
				// Recupera os indices das colunas
				int idxCodigo 		= c.getColumnIndex(Simulado.CODIGO);
				int idxNomeMateria  = c.getColumnIndex("materia");
				int idxDataCriacao  = c.getColumnIndex("data");
				int idxTotalRespondida 	= c.getColumnIndex("total_respondida");
				int idxTotalCorreta  = c.getColumnIndex("total_correta");
				int idxTotalErrada	= c.getColumnIndex("total_errada");
				int idxTotalQuestoes = c.getColumnIndex(Simulado.TOTAL_QUESTAO);
				int idxIndiceResposta = c.getColumnIndex("indice_resposta");
				int idxTempo = c.getColumnIndex("tempo");
				
				//Colocando os valores no objeto simulado			
				
				// Loop ate o final
				do {
					String simuladoQuestoes = null;
					String simuladoAcertos = null;
					String simuladoMateria = null;
					int totalRespondida = Integer.parseInt(c.getString(idxTotalRespondida));
					int totalAcertos    = Integer.parseInt(c.getString(idxTotalCorreta));
					int totalErradas	= Integer.parseInt(c.getString(idxTotalErrada));
					Long chave = c.getLong(idxCodigo);
					simuladoMateria = c.getString(idxNomeMateria) +  " (" + c.getString(idxDataCriacao) + ") ";
					simuladoQuestoes = c.getString(idxTotalRespondida) + " respondida(s) de um total de " +
					c.getString(idxTotalQuestoes) + " questões. Tempo: " + c.getString(idxTempo);
					
					try {
						simuladoAcertos = totalAcertos + " acerto(s) e " + totalErradas + " erro(s) teve " 
					    + ((totalAcertos*100)/totalRespondida) + "% de aproveitamento";
					}catch (ArithmeticException e) {
						simuladoAcertos = "Nao existem questões respondidas";
						//Log.e("Nao fez nenhum item: " + e.toString(), simuladoMateria);

					}
					int indiceResposta = c.getInt(idxIndiceResposta);
					
					HashMap<String, String> mapaSimulado = new HashMap<String, String>();
					mapaSimulado.put("codigo", chave.toString());
					mapaSimulado.put("nome_simulado", simuladoMateria);
					mapaSimulado.put("questoes", simuladoQuestoes);
					mapaSimulado.put("acertos", simuladoAcertos);
					mapaSimulado.put("indice_resposta", String.valueOf(indiceResposta));
										
					simulados.add(mapaSimulado);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar os simulados: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return simulados;
	}
	
	public List<HashMap<String, String>> listarMapaSimuladoGrafico(Context ctx) {
		SQLiteDatabase db = abreConexao(ctx);
		
		String sql = "SELECT s.codigo as codigo, s.total_questoes as total_questoes, m.nome_materia as materia, strftime('%d/%m/%Y', s.data_criacao) as data, s.tempo as tempo, "+
					 "(select count(*) from resposta where simulado_codigo = s.codigo) as total_respondida, "+
					 "(select count(*) from resposta where simulado_codigo = s.codigo and correta = 1) as total_correta, "+
					 "(select count(*) from resposta where simulado_codigo = s.codigo and correta = 0) as total_errada, "+
					 "(select indice_resposta from resposta where simulado_codigo = s.codigo order by indice_resposta desc LIMIT 0, 1) as indice_resposta "+
					 "FROM simulado s, materia m " +
					 "where s.materia_codigo=m.codigo " +
					 "ORDER BY CODIGO DESC";
		
		Cursor c = db.rawQuery(sql, null);
		List<HashMap<String, String>> simulados = new ArrayList<HashMap<String, String>>();
		
		try {
			if (c.moveToFirst()) {
				// Recupera os indices das colunas
				int idxCodigo 		= c.getColumnIndex(Simulado.CODIGO);
				int idxNomeMateria  = c.getColumnIndex("materia");
				int idxDataCriacao  = c.getColumnIndex("data");
				int idxTotalRespondida 	= c.getColumnIndex("total_respondida");
				int idxTotalCorreta  = c.getColumnIndex("total_correta");
				int idxTotalErrada	= c.getColumnIndex("total_errada");
				int idxTotalQuestoes = c.getColumnIndex(Simulado.TOTAL_QUESTAO);
				int idxIndiceResposta = c.getColumnIndex("indice_resposta");
				int idxTempo = c.getColumnIndex("tempo");
				
				//Colocando os valores no objeto simulado			
				
				// Loop ate o final
				do {
					String simuladoQuestoes = null;
					String simuladoAcertos = null;
					String simuladoMateria = null;
					int totalRespondida = Integer.parseInt(c.getString(idxTotalRespondida));
					int totalAcertos    = Integer.parseInt(c.getString(idxTotalCorreta));
					int totalErradas	= Integer.parseInt(c.getString(idxTotalErrada));
					Long chave = c.getLong(idxCodigo);
					simuladoMateria = c.getString(idxNomeMateria) +  " (" + c.getString(idxDataCriacao) + ") ";
					simuladoQuestoes = c.getString(idxTotalRespondida) + " respondida(s) de um total de " +
					c.getString(idxTotalQuestoes) + " questões. Tempo: " + c.getString(idxTempo);
					
					try {
						simuladoAcertos = totalAcertos + " acerto(s) e " + totalErradas + " erro(s) teve " 
					    + ((totalAcertos*100)/totalRespondida) + "% de aproveitamento.";
					}catch (ArithmeticException e) {
						simuladoAcertos = "Nao existem questões respondidas";
					}
					int indiceResposta = c.getInt(idxIndiceResposta);
					
					HashMap<String, String> mapaSimulado = new HashMap<String, String>();
					mapaSimulado.put("codigo", chave.toString());
					mapaSimulado.put("nome_simulado", simuladoMateria);
					mapaSimulado.put("questoes", simuladoQuestoes);
					mapaSimulado.put("acertos", simuladoAcertos);
					mapaSimulado.put("indice_resposta", String.valueOf(indiceResposta));
										
					simulados.add(mapaSimulado);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar os simulados: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return simulados;
	}
	
	public HashMap<String, String> mapaSimulado(Context ctx, Long id) {
		SQLiteDatabase db = abreConexao(ctx);
		
		String sql = " SELECT s.codigo as codigo, s.total_questoes as total_questoes, m.nome_materia as materia, strftime('%d/%m/%Y', s.data_criacao) as data, s.tempo as tempo, "+
					 " (select count(*) from resposta where simulado_codigo = s.codigo) as total_respondida, "+
					 " (select count(*) from resposta where simulado_codigo = s.codigo and correta = 1 ) as total_correta, "+
					 " (select count(*) from resposta where simulado_codigo = s.codigo and correta = 0) as total_errada, "+
					 " (select indice_resposta from resposta where simulado_codigo = s.codigo order by indice_resposta desc LIMIT 0, 1) as indice_resposta "+
					 " FROM simulado s, materia m " +
					 " where s.materia_codigo=m.codigo and s.codigo = " + id +
					 " ORDER BY CODIGO DESC ";
		
		Cursor c = db.rawQuery(sql, null);
		HashMap<String, String> mapaSimulado = new HashMap<String, String>();
		try {
			if (c.moveToFirst()) {
				// Recupera os indices das colunas
				int idxCodigo 		= c.getColumnIndex(Simulado.CODIGO);
				int idxNomeMateria  = c.getColumnIndex("materia");
				int idxDataCriacao  = c.getColumnIndex("data");
				int idxTotalRespondida 	= c.getColumnIndex("total_respondida");
				int idxTotalCorreta  = c.getColumnIndex("total_correta");
				int idxTotalErrada	= c.getColumnIndex("total_errada");
				int idxTotalQuestoes = c.getColumnIndex(Simulado.TOTAL_QUESTAO);
				int idxTempo = c.getColumnIndex(Simulado.TEMPO);
				
				String simuladoMateria = null;
				int totalRespondida = Integer.parseInt(c.getString(idxTotalRespondida));
				int totalQuestoes = Integer.parseInt(c.getString(idxTotalQuestoes));
				int totalAcertos  = Integer.parseInt(c.getString(idxTotalCorreta));
				int totalErros = Integer.parseInt(c.getString(idxTotalErrada));
				String tempo = c.getString(idxTempo);
				
				Long chave = c.getLong(idxCodigo);
				simuladoMateria = c.getString(idxNomeMateria) +  " (" + c.getString(idxDataCriacao) + ") ";
				
				mapaSimulado.put("codigo", chave.toString());
				mapaSimulado.put("nome_simulado", simuladoMateria);
				mapaSimulado.put("total_questoes", String.valueOf(totalQuestoes));
				mapaSimulado.put("questoes_respondidas", String.valueOf(totalRespondida));
				mapaSimulado.put("acertos", String.valueOf(totalAcertos));
				mapaSimulado.put("erros", String.valueOf(totalErros));
				mapaSimulado.put("tempo", tempo);						
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar os simulados: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return mapaSimulado;
	}
	
	public String getTempoSimulado(Context ctx, Long codigo) {
		String sql = " select tempo from simulado where codigo = " + codigo;
		SQLiteDatabase db = abreConexao(ctx);
		Cursor c = db.rawQuery(sql, null);
		String resultado = null;
		try {
			if (c.moveToFirst()) {
				int idxTempo = c.getColumnIndex(Simulado.TEMPO);
				resultado = c.getString(idxTempo);
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar o tempo: " + e.toString());
		} finally {
			c.close();
			fechaConexao();
		}
		return resultado;
	}
	
	public void atualizaTempoSimulado(Context ctx, String tempo,
			Long codigoSimulado) {
		SQLiteDatabase db = abreConexao(ctx);
		String sqlUpdate = "UPDATE simulado set tempo = '" + tempo
				+ "' where codigo = " + codigoSimulado;
		db.execSQL(sqlUpdate);
		fechaConexao();
	}

}
	

