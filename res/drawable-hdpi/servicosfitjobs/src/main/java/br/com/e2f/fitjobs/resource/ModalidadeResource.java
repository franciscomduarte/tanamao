package br.com.e2f.fitjobs.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.e2f.fitjobs.entidade.Modalidade;
import br.com.e2f.fitjobs.repository.ModalidadeRepository;

@RestController
@RequestMapping("/modalidades")
public class ModalidadeResource {

	@Autowired ModalidadeRepository modalidadeRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Modalidade> listarModalidades (){
		
		return (List<Modalidade>) modalidadeRepository.findAll();
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Modalidade incluirModalidades (@RequestBody Modalidade modalidade){
		return modalidadeRepository.save(modalidade);
		
	}
	

}
