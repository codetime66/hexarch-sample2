package br.com.stelo.gsurf.token.serviceadapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stelo.gsurf.token.models.TokenGsurf;
import br.com.stelo.gsurf.token.models.TokenModuloTerminal;
import br.com.stelo.gsurf.token.ports.GetTokenService;
import br.com.stelo.gsurf.token.ports.TokenRepository;

@Service
public class GetTokenServiceAdapter implements GetTokenService {

	@Autowired
	private TokenRepository repository;

	@Override
	public TokenModuloTerminal getTokenById(Long id) {
		return repository.getTokenById(id);
	}
	
	@Override
	public TokenGsurf getToken() {
		return repository.getToken();
	}
	
	@Override
	public TokenGsurf getRenewToken() {
		return repository.getRenewToken();
	}	
}
