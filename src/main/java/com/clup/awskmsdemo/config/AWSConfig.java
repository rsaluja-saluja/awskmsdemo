package com.clup.awskmsdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;

@Configuration
@ComponentScan(value = "com.clup.awskmsdemo")
public class AWSConfig {
    private static final Logger logger = LoggerFactory.getLogger(AWSConfig.class);


    @Value("${user.access.key}")
    private String userKey;

    @Value("${user.secret.key}")
    private String secretKey;

    @Value("${kms.key.arn}")
    private String keyArn;

    @Value("${kms.key.id}")
    private String keyId;

    @Bean(name ="kmsClient")
    @Primary
    public KmsClient KMSExample() {
        logger.info("the property valuesuser key:{} secret key: {} key ARN : {} keyid: {}", userKey,secretKey,keyArn,keyId);
        KmsClient kmsClient;
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(userKey,secretKey);
        kmsClient = KmsClient.builder().credentialsProvider(StaticCredentialsProvider.create(awsCreds)).region(Region.US_EAST_1).build();
        return kmsClient;
    }

    public  String getKeyArn() {
        return keyArn;
    }

    public  void setKeyArn(String keyArn) {
        keyArn = keyArn;
    }

}
