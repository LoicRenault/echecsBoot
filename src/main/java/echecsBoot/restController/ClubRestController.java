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

import echecsBoot.entity.Club;
import echecsBoot.entity.view.Views;
import echecsBoot.repository.ClubRepository;

@RestController
@RequestMapping("/rest/club")
@CrossOrigin(origins = "*")
public class ClubRestController {

	@Autowired
	private ClubRepository clubRepository;
	
	@JsonView(Views.Common.class)
	@GetMapping({ "", "/" })
	public ResponseEntity<List<Club>> findAll() {
		return new ResponseEntity<List<Club>>(clubRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Void> addFormation(@Valid @RequestBody Club club, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		clubRepository.save(club);

		URI uri = uCB.path("/rest/club/{id}").buildAndExpand(club.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@JsonView(Views.Common.class)
	public ResponseEntity<Club> findById(@PathVariable("id") Integer id) {
		Optional<Club> opt = clubRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Club>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Club> opt = clubRepository.findById(id);
		if (opt.isPresent()) {
			clubRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Club club, BindingResult br,
			@PathVariable("id") Integer id) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Club> opt = clubRepository.findById(id);
		if (opt.isPresent()) {
			Club clubEnBase = opt.get();
			clubEnBase.setNom(club.getNom());
			clubEnBase.setAdresse(club.getAdresse());
			clubEnBase.setMembres(club.getMembres());
			clubEnBase.setTournois(club.getTournois());
			clubEnBase = clubRepository.save(clubEnBase);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/nom/{nom}")
	public ResponseEntity<Boolean> checkNom(@PathVariable("nom") String nom) {
		Optional<Club> opt = clubRepository.findByNom(nom);
		System.out.println(opt);
		if (opt.isPresent()) {
			return new ResponseEntity<>(false,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(true,HttpStatus.OK);
		}
	}

}
