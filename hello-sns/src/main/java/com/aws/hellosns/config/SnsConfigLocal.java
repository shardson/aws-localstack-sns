package com.aws.hellosns.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("local")
public class SnsConfigLocal {

    private static final Logger LOG = LoggerFactory.getLogger(SnsConfigLocal.class);
    private final String contactEventsTopic;
    private final AmazonSNS snsClient;

    public SnsConfigLocal(){
        this.snsClient = AmazonSNSClient.builder()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", Regions.SA_EAST_1.getName()))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        CreateTopicRequest createTopicRequest = new CreateTopicRequest("contact-events");

        this.contactEventsTopic = this.snsClient.createTopic(createTopicRequest).getTopicArn();

        LOG.info("SNS Topic ARN: {}", this.contactEventsTopic);
    }

    @Bean
    public AmazonSNS snsClient(){
        return this.snsClient;
    }

    @Bean(name = "contactEventsType")
    public Topic snsContactEventsTopic(){
        return new Topic().withTopicArn(contactEventsTopic);
    }

}
