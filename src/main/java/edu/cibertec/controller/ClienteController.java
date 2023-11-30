package edu.cibertec.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.cibertec.dto.ClienteDTO;
import edu.cibertec.model.Cliente;
import edu.cibertec.repository.ClienteRepository;
import edu.cibertec.request.ClienteRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("clientes")
public class ClienteController {
	
	ClienteRepository clienteRepository;
	
	@GetMapping
	public List<ClienteDTO> list(){
		return clienteRepository.obtenerClienteDTO();
	}
	
	@GetMapping("name")// ResponseEntity<?> = el tipo de dato puede ser cualquiera
	public ResponseEntity<?> findName(String nombre){
		if(nombre != null) {
			List<Cliente> clientes = clienteRepository.buscarClienteNombre(nombre);
			//
			if (!clientes.isEmpty()) {
				return ResponseEntity.ok(clientes);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no registrado");
			}
		}
		return ResponseEntity.ok(clienteRepository.findAll());
	}
	
	@GetMapping("all")
	public List<Cliente>findAll(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Cliente>findbyId(@PathVariable Long id){
		return ResponseEntity.of(clienteRepository.findById(id));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente register(@RequestBody @Valid ClienteRequest clienteRequest) {
		Cliente cli = new Cliente();	
		cli.nombre = clienteRequest.nombre;
		cli.apellido = clienteRequest.apellido;
		cli.direccion = clienteRequest.direccion;
		cli.telefono = clienteRequest.telefono;
		cli.email = clienteRequest.email;
		cli.password = clienteRequest.password;
		Cliente clienteSave= clienteRepository.save(cli);
		return clienteSave;
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
	    if (clienteRepository.existsById(id)) {
	        clienteRepository.deleteById(id);
	        return ResponseEntity.ok("Cliente eliminado correctamente");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
	    }
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody @Valid ClienteRequest clienteRequest){
		return clienteRepository.findById(id).map(cli ->{
			cli.nombre = clienteRequest.nombre;
			cli.apellido = clienteRequest.apellido;
			cli.direccion = clienteRequest.direccion;
			cli.telefono = clienteRequest.telefono;
			cli.email = clienteRequest.email;
			cli.password = clienteRequest.password;
			clienteRepository.save(cli);
			return ResponseEntity.ok(cli);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

}
