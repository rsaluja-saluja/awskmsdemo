package com.clup.awskmsdemo;

import com.clup.awskmsdemo.service.KmsOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsClient;


@SpringBootApplication
public class AwskmsdemoApplication implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(AwskmsdemoApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(AwskmsdemoApplication.class, args);
	}

	@Autowired
	KmsOperations kmsOperations;

	@Override
	public void run(String... arg0) throws Exception {

		String jsonDataUnder4K = "{" +
				"   \"data\":[" +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }" +
				"   ]," +
				"   \"included\":[" +
				"      {" +
				"         \"type\":\"people\"," +
				"         \"id\":\"42\"," +
				"         \"attributes\":{" +
				"            \"name\":\"John\"," +
				"            \"age\":80," +
				"            \"gender\":\"male\"" +
				"         }" +
				"      }" +
				"   ]" +
				"}";

		String jsonDataAbove4k = "{" +
				"   \"data\":[" +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }," +
				"      {" +
				"         \"type\":\"articles\"," +
				"         \"id\":\"1\"," +
				"         \"attributes\":{" +
				"            \"title\":\"JSON:API paints my bikeshed!\"," +
				"            \"body\":\"The shortest article. Ever.\"," +
				"            \"created\":\"2015-05-22T14:56:29.000Z\"," +
				"            \"updated\":\"2015-05-22T14:56:28.000Z\"" +
				"         }," +
				"         \"relationships\":{" +
				"            \"author\":{" +
				"               \"data\":{" +
				"                  \"id\":\"42\"," +
				"                  \"type\":\"people\"" +
				"               }" +
				"            }" +
				"         }" +
				"      }" +
				"   ]," +
				"   \"included\":[" +
				"      {" +
				"         \"type\":\"people\"," +
				"         \"id\":\"42\"," +
				"         \"attributes\":{" +
				"            \"name\":\"John\"," +
				"            \"age\":80," +
				"            \"gender\":\"male\"" +
				"         }" +
				"      }" +
				"   ]" +
				"}";

		//describe key option
		kmsOperations.describeSpecifcKey();

		System.out.println("the json string is"+ jsonDataUnder4K);
		System.out.println("the size of the string " + jsonDataUnder4K.getBytes().length);

		System.out.println("the json string is"+ jsonDataAbove4k);
		System.out.println("the size of the string " + jsonDataAbove4k.getBytes().length);

		//the following will fail as we are using the encrypt function with data more than 4KB
		//kmsOperations.encrypt(SdkBytes.fromUtf8String(jsonDataAbove4k));

		//calling the KMS APIs for encryption and decryption
		SdkBytes sdkBytes =  kmsOperations.encrypt(SdkBytes.fromUtf8String(jsonDataUnder4K));
		logger.info("the response with the encrypted ciphertext as sdkbytes data: {} ",sdkBytes);

		sdkBytes = kmsOperations.deCrypt(sdkBytes);
		logger.info("the response with the decrypted plaintext: {} ",sdkBytes.asUtf8String());


		//calling the KMS operation using the data key for the data more than 4 KB
		kmsOperations.encryptUsingDataKey(SdkBytes.fromUtf8String(jsonDataAbove4k));
		kmsOperations.decryptUsingDataKey();



	}

}
