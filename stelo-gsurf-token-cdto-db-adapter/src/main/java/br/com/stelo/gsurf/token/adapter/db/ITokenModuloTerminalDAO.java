package br.com.stelo.gsurf.token.adapter.db;

import br.com.stelo.gsurf.token.adapter.db.entity.TokenModuloTerminalEntity;
import br.com.stelo.gsurf.token.models.TokenModuloTerminal;

public interface ITokenModuloTerminalDAO {
    TokenModuloTerminalEntity getTokenById(Long id);

	boolean update(TokenModuloTerminal tokenModuloTerminal);
}