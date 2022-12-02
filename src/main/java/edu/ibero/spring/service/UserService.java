package edu.ibero.spring.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.ibero.spring.entity.Usuarios;

public interface UserService {
	
	public Iterable<Usuarios> findAll();
	
	public Page<Usuarios> findAll(Pageable pageable);
	
	public Optional<Usuarios>findById(Long id);
	
	public Usuarios save(Usuarios user);
	
	public void deleteById(Long id);

}
