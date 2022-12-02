package edu.ibero.spring.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ibero.spring.entity.Usuarios;
import edu.ibero.spring.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {
	
	@Autowired
	private UserService userservice;

	//create new user
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Usuarios user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userservice.save(user));
	}
	
	//leer usuario por id
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long id){
		Optional<Usuarios> oUser= userservice.findById(id);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(oUser);
		}				
	}
	
	//actualizar datos por id
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@RequestBody Usuarios userdetails,@PathVariable(value = "id") Long id){
		Optional<Usuarios> oUser= userservice.findById(id);
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}else {
			//objeto por detalles
			oUser.get().setNombres(userdetails.getNombres());
			oUser.get().setApellidos(userdetails.getApellidos());
			oUser.get().setNombrecompleto(userdetails.getNombrecompleto());
			oUser.get().setDireccion(userdetails.getDireccion());
			oUser.get().setCiudad(userdetails.getCiudad());
			oUser.get().setCorreo(userdetails.getCorreo());
			oUser.get().setEstado(userdetails.getEstado());
			
			/*para todo el objeto completo.
			BeanUtils.copyProperties(userdetails, oUser.get());*/
			
			return ResponseEntity.status(HttpStatus.CREATED).body(userservice.save(oUser.get()));
		}		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
		
		if(!userservice.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}else {
			userservice.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
	}
	
	//Leer todos los usuarios
	@GetMapping("/userAll")
	public List<Usuarios> readAll(){
		List<Usuarios> users = StreamSupport.stream(userservice.findAll().spliterator(), false).collect(Collectors.toList());
		return users;
	}
}
