package br.com.e2f.fitjobs.resource;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.e2f.fitjobs.entidade.Conhecimento;
import br.com.e2f.fitjobs.entidade.ExperienciaProfissional;
import br.com.e2f.fitjobs.entidade.FormacaoCurso;
import br.com.e2f.fitjobs.entidade.Pessoa;
import br.com.e2f.fitjobs.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	PessoaRepository pessoaRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Pessoa> listarPessoas() {

		return (List<Pessoa>) pessoaRepository.findAll();

	}
	
	@RequestMapping(value="{id}/experiencias", method = RequestMethod.GET)
	public List<ExperienciaProfissional> listarExperiencias(@PathVariable Long id) {
		return  pessoaRepository.findOne(id).getExperienciaProfissionals();

	}
	
	@RequestMapping(value="{id}/formacoesCursos", method = RequestMethod.GET)
	public List<FormacaoCurso> listarFormacoesCurso(@PathVariable Long id) {
		return  pessoaRepository.findOne(id).getFormacaoCursos();

	}
	
	@RequestMapping(value="{id}/conhecimentos", method = RequestMethod.GET)
	public List<Conhecimento> listarConhecimento(@PathVariable Long id) {
		return  pessoaRepository.findOne(id).getConhecimentos();

	}
	
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public Pessoa getPessoa(@PathVariable Long id){
		return pessoaRepository.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Pessoa incluirPessoas(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);

	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Pessoa> alterarPessoas(@RequestBody Pessoa pessoa) {
		if (pessoa.getId()!=null){
			return new ResponseEntity<Pessoa>(pessoaRepository.save(pessoa), HttpStatus.OK);
		}
		return new ResponseEntity<Pessoa>(HttpStatus.NOT_MODIFIED);
	}

}
