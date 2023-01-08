package one.digitalinnovation.labpadroesprojetospring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.labpadroesprojetospring.models.Cliente;
import one.digitalinnovation.labpadroesprojetospring.services.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {
  @Autowired
  private ClienteService clienteService;

  @GetMapping
  public ResponseEntity<Iterable<Cliente>> findAll() {
    return ResponseEntity.ok(clienteService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> findOne(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(clienteService.findOne(id));
    } catch(NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Cliente> insert(@RequestBody Cliente cliente) {
    clienteService.insert(cliente);
    return ResponseEntity.ok(cliente);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
    try {
      clienteService.update(id, cliente);
      return ResponseEntity.ok(cliente);
    } catch(NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    try {
      clienteService.delete(id);
      return ResponseEntity.ok().build();
    } catch(NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
