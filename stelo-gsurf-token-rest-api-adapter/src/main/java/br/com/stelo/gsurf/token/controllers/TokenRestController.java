package br.com.stelo.gsurf.token.controllers;

import br.com.stelo.gsurf.token.models.ErrorMessage;
import br.com.stelo.gsurf.token.models.TokenGsurf;
import br.com.stelo.gsurf.token.ports.GetTokenService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gsurf-token")
@CrossOrigin
public class TokenRestController {

	@Autowired
	private GetTokenService getTokenService;

    @GetMapping
	public ResponseEntity getToken(@RequestParam(name="renew", required=false) Boolean renewToken) {
		TokenGsurf tmt = ( renewToken!=null && renewToken ) 
				? getTokenService.getRenewToken()
				: getTokenService.getToken();
		if (tmt != null) {
			return new ResponseEntity(tmt, HttpStatus.OK);
		}
		return new ResponseEntity(ErrorMessage.builder().message("token was not found").build(),
				HttpStatus.NOT_FOUND);
	}

}
