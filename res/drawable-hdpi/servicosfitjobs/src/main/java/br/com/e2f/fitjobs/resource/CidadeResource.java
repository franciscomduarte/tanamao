package br.com.e2f.fitjobs.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.e2f.fitjobs.entidade.Cidade;
import br.com.e2f.fitjobs.entidade.CursoComplementar;
import br.com.e2f.fitjobs.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired CidadeRepository cidadeRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Cidade> listarCidades (){
		
		return (List<Cidade>) cidadeRepository.findAll();
		
	}
	
	@RequestMapping(value ="{id}", method = RequestMethod.GET)
	public Cidade getCidade (@PathVariable Long id){
		return cidadeRepository.findOne(id);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public Cidade incluirCidades (@RequestBody Cidade modalidade){
		return cidadeRepository.save(modalidade);
		
	}
	

}
