package br.com.e2f.fitjobs.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.e2f.fitjobs.entidade.ExperienciaProfissional;
import br.com.e2f.fitjobs.entidade.FormacaoCurso;
import br.com.e2f.fitjobs.entidade.Pessoa;
import br.com.e2f.fitjobs.repository.ExperienciaRepository;
import br.com.e2f.fitjobs.repository.PessoaRepository;

@RestController
@RequestMapping("/{idPessoa}/experiencias")
public class ExperienciaResource {

	@Autowired ExperienciaRepository experienciaRepository;
	@Autowired PessoaRepository pessoasRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ExperienciaProfissional> listarExperiencias (@PathVariable Long idPessoa){
		
		return pessoasRepository.findOne(idPessoa).getExperienciaProfissionals();
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public Pessoa incluirExperiencias (@RequestBody Pessoa pessoa){
		return pessoasRepository.save(pessoa);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Pessoa> alterarExperienciaPessoa(@RequestBody Pessoa experiencia) {
		if (experiencia.getId()!=null){
			return new ResponseEntity<Pessoa>(pessoasRepository.save(experiencia), HttpStatus.OK);
		}
		return new ResponseEntity<Pessoa>(HttpStatus.NOT_MODIFIED);
	}
	

}
