package com.tripshare;

import com.tripshare.repository.UserRepository;
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

	/**
	 *
	 */
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//		userRepository.deleteAll();
	}

}
