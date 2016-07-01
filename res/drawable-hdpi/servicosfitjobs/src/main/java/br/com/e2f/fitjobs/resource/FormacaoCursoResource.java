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

import br.com.e2f.fitjobs.entidade.FormacaoCurso;
import br.com.e2f.fitjobs.repository.FormacaoCursoRepository;
import br.com.e2f.fitjobs.repository.PessoaRepository;

@RestController
@RequestMapping("formacoesCurso/{idPessoa}/")
public class FormacaoCursoResource {

	@Autowired FormacaoCursoRepository formacaoCursoRepository;
	@Autowired PessoaRepository pessoasRepository;

	
	@RequestMapping(method = RequestMethod.GET)
	public List<FormacaoCurso> listarFormacaoCursos (@PathVariable Long idPessoa){
		return pessoasRepository.findOne(idPessoa).getFormacaoCursos();
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public FormacaoCurso incluirFormacaoCursos (@RequestBody FormacaoCurso formacaoCurso){
		return formacaoCursoRepository.save(formacaoCurso);
		
	}
	

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<FormacaoCurso> alterarFormacaoCurso(@RequestBody FormacaoCurso formacaoCurso) {
		if (formacaoCurso.getId()!=null){
			return new ResponseEntity<FormacaoCurso>(formacaoCursoRepository.save(formacaoCurso), HttpStatus.OK);
		}
		return new ResponseEntity<FormacaoCurso>(HttpStatus.NOT_MODIFIED);
	}
	

}
