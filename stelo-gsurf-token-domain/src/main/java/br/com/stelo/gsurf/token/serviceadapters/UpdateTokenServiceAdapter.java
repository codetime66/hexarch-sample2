package br.com.stelo.gsurf.token.serviceadapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stelo.gsurf.token.models.TokenModuloTerminal;
import br.com.stelo.gsurf.token.ports.UpdateTokenService;
import br.com.stelo.gsurf.token.ports.TokenRepository;

@Service
public class UpdateTokenServiceAdapter implements UpdateTokenService {

	@Autowired
	private TokenRepository repository;

	@Override
	public boolean update(TokenModuloTerminal tmt) {
       return repository.update(tmt);		
	}
		
}
