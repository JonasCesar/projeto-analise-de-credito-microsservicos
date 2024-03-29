package com.cesar.propostaapp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
	
	@Value("${rabbitmq.propostapendente.exchange}")
	private String exchangePropostaPendente;
	
	@Value("${rabbitmq.propostaconcluida.exchange}")
	private String exchangePropostaConcuida;
	
	private ConnectionFactory connectionFactory; //interface do pacote amqp.rabbit que já é gerenciada pelo spring
	
	public RabbitMQConfiguration(ConnectionFactory connectionFactory) { // o próprio spring implementa e instancia o connectionFactiory e injeta no construtor
		this.connectionFactory = connectionFactory;
	}
	
	@Bean
	public RabbitAdmin criarRabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}
	
	@Bean
	public ApplicationListener<ApplicationReadyEvent> inicializarAdmin(RabbitAdmin rabbitAdmin){
		return event -> rabbitAdmin.initialize();
	}
	
	@Bean
	public Queue criarFilaPropostaPendenteMsAnaliseCredito(){
		return QueueBuilder.durable("proposta-pendente.ms-analise-credito").build(); // fila que será consumida pelo microsserviço ms-analise-credito
	}
	
	@Bean
	public Queue criarFilaPropostaPendenteMsNotificacao(){
		return QueueBuilder.durable("proposta-pendente.ms-notificacao").build(); // fila que será consumida pelo microsserviço ms-notificacao
	}
	
	@Bean
	public Queue criarFilaPropostaConcluidaMsProposta(){
		return QueueBuilder.durable("proposta-concluida.ms-proposta").build(); // fila que será consumida pelo microsserviço ms-proposta (esse do código atual)
	}
	
	@Bean
	public Queue criarFilaPropostaConcluidaMsNotificacao(){
		return QueueBuilder.durable("proposta-concluida.ms-notificacao").build(); // fila que será consumida pelo microsserviço ms-notificacao
	}
	
	@Bean
	public FanoutExchange criarFanoutExchangePropostaPendente() { //criação da exchange
		return ExchangeBuilder.fanoutExchange(exchangePropostaPendente).build();
	}
	
	@Bean
	public FanoutExchange criarFanoutExchangePropostaConcluida() { //criação da exchange
		return ExchangeBuilder.fanoutExchange(exchangePropostaConcuida).build();
	}
	
	@Bean
	public Binding criarBindingPropostaPendenteMSAnaliseCredito() { // linha que liga a exchange proposta-pendente.ex à fila proposta-pendente.ms-analise-credito
		return BindingBuilder.bind(criarFilaPropostaPendenteMsAnaliseCredito()).to(criarFanoutExchangePropostaPendente());
	}
	
	@Bean
	public Binding criarBindingPropostaPendenteMSNotificacao() { // linha que liga a exchange proposta-pendente.ex à fila proposta-pendente.ms-notificacao
		return BindingBuilder.bind(criarFilaPropostaPendenteMsNotificacao()).to(criarFanoutExchangePropostaPendente());
	}
	
	@Bean
	public Binding criarBindingPropostaConcluidaMSPropostaApp() { // linha que liga a exchange proposta-concluida.ex à fila proposta-concluida.ms-proposta
		return BindingBuilder.bind(criarFilaPropostaConcluidaMsProposta()).to(criarFanoutExchangePropostaConcluida());
	}
	
	public Binding criarBindingPropostaConcluidaMSNotificacao() { // linha que liga a exchange proposta-concluida.ex à fila proposta-concluida.ms-notificacao
		return BindingBuilder.bind(criarFilaPropostaConcluidaMsNotificacao()).to(criarFanoutExchangePropostaConcluida());
	}
	
	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbtiTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory);
		rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
		
		return rabbitTemplate;
		
	}
	

}
