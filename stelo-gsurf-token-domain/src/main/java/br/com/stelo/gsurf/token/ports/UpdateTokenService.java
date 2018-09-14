package br.com.stelo.gsurf.token.ports;

import br.com.stelo.gsurf.token.models.TokenModuloTerminal;

public interface UpdateTokenService {
   boolean update(TokenModuloTerminal tmt);
}
