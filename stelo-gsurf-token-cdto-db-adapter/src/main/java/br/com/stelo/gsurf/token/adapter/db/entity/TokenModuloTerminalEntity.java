package br.com.stelo.gsurf.token.adapter.db.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_SOLTC_TOKEN_MDULO_TERM", schema = "USR_CADU")
public class TokenModuloTerminalEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Transient
	private static final Long PK_VALOR_UNICO = new Long(1);

	@Id
	@Column(name = "ID_SOLTC_TOKEN")
	private Long idSolicitacaoToken = PK_VALOR_UNICO;

	@Column(name = "CD_TOKEN")
	private String codigoToken;

	@Column(name = "DT_SOLTC")
	private Date dataSolicitacao;

	@Column(name = "DT_EXPIR")
	private Date dataExpiracao;
	
	@Column(name = "STTUS_TOKEN")
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

}