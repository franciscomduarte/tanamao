package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the funcao database table.
 * 
 */
@Entity
@NamedQuery(name="Funcao.findAll", query="SELECT f FROM Funcao f")
public class Funcao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nome;

	//bi-directional many-to-one association to ExperienciaProfissional
	@OneToMany(mappedBy="funcao")
	private List<ExperienciaProfissional> experienciaProfissionals;

	public Funcao() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ExperienciaProfissional> getExperienciaProfissionals() {
		return this.experienciaProfissionals;
	}

	public void setExperienciaProfissionals(List<ExperienciaProfissional> experienciaProfissionals) {
		this.experienciaProfissionals = experienciaProfissionals;
	}

	public ExperienciaProfissional addExperienciaProfissional(ExperienciaProfissional experienciaProfissional) {
		getExperienciaProfissionals().add(experienciaProfissional);
		experienciaProfissional.setFuncao(this);

		return experienciaProfissional;
	}

	public ExperienciaProfissional removeExperienciaProfissional(ExperienciaProfissional experienciaProfissional) {
		getExperienciaProfissionals().remove(experienciaProfissional);
		experienciaProfissional.setFuncao(null);

		return experienciaProfissional;
	}

}