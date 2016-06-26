package br.com.e2f.fitjobs.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.e2f.fitjobs.entidade.Bairro;
import br.com.e2f.fitjobs.repository.BairroRepository;

@RestController
@RequestMapping("/bairros")
public class BairroResource {

	@Autowired BairroRepository bairroRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Bairro> listarBairros (){
		
		return (List<Bairro>) bairroRepository.findAll();
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Bairro incluirBairros (@RequestBody Bairro bairro){
		return bairroRepository.save(bairro);
		
	}
	

}
