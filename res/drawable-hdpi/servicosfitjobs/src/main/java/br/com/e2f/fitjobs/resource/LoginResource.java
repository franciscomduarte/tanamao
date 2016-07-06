package br.com.e2f.fitjobs.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.e2f.fitjobs.entidade.Pessoa;
import br.com.e2f.fitjobs.repository.PessoaRepository;

@RestController
@RequestMapping("/login")
public class LoginResource {

	@Autowired PessoaRepository pessoaRepository;
	
	
	@RequestMapping(value="/{email}/{senha}", method = RequestMethod.GET)
	public Pessoa getPessoa(@PathVariable String email, @PathVariable String senha){
		
		return pessoaRepository.verificarLogin(email, senha);
	}
	
	
	

}
