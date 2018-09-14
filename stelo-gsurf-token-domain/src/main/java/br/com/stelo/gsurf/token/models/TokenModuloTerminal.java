package br.com.stelo.gsurf.token.models;

import java.util.Date;

public class TokenModuloTerminal {

	private Long idSolicitacaoToken;
	private String codigoToken;
	private Date dataSolicitacao;
	private Date dataExpiracao;
	private String statusToken;

	public Long getIdSolicitacaoToken() {
		return idSolicitacaoToken;
	}

	public void setIdSolicitacaoToken(final Long idSolicitacaoToken) {
		this.idSolicitacaoToken = idSolicitacaoToken;
	}

	public String getCodigoToken() {
		return codigoToken;
	}

	public void setCodigoToken(final String codigoToken) {
		this.codigoToken = codigoToken;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(final Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(final Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public String getStatusToken() {
		return statusToken;
	}

	public void setStatusToken(String statusToken) {
		this.statusToken = statusToken;
	}
	
	@Override
	public String toString() {
		return "TokenModuloTerminal {" + "token='" + codigoToken + '\'' + ", solicitacao=" + dataSolicitacao + '\'' + ", expiration=" + dataExpiracao + '}';
	}
	
}
