package br.com.tnm.dao;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils.InsertHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.tnm.Conexao;
import br.com.tnm.bean.Questao;
import br.com.tnm.util.Resposta;

public class RespostaDaoImpl extends Conexao {
	
	Long codigo; 
	
	public void inserirResposta (Context ctx, Questao questao, Resposta resposta, char respostaMarcada, Long idSimulado, int indiceResposta){
		
		
		SQLiteDatabase db = abreConexao(ctx);
		//Fazer consulta codigo
		
		Cursor c = db.rawQuery("SELECT ifnull(max(codigo),0)+1 as codigo FROM resposta", null);
		
	try {
		
		if (c.moveToFirst()) {
				// Recupera os indices das colunas
		int idxCodigo = c.getColumnIndex("codigo");
		codigo =c.getLong(idxCodigo);
				
		
		InsertHelper ih = new InsertHelper(db, "resposta");
		
		final int col1 = ih.getColumnIndex("codigo");
		final int col2 = ih.getColumnIndex("resposta");
		final int col3 = ih.getColumnIndex("correta");
		final int col4 = ih.getColumnIndex("simulado_codigo");
		final int col5 = ih.getColumnIndex("questao_codigo");
		final int col6 = ih.getColumnIndex("indice_resposta");
	
		ih.prepareForInsert();
		ih.bind(col1, codigo);
		ih.bind(col2, respostaMarcada);
		ih.bind(col3, Resposta.converterResposta(resposta));
		ih.bind(col4, idSimulado);
		ih.bind(col5, questao.getCodigo());
		ih.bind(col6, indiceResposta);
		
				
		
		ih.execute();
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao inserir Resposta: " + e.toString());
			
		} finally {
			c.close();
			fechaConexao();
		
		
		}
	
	}
	
	
	public int retornaIndiceResposta(Context ctx, Long idSimulado) {
		SQLiteDatabase db = abreConexao(ctx);
		Cursor c = db.rawQuery("select indice_questao from resposta where simulado_codigo= "+ idSimulado + " order by indice_questao desc ", null);
		
		Integer indiceQuestao = null ;
		
		try {
			if (c.moveToFirst()) {
			
				//indice das colunas no banco de dados, desta forma fica mais organizado.
				int idxIndice 	= c.getColumnIndex("indice_questao");
				indiceQuestao = c.getInt(idxIndice);	
					
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar o indice: " + e.toString());
			return 0;
		} finally {
			fechaConexao();
		}
		return indiceQuestao+1;
	}	
	
}