package one.digitalinnovation.labpadroesprojetospring.services;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import one.digitalinnovation.labpadroesprojetospring.models.Cliente;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com 
 * isso, se necessário, podemos ter multiplas implementações dessa mesma 
 * interface.
 * 
 * @author wallasmaciel
 */
public interface ClienteService {
  Iterable<Cliente> findAll();
  Cliente findOne(Long id) throws NotFoundException;
  void insert(Cliente cliente);
  void update(Long id, Cliente cliente) throws NotFoundException;
  void delete(Long id) throws NotFoundException;
}
