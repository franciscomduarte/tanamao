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

import br.com.e2f.fitjobs.repository.CursoRepository;
import br.com.e2f.fitjobs.repository.PessoaRepository;

@RestController
@RequestMapping("formacoesCurso/{idPessoa}/")
public class FormacaoCursoResource {

	@Autowired CursoRepository cursoRepository;
	@Autowired PessoaRepository pessoasRepository;

	
	

}
