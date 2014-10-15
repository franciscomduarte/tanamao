package br.com.tnm.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.tnm.Conexao;
import br.com.tnm.bean.ComentarioQuestao;

public class ComentarioQuestaoDaoImpl extends Conexao {

	public ComentarioQuestao getComentarioQuestao(Context ctx, Long codigo) {
		SQLiteDatabase db = abreConexao(ctx);
		String sql = "SELECT * from comentario_questao "+
					 "WHERE questao_codigo = " + codigo;

		Cursor c = db.rawQuery(sql, null);
		ComentarioQuestao comentarioQuestao = new ComentarioQuestao();
		try {
			if (c.moveToFirst()) {
				// Recupera os indices das colunas
				int idxQuestaoCodigo = c.getColumnIndex(ComentarioQuestao.QUESTAO_CODIGO);
				int idxComentario = c.getColumnIndex(ComentarioQuestao.COMENTARIO);

				comentarioQuestao.setQuestaoCodigo(c.getLong(idxQuestaoCodigo));
				comentarioQuestao.setComentario(c.getString(idxComentario));
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar comentario da questao ["+ codigo + "]: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return comentarioQuestao;
	}

	public void salvarComentario(Context ctx, Long codigo, String comentario) {
		SQLiteDatabase db = abreConexao(ctx);
		String sql = "insert into comentario_questao(questao_codigo, comentario) " +
					 "values("+codigo+",'"+comentario+"');";
		db.execSQL(sql);
		fechaConexao();
	}

	public void atualizarComentario(Context ctx, Long codigo, String comentario) {
		SQLiteDatabase db = abreConexao(ctx);
		String sql = "update comentario_questao set comentario = " +
					 "'"+comentario+"' where questao_codigo = "+codigo;
		db.execSQL(sql);
		fechaConexao();
	}
	
}
