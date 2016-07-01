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

import br.com.e2f.fitjobs.entidade.CursoComplementar;
import br.com.e2f.fitjobs.repository.CursoComplementarRepository;
import br.com.e2f.fitjobs.repository.PessoaRepository;

@RestController
@RequestMapping("/cursosComplementares")
public class CursoComplementarResource {

	@Autowired CursoComplementarRepository cursoComplementarRepository;
	@Autowired PessoaRepository pessoaRepository;

	
	@RequestMapping(method = RequestMethod.GET)
	public List<CursoComplementar> listarCursoComplementars (){
		
		return (List<CursoComplementar>) cursoComplementarRepository.findAll();
		
	}
	
//	@RequestMapping(value ="{id}/", method = RequestMethod.GET)
//	public List<CursoComplementar> listarCursoComplementarsDaPessoa (@PathVariable Long id){
//		
//		return pessoaRepository.findOne(id).getFormacaoCursos().get
//		
//	}
	
	
	@RequestMapping(value ="{id}", method = RequestMethod.GET)
	public CursoComplementar getCursoComplementar (@PathVariable Long id){
		return cursoComplementarRepository.findOne(id);
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public CursoComplementar incluirCursoComplementars (@RequestBody CursoComplementar cursoComplementar){
		return cursoComplementarRepository.save(cursoComplementar);
		
	}
	

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<CursoComplementar> alterarCursoComplementar(@RequestBody CursoComplementar cursoComplementar) {
		if (cursoComplementar.getId()!=null){
			return new ResponseEntity<CursoComplementar>(cursoComplementarRepository.save(cursoComplementar), HttpStatus.OK);
		}
		return new ResponseEntity<CursoComplementar>(HttpStatus.NOT_MODIFIED);
	}
	

}
