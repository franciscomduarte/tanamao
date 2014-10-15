package br.com.tnm.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.tnm.Conexao;
import br.com.tnm.bean.Orgao;
import br.com.tnm.bean.QuestaoFiltro;

public class OrgaoDaoImpl extends Conexao {
	
	public List<Orgao> listarOrgaos(Context ctx, QuestaoFiltro questaoFiltro) {
		SQLiteDatabase db = abreConexao(ctx);
		String sql = "SELECT DISTINCT(org.codigo) AS codigo, org.nome_orgao AS nome_orgao"+
								" FROM questao q, assunto a, concurso c, cargo cg, orgao org"+
								" WHERE a.materia_codigo = " + questaoFiltro.getMateriaCodigo() + 
								" AND c.codigo = q.concurso_codigo"+
								" AND cg.codigo = c.nome_cargo " +
								" AND org.codigo=c.nome_orgao "+
								" AND q.assunto_codigo = a.codigo ";
		
		if(questaoFiltro.getBancaCodigo() != 0) {
			sql += " AND c.nome_banca = " + questaoFiltro.getBancaCodigo();
		}
		if(questaoFiltro.getAno() != 0) {
			sql += " AND strftime('%Y',c.data_prova) = '" + questaoFiltro.getAno() + "'";
		}
		if(questaoFiltro.getOrgaoCodigo() != 0) {
			sql += " AND c.nome_orgao = " + questaoFiltro.getOrgaoCodigo();
		}
		if(questaoFiltro.getCargoCodigo() != 0) {
			sql += " AND c.nome_cargo = " + questaoFiltro.getCargoCodigo();
		}
		if(!questaoFiltro.getAssuntos().equals(null)){
			sql += " AND a.codigo in ("+questaoFiltro.getAssuntos()+")";
		}
		
		Cursor c = db.rawQuery(sql, null);
		List<Orgao> orgaos = new ArrayList<Orgao>();

		try {
			if (c.moveToFirst()) {
				// Recupera os indices das colunas
				int idxNomeOrgao = c.getColumnIndex(Orgao.NOME_ORGAO);
				int idxCodigo = c.getColumnIndex(Orgao.CODIGO);
				// Loop ate o final
				orgaos.add(new Orgao(0,"--Todos--"));
				do {
					Orgao orgao = new Orgao();
					orgao.setCodigo(c.getLong(idxCodigo));
					orgao.setNome(c.getString(idxNomeOrgao));
					
					orgaos.add(orgao);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar os Orgaos: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return orgaos;
	}

}
