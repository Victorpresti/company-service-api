/*
--+
    | Project ACCOUNT SERVICE API - Java Class File : 1.0.0 Data: 10/06/2018
    | Copyright(c) by ProfitCode IT Solutions
    |
    | All rights reserved.
    |
    | This software is confidential and proprietary information of
    | ProfitCode IT Solutions ("Confidential Information").
    | You shall not disclose such Confidential Information and shall 
    | use it only in accordance with the terms of the license agreement
    | you entered with ProfitCode IT Solutions.
 +--
 */
package br.com.solutis.piloto.endpoint;

import br.com.solutis.piloto.entity.User;
import br.com.solutis.piloto.published.APIGenericResponse;
import br.com.solutis.piloto.published.StatusResponse;
import br.com.solutis.piloto.service.UserService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author ProfitCode IT Solutions
 *
 * User Rest Web Service End Point.
 *
 */

@RestController
@Log4j2
public class UserRestWsEndpoint {

	/**
	 * Set services. 
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * End points definitions. 
	 */	
	@PostMapping(value = "/public/create/user", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> create (@RequestBody User user) { 
		
		try {
			return ResponseEntity.ok(new APIGenericResponse(userService.create(user), new StatusResponse("Request API is successfully", HttpStatus.CREATED)));
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIGenericResponse(false, new StatusResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
		}		
	}
	
	@GetMapping(value = "/public/read/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> read (@PathVariable(value = "id") Long id) { 
		
		try {
			Optional<User> user = userService.read(id);
			if (user.isPresent()) {
				return ResponseEntity.ok(new APIGenericResponse(user.get(), new StatusResponse("Request API is successfully", HttpStatus.OK)));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIGenericResponse(null, new StatusResponse("User not found.", HttpStatus.NOT_FOUND)));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIGenericResponse(null, new StatusResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
		}		
	}
	
	@PutMapping(value = "/public/update/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> update (@PathVariable(value = "id") Long id,  @Valid @RequestBody User detail) { 
		
		try {
			User user = userService.update(id, detail);
			if (user == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIGenericResponse(user, new StatusResponse("User not found.", HttpStatus.NOT_FOUND)));
			}
			return ResponseEntity.ok(new APIGenericResponse(user, new StatusResponse("Request API is successfully", HttpStatus.OK)));
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIGenericResponse(null, new StatusResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
		}		
	}
	
	@DeleteMapping(value = "/public/delete/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> delete (@PathVariable(value = "id") Long id) {

		try {
			Boolean deleted = userService.delete(id);
			if (deleted) {
				return ResponseEntity.ok(new APIGenericResponse(deleted, new StatusResponse("Request API is successfully", HttpStatus.OK)));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIGenericResponse(deleted, new StatusResponse("User not found.", HttpStatus.NOT_FOUND)));
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIGenericResponse(null, new StatusResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
		}		
	}
}
