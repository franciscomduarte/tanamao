package br.com.tnm.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.tnm.Conexao;
import br.com.tnm.bean.Materia;

public class MateriaDaoImpl extends Conexao {
	
	public List<HashMap<Long, String>> listarMaterias(Context ctx) {
		SQLiteDatabase db = abreConexao(ctx);
		Cursor c = db.rawQuery("SELECT codigo, nome_materia FROM materia", null);
		List<HashMap<Long, String>> materias = new ArrayList<HashMap<Long, String>>();

		try {
			if (c.moveToFirst()) {
				// Recupera os �ndices das colunas
				int idxNomeMateria = c.getColumnIndex(Materia.NOME_MATERIA);
				int idxCodigo = c.getColumnIndex(Materia.CODIGO);
				// Loop at� o final
				do {
					Long chave = c.getLong(idxCodigo);
					String valor = c.getString(idxNomeMateria);
					
					HashMap<Long, String> item = new HashMap<Long, String>();
					item.put(chave, valor);
					
					materias.add(item);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar os Materias: " + e.toString());
			return null;
		} finally {
			fechaConexao();
		}
		return materias;
	}

	
	public List<HashMap<String, String>> listarMapaMaterias(Context ctx) {
		SQLiteDatabase db = abreConexao(ctx);
		
		String sql = "SELECT m .codigo, m.nome_materia, "+ 
					 "(SELECT COUNT(*) FROM questao q, assunto a "+ 
					 "WHERE q.assunto_codigo = a.codigo  "+
					 "AND a.materia_codigo = m.codigo) AS total "+ 
					 "FROM materia m where total>0";
		
		Cursor c = db.rawQuery(sql, null);
		List<HashMap<String, String>> materias = new ArrayList<HashMap<String, String>>();

		try {
			if (c.moveToFirst()) {
				// Recupera os �ndices das colunas
				int idxNomeMateria = c.getColumnIndex(Materia.NOME_MATERIA);
				int idxCodigo = c.getColumnIndex(Materia.CODIGO);
				// Loop at� o final
				do {
					Long chave = c.getLong(idxCodigo);
					String valor = c.getString(idxNomeMateria);
					Long total = c.getLong(2);
					
					HashMap<String, String> item = new HashMap<String, String>();
					item.put("id", chave.toString());
					item.put("nome_materia", valor + " ("+ total.toString() + ")");
					//item.put("total", "("+ total.toString() + ") quest�es dispon�veis" );
					
					materias.add(item);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar os Materias: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return materias;
	}
	
	
	public List<Materia> listarMateriasCombo(Context ctx) {
		SQLiteDatabase db = abreConexao(ctx);
		
		String sql = " select  b.codigo, nome_materia, count(b.codigo) as total " +
				     " from questao a, materia b " +
				     " where a.materia_codigo = b.codigo group by b.codigo, nome_materia";
		
		Cursor c = db.rawQuery(sql, null);
		List<Materia> materias = new ArrayList<Materia>();

		try {
			if (c.moveToFirst()) {
				int idxNomeMateria = c.getColumnIndex(Materia.NOME_MATERIA);
				int idxCodigo = c.getColumnIndex(Materia.CODIGO);
				int idxTotal = c.getColumnIndex("total");

				do {
					Materia materia = new Materia();
					Long codigo = c.getLong(idxCodigo);
					String nome = c.getString(idxNomeMateria);
					Long total = c.getLong(idxTotal);
					
					materia.setCodigo(codigo);
					materia.setNome(nome+"("+total+")");
					materias.add(materia);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar os Materias: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return materias;
	}

	
	
	
	public Materia getMateria(Context ctx, String id) {
		SQLiteDatabase db = abreConexao(ctx);
		Cursor c = db.rawQuery("SELECT codigo, nome_materia FROM materia where codigo = "+id, null);
		Materia materia = new Materia();

		try {
			if (c.moveToFirst()) {
				// Recupera os �ndices das colunas
				int idxNomeMateria = c.getColumnIndex(Materia.NOME_MATERIA);
				int idxCodigo = c.getColumnIndex(Materia.CODIGO);
				do {
				materia.setCodigo(c.getLong(idxCodigo));
				materia.setNome(c.getString(idxNomeMateria));									
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao busca os Materia: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return materia;
	}	
	
	public long getTotalMateria(Context ctx) {
		SQLiteDatabase db = abreConexao(ctx);
		Cursor c = db.rawQuery("SELECT count(*) as total FROM materia", null);
		long total = 0;

		try {
			if (c.moveToFirst()) {
				// Recupera os indices das colunas
				int idxTotal = c.getColumnIndex("total");
				total = c.getLong(idxTotal);
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao carregar materia: " + e.toString());
			return total;
		} finally {
			c.close();
			fechaConexao();
		}
		return total;
	}	
	
}
