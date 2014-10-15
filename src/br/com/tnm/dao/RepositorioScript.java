package br.com.tnm.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import br.com.tnm.Conexao;
import br.com.tnm.dao.Repositorio.RepositorioAssuntoLote1;
import br.com.tnm.dao.Repositorio.RepositorioBanca;
import br.com.tnm.dao.Repositorio.RepositorioCargo;
import br.com.tnm.dao.Repositorio.RepositorioConcursoLote1;
import br.com.tnm.dao.Repositorio.RepositorioMateria;
import br.com.tnm.dao.Repositorio.RepositorioOrgao;
import br.com.tnm.dao.Repositorio.encunciado.RepositorioEnunciadoAtualidades;
import br.com.tnm.dao.Repositorio.encunciado.RepositorioEnunciadoConhecimentosBancarios;
import br.com.tnm.dao.Repositorio.encunciado.RepositorioEnunciadoEstatistica;
import br.com.tnm.dao.Repositorio.encunciado.RepositorioEnunciadoEtica;
import br.com.tnm.dao.Repositorio.encunciado.RepositorioEnunciadoInformatica;
import br.com.tnm.dao.Repositorio.encunciado.RepositorioEnunciadoMatematica;
import br.com.tnm.dao.Repositorio.encunciado.RepositorioEnunciadoMatematicaFinanceira;
import br.com.tnm.dao.Repositorio.encunciado.RepositorioEnunciadoPortugues;
import br.com.tnm.dao.Repositorio.encunciado.RepositorioEnunciadoRaciocinioLogico;
import br.com.tnm.dao.Repositorio.encunciado.RepositorioEnunciadoTi;
import br.com.tnm.dao.Repositorio.questoes.RepositorioQuestoesAtualidades;
import br.com.tnm.dao.Repositorio.questoes.RepositorioQuestoesConhecimentosBancarios;
import br.com.tnm.dao.Repositorio.questoes.RepositorioQuestoesEstatistica;
import br.com.tnm.dao.Repositorio.questoes.RepositorioQuestoesEtica;
import br.com.tnm.dao.Repositorio.questoes.RepositorioQuestoesInformatica;
import br.com.tnm.dao.Repositorio.questoes.RepositorioQuestoesMatematica;
import br.com.tnm.dao.Repositorio.questoes.RepositorioQuestoesMatematicaFinanceira;
import br.com.tnm.dao.Repositorio.questoes.RepositorioQuestoesPortugues;
import br.com.tnm.dao.Repositorio.questoes.RepositorioQuestoesRaciocinioLogico;
import br.com.tnm.dao.Repositorio.questoes.RepositorioQuestoesTi;


public class RepositorioScript extends Conexao {
	
	// Script para fazer drop na tabela
	private static final String SCRIPT_DATABASE_DELETE_MATERIA = "DROP TABLE IF EXISTS materia";
	private static final String SCRIPT_DATABASE_DELETE_QUESTAO = "DROP TABLE IF EXISTS questao";
	private static final String SCRIPT_DATABASE_DELETE_ORGAO = "DROP TABLE IF EXISTS orgao";
	private static final String SCRIPT_DATABASE_DELETE_CONCURSO = "DROP TABLE IF EXISTS concurso";
	private static final String SCRIPT_DATABASE_DELETE_CARGO = "DROP TABLE IF EXISTS cargo";
	private static final String SCRIPT_DATABASE_DELETE_BANCA = "DROP TABLE IF EXISTS banca";
	private static final String SCRIPT_DATABASE_DELETE_ASSUNTO = "DROP TABLE IF EXISTS assunto";
	private static final String SCRIPT_DATABASE_DELETE_SIMULADO = "DROP TABLE IF EXISTS simulado";
	private static final String SCRIPT_DATABASE_DELETE_RESPOSTA = "DROP TABLE IF EXISTS resposta";
	private static final String SCRIPT_DATABASE_DELETE_QUESTAO_CERTO_ERRADO = "DROP TABLE IF EXISTS questao_certo_errado";
	private static final String SCRIPT_DATABASE_DELETE_ITEM_CERTO_ERRADO = "DROP TABLE IF EXISTS item_certo_errado";
	private static final String SCRIPT_DATABASE_DELETE_VIEW_QUESTAO_FILTRO = "DROP VIEW IF EXISTS questao_filtro";
	//Coloquei a opção de não apagar a tabela de comentários.
	//private static final String SCRIPT_DATABASE_DELETE_COMENTARIO_QUESTAO = "DROP TABLE IF EXISTS comentario_questao";
	private static final String SCRIPT_DATABASE_DELETE_ENUNCIADO_QUESTAO = "DROP TABLE IF EXISTS enunciado_questao";
	
	
	private static final String SCRIPT_DATABASE_CREATE_QUESTAO = "CREATE TABLE questao (" +
			" codigo integer NOT NULL, " +
			" enunciado_codigo integer NOT NULL, " +
			" item_a text, " +
			" item_b text, " +
			" item_c text, " +
			" item_d text, " +
			" item_e text, " +
			" resposta_certa tex NOT NULL, " +
			" assunto_codigo integer NOT NULL, " +
			" concurso_codigo integer NOT NULL, " +
			" tipo_questao integer NOT NULL, " +
			" rotulo TEXT NULL);";
	
	private static final String CREATE_QUESTAO_IDX_CODIGO 			= " CREATE INDEX [idx_codigo_questao] ON [questao] ([codigo]) ";
	private static final String CREATE_QUESTAO_IDX_ASSUNTO_CODIGO 	= " CREATE INDEX [idx_assunto_codigo] ON [questao] ([assunto_codigo]) ";
	private static final String CREATE_QUESTAO_IDX_CONCURSO_CODIGO 	= " CREATE INDEX [idx_concurso_codigo] ON [questao] ([concurso_codigo]) ";
	private static final String CREATE_QUESTAO_IDX_ENUNCIADO_CODIGO = " CREATE INDEX [idx_enunciado_codigo] ON [questao] ([enunciado_codigo]) ";
	
	private static final String SCRIPT_DATABASE_CREATE_ENUNCIADO = "CREATE TABLE enunciado_questao (" +
			" codigo integer NOT NULL, " +
			" enunciado TEXT NOT NULL);";
	
	private static final String CREATE_ENUNCIADO_IDX_CODIGO = " CREATE INDEX [idx_codigo_enunciado] ON [enunciado_questao] ([codigo]) ";
	
	
	private static final String SCRIPT_DATABASE_CREATE_MATERIA = " CREATE TABLE materia (" +
			" codigo integer NOT NULL PRIMARY KEY, " +
			" nome_materia TEXT NOT NULL);";
	private static final String CREATE_MATERIA_IDX_CODIGO = " CREATE INDEX [idx_codigo_materia] ON [materia] ([codigo]) ";
	
	private static final String SCRIPT_DATABASE_CREATE_ORGAO = " CREATE TABLE orgao (" +
			" codigo integer NOT NULL PRIMARY KEY, " +
			" nome_orgao text NOT NULL);";
	private static final String CREATE_ORGAO_IDX_CODIGO = " CREATE INDEX [idx_codigo_orgao] ON [orgao] ([codigo]) ";
	
	private static final String SCRIPT_DATABASE_CREATE_CONCURSO = " CREATE TABLE concurso ( " +
			" codigo integer NOT NULL," +
			" nome_concurso text NOT NULL," +
			" nivel text NOT NULL," +
			" data_prova date NOT NULL, " +
			" nome_cargo text NOT NULL, " +
			" nome_banca text NOT NULL, " +
			" nome_orgao text NOT NULL, " +
			" total_questoes integer null, " +
			" data_inclusao datetime NOT NULL, " +
			" tipo_questao text NOT NULL);";
	private static final String CREATE_CONCURSO_IDX_CODIGO = " CREATE INDEX [idx_codigo_concurso] ON [concurso] ([codigo]) ";
	private static final String CREATE_CONCURSO_IDX_NOME_CARGO = " CREATE INDEX [idx_nome_cargo] ON [concurso] ([nome_cargo]) ";
	private static final String CREATE_CONCURSO_IDX_NOME_BANCA = " CREATE INDEX [idx_nome_banca] ON [concurso] ([nome_banca]) ";
	private static final String CREATE_CONCURSO_IDX_NOME_ORGAO = " CREATE INDEX [idx_nome_orgao] ON [concurso] ([nome_orgao]) ";
	private static final String CREATE_CONCURSO_IDX_DATA_PROVA = " CREATE INDEX [idx_data_prova] ON [concurso] ([data_prova]) ";
	
	private static final String SCRIPT_DATABASE_CREATE_CARGO = "CREATE TABLE cargo (" +
			" codigo integer NOT NULL PRIMARY KEY, " +
			" nome_cargo text NOT NULL);";
	private static final String CREATE_CARGO_IDX_CARGO_CODIGO = " CREATE INDEX [idx_codigo_cargo] ON [cargo] ([codigo]) ";
	
	private static final String SCRIPT_DATABASE_CREATE_BANCA = "CREATE TABLE banca (" +
			" codigo integer NOT NULL PRIMARY KEY, " +
			" nome_banca text NOT NULL);";
	private static final String CREATE_BANCA_IDX_BANCA_CODIGO = " CREATE INDEX [idx_codigo_banca] ON [banca] ([codigo]) ";
	
	private static final String SCRIPT_DATABASE_CREATE_ASSUNTO = "CREATE TABLE assunto (" +
			" codigo integer NOT NULL," +
			" nome_assunto text NOT NULL," +
			" materia_codigo integer NOT NULL," +
			" palavra_chave text NOT NULL," +
			" assunto_codigo integer NOT NULL);";
	private static final String CREATE_ASSUNTO_IDX_CODIGO = " CREATE INDEX [idx_codigo_assunto] ON [assunto] ([codigo]) ";
	private static final String CREATE_ASSUNTO_IDX_MATERIA_CODIGO = " CREATE INDEX [idx_materia_codigo_assunto] ON [assunto] ([materia_codigo]) ";
	
	private static final String SCRIPT_DATABASE_CREATE_SIMULADO = "CREATE TABLE simulado (" +
			" codigo integer NOT NULL," +
			" data_criacao datetime NOT NULL," +
			" banca_codigo integer NOT NULL," +
			" orgao_codigo integer NOT NULL," +
			" cargo_codigo integer NOT NULL," +
			" ano integer NOT NULL," +
			" total_questoes integer NOT NULL," +
			" tipo_questao text," +
			" nivel_questao text," +
			" finalizado boolean null, " +
			" materia_codigo integer NOT NULL," +
			" assuntos text, " +
			" tempo text DEFAULT '00:00');";
	
	private static final String CREATE_SIMULADO_IDX_CODIGO = " CREATE INDEX [idx_codigo_simulado] ON [simulado] ([codigo]) ";
	private static final String CREATE_SIMULADO_IDX_MATERIA_CODIGO = " CREATE INDEX [idx_materia_codigo] ON [simulado] ([materia_codigo]) ";
	private static final String CREATE_SIMULADO_IDX_SIMULADO_BANCA_CODIGO = " CREATE INDEX [idx_simualdo_banca_codigo] ON [simulado] ([banca_codigo]) ";
	private static final String CREATE_SIMULADO_IDX_SIMULADO_ORGAO_CODIGO = " CREATE INDEX [idx_simualdo_orgao_codigo] ON [simulado] ([orgao_codigo]) ";
	private static final String CREATE_SIMULADO_IDX_SIMULADO_CARGO_CODIGO = " CREATE INDEX [idx_simualdo_cargo_codigo] ON [simulado] ([cargo_codigo]) ";

	
	private static final String SCRIPT_DATABASE_CREATE_RESPOSTA = "CREATE TABLE resposta (" +
			" codigo integer NOT NULL," +
			" resposta text NOT NULL," +
			" correta integer NOT NULL," +
			" simulado_codigo integer NOT NULL," +
			" questao_codigo integer, " +
			" questao_certo_errado_codigo integer, " +
			" indice_resposta integer not null);";
	private static final String CREATE_RESPOSTA_IDX_CODIGO = " CREATE INDEX [idx_codigo_resposta] ON [resposta] ([codigo]) ";
	private static final String CREATE_RESPOSTA_IDX_QUESTAO_CODIGO = " CREATE INDEX [idx_questao_codigo] ON [resposta] ([questao_codigo]) ";
	private static final String CREATE_RESPOSTA_IDX_SIMULADO_CODIGO = " CREATE INDEX [idx_simulado_codigo] ON [resposta] ([simulado_codigo]) ";
	private static final String CREATE_RESPOSTA_IDX_INDICE_RESPOSTA = " CREATE INDEX [idx_indice_resposta] ON [resposta] ([indice_resposta]) ";

	
	private static final String SCRIPT_DATABASE_CREATE_QUESTAO_CERTO_ERRADO = "CREATE TABLE questao_certo_errado (" +
			" codigo integer NOT NULL," +
			" cod_global integer NOT NULL," +
			" cod_na_prova integer NOT NULL," +
			" enunciado text NOT NULL," +
			" assunto_codigo integer, " +
			" concurso_codigo integer);";
	
	private static final String SCRIPT_DATABASE_CREATE_COMENTARIO_QUESTAO = "CREATE TABLE IF NOT EXISTS comentario_questao ("+
			" questao_codigo integer not null, " +
			" comentario text not null);";
	
	private static final String CREATE_QUESTAO_CERTO_ERRADO_IDX_CODIGO = " CREATE INDEX [idx_codigo_questao_certo_errado] ON [questao_certo_errado] ([codigo]) ";

	
	private static final String SCRIPT_DATABASE_CREATE_ITEM_CERTO_ERRADO = "CREATE TABLE item_certo_errado (" +
			" codigo integer NOT NULL," +
			" nome_item text NOT NULL," +
			" resposta_item text NOT NULL," +
			" assunto_codigo integer, " +
			" questao_certo_errado_codigo integer);";
	
	
	private static final String SCRIPT_DATABASE_CREATE_VIEW_QUESTAO_FILTRO = "CREATE VIEW questao_filtro AS select c.codigo as concurso_codigo, "+
			"b.codigo as banca_codigo,  "+
			"o.codigo as orgao_codigo,  "+
			"strftime(\"%Y\",c.data_prova) as ano, cg.codigo as cargo_codigo, (select count(*)  from questao where concurso_codigo = c.codigo ) as total_questoes "+
			"from concurso c, banca b, orgao o, cargo cg "+
			"where c.nome_banca = b.codigo "+
			"and     c.nome_orgao = o.codigo "+
			"and     c.nome_cargo = cg.codigo "+
			"and  (select count(*)  from questao where concurso_codigo = c.codigo )  > 0";
	
	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
		SCRIPT_DATABASE_CREATE_QUESTAO,
		SCRIPT_DATABASE_CREATE_MATERIA,
		SCRIPT_DATABASE_CREATE_ORGAO,
		SCRIPT_DATABASE_CREATE_CONCURSO,
		SCRIPT_DATABASE_CREATE_CARGO, 
		SCRIPT_DATABASE_CREATE_BANCA,
		SCRIPT_DATABASE_CREATE_ASSUNTO,
		SCRIPT_DATABASE_CREATE_SIMULADO,
		SCRIPT_DATABASE_CREATE_RESPOSTA,
		SCRIPT_DATABASE_CREATE_QUESTAO_CERTO_ERRADO,
		SCRIPT_DATABASE_CREATE_ITEM_CERTO_ERRADO,
		SCRIPT_DATABASE_CREATE_COMENTARIO_QUESTAO,
		CREATE_QUESTAO_IDX_CODIGO,
		CREATE_QUESTAO_IDX_ASSUNTO_CODIGO,
		CREATE_QUESTAO_IDX_CONCURSO_CODIGO,
		CREATE_QUESTAO_IDX_ENUNCIADO_CODIGO,
		CREATE_MATERIA_IDX_CODIGO,
		CREATE_ORGAO_IDX_CODIGO,
		CREATE_CONCURSO_IDX_CODIGO,
		CREATE_CARGO_IDX_CARGO_CODIGO,
		CREATE_BANCA_IDX_BANCA_CODIGO,
		CREATE_ASSUNTO_IDX_CODIGO,
		CREATE_SIMULADO_IDX_CODIGO,
		CREATE_SIMULADO_IDX_MATERIA_CODIGO,
		CREATE_RESPOSTA_IDX_CODIGO,
		CREATE_RESPOSTA_IDX_QUESTAO_CODIGO,
		CREATE_QUESTAO_CERTO_ERRADO_IDX_CODIGO,	
		CREATE_CONCURSO_IDX_NOME_CARGO,
		CREATE_CONCURSO_IDX_NOME_BANCA,
		CREATE_CONCURSO_IDX_NOME_ORGAO,
		CREATE_SIMULADO_IDX_SIMULADO_BANCA_CODIGO,
		CREATE_SIMULADO_IDX_SIMULADO_ORGAO_CODIGO,
		CREATE_RESPOSTA_IDX_SIMULADO_CODIGO,
		CREATE_CONCURSO_IDX_DATA_PROVA,
		CREATE_ASSUNTO_IDX_MATERIA_CODIGO,
		CREATE_SIMULADO_IDX_SIMULADO_CARGO_CODIGO,
		CREATE_RESPOSTA_IDX_INDICE_RESPOSTA,
		SCRIPT_DATABASE_CREATE_VIEW_QUESTAO_FILTRO,
		SCRIPT_DATABASE_CREATE_ENUNCIADO,
		CREATE_ENUNCIADO_IDX_CODIGO,
		
		};

	
	private static final String[] SCRIPT_DATABASE_DELETE = new String[] {
		SCRIPT_DATABASE_DELETE_MATERIA,
		SCRIPT_DATABASE_DELETE_QUESTAO,
		SCRIPT_DATABASE_DELETE_ORGAO,
		SCRIPT_DATABASE_DELETE_CONCURSO,
		SCRIPT_DATABASE_DELETE_CARGO,
		SCRIPT_DATABASE_DELETE_BANCA,
		SCRIPT_DATABASE_DELETE_ASSUNTO,
		SCRIPT_DATABASE_DELETE_SIMULADO,
		SCRIPT_DATABASE_DELETE_RESPOSTA,
		SCRIPT_DATABASE_DELETE_ITEM_CERTO_ERRADO,
		SCRIPT_DATABASE_DELETE_QUESTAO_CERTO_ERRADO,
		SCRIPT_DATABASE_DELETE_VIEW_QUESTAO_FILTRO,
		SCRIPT_DATABASE_DELETE_ENUNCIADO_QUESTAO,
	};
	
	private List<String[]> inserts = new ArrayList<String[]>();
	private List<String[]> insertsAssunto = new ArrayList<String[]>();
	private List<String[]> insertsConcurso = new ArrayList<String[]>();
	private List<String[]> insertsEnunciado = new ArrayList<String[]>();
	private List<String[]> insertsQuestao = new ArrayList<String[]>();
	

	// Nome do banco
	private static final String NOME_BANCO = "tanamao";

	private static final int VERSAO_BANCO = 51;
	public static final String TABELA_MATERIA = "materia";

	// Classe utilitaria para abrir, criar, e atualizar o banco de dados
	private SQLiteHelper dbHelper;

	// Cria o banco de dados com um script SQL
	public RepositorioScript(Context ctx) {
		
		inserts.add(RepositorioMateria.VALORES_TABELA_MATERIA);
		inserts.add(RepositorioBanca.VALORES_TABELA_BANCA);
		inserts.add(RepositorioCargo.VALORES_TABELA_CARGO);
		inserts.add(RepositorioOrgao.VALORES_TABELA_ORGAO);
		
		insertsAssunto.add(RepositorioAssuntoLote1.VALORES_TABELA_ASSUNTO_LOTE1);
		insertsConcurso.add(RepositorioConcursoLote1.VALORES_TABELA_CONCURSOS_LOTE1);
		
		//Repositorio Enunciados
		
		insertsEnunciado.add(RepositorioEnunciadoAtualidades.VALORES_ENUNCIADO_ATUALIDADES);
		insertsEnunciado.add(RepositorioEnunciadoConhecimentosBancarios.VALORES_ENUNCIADO_CONHECIMENTOS_BANCARIOS);
		insertsEnunciado.add(RepositorioEnunciadoEstatistica.VALORES_ENUNCIADO_ESTATISTICA);
		insertsEnunciado.add(RepositorioEnunciadoEtica.VALORES_ENUNCIADO_ETICA);
		insertsEnunciado.add(RepositorioEnunciadoInformatica.VALORES_ENUNCIADO_INFORMATICA);
		insertsEnunciado.add(RepositorioEnunciadoMatematica.VALORES_ENUNCIADO_MATEMATICA);
		insertsEnunciado.add(RepositorioEnunciadoMatematicaFinanceira.VALORES_ENUNCIADO_MATEMATICA_FINANCEIRA);
		insertsEnunciado.add(RepositorioEnunciadoPortugues.VALORES_ENUNCIADO_PORTUGUES);
		insertsEnunciado.add(RepositorioEnunciadoRaciocinioLogico.VALORES_ENUNCIADO_RACIOCINIO_LOGICO);
		insertsEnunciado.add(RepositorioEnunciadoTi.VALORES_ENUNCIADO_TI);
		
		//Repositorio Questoes
		
		insertsQuestao.add(RepositorioQuestoesAtualidades.VALORES_QUESTOES_ATUALIDADES);
		insertsQuestao.add(RepositorioQuestoesConhecimentosBancarios.VALORES_QUESTOES_CONHECIMENTOS_BANCARIOS);
		insertsQuestao.add(RepositorioQuestoesEstatistica.VALORES_QUESTOES_ESTATISTICA);
		insertsQuestao.add(RepositorioQuestoesEtica.VALORES_QUESTOES_ETICA);
		insertsQuestao.add(RepositorioQuestoesInformatica.VALORES_QUESTOES_INFORMATICA);
		insertsQuestao.add(RepositorioQuestoesMatematica.VALORES_QUESTOES_MATEMATICA);
		insertsQuestao.add(RepositorioQuestoesMatematicaFinanceira.VALORES_QUESTOES_MATEMATICA_FINANCEIRA);
		insertsQuestao.add(RepositorioQuestoesPortugues.VALORES_QUESTOES_PORTUGUES);
		insertsQuestao.add(RepositorioQuestoesRaciocinioLogico.VALORES_QUESTOES_RACIOCINIO_LOGICO);
		insertsQuestao.add(RepositorioQuestoesTi.VALORES_QUESTOES_TI);
				
		// Criar utilizando um script SQL
		dbHelper = new SQLiteHelper(ctx, RepositorioScript.NOME_BANCO, RepositorioScript.VERSAO_BANCO,
				RepositorioScript.SCRIPT_DATABASE_CREATE, RepositorioScript.SCRIPT_DATABASE_DELETE, inserts, insertsAssunto, insertsConcurso, insertsQuestao, insertsEnunciado);
			
		// abre o banco no modo escrita para poder alterar tamb�m
		db = dbHelper.getWritableDatabase();
		
		fechar();
	}
	
	public void fechar() {
		super.fechaConexao();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
	
}

