package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.example.demo.model.Track;
import com.example.demo.model.User;
import com.example.demo.service.TrackService;
import com.example.demo.service.TrackService;
import com.example.demo.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/register")
public class UserController {
	@Autowired
	private UserService service;

	@RequestMapping(value = "/register/**", method = RequestMethod.OPTIONS)
	public void corsHeaders(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
		response.addHeader("Access-Control-Max-Age", "3600");
	}

	@RequestMapping("/addRegister")
	public ResponseEntity<User> addRegister(@RequestBody User user) {
		service.addRegister(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

//	@PostMapping("/login")
//	   public ResponseEntity<?> validate(HttpSession session,@RequestBody User user)throws UserNotFoundException {
////             String view = "error";
//	       User found = service.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
//	       if (found != null) {
////	           session.setAttribute("emailId", user.getEmailId());
////	           return new ResponseEntity<User>(user,HttpStatus.OK);
////	            throw new UserNotFoundException("User Not Found");
//		       return new ResponseEntity<User>(found,HttpStatus.UNAUTHORIZED);	     
//
//	       } else {
//	    	   String jwtToken= generateToken(user);
////	       return new ResponseEntity<User>(user,HttpStatus.UNAUTHORIZED);
//	       found.setToken(jwtToken);
//    	   return new ResponseEntity<User>(user,HttpStatus.UNAUTHORIZED);
//	       }}
//
//	private String generateToken(User user) {
//        String jwtToken=
//        		Jwts.builder().setId(user.getEmailId()).
//        		setSubject(user.getEmailId()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
//		return jwtToken;
//	}
	@PutMapping("/updatePassword/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable String id, @RequestBody User user ){
        service.updatePassword(user, id);
        return new ResponseEntity<User>(user,HttpStatus.OK);
        
    }

//	@PutMapping("/updatepassword/{id}")
//	public ResponseEntity<User> updatepassword(@PathVariable String id, @RequestBody User user)
//			throws UserNotFoundException {
//		service.updatepassword(id, user);
//		return new ResponseEntity<User>(HttpStatus.OK);
//	}

//	@PutMapping("/updatepassword/{id}")
//    public ResponseEntity<User> updatepassword(@PathVariable String id,@RequestBody User user)
//    {
//        try
//        {
//        service.updatepassword(id,user);
//        return new ResponseEntity<User>(HttpStatus.OK);
//        }
//        catch(UserNotFoundException e)
//        {
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//        }
//    }
//		
	@PostMapping("/login")
	public ResponseEntity<?> validate(HttpSession session, @RequestBody User user) throws UserNotFoundException {
		// String view = "error";
		User found = service.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
		if (found != null) {
//            session.setAttribute("username", user.getUserName());
			String jwttoken = generateToken(user);
			user.setToken(jwttoken);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else
			return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
	}

	private String generateToken(User user){
		String jwttoken = Jwts.builder().setId(user.getEmailId()).setSubject(user.getEmailId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
		return jwttoken;
	}

}
