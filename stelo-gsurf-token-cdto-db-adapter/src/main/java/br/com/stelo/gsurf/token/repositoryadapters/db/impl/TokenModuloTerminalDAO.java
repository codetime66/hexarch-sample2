package br.com.stelo.gsurf.token.repositoryadapters.db.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.stelo.gsurf.token.repositoryadapters.db.ITokenModuloTerminalDAO;
import br.com.stelo.gsurf.token.repositoryadapters.db.entity.TokenModuloTerminalEntity;
import br.com.stelo.gsurf.token.models.TokenModuloTerminal;

@Transactional
@Repository
public class TokenModuloTerminalDAO implements ITokenModuloTerminalDAO {

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public TokenModuloTerminalEntity getTokenById(Long id) {
		return entityManager.find(TokenModuloTerminalEntity.class, id);
	}

	@Override
	public boolean update(TokenModuloTerminal tokenModuloTerminal) {
		boolean update = false;
		TokenModuloTerminalEntity tmtEntity = getTokenById(tokenModuloTerminal.getIdSolicitacaoToken());
		if(tmtEntity!=null) {
			tmtEntity.setCodigoToken(tokenModuloTerminal.getCodigoToken());
			tmtEntity.setDataSolicitacao(tokenModuloTerminal.getDataSolicitacao());
			tmtEntity.setDataExpiracao(tokenModuloTerminal.getDataExpiracao());
			tmtEntity.setStatusToken(tokenModuloTerminal.getStatusToken());
			update=true;
			entityManager.flush();
		}
		return update;		
	}

}
