package br.com.e2f.fitjobs.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.e2f.fitjobs.entidade.Empresa;
import br.com.e2f.fitjobs.repository.EmpresaRepository;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {

	@Autowired EmpresaRepository empresaRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Empresa> listarEmpresas (){
		
		return (List<Empresa>) empresaRepository.findAll();
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Empresa incluirEmpresas (@RequestBody Empresa empresa){
		return empresaRepository.save(empresa);
		
	}
	

}
