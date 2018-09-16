package br.com.stelo.gsurf.token.controllers;

import br.com.stelo.gsurf.token.models.ErrorMessage;
import br.com.stelo.gsurf.token.models.TokenGsurf;
import br.com.stelo.gsurf.token.models.TokenModuloTerminal;
import br.com.stelo.gsurf.token.ports.GetTokenService;

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

	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public ResponseEntity getToken() {
		TokenGsurf tmt = getTokenService.getToken();
		if (tmt != null) {
			return new ResponseEntity(tmt, HttpStatus.OK);
		}
		return new ResponseEntity(ErrorMessage.builder().message("token was not found").build(),
				HttpStatus.NOT_FOUND);
	}

}
