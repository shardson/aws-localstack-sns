package com.aws.hellosns.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ContactPublisher {

    //private static final Logger LOG = LoggerFactory.getLogger(
      //      ContactPublisher.class);

    private AmazonSNS snsClient;
    private Topic contactEventsTopic;

    public ContactPublisher(AmazonSNS snsClient, Topic contactEventsTopic) {
       this.snsClient = snsClient;
       this.contactEventsTopic = contactEventsTopic;
    }

    public void publishContactEvent(String message) {
        PublishResult publishResult = snsClient.publish(
                contactEventsTopic.getTopicArn(),
                message);

        //LOG.info("MessageId: {}", publishResult.getMessageId());

    }

}
