package br.com.tnm.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.tnm.Conexao;
import br.com.tnm.bean.Banca;
import br.com.tnm.bean.QuestaoFiltro;

public class BancaDaoImpl extends Conexao {
	
	public List<Banca> listarBancas(Context ctx) {
		SQLiteDatabase db = abreConexao(ctx);
		Cursor c = db.rawQuery("SELECT codigo, nome_banca FROM banca order by nome_banca", null);
		List<Banca> bancas = new ArrayList<Banca>();

		try {
			if (c.moveToFirst()) {
				// Recupera os  indices das colunas
				int idxNomeBanca = c.getColumnIndex(Banca.NOME_BANCA);
				int idxCodigo = c.getColumnIndex(Banca.CODIGO);
				// Loop ate o final
				bancas.add(new Banca(0, "-- Todas --"));
				do {
					Banca banca = new Banca();
					banca.setCodigo(c.getLong(idxCodigo));
					banca.setNome(c.getString(idxNomeBanca));
					bancas.add(banca);
				
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar os bancas: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return bancas;
	}

	public List<Banca> listarBancasFiltro(Context ctx, QuestaoFiltro questaoFiltro) {
		SQLiteDatabase db = abreConexao(ctx);
		
		
		String sql = "SELECT DISTINCT(ba.codigo) AS codigo, ba.nome_banca AS nome_banca"+
					" FROM banca ba, questao q, assunto a, concurso c " +
					" WHERE a.materia_codigo = " + questaoFiltro.getMateriaCodigo() +
					" AND c.codigo = q.concurso_codigo " +
					" AND ba.codigo=c.nome_banca" +
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
		
		
		Cursor c = db.rawQuery(sql, null);
		List<Banca> bancas = new ArrayList<Banca>();

		try {
			if (c.moveToFirst()) {
				// Recupera os �ndices das colunas
				int idxNomeBanca = c.getColumnIndex(Banca.NOME_BANCA);
				int idxCodigo = c.getColumnIndex(Banca.CODIGO);
				// Loop at� o final
				
				bancas.add(new Banca(0, "-- Todos --"));
				do {
					
					Banca banca = new Banca();
					banca.setCodigo(c.getLong(idxCodigo));
					banca.setNome(c.getString(idxNomeBanca));
					bancas.add(banca);
				
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar os bancas: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return bancas;
	}




}
