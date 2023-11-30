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

import edu.cibertec.dto.ProductoDTO;
import edu.cibertec.model.Producto;
import edu.cibertec.repository.ProductoRepository;
import edu.cibertec.request.ProductoRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("productos")
public class ProductoController {
	ProductoRepository productoRepository;
	
	@GetMapping
	public List<ProductoDTO>list(){
		return productoRepository.obtenerProductoDTO();
	}
	@GetMapping("all")
	public List<Producto>findAll(){
		return productoRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Producto>findById(@PathVariable Long id){
		return ResponseEntity.of(productoRepository.findById(id));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Producto register(@RequestBody @Valid ProductoRequest productoRequest) {
		Producto producto = new Producto();
		producto.modelo = productoRequest.modelo;
		producto.descipcion = productoRequest.descipcion;
		producto.precio = productoRequest.precio;
		producto.stock = productoRequest.stock;
		producto.marca = productoRequest.marca;
		Producto productoSave = productoRepository.save(producto);		
		return productoSave;
	}
	/*@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		productoRepository.deleteById(id);
	}*/
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
	    if (productoRepository.existsById(id)) {
	        productoRepository.deleteById(id);
	        return ResponseEntity.ok("Producto eliminado correctamente");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
	    }
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Producto>update(@PathVariable Long id, @RequestBody @Valid ProductoRequest productoRequest){
		return productoRepository.findById(id).map(producto -> {
			producto.modelo = productoRequest.modelo;
			producto.descipcion = productoRequest.descipcion;
			producto.precio = productoRequest.precio;
			producto.stock = productoRequest.stock;
			producto.marca = productoRequest.marca;
			productoRepository.save(producto);
			return ResponseEntity.ok(producto);
		}).orElseGet(()-> ResponseEntity.notFound().build());
	}
}
