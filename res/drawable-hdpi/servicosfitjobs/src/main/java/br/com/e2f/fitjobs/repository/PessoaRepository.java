/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.e2f.fitjobs.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.e2f.fitjobs.entidade.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	@Transactional
	@Modifying  
	@Query("UPDATE Pessoa p SET p.endereco = :endereco, p.cidade.id = :cidade,  p.bairro.id = :bairro  WHERE p.id = :id")
    void updateEndereco(@Param("endereco") String endereco, @Param("cidade") Long cidade, @Param("bairro") Long bairro, @Param("id") Long id);


	@Query("select p from Pessoa p where p.email=:email and p.senha=:senha")
	Pessoa verificarLogin(@Param("email") String email, @Param("senha") String senha);
	
}
