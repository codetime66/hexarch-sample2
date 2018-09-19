package br.com.stelo.gsurf.token.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "token", "expiration" })
public class TokenGsurf {

	@JsonProperty("token")
	private String token;

	@JsonProperty("expiration")
	@JsonDeserialize(using = CustomDateDeserializer.class)
	private Date expiration;

	@JsonProperty("token")
	public String getToken() {
		return token;
	}

	@JsonProperty("token")
	public void setToken(String token) {
		this.token = token;
	}

	@JsonProperty("expiration")
	public Date getExpiration() {
		return expiration;
	}

	@JsonProperty("expiration")
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	@JsonIgnore
	public TokenModuloTerminal toTokenModuloTerminal() {
		TokenModuloTerminal tmt = new TokenModuloTerminal();
		tmt.setIdSolicitacaoToken(1L);
		tmt.setCodigoToken(getToken());
		tmt.setDataExpiracao(getExpiration());
		tmt.setDataSolicitacao(new Date());
		tmt.setStatusToken("R");
		return tmt; 
	}
	
	@Override
	public String toString() {
		return "TokenGsurf{" + "token='" + token + '\'' + ", expiration=" + getExpiration() + '}';
	}

}