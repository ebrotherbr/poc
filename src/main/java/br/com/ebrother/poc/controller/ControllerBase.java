package br.com.ebrother.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ebrother.poc.messages.MessageByLocaleService;

public class ControllerBase {

	@Autowired
	protected MessageByLocaleService mensagens;

}
