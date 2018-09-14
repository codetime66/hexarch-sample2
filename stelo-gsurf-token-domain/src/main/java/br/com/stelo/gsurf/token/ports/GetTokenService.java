package br.com.stelo.gsurf.token.ports;

import br.com.stelo.gsurf.token.models.TokenGsurf;
import br.com.stelo.gsurf.token.models.TokenModuloTerminal;

public interface GetTokenService {
	TokenModuloTerminal getTokenById(Long id);

	TokenGsurf getToken();
}
