package br.com.ebrother.poc.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ebrother.poc.messages.MessageByLocaleService;

public class ServiceBase {

	@Autowired
	protected MessageByLocaleService mensagens;

}
