package br.com.stelo.gsurf.token.repositoryadapters;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.stelo.gsurf.token.repositoryadapters.db.entity.TokenModuloTerminalEntity;
import br.com.stelo.gsurf.token.repositoryadapters.db.impl.TokenModuloTerminalDAO;
import br.com.stelo.gsurf.token.models.TokenGsurf;
import br.com.stelo.gsurf.token.models.TokenModuloTerminal;
import br.com.stelo.gsurf.token.ports.TokenRepository;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TokenRepositoryAdapter implements TokenRepository {

	private static final Logger log = LoggerFactory.getLogger(TokenRepositoryAdapter.class);
	
	@Value("${gsurf.token.url.srv}")
	private String gsurf_token_url_srv;
	
	@Autowired
	private TokenModuloTerminalDAO tokenModuloTerminalDAO;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public TokenModuloTerminal getTokenById(Long id) {
       TokenModuloTerminalEntity tmtEntity = tokenModuloTerminalDAO.getTokenById(id);
       TokenModuloTerminal tmt = null;
       if(tmtEntity!=null) {
    	   tmt = new TokenModuloTerminal();
    	   BeanUtils.copyProperties(tmtEntity, tmt);
       }
       log.info(tmt.toString());
       return tmt;
	}

	@Override
	public boolean update(TokenModuloTerminal tokenModuloTerminal) {
        return tokenModuloTerminalDAO.update(tokenModuloTerminal);   
	}

	@Override
	public TokenGsurf gerarToken() {
		
		TokenGsurf tokenGsurf = restTemplate.getForObject(
				gsurf_token_url_srv, TokenGsurf.class);
		log.info(tokenGsurf.toString());
		
		return tokenGsurf;
	}

	@Override
	public TokenGsurf getToken() {
		TokenGsurf tokenGsurf = null;
		TokenModuloTerminal tmt = getTokenById(1L);
		if(tokenExpirado(tmt.getDataExpiracao())){
			tokenGsurf = gerarToken();
            update(tokenGsurf.toTokenModuloTerminal());			
		}else {
			tokenGsurf = new TokenGsurf();
			tokenGsurf.setToken(tmt.getCodigoToken());
			tokenGsurf.setExpiration(tmt.getDataExpiracao());
		}
		return tokenGsurf;
	}
	
	@Override
	public boolean tokenExpirado(Date dataExpiracao) {
		return dataExpiracao.before(new Date());
	}
}
