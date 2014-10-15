package br.com.tnm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Conexao {
	
	// Nome do banco
	private static final String NOME_BANCO = "tanamao";
	
	protected SQLiteDatabase db;

	public SQLiteDatabase abreConexao(Context ctx) {
		// Abre o banco de dados j� existente
		if (db == null || !db.isOpen()) {
			db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
			Log.i("BANCO DE DADOS", "Conexão aberta");
		}
		return db;
	}
	
	public void fechaConexao(){
		if(db != null) {
			db.close();
		}
		Log.i("BANCO DE DADOS", "Conexão fechada");
	}

}
