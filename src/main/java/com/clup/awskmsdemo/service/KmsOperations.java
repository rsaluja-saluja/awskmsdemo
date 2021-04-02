package com.clup.awskmsdemo.service;

import com.clup.awskmsdemo.config.AWSConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

@Component
public class KmsOperations {

    private static final Logger logger = LoggerFactory.getLogger(KmsOperations.class);

    @Autowired
    KmsClient kmsClient;

    @Autowired
    AWSConfig awsConfig;



    public SdkBytes encrypt(SdkBytes jsonString) {

       // logger.info("the key arn value is : {}", awsConfig.getKeyArn());
        EncryptRequest encryptRequest = EncryptRequest.builder().keyId(awsConfig.getKeyArn()).plaintext(jsonString).build();
        if (kmsClient!= null) {
            EncryptResponse encryptResponse = kmsClient.encrypt(encryptRequest);
            return encryptResponse.ciphertextBlob();

        }else
            logger.info("the kms client is null ");
        return null;
    }

    public SdkBytes deCrypt(SdkBytes encryptedJsonString) {
        DecryptRequest decryptRequest = DecryptRequest.builder().ciphertextBlob(encryptedJsonString).build();
        DecryptResponse decryptResponse = kmsClient.decrypt(decryptRequest);
        return decryptResponse.plaintext();

    }

    public void encryptUsingDataKey(SdkBytes jsonString) {
        try {


            //#1 generating a Data key by calling the KMS API - this will create with the plain text and the encrypted data key
            GenerateDataKeyRequest generateDataKeyRequest = GenerateDataKeyRequest.builder().keyId(awsConfig.getKeyArn()).keySpec(DataKeySpec.AES_128).build();
            GenerateDataKeyResponse generateDataKeyResponse = kmsClient.generateDataKey(generateDataKeyRequest);

            //#2 reading the plain text data key to proceed with file encryption
            SecretKeySpec key = new SecretKeySpec(generateDataKeyResponse.plaintext().asByteArray(),"AES");
            Cipher cipher;
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            //#3 getting the encoded content
            byte[] encodedSecret = cipher.doFinal(jsonString.asByteArray());


            //#4 writing the encoded content to a file
            String path = Paths.get(".").toAbsolutePath().normalize().toString()+ "/Encoded_content.json";
            KmsOperations.writeToFile(SdkBytes.fromByteArray(encodedSecret),path);


            //#5 writing the encoded data key into a file for future references.
            path = Paths.get(".").toAbsolutePath().normalize().toString() + "/encrypted_data_key.txt";
            KmsOperations.writeToFile(generateDataKeyResponse.ciphertextBlob(), path);

            logger.info("the json data has been encoded and written into Encoded_content.json and the encrypted data key has been written in encrypted_data_key.txt");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void decryptUsingDataKey() {
        try {

            //#1 reading the cipher text blob content
            String path = Paths.get(".").toAbsolutePath().normalize().toString() + "/encrypted_data_key.txt";
            SdkBytes sdkBytes = KmsOperations.readFromFile(path);

            //#2 decrypting the encrypted data key using the kms client
            DecryptRequest decryptRequest = DecryptRequest.builder().ciphertextBlob(sdkBytes).build();
            DecryptResponse decryptResponse = kmsClient.decrypt(decryptRequest);

            //#3 using the decrypted plain text to decode the file content
            SecretKeySpec secretKeySpec = new SecretKeySpec(decryptResponse.plaintext().asByteArray(), "AES");
            path = Paths.get(".").toAbsolutePath().normalize().toString() + "/Encoded_content.json";
            sdkBytes = KmsOperations.readFromFile(path);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            //#4 printing the decrypted content
           logger.info("The decrypted content with the plaintext data key {}",SdkBytes.fromByteArray(cipher.doFinal(sdkBytes.asByteArray())).asUtf8String());

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void describeSpecifcKey(){


        try {
            DescribeKeyRequest keyRequest = DescribeKeyRequest.builder()
                    .keyId(awsConfig.getKeyArn())
                    .build();

            DescribeKeyResponse response = kmsClient.describeKey(keyRequest);
            logger.info("The key description is "+response.keyMetadata().description());
            logger.info("The key ARN is "+response.keyMetadata().arn());
            logger.info("the key AWS account id: "+ response.keyMetadata().awsAccountId());

        } catch (KmsException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void writeToFile(SdkBytes bytesToWrite,String path) throws IOException {
        FileChannel fc;
        FileOutputStream outputStream = new FileOutputStream(path);
        fc = outputStream.getChannel();
        fc.write(bytesToWrite.asByteBuffer());
        outputStream.close();
        fc.close();
    }

    public static SdkBytes readFromFile(String path) throws IOException {
        InputStream in2 = new FileInputStream(path);
        return SdkBytes.fromInputStream(in2);
    }



}
