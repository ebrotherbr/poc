package br.com.ebrother.poc.messages.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import br.com.ebrother.poc.messages.MessageByLocaleService;

@Component
public class MessageByLocaleServiceImpl implements MessageByLocaleService {

	@Autowired
	private MessageSource messageSource;

	@Override
	public String getMessage(final String id) {
		final Locale locale = LocaleContextHolder.getLocale();
		return this.messageSource.getMessage(id, null, locale);
	}

}
