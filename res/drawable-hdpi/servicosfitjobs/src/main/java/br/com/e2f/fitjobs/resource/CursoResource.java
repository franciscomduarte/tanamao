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

import br.com.e2f.fitjobs.entidade.Curso;
import br.com.e2f.fitjobs.repository.CursoRepository;
import br.com.e2f.fitjobs.repository.PessoaRepository;

@RestController
@RequestMapping("/cursos")
public class CursoResource {

	@Autowired CursoRepository cursoRepository;
	@Autowired PessoaRepository pessoaRepository;

	
	@RequestMapping(method = RequestMethod.GET)
	public List<Curso> listarCursoComplementars (){
		
		return (List<Curso>) cursoRepository.findAll();
		
	}
	
//	@RequestMapping(value ="{id}/", method = RequestMethod.GET)
//	public List<Curso> listarCursoComplementarsDaPessoa (@PathVariable Long id){
//		
//		return pessoaRepository.findOne(id).getFormacaoCursos().get
//		
//	}
	
	
	@RequestMapping(value ="{id}", method = RequestMethod.GET)
	public Curso getCursoComplementar (@PathVariable Long id){
		return cursoRepository.findOne(id);
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public Curso incluirCursoComplementars (@RequestBody Curso curso){
		return cursoRepository.save(curso);
		
	}
	

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Curso> alterarCursoComplementar(@RequestBody Curso curso) {
		if (curso.getId()!=null){
			return new ResponseEntity<Curso>(cursoRepository.save(curso), HttpStatus.OK);
		}
		return new ResponseEntity<Curso>(HttpStatus.NOT_MODIFIED);
	}
	

}
