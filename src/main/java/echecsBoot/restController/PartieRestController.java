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

import echecsBoot.entity.Partie;
import echecsBoot.entity.view.Views;
import echecsBoot.repository.PartieRepository;

@RestController
@RequestMapping("/rest/partie")
@CrossOrigin(origins = "*")
public class PartieRestController {
	
	@Autowired
	private PartieRepository partieRepository;
	
	@JsonView(Views.Common.class)
	@GetMapping({ "", "/" })
	public ResponseEntity<List<Partie>> findAll() {
		return new ResponseEntity<List<Partie>>(partieRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Void> addFormation(@Valid @RequestBody Partie partie, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		partieRepository.save(partie);

		URI uri = uCB.path("/rest/partie/{id}").buildAndExpand(partie.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@JsonView(Views.Common.class)
	public ResponseEntity<Partie> findById(@PathVariable("id") Integer id) {
		Optional<Partie> opt = partieRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Partie>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Partie> opt = partieRepository.findById(id);
		if (opt.isPresent()) {
			partieRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Partie partie, BindingResult br,
			@PathVariable("id") Integer id) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Partie> opt = partieRepository.findById(id);
		if (opt.isPresent()) {
			Partie partieEnBase = opt.get();
			partieEnBase.setJoueurBlancs(partie.getJoueurBlancs());
			partieEnBase.setJoueurNoirs(partie.getJoueurNoirs());
			partieEnBase.setCadence(partie.getCadence());
			partieEnBase.setResultBlancs(partie.getResultBlancs());
			partieEnBase.setResultNoirs(partie.getResultNoirs());
			partieEnBase.setDatePartie(partie.getDatePartie());
			partieEnBase.setPartieTournoi(partie.getPartieTournoi());
			partieEnBase = partieRepository.save(partieEnBase);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/nom/{nom}")
	public ResponseEntity<Boolean> checkNom(@PathVariable("nom") String nom) {
		Optional<Partie> opt = partieRepository.findByNom(nom);
		System.out.println(opt);
		if (opt.isPresent()) {
			return new ResponseEntity<>(false,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(true,HttpStatus.OK);
		}
	}
	
	

}
