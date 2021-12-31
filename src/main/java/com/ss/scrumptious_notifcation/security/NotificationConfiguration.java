package com.ss.scrumptious_notifcation.security;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class NotificationConfiguration {

	// The IAM access key.
    @Value("${AWS_SECRET_ACCESS_KEY}")
    private String secretKey;

 // The IAM secret key.
    @Value("${AWS_ACCESS_KEY_ID}")
    private String accessKey;

 // The sns region name.
    @Value("amazon.sns.region")
    private String SNSRegionName;

    // The ses region name.
    @Value("${amazon.ses.region}")
    private String SESRegionName;

    @Bean
    public AmazonSNS getAmazonSNSClient() {
    	//LOG.info("Access: " + accessKey + " Secret: " + secretKey);
        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
        // Get Amazon S3 client and return the S3 client object
        return AmazonSNSClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(SNSRegionName)
                .build();
    }

    @Bean
    public AmazonSimpleEmailService getAmazonSESClient() {
        final AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(SESRegionName).build();
    }
}
