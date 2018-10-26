package br.com.stelo.gsurf.token.configuration;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

	@Value("${stelo.admin.http-proxy.username}")
	private String proxyUsername;

	@Value("${stelo.admin.http-proxy.password}")
	private String proxyPassword;

	@Value("${stelo.admin.http-proxy.url}")
	private String proxyUrl;

	@Value("${stelo.admin.http-proxy.port}")
	private int proxyPort;

	@Bean
	public RestTemplate restTemplate() {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(new AuthScope(proxyUrl, proxyPort),
				new UsernamePasswordCredentials(proxyUsername, proxyPassword));
		HttpHost httpHost = new HttpHost(proxyUrl, proxyPort);
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		clientBuilder.setProxy(httpHost).setDefaultCredentialsProvider(credentialsProvider).disableCookieManagement();
		HttpClient httpClient = clientBuilder.build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setHttpClient(httpClient);

		return new RestTemplate(factory);
	}

}
