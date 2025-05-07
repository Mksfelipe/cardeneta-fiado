package br.com.mercadinho.domain.service;

import java.util.HashSet;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mercadinho.api.dto.UserDTO;
import br.com.mercadinho.domain.exception.RoleException;
import br.com.mercadinho.domain.exception.UserExistCpfException;
import br.com.mercadinho.domain.exception.UserNotFoundException;
import br.com.mercadinho.domain.model.Account;
import br.com.mercadinho.domain.model.ERole;
import br.com.mercadinho.domain.model.Role;
import br.com.mercadinho.domain.model.User;
import br.com.mercadinho.domain.payload.request.SignupRequest;
import br.com.mercadinho.domain.repository.RoleRepository;
import br.com.mercadinho.domain.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service("clientService")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Page<User> getAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Page<User> searchUsersByFullName(String fullName, Pageable pageable) {
		return userRepository.searchByFullName(fullName, pageable);
	}

	public User findbyId(Long id) {
		return findByUser(id);
	}

	public User findbyCpf(String cpf) {
		return findByUserCpf(cpf);
	}

	@Transactional
	public User update(UserDTO userDTO, Long id) {
		User userAtual = findByUser(id);

		BeanUtils.copyProperties(userDTO, userAtual, "id", "account", "cpf");

		return userRepository.save(userAtual);
	}

	@Transactional
	public User registerUser(SignupRequest signUpRequest) {


		if (Boolean.TRUE.equals(userRepository.existsByCpf(signUpRequest.getCpf()))) {
			throw new UserExistCpfException("Error: Cpf is already taken!");
		}

		User user = new User();
		mapper.map(signUpRequest, user);
		
		String cpfLimpo = user.getCpf().replace(".", "").replace("-", "");
		String password = user.getFirstName().substring(0, 2).toLowerCase() + cpfLimpo.substring(0, 4);
		System.out.println(password);
		user.setPassword(passwordEncoder.encode(password));
		
		Account account = new Account();
		user.setAccount(account);

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RoleException("Error: Role is not found: " + ERole.ROLE_ADMIN));
					roles.add(adminRole);

					break;
				case "user":
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RoleException("Error: Role is not found: " + ERole.ROLE_USER));
					roles.add(userRole);

					break;
				default:
					throw new RoleException("Role not find: " + role);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return user;
	}

	private User findByUser(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(String.format("user not found by ID: %d", id)));
	}

	private User findByUserCpf(String cpf) {
		return userRepository.findByCpf(cpf)
				.orElseThrow(() -> new UserNotFoundException(String.format("user not found by CPF: %s", cpf)));
	}

}
