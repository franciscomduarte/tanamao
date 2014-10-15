package br.com.tnm.dao;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Implementacao de SQLiteOpenHelper
 * 
 * Classe utilit�ria para abrir, criar, e atualizar o banco de dados
 * 
 * @author ricardo
 */
class SQLiteHelper extends SQLiteOpenHelper {
	
	
	private static final String SCRIPT_INSERT_QUESTAO = "INSERT INTO questao (codigo, enunciado_codigo, item_a, item_b, item_c, item_d, item_e, resposta_certa, assunto_codigo, concurso_codigo, tipo_questao) VALUES ";
	private static final String SCRIPT_INSERT_CONCURSO = "INSERT INTO concurso (codigo, nome_concurso, nivel, data_prova, nome_cargo, nome_banca, nome_orgao, total_questoes, data_inclusao, tipo_questao) VALUES ";
	private static final String SCRIPT_INSERT_ASSUNTO = "INSERT INTO assunto (codigo, nome_assunto, materia_codigo, palavra_chave, assunto_codigo) VALUES ";
	private static final String SCRIPT_INSERT_ENUNCIADO = "INSERT INTO enunciado_questao (codigo, enunciado) VALUES ";
	
	private static final String CATEGORIA = "tanamao";

	private String[] scriptSQLCreate;
	private String[] scriptSQLDelete;
	private List<String[]> scriptSQLInsert;
	private List<String[]> scriptSQLInsertAssunto;
	private List<String[]> scriptSQLInsertConcurso;
	private List<String[]> scriptSQLInsertQuestao;
	private List<String[]> scriptSQLInsertEnunciado;
	

	
	/**
	 * Cria uma instancia de SQLiteHelper
	 * 
	 * @param context
	 * @param nomeBanco nome do banco de dados
	 * @param versaoBanco versao do banco de dados (se for diferente para atualizar)
	 * @param scriptSQLCreate SQL com o create table..
	 * @param scriptSQLDelete SQL com o drop table...
	 */
	SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String[] scriptSQLDelete, 
			     List<String[]> scriptSQLInsert, List<String[]> scriptSQLInsertAssunto, 
			     List<String[]> scriptSQLInsertConcurso, List<String[]> scriptSQLInsertQuestao, List<String[]> scriptSQLInsertEnunciado) {
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLDelete = scriptSQLDelete;
		this.scriptSQLInsert = scriptSQLInsert;
		this.scriptSQLInsertAssunto = scriptSQLInsertAssunto;
		this.scriptSQLInsertConcurso = scriptSQLInsertConcurso;
		this.scriptSQLInsertQuestao = scriptSQLInsertQuestao;
		this.scriptSQLInsertEnunciado = scriptSQLInsertEnunciado;
	}

	@Override
	// Criar novo banco...
	public void onCreate(SQLiteDatabase db) {
		Log.i(CATEGORIA, "Criando banco com sql");
		int qtdeScripts = scriptSQLCreate.length;

		// Executa cada sql passado como parametro
		for (int i = 0; i < qtdeScripts; i++) {
			String sql = scriptSQLCreate[i];
			db.execSQL(sql);
		}
		Log.i(CATEGORIA, "fIM Criando banco com sql");
		inserts(db);
		insertsAssunto(db);
		insertsConcurso(db);
		insertsEnunciado(db);
		insertsQuestao(db);
	}
	
	public void insertsEnunciado(SQLiteDatabase db) {
		Log.i(CATEGORIA, "INICIO Inserindo enunciado no banco com sql");
		//SQLiteStatement insert;
		for (int i = 0; i < scriptSQLInsertEnunciado.size(); i++) {
			
			String[] sqls = (String[])scriptSQLInsertEnunciado.get(i);
			int qtdeScripts = sqls.length;
			// Executa cada sql passado como par�metro
			for (int j = 0; j < qtdeScripts; j++) {
				String sql = sqls[j];
//				insert = db.compileStatement(SCRIPT_INSERT_QUESTAO + sql);
//				insert.executeInsert();
				db.execSQL(SCRIPT_INSERT_ENUNCIADO + sql);
			}
		}
		Log.i(CATEGORIA, "FIM Inserindo enunciado no banco com sql");
	}
	
	
	public void insertsQuestao(SQLiteDatabase db) {
		Log.i(CATEGORIA, "INICIO Inserindo questões no banco com sql");
		//SQLiteStatement insert;
		for (int i = 0; i < scriptSQLInsertQuestao.size(); i++) {
			
			String[] sqls = (String[])scriptSQLInsertQuestao.get(i);
			int qtdeScripts = sqls.length;
			// Executa cada sql passado como par�metro
			for (int j = 0; j < qtdeScripts; j++) {
				String sql = sqls[j];
//				insert = db.compileStatement(SCRIPT_INSERT_QUESTAO + sql);
//				insert.executeInsert();
				db.execSQL(SCRIPT_INSERT_QUESTAO + sql);
			}
		}
		Log.i(CATEGORIA, "FIM Inserindo questões no banco com sql");
	}
	
	public void insertsConcurso(SQLiteDatabase db) {
		Log.i(CATEGORIA, "INICIO Inserindo no banco com sql");
		//SQLiteStatement insert;
		for (int i = 0; i < scriptSQLInsertConcurso.size(); i++) {
			
			String[] sqls = (String[])scriptSQLInsertConcurso.get(i);
			int qtdeScripts = sqls.length;
			// Executa cada sql passado como par�metro
			for (int j = 0; j < qtdeScripts; j++) {
				String sql = sqls[j];
//				insert = db.compileStatement(SCRIPT_INSERT_CONCURSO + sql);
//				insert.executeInsert();
				db.execSQL(SCRIPT_INSERT_CONCURSO + sql);
			}
		}
		Log.i(CATEGORIA, "FIM Inserindo no banco com sql");
	}
	
	public void insertsAssunto(SQLiteDatabase db) {
		Log.i(CATEGORIA, "INICIO Inserindo no banco com sql");
		//SQLiteStatement insert;
		for (int i = 0; i < scriptSQLInsertAssunto.size(); i++) {
			String[] sqls = (String[])scriptSQLInsertAssunto.get(i);
			int qtdeScripts = sqls.length;
			// Executa cada sql passado como par�metro
			for (int j = 0; j < qtdeScripts; j++) {
				String sql = sqls[j];
				db.execSQL(SCRIPT_INSERT_ASSUNTO + sql);
//				insert = db.compileStatement(SCRIPT_INSERT_ASSUNTO + sql);
//				insert.executeInsert();
			}
		}
		Log.i(CATEGORIA, "FIM Inserindo no banco com sql");
	}
	
	// Criar novo banco...
	public void inserts(SQLiteDatabase db) {
		Log.i(CATEGORIA, "INICIO Inserindo no banco com sql");
		//SQLiteStatement insert;
		for (int i = 0; i < scriptSQLInsert.size(); i++) {
			
			String[] sqls = (String[])scriptSQLInsert.get(i);
			int qtdeScripts = sqls.length;
			// Executa cada sql passado como par�metro
			for (int j = 0; j < qtdeScripts; j++) {
				String sql = sqls[j];
//				insert = db.compileStatement(sql);
//				insert.executeInsert();
				db.execSQL(sql);
			}
		}
		Log.i(CATEGORIA, "FIM Inserindo no banco com sql");
	}
   
	@Override
	// Mudou a versao...
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
		Log.w(CATEGORIA, "Atualizando da versão " + versaoAntiga + " para " + novaVersao + ". Todos os registros serão deletados.");
		
		int qtdeScripts = scriptSQLDelete.length;

		// Executa cada sql passado como par�metro
		for (int i = 0; i < qtdeScripts; i++) {
			String sql = scriptSQLDelete[i];
			db.execSQL(sql);
		}
		
		// Deleta as tabelas...
		// Cria novamente...
		onCreate(db);
	}
}