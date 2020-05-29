package echecsBoot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import echecsBoot.entity.Login;
import echecsBoot.repository.LoginRepository;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Login> opt = loginRepository.findByIdWithRoles(username);
		if (!opt.isPresent()) {
			throw new UsernameNotFoundException("utilisateur inconnu");
		}
		Utilisateur user = new Utilisateur(opt.get());
		return user;
	}

}
