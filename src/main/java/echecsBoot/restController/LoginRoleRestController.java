package echecsBoot.restController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/role")
@CrossOrigin(origins = "*")
public class LoginRoleRestController {

	@GetMapping("")
	public ResponseEntity<Void> role() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
