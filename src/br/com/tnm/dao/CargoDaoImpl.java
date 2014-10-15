package br.com.tnm.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.tnm.Conexao;
import br.com.tnm.bean.Cargo;
import br.com.tnm.bean.QuestaoFiltro;

public class CargoDaoImpl extends Conexao {
	
	public List<Cargo> listarCargos(Context ctx, QuestaoFiltro questaoFiltro) {
		SQLiteDatabase db = abreConexao(ctx);
		String sql = "SELECT DISTINCT(cg.codigo) AS codigo, cg.nome_cargo AS nome_cargo "+
			" FROM questao q, assunto a, concurso c, cargo cg " +
			" WHERE a.materia_codigo = " + questaoFiltro.getMateriaCodigo() +
			" AND c.codigo = q.concurso_codigo " +
			" AND cg.codigo = c.nome_cargo " +
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
		
		List<Cargo> cargos = new ArrayList<Cargo>();

		try {
			if (c.moveToFirst()) {
				// Recupera os indices das colunas
				int idxCargo = c.getColumnIndex(Cargo.CODIGO);
				int idxNome = c.getColumnIndex(Cargo.NOME_CARGO);
				// Loop ate o final
				cargos.add(new Cargo(0, "-- Todos --"));
				do {
					Cargo cargo = new Cargo();
					cargo.setCodigo(c.getLong(idxCargo));
					cargo.setNome(c.getString(idxNome));
					// recupera os atributos de questoes
					cargos.add(cargo);
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("CONSULTA BANCO", "Erro ao buscar os cargos: " + e.toString());
			return null;
		} finally {
			c.close();
			fechaConexao();
		}
		return cargos;
	}

}
