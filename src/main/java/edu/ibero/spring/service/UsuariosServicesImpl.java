package edu.ibero.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ibero.spring.entity.Usuarios;
import edu.ibero.spring.repository.UsuariosRepository;

@Service
public class UsuariosServicesImpl implements UserService{

	@Autowired 
	private UsuariosRepository userrepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Usuarios> findAll() {
		return userrepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuarios> findAll(Pageable pageable) {
		return userrepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuarios> findById(Long id) {
		return userrepository.findById(id);
	}

	@Override
	@Transactional
	public Usuarios save(Usuarios user) {
		return userrepository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userrepository.deleteById(id);
	}

}
