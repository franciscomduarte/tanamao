package br.com.e2f.fitjobs.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Integer cref;

	@Transient
	@JsonIgnore
	private Date dataCadastro;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@Column(name="data_nascimento")
	private Date dataNascimento;

	private String domingo;

	private String email;

	private String endereco;

	private String nome;

	private String quarta;

	private String quinta;

	private String sabado;

	private String segunda;

	private String senha;

	private String sexta;

	private String telefone;

	private String terca;

	private String tipo;

	@OneToMany
	@JoinColumn(name="pessoa_id")
	private List<Assinatura> assinaturas;


	@OneToMany
	@JoinColumn(name="pessoa_id")
	private List<ExperienciaProfissional> experienciaProfissionals;

	@OneToMany
	@JoinColumn(name="pessoa_id")
	private List<Curso> cursos;
	
	
	@OneToMany
	@JoinColumn(name="pessoa_id")
	private List<HistoricoAcesso> historicoAcessos;

	@OneToOne
	@JoinColumn(name="cidade_id")
	private Cidade cidade;
	
	@OneToOne
	@JoinColumn(name="bairro_id")
	private Bairro bairro;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "conhecimento", 
	joinColumns = { @JoinColumn(name = "pessoa_id", 
	updatable =  false) }, inverseJoinColumns = {
	@JoinColumn(name = "modalidade_id", nullable = false, updatable = false) })
	private List<Modalidade> modalidades;
	

	public Pessoa() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCref() {
		return this.cref;
	}

	public void setCref(Integer cref) {
		this.cref = cref;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDomingo() {
		return this.domingo;
	}

	public void setDomingo(String domingo) {
		this.domingo = domingo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getQuarta() {
		return this.quarta;
	}

	public void setQuarta(String quarta) {
		this.quarta = quarta;
	}

	public String getQuinta() {
		return this.quinta;
	}

	public void setQuinta(String quinta) {
		this.quinta = quinta;
	}

	public String getSabado() {
		return this.sabado;
	}

	public void setSabado(String sabado) {
		this.sabado = sabado;
	}

	public String getSegunda() {
		return this.segunda;
	}

	public void setSegunda(String segunda) {
		this.segunda = segunda;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSexta() {
		return this.sexta;
	}

	public void setSexta(String sexta) {
		this.sexta = sexta;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTerca() {
		return this.terca;
	}

	public void setTerca(String terca) {
		this.terca = terca;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Assinatura> getAssinaturas() {
		return this.assinaturas;
	}

	public void setAssinaturas(List<Assinatura> assinaturas) {
		this.assinaturas = assinaturas;
	}

	public List<ExperienciaProfissional> getExperienciaProfissionals() {
		return this.experienciaProfissionals;
	}

	public void setExperienciaProfissionals(List<ExperienciaProfissional> experienciaProfissionals) {
		this.experienciaProfissionals = experienciaProfissionals;
	}

	public List<HistoricoAcesso> getHistoricoAcessos() {
		return this.historicoAcessos;
	}

	public void setHistoricoAcessos(List<HistoricoAcesso> historicoAcessos) {
		this.historicoAcessos = historicoAcessos;
	}
	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public List<Modalidade> getModalidades() {
		return modalidades;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public void setModalidades(List<Modalidade> modalidades) {
		this.modalidades = modalidades;
	}

	@Override
	public int hashCode() {
		 return id == null ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}