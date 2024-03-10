package com.cesar.notificacao.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.cesar.notificacao.constante.MensagemConstante;
import com.cesar.notificacao.domain.Proposta;
import com.cesar.notificacao.service.NotificacaoSnsService;

@Component
public class PropostaPendenteListener {
	
	private NotificacaoSnsService notificacaoSnsService;
		
	public PropostaPendenteListener(NotificacaoSnsService notificacaoSnsService) {
		this.notificacaoSnsService = notificacaoSnsService;
	}

	@RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
	public void propostaPendente(Proposta proposta) {
		String mensagem = String.format(MensagemConstante.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
		notificacaoSnsService.notificar(mensagem);
	}

}
