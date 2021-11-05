package com.ss.scrumptious_notifcation.security;

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
@PropertySource("classpath:aws-config.properties")
public class NotificationConfiguration {

	// The IAM access key.
    @Value("${amazon.sns.access.key}")
    private String accessKey; 
    
 // The IAM secret key.
    @Value("${amazon.sns.secret.key}")
    private String secretKey;
    
 // The bucket region name.
    @Value("${amazon.sns.region.name}")
    private String s3RegionName;
    
    @Bean
    public AmazonSNS getAmazonSNSClient() {
    	//LOG.info("Access: " + accessKey + " Secret: " + secretKey);
        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
        // Get Amazon S3 client and return the S3 client object
        return AmazonSNSClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(s3RegionName)
                .build();
    }
}
