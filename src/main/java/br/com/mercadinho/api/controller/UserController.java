package br.com.mercadinho.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadinho.api.dto.AccountDTO;
import br.com.mercadinho.api.dto.UserDTO;
import br.com.mercadinho.domain.model.User;
import br.com.mercadinho.domain.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Page<UserDTO> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
		Page<User> listUserPage = userService.getAll(pageable);

		List<UserDTO> usersDTO = listUserPage.getContent().stream().map(UserDTO::new).toList();

		return new PageImpl<>(usersDTO, pageable, listUserPage.getTotalElements());
	}

	@GetMapping("/search")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Page<UserDTO> findAll(@RequestParam String fullName,
			@PageableDefault(page = 0, size = 10) Pageable pageable) {
		Page<User> listUserPage = userService.searchUsersByFullName(fullName, pageable);

		List<UserDTO> usersDTO = listUserPage.getContent().stream().map(UserDTO::new).toList();

		return new PageImpl<>(usersDTO, pageable, listUserPage.getTotalElements());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserDTO findById(@PathVariable Long id) {
		User user = userService.findbyId(id);
		AccountDTO accountDTO = new AccountDTO();

		modelMapper.map(user.getAccount(), accountDTO);
		UserDTO userDTO = new UserDTO(user);
		userDTO.setAccount(accountDTO);

		return userDTO;

	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserDTO updated(@RequestBody UserDTO userDTO, @PathVariable Long id) {
		User user = userService.update(userDTO, id);
		return new UserDTO(user);
	}

}
