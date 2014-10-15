package br.com.tnm.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.tnm.Conexao;
import br.com.tnm.bean.Concurso;

public class ConcursoDaoImpl extends Conexao {
	
	public List<HashMap<Long, String>> listarConcursos(Context ctx) {
		SQLiteDatabase db = abreConexao(ctx);
		Cursor c = db.rawQuery("SELECT codigo, nome_concurso FROM concurso", null);
		List<HashMap<Long, String>> concursos = new ArrayList<HashMap<Long, String>>();

		try {
			if (c.moveToFirst()) {
				// Recupera os �ndices das colunas
				int idxNomeConcurso = c.getColumnIndex(Concurso.NOME_CONCURSO);
				int idxCodigo = c.getColumnIndex(Concurso.CODIGO);
				// Loop at� o final
				do {
					Long chave = c.getLong(idxCodigo);
					String valor = c.getString(idxNomeConcurso);
					
					HashMap<Long, String> item = new HashMap<Long, String>();
					item.put(chave, valor);
					
					concursos.add(item);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar os Concursos: " + e.toString());
			return null;
		} finally {
			fechaConexao();
		}
		return concursos;
	}

}
