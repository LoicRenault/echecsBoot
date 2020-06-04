package echecsBoot.restController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import echecsBoot.entity.Login;
import echecsBoot.entity.view.Views;
import echecsBoot.repository.LoginRepository;

@RestController
@RequestMapping("/rest/login")
@CrossOrigin(origins = "*")
public class LoginRestController {

	@Autowired
	private LoginRepository loginRepository;

	@GetMapping("")
	public ResponseEntity<Void> login() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@JsonView(Views.Common.class)
	public ResponseEntity<Login> findByIdWithRoles(@PathVariable("id") String id) {
		Optional<Login> opt = loginRepository.findByIdWithRoles(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Login>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

}
