package br.com.stelo.gsurf.token.ports;

import java.util.Date;

import br.com.stelo.gsurf.token.models.TokenGsurf;
import br.com.stelo.gsurf.token.models.TokenModuloTerminal;

public interface TokenRepository {
    TokenGsurf gerarToken();	
    TokenModuloTerminal getTokenById(Long id);
    boolean update(TokenModuloTerminal tokenModuloTerminal);
	TokenGsurf getToken();
	boolean tokenExpirado(Date dataExpiracao);
	TokenGsurf getRenewToken();
}
