package one.digitalinnovation.labpadroesprojetospring.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import one.digitalinnovation.labpadroesprojetospring.models.Cliente;
import one.digitalinnovation.labpadroesprojetospring.models.Endereco;
import one.digitalinnovation.labpadroesprojetospring.repositories.ClienteRepository;
import one.digitalinnovation.labpadroesprojetospring.repositories.EnderecoRepository;
import one.digitalinnovation.labpadroesprojetospring.services.ClienteService;
import one.digitalinnovation.labpadroesprojetospring.services.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {
  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private EnderecoRepository enderecoRepository;
  @Autowired
  private ViaCepService viaCepService;

  @Override
  public Iterable<Cliente> findAll() {
    return clienteRepository.findAll();
  }

  @Override
  public Cliente findOne(Long id) throws NotFoundException {
    Optional<Cliente> cliente = clienteRepository.findById(id);
    if (!cliente.isPresent()) throw new NotFoundException();
    return clienteRepository.findById(id).get();
  }

  @Override
  public void insert(Cliente cliente) {
    String cep = cliente.getEndereco().getCep();
    Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
      // If not exists request 'ViaCEP'
      Endereco newEndereco = viaCepService.consultarCep(cep);
      enderecoRepository.save(newEndereco);
      return newEndereco;
    });
    cliente.setEndereco(endereco);
    clienteRepository.save(cliente);
  }

  @Override
  public void update(Long id, Cliente cliente) throws NotFoundException {
    Optional<Cliente> resultCliente = clienteRepository.findById(id);
    if (!resultCliente.isPresent()) throw new NotFoundException();
    String cep = cliente.getEndereco().getCep();
    Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
      // If not exists request 'ViaCEP'
      Endereco newEndereco = viaCepService.consultarCep(cep);
      enderecoRepository.save(newEndereco);
      return newEndereco;
    });
    cliente.setEndereco(endereco);
    clienteRepository.save(cliente);
  }

  @Override
  public void delete(Long id) throws NotFoundException {
    if (!clienteRepository.findById(id).isPresent()) throw new NotFoundException();
    clienteRepository.deleteById(id);
  }  
}
