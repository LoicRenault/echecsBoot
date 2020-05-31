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

import echecsBoot.entity.Tournoi;
import echecsBoot.entity.view.Views;
import echecsBoot.repository.TournoiRepository;

@RestController
@RequestMapping("/rest/tournoi")
@CrossOrigin(origins = "*")
public class TournoiRestController {
	
	@Autowired
	private TournoiRepository tournoiRepository;
	
	@JsonView(Views.Common.class)
	@GetMapping({ "", "/" })
	public ResponseEntity<List<Tournoi>> findAll() {
		return new ResponseEntity<List<Tournoi>>(tournoiRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Void> addFormation(@Valid @RequestBody Tournoi tournoi, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		tournoiRepository.save(tournoi);

		URI uri = uCB.path("/rest/tournoi/{id}").buildAndExpand(tournoi.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@JsonView(Views.Common.class)
	public ResponseEntity<Tournoi> findById(@PathVariable("id") Integer id) {
		Optional<Tournoi> opt = tournoiRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Tournoi>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Tournoi> opt = tournoiRepository.findById(id);
		if (opt.isPresent()) {
			tournoiRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Tournoi tournoi, BindingResult br,
			@PathVariable("id") Integer id) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Tournoi> opt = tournoiRepository.findById(id);
		if (opt.isPresent()) {
			Tournoi tournoiEnBase = opt.get();
			tournoiEnBase.setNom(tournoi.getNom());
			tournoiEnBase.setDate(tournoi.getDate());
			tournoiEnBase.setNbRondes(tournoi.getNbRondes());
			tournoiEnBase.setSalle(tournoi.getSalle());
			tournoiEnBase.setFraisInscription(tournoi.getFraisInscription());
			tournoiEnBase.setPrix(tournoi.getPrix());
			tournoiEnBase.setCadence(tournoi.getCadence());
			tournoiEnBase.setArbitre(tournoi.getArbitre());
			tournoiEnBase.setOrganisateur(tournoi.getOrganisateur());
			tournoiEnBase.setParties(tournoi.getParties());
			tournoiEnBase.setParticipations(tournoi.getParticipations());
			tournoiEnBase = tournoiRepository.save(tournoiEnBase);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/nom/{nom}")
	public ResponseEntity<Boolean> checkNom(@PathVariable("nom") String nom) {
		Optional<Tournoi> opt = tournoiRepository.findByNom(nom);
		System.out.println(opt);
		if (opt.isPresent()) {
			return new ResponseEntity<>(false,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(true,HttpStatus.OK);
		}
	}

}
