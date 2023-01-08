package one.digitalinnovation.labpadroesprojetospring.repositories;

import org.springframework.data.repository.CrudRepository;

import one.digitalinnovation.labpadroesprojetospring.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
  
}
