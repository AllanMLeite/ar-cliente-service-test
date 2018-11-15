package com.ar.clienteservicetest.cliente;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class ClienteServiceTest {

	@Test
	public void deveRetornarMensagemQuandoSituacaoDoCpfForIrregular() throws Exception {

		String ENDPOINT_INCLUIR_CLIENTE = "http://localhost:8080/cliente";

		String body = "{\"cpf\":\"01234567890\"}";

		RequestEntity<String> request = RequestEntity.post(new URI(ENDPOINT_INCLUIR_CLIENTE))
				.contentType(MediaType.APPLICATION_JSON).body(body);

		try {
			new RestTemplate().exchange(request, String.class);
			Assert.fail();
		} catch (HttpClientErrorException exception) {
			assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getRawStatusCode());
			assertEquals("{\"message\":\"CPF com situacao irregular.\"}", exception.getResponseBodyAsString());
		}
	}

}
