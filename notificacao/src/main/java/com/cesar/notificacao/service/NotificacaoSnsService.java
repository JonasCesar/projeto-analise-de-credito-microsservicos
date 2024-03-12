package com.cesar.notificacao.service;

import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;

@Service
public class NotificacaoSnsService {

	private AmazonSNS amazonSNS;
	
	public NotificacaoSnsService(AmazonSNS amazonSNS) {
		this.amazonSNS = amazonSNS;
	}
	
	public void notificar(String telefone, String mensagem) {
		PublishRequest publishRequest = new PublishRequest().withMessage(mensagem).withPhoneNumber(telefone);
		amazonSNS.publish(publishRequest);
	}

}
