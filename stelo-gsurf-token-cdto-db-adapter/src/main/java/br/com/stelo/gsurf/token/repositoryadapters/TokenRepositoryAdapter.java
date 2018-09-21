package br.com.stelo.gsurf.token.repositoryadapters;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Value("${gsurf_ws_key}")
    private String gsurf_ws_key;
    
    @Autowired
    private TokenModuloTerminalDAO tokenModuloTerminalDAO;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public TokenModuloTerminal getTokenById(Long id) {
        TokenModuloTerminalEntity tmtEntity = tokenModuloTerminalDAO.getTokenById(id);
        TokenModuloTerminal tmt = null;
        if (tmtEntity != null) {
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
    	
    	log.info("TokenRepositoryAdapter.gerarToken(): gsurf_token_url_srv: "+gsurf_token_url_srv);
    	log.info("TokenRepositoryAdapter.gerarToken(): gsurf_ws_key: "+gsurf_ws_key);
    	
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //headers.add("ws_key", "5a366530-f804-4651-aafb-a84d6dca0220");
        headers.add("ws_key", gsurf_ws_key );
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<TokenGsurf> tokenGsurfEntity = restTemplate.exchange(gsurf_token_url_srv, HttpMethod.GET, entity, TokenGsurf.class, 100);
        log.info("TokenRepositoryAdapter.gerarToken(): tokenGsurfEntity.getStatusCode() :"+tokenGsurfEntity.getStatusCode());
        log.info("TokenRepositoryAdapter.gerarToken(): tokenGsurfEntity.getBody().getExpiration() : "+tokenGsurfEntity.getBody().getExpiration());
        if (tokenGsurfEntity.getStatusCode() == HttpStatus.OK) {
        	log.info("TokenRepositoryAdapter.gerarToken(): tokenGsurfEntity.getBody().getToken()="+tokenGsurfEntity.getBody().getToken());
        	return tokenGsurfEntity.getBody();
		} else {
			log.info("TokenRepositoryAdapter.gerarToken(): null");
			return null;
		}         	
    }
    
    @Override
    public TokenGsurf getToken() {
        TokenGsurf tokenGsurf = null;
        TokenModuloTerminal tmt = getTokenById(1L);
        if (tokenExpirado(tmt.getDataExpiracao())) {
            tokenGsurf = gerarToken();
            update(tokenGsurf.toTokenModuloTerminal());
        } else {
            tokenGsurf = new TokenGsurf();
            tokenGsurf.setToken(tmt.getCodigoToken());
            tokenGsurf.setExpiration(tmt.getDataExpiracao());
        }
        return tokenGsurf;
    }

    @Override
    public boolean tokenExpirado(Date dataExpiracao) {
    	log.info("### tokenExpirado: dataExpiracao="+dataExpiracao);
        return dataExpiracao.before(new Date());
    }
    
}
