package br.com.mercadinho.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mercadinho.domain.model.User;
import br.com.mercadinho.domain.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	
	@Override
	@Transactional
	public UserDetails  loadUserByUsername(String cpf) throws UsernameNotFoundException, DataAccessException {
		User user = userRepository.findByCpf(cpf)
		        .orElseThrow(() -> new UsernameNotFoundException("Client not found"));
		
		return UserDetailsImpl.build(user);

	}
}