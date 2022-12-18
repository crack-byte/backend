package com.tripshare.service;

import com.tripshare.dto.UserDTO;
import com.tripshare.entity.User;
import com.tripshare.repository.UserRepository;
import com.tripshare.dto.PaginationDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	/**
	 * @param userDTO
	 * @return
	 */
	public UserDTO createUser(UserDTO userDTO) {
		User user = new User(userDTO);
		return new UserDTO(userRepository.save(user));
	}

	/**
	 * @param limit
	 * @param page
	 * @return
	 */
	public PaginationDTO<UserDTO> findAll(int limit, int page) {
		Page<User> usersPage = userRepository.findAll(Pageable.ofSize(limit).withPage(page));
		return new PaginationDTO<>(usersPage
				.stream()
				.map(UserDTO::new)
				.collect(Collectors.toList()), page + 1, (int) usersPage.getTotalElements());
	}

}
