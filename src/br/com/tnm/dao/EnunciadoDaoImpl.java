package br.com.tnm.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.tnm.Conexao;
import br.com.tnm.bean.Enunciado;

public class EnunciadoDaoImpl extends Conexao {

	public String burcarEnunciado(Context ctx, long codigo) {
		SQLiteDatabase db = abreConexao(ctx);
		String sql = "SELECT enunciado " + 
					 "FROM enunciado_questao "+
					 "where codigo = "+codigo;
		
		Cursor c = db.rawQuery(sql, null);

		String valor = "";

		try {
			if (c.moveToFirst()) {
				// Recupera os indices das colunas
				int idxEnunciado = c.getColumnIndex(Enunciado.ENUNCIADO);

				do {
					valor = c.getString(idxEnunciado);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar enunciado: " + e.toString());
			return null;
		} finally {
			fechaConexao();
		}
		return valor;
	}

}
