package echecsBoot.restController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import echecsBoot.entity.Arbitre;
import echecsBoot.entity.view.Views;
import echecsBoot.repository.ArbitreRepository;

@RestController
@RequestMapping("/rest/arbitre")
@CrossOrigin(origins = "*")
public class ArbitreRestController {
	
	@Autowired
	private ArbitreRepository arbitreRepository;
	
	@JsonView(Views.Common.class)
	@GetMapping({ "", "/" })
	public ResponseEntity<List<Arbitre>> findAll() {
		return new ResponseEntity<List<Arbitre>>(arbitreRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Void> addFormation(@Valid @RequestBody Arbitre arbitre, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		arbitreRepository.save(arbitre);

		URI uri = uCB.path("/rest/arbitre/{id}").buildAndExpand(arbitre.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@JsonView(Views.Common.class)
	public ResponseEntity<Arbitre> findById(@PathVariable("id") Integer id) {
		Optional<Arbitre> opt = arbitreRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Arbitre>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Arbitre> opt = arbitreRepository.findById(id);
		if (opt.isPresent()) {
			arbitreRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Arbitre arbitre, BindingResult br,
			@PathVariable("id") Integer id) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Arbitre> opt = arbitreRepository.findById(id);
		if (opt.isPresent()) {
			Arbitre arbitreEnBase = opt.get();
			arbitreEnBase.setPrenom(arbitre.getPrenom());
			arbitreEnBase.setNom(arbitre.getNom());
			arbitreEnBase.setEmail(arbitre.getEmail());
			arbitreEnBase.setNiveau(arbitre.getNiveau());
			arbitreEnBase.setTournois(arbitre.getTournois());
			arbitreEnBase = arbitreRepository.save(arbitreEnBase);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/nom/{nom}")
	public ResponseEntity<Boolean> checkNom(@PathVariable("nom") String nom) {
		Optional<Arbitre> opt = arbitreRepository.findByNom(nom);
		System.out.println(opt);
		if (opt.isPresent()) {
			return new ResponseEntity<>(false,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(true,HttpStatus.OK);
		}
	}

}
