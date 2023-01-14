package com.tripshare;

import com.tripshare.dto.UserDTO;
import com.tripshare.repository.UserRepository;
import com.tripshare.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 *
 */
@Component
public class Bootstrap {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	CacheService cacheService;
	/**
	 *
	 */
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername("admin");
		cacheService.put("user", "admin", userDTO);
		UserDTO o = (UserDTO) cacheService.get("user", "admin");
		System.out.println(o);
//		userRepository.deleteAll();
	}

}
