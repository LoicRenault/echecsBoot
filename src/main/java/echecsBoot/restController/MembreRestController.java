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

import echecsBoot.entity.Membre;
import echecsBoot.entity.view.Views;
import echecsBoot.repository.MembreRepository;

@RestController
@RequestMapping("/rest/membre")
@CrossOrigin(origins = "*")
public class MembreRestController {

	@Autowired
	private MembreRepository membreRepository;

	@JsonView(Views.Common.class)
	@GetMapping({ "", "/" })
	public ResponseEntity<List<Membre>> findAll() {
		return new ResponseEntity<List<Membre>>(membreRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Void> addMembre(@Valid @RequestBody Membre membre, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		membreRepository.save(membre);

		URI uri = uCB.path("/rest/membre/{id}").buildAndExpand(membre.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@JsonView(Views.Common.class)
	public ResponseEntity<Membre> findById(@PathVariable("id") Integer id) {
		Optional<Membre> opt = membreRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Membre>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Membre> opt = membreRepository.findById(id);
		if (opt.isPresent()) {
			membreRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Membre membre, BindingResult br,
			@PathVariable("id") Integer id) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Membre> opt = membreRepository.findById(id);
		if (opt.isPresent()) {
			Membre membreEnBase = opt.get();
			membreEnBase.setPrenom(membre.getPrenom());
			membreEnBase.setNom(membre.getNom());
			membreEnBase.setEmail(membre.getEmail());
			membreEnBase.setElo(membre.getElo());
			membreEnBase.setCivilite(membre.getCivilite());
			membreEnBase.setNationalite(membre.getNationalite());
			membreEnBase.setAdresse(membre.getAdresse());
			membreEnBase.setPartieBlancs(membre.getPartieBlancs());
			membreEnBase.setPartieNoirs(membre.getPartieNoirs());
			membreEnBase.setClub(membre.getClub());
			membreEnBase = membreRepository.save(membreEnBase);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}