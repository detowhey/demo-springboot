package com.henriquealmeida.democrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henriquealmeida.democrud.entities.User;
import com.henriquealmeida.democrud.repositories.UserRepository;
import com.henriquealmeida.democrud.services.exception.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert (User obj) {
		return repository.save(obj);
	}
	
	public void delete (Long id) {
		repository.deleteById(id);
	}
	
	public User update (Long id, User obj) {
		// the getOne method is more efficient because it only prepares the object. Without accessing the database
		User entity = repository.getOne(id);
		updateData(entity,obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
}
