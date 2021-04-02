# AWS KMS Operations Implementation using Java 
we will be learning about the following things in KMS and will be doing a sample implementation.
- Symmetric Encryption
- Customer Master key(CMK)
- Data key

## Symmetric Encryption
Symmetric encryption is a type of encryption where only one key (a secret key) is used to both encrypt and decrypt electronic information. The entities communicating via symmetric encryption must exchange the key so that it can be used in the decryption process. AES is one of the standard supports Symmetric Encryption. 

## Customer Master Key (CMK)
- A customer master key (CMK) is a logical representation of a master key. The CMK includes metadata, such as the key ID, creation date, description, and key state. The CMK also contains the key material used to encrypt and decrypt data.
- KMS Encrypt and Decrypt APIs will be using the Master key via API, and these Master keys will not be leaving the AWS.
- These keys are used to encrypt data within 4096 Bytes (4KB)
## Data Keys 
- Data keys are encryption keys that you can use to encrypt data, including large amounts of data and other data encryption keys.
- You can use AWS KMS customer master keys (CMKs) to generate, encrypt, and decrypt data keys. However, AWS KMS does not store, manage, or track your data keys, or perform cryptographic operations with data keys. You must use and manage data keys outside of AWS KMS.

## Major Operation we use via Applications
- kms:Encrypt
- kms:Decrypt
- kms:GenerateDataKey
- kms:GenerateDataKeyWithoutPlainText
- kms:DescribeKey

## Encrypt Operation
- Encrypts plaintext into ciphertext by using a customer master key (CMK). 
- You can encrypt small amounts of arbitrary data, such as a personal identifier or database password, or other sensitive information.
- This API can only encrypt a data of length 4KB.

## Decrypt Operation
- Decrypts ciphertext that was encrypted by a AWS KMS customer master key (CMK).
- You can use this operation to decrypt ciphertext that was encrypted under a symmetric or asymmetric CMK.

## GeneratedataKey Operation (With or Without Plain text Key)
- Generates a unique symmetric data key for client-side encryption. This operation returns a plaintext copy of the data key and a copy that is encrypted under a customer master key (CMK) that you specify. You can use the plaintext key to encrypt your data outside of AWS KMS and store the encrypted data key with the encrypted data.
- GenerateDataKey returns a unique data key for each request. 

![](https://docs.aws.amazon.com/kms/latest/developerguide/images/generate-data-key.png)
**Figure1** - Generating the Data Key

![](https://docs.aws.amazon.com/kms/latest/developerguide/images/encrypt-with-data-key.png)
**Figure2** - Encrypt Data with Data Key

![](https://docs.aws.amazon.com/kms/latest/developerguide/images/decrypt.png)
**Figure3** - Decrypting the Data Key

## Describe Key operation
- Provides detailed information about a customer master key (CMK). You can run DescribeKey on a customer managed CMK or an AWS managed CMK.
- This detailed information includes the key ARN, creation date (and deletion date, if applicable), the key state, and the origin and expiration date (if any) of the key material. For CMKs in custom key stores, it includes information about the custom key store, such as the key store ID and the AWS CloudHSM cluster ID.

## References
- AWS SDK for Java: https://docs.aws.amazon.com/sdk-for-java/index.html 
- Sdk v2 java examples: https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples.html 
- Java code sample catalog: https://docs.aws.amazon.com/code-samples/latest/catalog/welcome.html 
