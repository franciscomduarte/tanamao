package br.com.tnm.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.tnm.Conexao;
import br.com.tnm.bean.Assunto;

public class AssuntoDaoImpl extends Conexao {

	public List<HashMap<String, String>> listarAssuntos(Context ctx) {
		SQLiteDatabase db = abreConexao(ctx);
		Cursor c = db
				.rawQuery("SELECT codigo, nome_assunto FROM assunto", null);
		List<HashMap<String, String>> assuntos = new ArrayList<HashMap<String, String>>();

		try {
			if (c.moveToFirst()) {
				// Recupera os �ndices das colunas
				int idxNomeAssunto = c.getColumnIndex(Assunto.NOME_ASSUNTO);
				int idxCodigo = c.getColumnIndex(Assunto.CODIGO);
				// Loop at� o final
				

				do {
					Long chave = c.getLong(idxCodigo);
					String valor = c.getString(idxNomeAssunto);

					HashMap<String, String> item = new HashMap<String, String>();
					item.put(chave.toString(), valor);

					assuntos.add(item);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO",
					"Erro ao buscar os assuntos: " + e.toString());
			return null;
		} finally {
			fechaConexao();
		}
		return assuntos;
	}
	
	public ArrayList<Assunto> listarAssuntosPorMateria(Context ctx, long idMateria) {
		SQLiteDatabase db = abreConexao(ctx);
		String sql = "SELECT a.codigo as codigo, a.nome_assunto as nome_assunto, count(*) as total "+
                "FROM assunto a, questao q "+
                "WHERE q.assunto_codigo = a.codigo "+
                "AND   a.materia_codigo = "+idMateria+" "+
                "GROUP BY a.codigo order by a.nome_assunto";
		
		Cursor c = db.rawQuery(sql, null);
		ArrayList<Assunto> assuntos = new ArrayList<Assunto>();

		try {
			if (c.moveToFirst()) {
				// Recupera os ï¿½ndices das colunas
				int idxNomeAssunto = c.getColumnIndex(Assunto.NOME_ASSUNTO);
				int idxCodigo = c.getColumnIndex(Assunto.CODIGO);
				int idxTotal = c.getColumnIndex("total");
				// Loop atï¿½ o final
				assuntos.add(new Assunto(0,"--Selecione Todos--"));

				do {
					Long chave = c.getLong(idxCodigo);
					String valor = c.getString(idxNomeAssunto);
					long total = c.getLong(idxTotal);
					
					Assunto a = new Assunto();
					a.setCodigo(chave);
					a.setNome(valor);
					a.setChecked(true);
					a.setTotal(total);
					assuntos.add(a);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar os assuntos: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return assuntos;
	}

//	public List<HashMap<String, String>> listarAssuntosPorMateria(Context ctx,
//			long idMateria) {
//		SQLiteDatabase db = abreConexao(ctx);
//		String sql = "SELECT a.codigo as codigo, a.nome_assunto as nome_assunto, count(*) as total "+
//					 "FROM assunto a, questao q "+
//					 "WHERE q.assunto_codigo = a.codigo "+
//					 "AND   a.materia_codigo = "+idMateria+" "+
//					 "GROUP BY a.codigo order by a.nome_assunto";
//					 
//		Cursor c = db.rawQuery(sql, null);
//		List<HashMap<String, String>> assuntos = new ArrayList<HashMap<String, String>>();
//
//		try {
//			if (c.moveToFirst()) {
//				// Recupera os indices das colunas
//				int idxNomeAssunto = c.getColumnIndex(Assunto.NOME_ASSUNTO);
//				int idxCodigo = c.getColumnIndex(Assunto.CODIGO);
//				// Loop ate o final
//				do {
//					Long chave = c.getLong(idxCodigo);
//					String valor = c.getString(idxNomeAssunto);
//					Long total = c.getLong(c.getColumnIndex("total"));
//					
//					HashMap<String, String> item = new HashMap<String, String>();
//					item.put("id", chave.toString());
//					item.put("nome_assunto", valor+" ("+total+")");
//
//					assuntos.add(item);
//				} while (c.moveToNext());
//
//			}
//		} catch (Exception e) {
//			Log.e("CONSULTA BANCO",
//					"Erro ao buscar os assuntos: " + e.toString());
//			return null;
//		} finally {
//			fechaConexao();
//		}
//		return assuntos;
//	}

}
