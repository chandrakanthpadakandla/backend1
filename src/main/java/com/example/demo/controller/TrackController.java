package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Track;
import com.example.demo.service.TrackService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/track")
public class TrackController {
	@Autowired
	private TrackService service1;

	@RequestMapping(value = "/track/**", method = RequestMethod.OPTIONS)
	public void corsHeaders(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
		response.addHeader("Access-Control-Max-Age", "3600");
	}

	@PostMapping("/addTrack")

	public ResponseEntity<?> saveTrack(@RequestBody Track track) {

		service1.addTrack(track);

		return new ResponseEntity<Track>(track, HttpStatus.CREATED);

	}

//	@GetMapping("/getTrack")
//
//	public ResponseEntity<?> getAllTracks() {
//
//		return new ResponseEntity<List<Track>>(service1.getAllTracks(), HttpStatus.OK);
//
//	}

//	@GetMapping("/trackById/{id}")
//
//	public ResponseEntity<?> getTracksById(@RequestParam("id") int id) {
//
//		return new ResponseEntity<Optional<Track>>(service1.getTracksById(id), HttpStatus.OK);
//
//	}

	@DeleteMapping("/deleteTrack/{id}")
	public ResponseEntity<Track> deleteTrack(@PathVariable int id) {
		service1.deleteTrack(id);
		return new ResponseEntity<Track>(HttpStatus.OK);
	}

	@GetMapping("/displayTrack/{emailId}")
	public ResponseEntity<List<Track>> displayAllTracks(@PathVariable String emailId) throws UserNotFoundException {

		List<Track> userTrack = service1.findByEmailId(emailId);
		// List<Recommend> recommendList=service.displayAllRecommends();

		return new ResponseEntity<List<Track>>(userTrack, HttpStatus.OK);

	}
//
//	@PutMapping("/update")
//
//	public ResponseEntity<?> updateTrack(@RequestBody Track track) {
//
//		service1.update(track);
//
//		return new ResponseEntity<String>("Updated", HttpStatus.CREATED);
//
//	}

}
