package br.com.tnm.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.tnm.Conexao;

public class VersaoDaoImpl extends Conexao {

	public boolean getVersao(Context ctx, int versao) {
		SQLiteDatabase db = abreConexao(ctx);
		Cursor c = db.rawQuery("SELECT codigo FROM versao", null);
		int versaoBanco;
		boolean status = false;
		try {
			if (c.moveToFirst()) {
				// Recupera os indices das colunas
				int idxVersao = c.getColumnIndex("codigo");
				versaoBanco = c.getInt(idxVersao);

				if (versao == versaoBanco) {
					status = true;
				}

			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao carregar materia: " + e.toString());
			return status;
		} finally {
			c.close();
			fechaConexao();

		}
		return status;
	}

	
	public void atualizaVersao(Context ctx, int versaoCodigo){
		
		SQLiteDatabase db = abreConexao(ctx);
		String sqlUpdate = "UPDATE versao set codigo = "+ versaoCodigo;
		
		db.execSQL(sqlUpdate);
		
		fechaConexao();
		
	}
	
}
