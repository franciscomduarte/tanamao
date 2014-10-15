package br.com.tnm.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.tnm.Conexao;
import br.com.tnm.bean.Questao;
import br.com.tnm.bean.QuestaoFiltro;
import br.com.tnm.bean.Simulado;

public class QuestaoDaoImpl extends Conexao {
	
	private List<Questao> listaQuestoes = new ArrayList<Questao>();
	
	public Cursor getCursor(Context ctx) {
		try {
			SQLiteDatabase db = abreConexao(ctx); 
			return db.query(Questao.NOME_TABELA, Questao.colunas, null, null, null, null, null, null);
		} catch (SQLException e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar as questoes: " + e.toString());
			return null;
		} finally {
			fechaConexao();
		}
	}
	
	// Retorna uma lista com todos os questoes
	public List<String> listarEnunciadosQuestoes(Context ctx) {
//		Cursor c = getCursor(ctx);
		SQLiteDatabase db = abreConexao(ctx);
		String sql = "select e.enunciado as enunciado " +
					 "from enunciado_questao e, questao q" +
					 "where q.enunciado_codigo = e.codigo";
		Cursor c = db.rawQuery(sql, null);
		List<String> questoes = new ArrayList<String>();

		try {
			if (c.moveToFirst()) {
				// Recupera os indices das colunas
				int idxEnunciado = c.getColumnIndex("enunciado");
				// Loop ate o final
				do {
					// recupera os atributos de questoes
					questoes.add(c.getString(idxEnunciado));
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar a questao: " + e.toString());
			return null;
		} finally {
			fechaConexao();
		}
		return questoes;
	}
	
	// Retorna uma lista com todos os questoes
	public void listarQuestoes(Context ctx, long idSimulado) {
		SQLiteDatabase db = abreConexao(ctx);
		String sql = "SELECT q.*, (b.nome_banca||'-'||c.nome_concurso||' ('"+
					 "||strftime(\"%Y\",c.data_prova)||') - '||a.nome_assunto) AS origem_questao, c.nivel as nivel, " +
					 "ifnull((SELECT resposta.correta FROM resposta WHERE resposta.questao_codigo = q.codigo "+ 
					 "AND resposta.simulado_codigo = "+idSimulado+"),null) AS resposta, " +
					 "ifnull((SELECT comentario from comentario_questao where questao_codigo = q.codigo),null) as comentario " +
					 "FROM questao q, concurso c, assunto a, banca b "+
					 "WHERE q.concurso_codigo = c.codigo "+
					 "AND c.nome_banca = b.codigo "+
					 "AND q.assunto_codigo = a.codigo "+
					 "AND a.materia_codigo = (SELECT s.materia_codigo FROM simulado s WHERE s.codigo = "+idSimulado+") ";
					  
		//Buscando simulado para montar o Sql da questão
		
		Simulado simulado = new SimuladoDaoImpl().getSimulado(ctx, idSimulado);
		
		if (simulado.getBancaCodigo() != 0)
				sql += "AND   c.nome_banca = (SELECT s.banca_codigo FROM simulado s WHERE s.codigo = "+idSimulado+") "; 
		if (simulado.getOrgaoCodigo() != 0)			  
				sql += "AND   c.nome_orgao = (SELECT s.orgao_codigo FROM simulado s WHERE s.codigo = "+idSimulado+") ";
		if (simulado.getAno() != 0)
				sql += "AND   strftime(\"%Y\",c.data_prova) = (SELECT s.ano FROM simulado s WHERE s.codigo = "+idSimulado+") ";
		if (simulado.getTipoQuestao() != null)
				sql += "AND   c.tipo_questao = (SELECT s.tipo_questao FROM simulado s WHERE s.codigo = "+idSimulado+") ";
		if (simulado.getNivelQuestao() != null)
				sql += "AND   c.nivel = (SELECT s.nivel_questao FROM simulado s WHERE s.codigo = "+idSimulado+")";
		if (simulado.getCargoCodigo() != 0)
				sql += "AND   c.nome_cargo = (SELECT s.cargo_codigo FROM simulado s WHERE s.codigo = "+idSimulado+")";
		if(!simulado.getAssuntos().equals(null)){
			sql += " AND a.codigo in ("+simulado.getAssuntos()+")";
		}
		
		// Cursor referente as questões
		Cursor c = db.rawQuery(sql, null);
		List<Questao> questoes = new ArrayList<Questao>();
		List<Questao> questoesRespondidas = new ArrayList<Questao>();
		
		try {
			if (c.moveToFirst()) {

				// Recupera os  indices das colunas
				int idxCodigo = c.getColumnIndex(Questao.CODIGO);
				int idxEnunciado = c.getColumnIndex(Questao.ENUNCIADO);
				int idxItemA = c.getColumnIndex(Questao.ITEM_A);
				int idxItemB = c.getColumnIndex(Questao.ITEM_B);
				int idxItemC = c.getColumnIndex(Questao.ITEM_C);
				int idxItemD = c.getColumnIndex(Questao.ITEM_D);
				int idxItemE = c.getColumnIndex(Questao.ITEM_E);
				int idxResposta = c.getColumnIndex(Questao.RESPOSTA_CERTA);
				int idxTipoQuestao = c.getColumnIndex(Questao.TIPO_QUESTAO);
				int idxOrigemQuestao	= c.getColumnIndex("origem_questao");
				int idxNivel = c.getColumnIndex("nivel");
				int idxRotulo = c.getColumnIndex(Questao.ROTULO);
				int idxComentario = c.getColumnIndex("comentario");//trasiente

				// Loop ate o final
				do {
					Questao questao = new Questao();
					// recupera os atributos de questoes
					questao.setCodigo(c.getLong(idxCodigo));
					
					//Buscando enunciado
					EnunciadoDaoImpl enunciadoDao = new EnunciadoDaoImpl();
					questao.setEnunciado(enunciadoDao.burcarEnunciado(ctx, c.getLong(idxEnunciado)));
					questao.setItemA(c.getString(idxItemA));
					questao.setItemB(c.getString(idxItemB));
					questao.setItemC(c.getString(idxItemC));
					questao.setItemD(c.getString(idxItemD));
					questao.setItemE(c.getString(idxItemE));
					questao.setTipoQuestao(c.getString(idxTipoQuestao));
					questao.setRepostaCerta(c.getString(idxResposta));
					questao.setRotulo(c.getString(idxRotulo));
					questao.setComentario(c.getString(idxComentario));
					
					if (c.getString(idxNivel).equals("S"))
						questao.setOrigemQuestao(c.getString(idxOrigemQuestao) + " - Superior");
					else
						questao.setOrigemQuestao(c.getString(idxOrigemQuestao) + " - Médio");
					
					
					if (c.getString(c.getColumnIndex("resposta")) != null)
						questoesRespondidas.add(questao);
					else
						questoes.add(questao);

				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar a questao: " + e.toString());
		
		} finally {
			c.close();
			fechaConexao();
			
			//Dessa forma as questões já respondidas não serão repetidas.
			Collections.shuffle(questoes);
			
			for (int i = 0; i < questoesRespondidas.size(); i++) {
				listaQuestoes.add(questoesRespondidas.get(i));
			}
			
			for (int i = 0; i < questoes.size(); i++) {
				listaQuestoes.add(questoes.get(i));
			}
		}
		
	}
	
	public Integer listarQuestoesFiltro(Context ctx, QuestaoFiltro questaoFiltro) {
		SQLiteDatabase db = abreConexao(ctx);
		
		Integer total = null;
		
		String sql = "SELECT count (*)  as total " +
					  "FROM questao q, concurso c, assunto a, banca b "+
					  "WHERE q.concurso_codigo = c.codigo "+
					  "AND c.nome_banca = b.codigo "+
					  "AND q.assunto_codigo = a.codigo "+
					  "AND a.materia_codigo = "+questaoFiltro.getMateriaCodigo();
					  
		//Buscando simulado para montar o Sql da questão
		
		//Simulado simulado = new SimuladoDaoImpl().getSimulado(ctx, idSimulado);
		
		if (questaoFiltro.getBancaCodigo() != 0)
				sql += " AND   c.nome_banca = "+questaoFiltro.getBancaCodigo() ; 
		if (questaoFiltro.getOrgaoCodigo() != 0)			  
				sql += " AND   c.nome_orgao = "+ questaoFiltro.getOrgaoCodigo();
		if (questaoFiltro.getAno() != 0)
				sql += " AND   strftime(\"%Y\",c.data_prova) =  '"+questaoFiltro.getAno()+ "'";
		if (questaoFiltro.getTipoQuestao() != null)
				sql += " AND   c.tipo_questao = "+questaoFiltro.getTipoQuestao();
		if (questaoFiltro.getNivelQuestao() != null)
				sql += " AND   c.nivel = '"+questaoFiltro.getNivelQuestao() + "'";
		if (questaoFiltro.getCargoCodigo() != 0)
			sql += " AND  c.nome_cargo = '"+questaoFiltro.getCargoCodigo() + "'";
		if(!questaoFiltro.getAssuntos().equals(null)){
			sql += " AND a.codigo in ("+questaoFiltro.getAssuntos()+")";
		}
		
		//Log.e("SQL", sql);
		// Cursor referente as questões
		Cursor c = db.rawQuery(sql, null);
		
		try {
			if (c.moveToFirst()) {

				// Recupera os  indices das colunas
				int idxCodigo = c.getColumnIndex("total");

				total = c.getInt(idxCodigo);
				 
				 
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar a questao: " + e.toString());
		
		} finally {
			c.close();
			fechaConexao();

		}
		
		return total;

		
	}
	
	public void reposicionaItemLista(int indice, Questao q){
		this.listaQuestoes.add(q);
		this.listaQuestoes.remove(q);
		
		ArrayList<Questao> novasQuestoes = new ArrayList<Questao>();
		for (Questao questao : this.listaQuestoes) {
			novasQuestoes.add(questao);
		}
		
		this.listaQuestoes = novasQuestoes;
		
	}
	
	// Busca o questao pelo id
	public Questao buscarQuestao(int indice) {
		return this.listaQuestoes.get(indice);
	}
	
	public long totalQuestoes(){
		return this.listaQuestoes.size();
	}

	public void alterarRotulo(Context ctx, String rotulo, Long codigo){
		SQLiteDatabase db = abreConexao(ctx);			
		String sqlUpdate = "UPDATE questao set rotulo = '"+rotulo+"' where codigo = "+codigo;
		db.execSQL(sqlUpdate);
		fechaConexao();
	}
	
	
}
