package com.nelioalves.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll (){
		return userRepository.findAll();
	}
	
	public User findById (String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow( ()-> new ObjectNotFoundException("Objeto não encontrato"));
	}
	
	public User insert(User user) {
		return userRepository.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User user) {
		User userNew = findById(user.getId());
        updateData(userNew, user);
		return userRepository.save(userNew);
		
	}
	
	private void updateData(User userNew, User user) {
		userNew.setName(user.getName());
		userNew.setEmail(user.getEmail());		
	}

	public User fromDTO (UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
}
