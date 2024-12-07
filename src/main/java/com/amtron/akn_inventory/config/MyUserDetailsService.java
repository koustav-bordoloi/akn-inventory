package com.amtron.akn_inventory.config;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.amtron.akn_inventory.model.user.User;
import com.amtron.akn_inventory.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> optionalUser = userRepository.findByUsername(username);

		if (!optionalUser.isPresent()) {
			optionalUser = userRepository.findByMobileNo(username);
		}
		if (!optionalUser.isPresent()) {
			optionalUser = userRepository.findByEmail(username);
		}

		if (!optionalUser.isPresent()) {
			log.error("Could not find user with that username: {}", username);
			throw new UsernameNotFoundException("Invalid credentials!");
		}

		User user = optionalUser.get();

		log.debug("Provided username : {}, Found username : {}", username, user.getUsername());

		Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet());

		return new MySecurityUser(user.getUsername(), user.getPassword(), user.getEnabled(),
				true, true, true,
				grantedAuthorities, user.getFullName(), user.getEmail(), user.getMobileNo());
	}

}
