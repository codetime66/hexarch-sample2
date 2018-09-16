package br.com.stelo.gsurf.token.repositoryadapters.db;

import br.com.stelo.gsurf.token.repositoryadapters.db.entity.TokenModuloTerminalEntity;
import br.com.stelo.gsurf.token.models.TokenModuloTerminal;

public interface ITokenModuloTerminalDAO {
    TokenModuloTerminalEntity getTokenById(Long id);

	boolean update(TokenModuloTerminal tokenModuloTerminal);
}