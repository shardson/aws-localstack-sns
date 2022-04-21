package com.aws.hellosns.controller;

import com.aws.hellosns.service.ContactPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {

    private ContactPublisher contactPublisher;
    private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);

    ContactController(ContactPublisher contactPublisher){
        this.contactPublisher = contactPublisher;
    }

    //Rota para testar se o projeto tá rodando
    @GetMapping("/TesteRota")
    public ResponseEntity<?> testeRota() {

        LOG.info("Teste Rota Controler");



        try {
            contactPublisher.publishContactEvent("Mensagem Publicada no Teste Rota");
            LOG.info("Mensagem Após Publicação do SNS com Sucesso!!!");
        } catch (Exception e) {
            LOG.error("Falha ao publicar a mensagem no SNS!!! Erro: " + e);
        }

        return ResponseEntity.ok("TesteRotaOk");

    }

}
