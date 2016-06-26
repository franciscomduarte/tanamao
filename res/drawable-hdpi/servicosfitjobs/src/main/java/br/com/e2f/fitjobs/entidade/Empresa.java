package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="data_cadastro")
	private String dataCadastro;

	private String email;

	private String nome;
	
	private String cnpj;

	@Column(name="nome_responsavel")
	private String nomeResponsavel;

	private String senha;

	private String telefone;

	//bi-directional many-to-one association to Assinatura
	@OneToMany(mappedBy="empresa")
	private List<Assinatura> assinaturas;

	//bi-directional many-to-one association to HistoricoAcesso
	@OneToMany(mappedBy="empresa")
	private List<HistoricoAcesso> historicoAcessos;

	public Empresa() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeResponsavel() {
		return this.nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Assinatura> getAssinaturas() {
		return this.assinaturas;
	}

	public void setAssinaturas(List<Assinatura> assinaturas) {
		this.assinaturas = assinaturas;
	}

	public Assinatura addAssinatura(Assinatura assinatura) {
		getAssinaturas().add(assinatura);
		assinatura.setEmpresa(this);

		return assinatura;
	}

	public Assinatura removeAssinatura(Assinatura assinatura) {
		getAssinaturas().remove(assinatura);
		assinatura.setEmpresa(null);

		return assinatura;
	}

	public List<HistoricoAcesso> getHistoricoAcessos() {
		return this.historicoAcessos;
	}

	public void setHistoricoAcessos(List<HistoricoAcesso> historicoAcessos) {
		this.historicoAcessos = historicoAcessos;
	}

	public HistoricoAcesso addHistoricoAcesso(HistoricoAcesso historicoAcesso) {
		getHistoricoAcessos().add(historicoAcesso);
		historicoAcesso.setEmpresa(this);

		return historicoAcesso;
	}

	public HistoricoAcesso removeHistoricoAcesso(HistoricoAcesso historicoAcesso) {
		getHistoricoAcessos().remove(historicoAcesso);
		historicoAcesso.setEmpresa(null);

		return historicoAcesso;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}