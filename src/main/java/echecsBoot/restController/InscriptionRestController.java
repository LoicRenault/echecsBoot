package echecsBoot.restController;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import echecsBoot.entity.Login;
import echecsBoot.entity.LoginRole;
import echecsBoot.entity.Role;
import echecsBoot.repository.LoginRepository;
import echecsBoot.repository.MembreRepository;
import echecsBoot.repository.RoleRepository;

@RestController
@RequestMapping("/rest/inscription")
@CrossOrigin(origins = "*")
public class InscriptionRestController {
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MembreRepository membreRepository;

	@PostMapping({ "", "/" })
	public ResponseEntity<Void> inscription(@Valid @RequestBody Login login, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Login> opt = loginRepository.findById(login.getLogin());
		if (opt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		login.setPassword(passwordEncoder.encode(login.getPassword()));
		login.setEnable(true);
		membreRepository.save(login.getMembre());
		loginRepository.save(login);
		LoginRole loginRole = new LoginRole();
		loginRole.setLogin(login);
		loginRole.setRole(Role.ROLE_USER);
		roleRepository.save(loginRole);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/{login}")
	public ResponseEntity<Boolean> disponibilite(@PathVariable("login") String login) {
		Optional<Login> opt = loginRepository.findById(login);
		if (opt.isPresent()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

}
