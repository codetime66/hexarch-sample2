package br.com.stelo.gsurf.token.controllers;

import br.com.stelo.gsurf.token.models.ErrorMessage;
import br.com.stelo.gsurf.token.models.TokenGsurf;
import br.com.stelo.gsurf.token.models.TokenModuloTerminal;;
import br.com.stelo.gsurf.token.ports.GetTokenService;
import br.com.stelo.gsurf.token.ports.UpdateTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TokenRestController {

	@Autowired
	private GetTokenService getTokenService;

	@Autowired
	private UpdateTokenService updateTokenService;

	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public ResponseEntity getToken() {
		TokenGsurf tmt = getTokenService.getToken();
		if (tmt != null) {
			return new ResponseEntity(tmt, HttpStatus.OK);
		}
		return new ResponseEntity(ErrorMessage.builder().message("token was not found").build(),
				HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/token/{id}", method = RequestMethod.GET)
	public ResponseEntity getToken(@PathVariable Long id) {
		TokenModuloTerminal tmt = getTokenService.getTokenById(id);
		if (tmt != null) {
			
			return new ResponseEntity(tmt, HttpStatus.OK);
		}
		return new ResponseEntity(ErrorMessage.builder().message("token with id " + id + " was not found").build(),
				HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/token", method = RequestMethod.PUT)
	public ResponseEntity updateToken(@Valid @RequestBody TokenModuloTerminal tmt) {
		boolean upd = updateTokenService.update(tmt);
		if (upd) {
			return new ResponseEntity("{\"id\": " + tmt.getIdSolicitacaoToken() + "}", HttpStatus.CREATED);
		}

		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

}
